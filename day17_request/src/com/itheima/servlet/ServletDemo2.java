package com.itheima.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo2
 */
public class ServletDemo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求方式
		String method = request.getMethod();
		//判断请求方式
		if("post".equals(method)){
			//如果是post的请求方式，处理乱码问题
			request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("username");
			System.out.println(username);
		}else {
			//如果是get的请求方式，处理乱码问题
			String username = request.getParameter("username");
			username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(username);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
