package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountServlet
 */
public class CountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查看登录成功人数
		//获取ServletContext对象
		ServletContext sc = getServletContext();
		//从ServletContext域对象中获取登录次数
		int count = (int) sc.getAttribute("count");
		//将登录次数响应给客户端
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write("登录的总次数为:"+count+"次");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
