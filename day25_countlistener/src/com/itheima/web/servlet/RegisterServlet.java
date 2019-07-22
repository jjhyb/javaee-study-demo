package com.itheima.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
			//解决中文乱码问题
			request.setCharacterEncoding("utf-8");
			//创建javaBean对象
			User user = new User();
			//注册时间转换器
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			//使用BeanUtils将数据封装到javaBean中
			BeanUtils.populate(user, request.getParameterMap());
			//另外设置id和复选框的值
			String[] hobbys = request.getParameterValues("hobby");
			//创建StringBuilder用于字符串的拼接
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < hobbys.length; i++) {
				if(i==hobbys.length -1){
					sb.append(hobbys[i]);
					break;
				}
				sb.append(hobbys[i]).append("#");
			}
			user.setHobby(sb.toString());
			//用UUID生成id
			user.setId(UUID.randomUUID().toString());
			
			//调用业务逻辑
			UserService us = new UserServiceImpl();
			boolean flag = us.register(user);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			if(flag){
				//注册成功
				writer.write("注册成功,3秒后跳转至登录页面！");
				response.setHeader("refresh", "3;url=/day18_cookiesession/login.jsp");
			}else {
				//注册失败
				writer.write("注册失败，3秒后跳回注册页面，请重新注册");
				response.setHeader("refresh", "3;url=/day18_cookiesession/register.html");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
