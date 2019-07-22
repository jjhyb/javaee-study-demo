package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置服务器请求解析的时候使用的编码方式为UTF-8,只能用于post提交方式
			request.setCharacterEncoding("UTF-8");
			//创建javaBean对象
			User user = new User();
			//注册时间类型转换器
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			//将map中的键值对直接封装到javaBean中
			BeanUtils.populate(user, request.getParameterMap());
			//复选框和id要手动设置
			String[] hobbys = request.getParameterValues("hobby");
			//使用StringBuilder进行复选框的字符串拼接
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < hobbys.length; i++) {
				if(i == hobbys.length-1){
					sb.append(hobbys[i]);
					break;
				}
				sb.append(hobbys[i]).append("#");
			}
			user.setHobby(sb.toString());
			//使用UUID自动生成id字符串
			String id = UUID.randomUUID().toString();
			user.setId(id);
			//调用业务逻辑
			UserService us = new UserServiceImpl();
			boolean flag = us.register(user);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			//对注册的返回结果进行判断
			if(flag){
				//如果注册成功,跳转到登录页面
//				response.sendRedirect("login.jsp");
				writer.write("注册成功，3秒后跳转到登录页面");
				response.setHeader("refresh", "3;url=/day17_request/login.jsp");
			}else {
				//如果注册失败,提示
				writer.write("注册失败,请重新注册");
//				response.sendRedirect("register.html");
				response.setHeader("refresh", "3;url=/day17_request/register.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
