package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Record;
import com.itheima.domain.User;
import com.itheima.service.RecordService;
import com.itheima.service.impl.RecordServiceImpl;

/**
 * Servlet implementation class RecordServlet
 */
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		User user = (User) request.getSession().getAttribute("user");
		RecordService rs = new RecordServiceImpl();
		List<Record> list = rs.findAll(user.getId());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(list.size()!=0){
			request.getSession().setAttribute("list", list);
			response.sendRedirect("record.jsp");
		}else{
			writer.write("信息错误，无法查询！");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/list.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
