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
		request.setCharacterEncoding("utf-8");
		//获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用业务逻辑
		UserService us = new UserServiceImpl();
		User user = us.doLogin(username, password);
		//对登录结果进行判断
		if(user != null){
			//登录成功，则进入首页,首页并显示欢迎Xxx
			//将user对象存入request域中
			request.setAttribute("user", user);
			//请求转发
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			//登录失败，给予提示用户名或密码错误
			//将用户名或密码错误存入request域中
			request.setAttribute("msg", "用户名或密码错误,请重新登录！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
