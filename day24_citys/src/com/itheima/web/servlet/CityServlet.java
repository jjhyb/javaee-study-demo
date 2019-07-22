package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.CityService;
import com.itheima.service.impl.CityServiceImpl;

/**
 * Servlet implementation class CityServlet
 */
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单数据
		String method = request.getParameter("method");
		System.out.println(method);
		//对get提交进行乱码处理
		method = new String(method.getBytes("iso-8859-1"),"UTF-8");
		//获取本类字节码文件
		Class<? extends CityServlet> clazz = this.getClass();
		try {
			//根据客户端传参获取相对应的方法对象
			Method md = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//执行方法
			md.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 获取省的所有数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接调用业务层
		CityService cs = new CityServiceImpl();
		String json = cs.searchPro();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	/**
	 * 根据客户端传过来的pid获取城市信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchCity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取由ajax异步传过来的pid
		String pid = request.getParameter("pid");
		//调用业务逻辑
		CityService cs = new CityServiceImpl();
		String json = cs.searchCity(pid);
		response.setContentType("text/html;charset=UTF-8");
		//将json字符串写回客户端
		response.getWriter().write(json);
	}

}
