package com.yibo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.yibo.domain.User;
import com.yibo.web.service.UserService;
import com.yibo.web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端页面传过来的参数
		String md = request.getParameter("method");
		//通过本类对象获取字节码文件
		Class<? extends UserServlet> clazz = this.getClass();
		try {
			//通过反射获取方法对象
			Method method = clazz.getMethod(md, HttpServletRequest.class,HttpServletResponse.class);
			//因为参数和方法名相同，直接用反射调用方法
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//创建javaBean对象，用于封装获取到的表单数据
			User user = new User();
			//注册时间类型转换器,用于字符串对javaBean中的Date的格式转换
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
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
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
			}else {
				//如果注册失败，给予提示，也是3秒钟跳回注册页面
				pw.write("注册失败，3秒后跳回到注册页面，请重新注册");
				response.setHeader("refresh", "3;url="+request.getContextPath()+"/register.html");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String remember = request.getParameter("remember");
		String auto = request.getParameter("auto");
		
		//调用业务逻辑
		UserService us = new UserServiceImpl();
		User user = us.login(username,password);
		HttpSession session = request.getSession();
		//判断是否登录成功
		if(user != null){
			//将user存到域对象session中
			session.setAttribute("user", user);
			
			String info = username+"#"+password+"#"+auto;
			Cookie cookie1 = new Cookie("info", info);
			
			cookie1.setPath(request.getContextPath());
			cookie1.setMaxAge(24*60*60);
			//那么登录成功，进行下一步的操作,判断是否记录用户名和密码已经是否自动登录
			if(!"at".equals(auto) && !"rem".equals(remember)){
				cookie1.setMaxAge(0);
			}
			response.addCookie(cookie1);
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/index.jsp");
		}else {
			//那就是登录失败
			session.setAttribute("msg", "用户名或密码错误!");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if("info".equals(cookie.getName())){
					String value = cookie.getValue();
					String[] values = value.split("#");
					if(values.length>=2){
						cookie.setValue(values[0]+"#"+values[1]);
						response.addCookie(cookie);
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//这里是要查询购物车数据库表，根据个人id信息查询出商品信息展示在页面上
		//如果没有登录那么就直接跳转到登录页面
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.getWriter().write("请先登录，在查看购物车");
			response.setHeader("refresh", "3;url="+request.getContextPath()+"/login.jsp");
		}
		response.setHeader("refresh", "0;url="+request.getContextPath()+"/cart.jsp");
	}
}
