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
 * Servlet implementation class WithdrawServlet
 */
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		//获取表单数据
		double money = Double.parseDouble(request.getParameter("money"));
		int type = Integer.parseInt(request.getParameter("type"));
		//从session中获取取款用户信息
		User user = (User) request.getSession().getAttribute("user");
		
		//调用业务逻辑
		UserService us = new UserServiceImpl();
		boolean flag = us.withdraw(user.getUsername(), money, type);
		
		//重新查询一下User,确保数据的准确性
		user = us.findUserByName(user.getUsername());
		request.getSession().setAttribute("user", user);
				
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(flag){
			writer.write("取款成功，3秒后跳转至首页");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/list.jsp");
		}else{
			writer.write("取款失败，余额有可能不足!");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/list.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
