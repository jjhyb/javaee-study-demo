package com.itheima.request;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet01
 */
public class RequestServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String header = request.getHeader("user-agent");
		System.out.println(header);
		
		/*
		 * 获取客户端提交的全部参数名
		 * Enumeration<String> pn = request.getParameterNames();
		while(pn.hasMoreElements()){
			System.out.println("你好");
			String value = pn.nextElement();
			System.out.println(value);
		}*/
		
		//获取全部的请求头的name
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String headername = headerNames.nextElement();
			System.out.println(headername);
			//根据请求头name获取请求头的value
			System.out.println(request.getHeader(headername));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
