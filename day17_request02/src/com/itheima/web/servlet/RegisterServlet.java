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
			request.setCharacterEncoding("UTF-8");
			//创建javaBean对象，用于封装获取到的表单数据
			User user = new User();
			//注册时间类型转换器,用于字符串对javaBean中的Date的格式转换
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			//使用BeanUtils进行快速的封装到javaBean中
			BeanUtils.populate(user, request.getParameterMap());
			
			//手动设置复选框和id属性
			String[] hobby = request.getParameterValues("hobby");
			//使用StringBuilder来进行复选框的字符串拼接
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < hobby.length; i++) {
				if(i == hobby.length-1){
					sb.append(hobby[i]);
					break;
				}
				sb.append(hobby[i]).append("#");
			}
			user.setHobby(sb.toString());
			
			//使用UUID进行id的获取
			String id = UUID.randomUUID().toString();
			user.setId(id);
			
			//调用业务逻辑
			UserService us = new UserServiceImpl();
			boolean flag = us.register(user);
			//解决响应的中文乱码问题
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			//对返回的注册结果进行判断
			if(flag){
				//如果注册成功，3秒后跳转到登录页面
				pw.write("注册成功，3秒后跳转到登录页面");
				response.setHeader("refresh", "3;url=/day17_request02/login.jsp");
			}else {
				//如果注册失败，给予提示，也是3秒钟跳回注册页面
				pw.write("注册失败，3秒后跳回到注册页面，请重新注册");
				response.setHeader("refresh", "3;url=/day17_request02/register.html");
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
