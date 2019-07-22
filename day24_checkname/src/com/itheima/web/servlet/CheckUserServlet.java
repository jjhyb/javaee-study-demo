package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class CheckUserServlet
 */
public class CheckUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println(username);
		UserService us = new UserServiceImpl();
		User user = us.checkUser(username);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(user != null){
			writer.write("此用户名已存在！");
		}else {
			writer.write("用户名可用！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
