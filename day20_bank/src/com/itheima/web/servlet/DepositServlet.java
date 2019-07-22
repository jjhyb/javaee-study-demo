package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class DepositServlet
 */
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		double money =Double.parseDouble(request.getParameter("money"));
		int type = Integer.parseInt(request.getParameter("type"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
		
		UserService us = new UserServiceImpl();
		boolean flag = us.deposit(username,money,type);
		
		//重新查询一下User,确保数据的准确性
		user = us.findUserByName(username);
		request.getSession().setAttribute("user", user);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(flag){
			writer.write("存钱成功，3秒后跳转至首页");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/list.jsp");
		}else {
			writer.write("存钱失败，3秒后跳转至首页");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/list.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
