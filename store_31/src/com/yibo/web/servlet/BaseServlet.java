package com.yibo.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yibo.constant.Constant;

public class BaseServlet extends HttpServlet{
	//定义一个boolean类型的成员变量，用true和false控制请求转发和重定向
	public boolean result = false;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端发过来的请求参数
		String methodstr = request.getParameter("method");
		//获取调用对象的字节码文件对象
		Class<? extends BaseServlet> clazz = this.getClass();
		try {
			//用反射，通过字节码文件对象获取方法对象
			Method method = clazz.getDeclaredMethod(methodstr, HttpServletRequest.class,HttpServletResponse.class);
			//暴力反射
			method.setAccessible(true);
			//调用方法,并得到路径的返回值path
			String path = (String) method.invoke(this, request,response);
			//判断path,如果path不为null
			if(path != null){
				//根据成员变量result判断，进行具体的分发转向
				if(result){
					//如果result为true,那么执行重定向
					response.sendRedirect(path);
				}else {
					//如果为false,执行请求转发
					request.getRequestDispatcher(path).forward(request, response);;
				}
			}else {
				if(Constant.AJAX){
					return;
				}
				//如果path为null,那么统一跳转到msg页面
				request.getSession().setAttribute("msg", "服务器繁忙，请稍后重试");
				response.sendRedirect(request.getContextPath()+"/jsp/msg.jsp");
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
