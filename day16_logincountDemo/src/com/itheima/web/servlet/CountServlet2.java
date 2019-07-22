package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountServlet2
 */
public class CountServlet2 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取ServletContext对象
		ServletContext sc = getServletContext();
		//获取登录成功的总人数
		int count = (int) sc.getAttribute("count");
		//将登录总人数响应给客户端
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("登录成功的总人数为:"+count+"人");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
