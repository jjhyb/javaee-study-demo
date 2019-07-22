package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		//获取登录表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用业务逻辑
		UserService us = new UserServiceImpl();
		User user = us.doLogin(username,password);
		System.out.println(user);
		//判断是否登录成功
		if(user != null){
			//登录成功，则将返回的user对象存入request域中
			request.setAttribute("user", user);
			//请求转发到首页
			request.getRequestDispatcher("home.jsp").forward(request, response);;
		}else {
			//如果登录失败，给予提示
			request.setAttribute("msg", "用户名或密码错误!");
			//同样也请求转发到登录页面
			request.getRequestDispatcher("login.jsp").forward(request, response);;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
