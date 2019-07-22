package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
	
		//获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String remember = request.getParameter("remember");
		//获取Session对象
		HttpSession session = request.getSession();
		//从Session对象中获取"code"的value
		String code = (String)session.getAttribute("code");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		//用从Session中获取的code去和客户端传过来的vcode比对
		if(code.equalsIgnoreCase(vcode)){
			//如果能匹配上，则表示验证码正确
			//调用业务逻辑，进行判断登录
			UserService us = new UserServiceImpl();
			User user = us.doLogin(username, password);
			if(user != null){
				//表示登录成功
				//获取是否记住用户名和密码框的值，进行是否记住密码和用户名操作
				//创建cookie
				Cookie cookie1 = new Cookie("username", username);
				Cookie cookie2 = new Cookie("password", password);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format = sdf.format(date);
				String time = URLEncoder.encode("你上一次登录时间为"+format, "utf-8");
				Cookie cookie3 = new Cookie("time", time);
				//设置cookie的有效路径
				cookie1.setPath(request.getContextPath());
				cookie2.setPath(request.getContextPath());
				cookie3.setPath(request.getContextPath());
				cookie3.setMaxAge(3*60*60);
				if("rem".equals(remember)){
					//设置cookie的有效期
					cookie1.setMaxAge(3*60*60);
					cookie2.setMaxAge(3*60*60);
				}else {
					//设置cookie的有效期
					cookie1.setMaxAge(0);
					cookie2.setMaxAge(0);
				}
				response.addCookie(cookie1);
				response.addCookie(cookie2);
				response.addCookie(cookie3);
				//将对象user存储到域对象request中
				session.setAttribute("user", user);
				//重定向转发到首页
				writer.write("登录成功！");
				response.setHeader("refresh", "1;url="+request.getContextPath()+"/home.jsp");
			}else {
				//表示登录失败
				writer.write("登录失败,3秒后跳转到首页");
				//重定向到login.jsp页面
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
			}
		}else {
			//表示验证码错误
			session.setAttribute("msg", "验证码错误！");
			//请求转发到login.jsp页面
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
