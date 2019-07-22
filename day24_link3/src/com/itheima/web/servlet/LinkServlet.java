package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.LinkService;
import com.itheima.service.LinkServiceImpl;

/**
 * Servlet implementation class LinkServlet
 */
public class LinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取获取客户端参数
		String md = request.getParameter("method");
		//获取本类字节码对象
		Class<? extends LinkServlet> clazz = this.getClass();
		try {
			//通过反射拿到方法名相对应的方法对象
			Method method = clazz.getMethod(md, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void searchPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//调用业务逻辑
		LinkService ls = new LinkServiceImpl();
		String json = ls.searchPro();
		//解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	public void searchCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户度参数
		String pid = request.getParameter("pid");
		//调用业务逻辑
		LinkService ls = new LinkServiceImpl();
		String json = ls.searchCity(pid);
		//解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	public void searchDis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户度参数
		String cid = request.getParameter("cid");
		//调用业务逻辑
		LinkService ls = new LinkServiceImpl();
		String json = ls.searchDis(cid);
		//解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户度参数
		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		//调用业务逻辑
		LinkService ls = new LinkServiceImpl();
		String json = ls.search(name);
		//解决中文乱码
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}

}
