package com.yibo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yibo.service.CategoryService;
import com.yibo.service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务层方法查询category表中的数据
		System.out.println("你好");
		CategoryService cs = new CategoryServiceImpl();
		String json = cs.findAll();
		//设置response的数据回写乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		return null;
	}

}
