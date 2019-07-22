package com.itheima.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CountServlet
 */
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
		System.out.println(msg);
		HttpSession session = request.getSession();
		String sessionid = session.getId();
		ServletContext sc = request.getServletContext();
		Map<String,Long> map = (Map<String, Long>) sc.getAttribute("map");
		map.put(sessionid, new Date().getTime());
		if(session.getAttribute("user") != null){
			
		}else{
			response.getWriter().write("[]");;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
