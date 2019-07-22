package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Users;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用业务逻辑
		UserService us = new UserServiceImpl();
		Users user = us.doLogin(username,password);
		//判断是否登录成功
		if(user != null){
			//定义计数器变量count,并将其初始化为0
			int count = 0;
			//获取ServletContext对象---->因为统计登录人数的计数器要存放到该对象中
			ServletContext sc = getServletContext();
			//从该对象中获取该计数器，如果第一次登录则该计数器为null
			Object obj = sc.getAttribute("count");
			/*//如果该计数器为null
			 * if(obj == null){
				count++;//则count++
				//将"count"和自增后的count一起存入ServletContext中
				sc.setAttribute("count", count);
			}else {//如果该计数器不为null(则证明之前有人登录成功过)
				//那么用count接收ServletContext获取的值
				count = (int) obj;
				//然后count自增
				count++;
				//将"count"和自增后的count一起存入ServletContext中
				sc.setAttribute("count", count);
			}*/
			if(obj != null){
				count = (int) obj;
			}
			count++;
			sc.setAttribute("count", count);
			
			//跳转到success.html页面(分发转向)
			//使用重定向跳转，重定向的响应码为302
			response.setStatus(302);	
			response.setHeader("Location", "success.html");
			
		}else {
			//登录失败，直接响应给客户端"登录失败"
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("登录失败！");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
