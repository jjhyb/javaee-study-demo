package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Users;
import com.itheima.service.UserService2;
import com.itheima.service.impl.UserServiceImpl2;

/**
 * Servlet implementation class LoginServlet2
 */
public class LoginServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//调用业务逻辑
		UserService2 us2 = new UserServiceImpl2();
		Users user = us2.doLogin(username,password);
		//判断是否登录成功
		if(user != null){
			//定义计数器变量count,并初始化为0
			int count = 0;
			//获取ServletContext对象
			ServletContext sc = getServletContext();
			//从ServletContext对象中获取key为"count"的value
			Object obj = sc.getAttribute("count");
			if(obj != null){//如果"count"的值不为null
				//则直接把值赋值给计数器count
				count = (int) obj;
			}
			//计数器自增
			count++;
			//将"count"和count存入到ServletContext域对象中
			sc.setAttribute("count", count);
			
			//重定向,设置响应码为302响应给客户端
			response.setStatus(302);
			response.setHeader("Location", "success.html");
		}else {
			//如果登录失败则直接反馈给客户端"登录失败"
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("登录失败，请重新登录！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
