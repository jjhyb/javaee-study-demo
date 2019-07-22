package com.itheima.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo1
 */
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端的ip地址
		String ip = request.getLocalAddr();
		System.out.println(ip);
		//获取客户端的平台信息和浏览器信息
		String header = request.getHeader("user-Agent");
		System.out.println(header);
		//获取客户端的数据提交方式
		String method = request.getMethod();
		System.out.println(method);
		//获取项目的根路径
		String contextPath = request.getContextPath();
		System.out.println(contextPath);
		//获取项目的URL地址
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
