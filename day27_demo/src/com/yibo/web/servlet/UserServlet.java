package com.yibo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.yibo.domain.User;
import com.yibo.service.UserService;
import com.yibo.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String md = request.getParameter("method");
		Class<? extends UserServlet> clazz = this.getClass();
		try {
			Method method = clazz.getMethod(md, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			//使用BeanUtils封装数据
			BeanUtils.populate(user, request.getParameterMap());
			//调用业务层方法
			UserService us = new UserServiceImpl();
			Boolean flag = us.regist(user);
			//对返回结果进行判断
			if(flag){
				//如果为真，则表注册成功;
				response.sendRedirect(request.getContextPath()+"/success.jsp");
			}else {
				//否则注册失败，直接跳回注册页面让其重新注册
				response.sendRedirect(request.getContextPath()+"/regist.jsp");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println(username);
		//调用业务层方法查找
		UserService us = new UserServiceImpl();
		User user = us.search(username);
		System.out.println(user);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(user != null){
			writer.write("该用户名已被占用");
			System.out.println("1");
		}else {
			writer.write("该用户名可以使用");
			System.out.println("2");
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用业务层方法查找
		UserService us = new UserServiceImpl();
		User user = us.login(username,password);
		System.out.println(user);
		if(user != null){
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else {
			request.setAttribute("msg", "用户名和密码不匹配");
			request.getRequestDispatcher("/login.jsp").forward(request, response);;
		}
	}
}
