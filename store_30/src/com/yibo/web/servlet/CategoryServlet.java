package com.yibo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yibo.service.CategoryService;
import com.yibo.service.impl.CategoryServiceImpl;

/**
 * 用异步请求的方式，将获得的商品分类信息，通过json格式写回客户端
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务层方法查询category表中的数据
		CategoryService cs = new CategoryServiceImpl();
		String json = cs.findAll();
		//设置response的数据回写乱码问题
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
		return null;
	}

}
