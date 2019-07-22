package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService us = new UserServiceImpl();
		User user = us.login(username, password);
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		if(user != null){
			session.setAttribute("user", user);
//			request.getRequestDispatcher("list.jsp").forward(request, response);
//			response.setHeader("refresh", "3;url="+request.getContextPath()+"/list.jsp");
			response.sendRedirect(request.getContextPath()+"/list.jsp");
		}else {
			session.setAttribute("msg", "用户名或密码错误");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
