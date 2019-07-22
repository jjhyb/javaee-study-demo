package com.yibo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yibo.mysession.MySessionContext;

public class AcountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取到传过来的sessionid并打印
		String jsessionid=(String) request.getParameter("jsessionid");
		System.out.println(jsessionid);
		
		//从HashMap集合中获取session并打印
		HttpSession jsession = MySessionContext.getSession(jsessionid);
		System.out.println(jsession.getId());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
