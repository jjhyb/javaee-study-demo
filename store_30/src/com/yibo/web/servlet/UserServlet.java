package com.yibo.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yibo.bean.User;
import com.yibo.constant.Constant;
import com.yibo.service.UserService;
import com.yibo.service.impl.UserServiceImpl;
import com.yibo.utils.CookieUtil;
import com.yibo.utils.UUIDUtils;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取注册表单参数验证码
		String vcode = request.getParameter("vcode");
		//获取session
		HttpSession session = request.getSession();
		//session中获取验证码用于校验
		String code = (String) session.getAttribute("code");
		if(!code.equalsIgnoreCase(vcode)){
			session.setAttribute("check", "验证码错误");
			result = Constant.REDIRECT;
			return request.getContextPath()+"/jsp/register.jsp";
		}
		//创建user对象，用于封装表单数据
		User user = new User();
		try {
			//获取表单数据,并封装到user对象中
			BeanUtils.populate(user, request.getParameterMap());
			//手动设置uid
			user.setUid(UUIDUtils.getId());
			//手动设置激活状态码
			user.setCode(UUIDUtils.getId());
			//用户注册，所以设置激活状态值为未激活，需要用户另行激活
			user.setState(Constant.UNACTIVE);
			
			//调用业务逻辑进行注册
			UserService us = new UserServiceImpl();
			Boolean flag = us.regist(user);
			
			//判断是否注册成功
			if(flag){
				//注册成功,保存提示信息
				session.setAttribute("msg", "注册成功,请到邮箱进行激活");
			}else {
				//注册失败,保存用户提示信息
				session.setAttribute("msg", "注册失败,请重新注册");
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//设置分发转向的boolean值为重定向
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/msg.jsp";
	}
	
	private String checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户名信息
		String username = request.getParameter("username");
		//调用业务层方法校验用户名是否存在
		UserService us = new UserServiceImpl();
		User user = us.checkName(username);
		System.out.println(user);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		//对返回结果进行判断
		if(user != null){
			//用户名已存在
			writer.write("该用户名已被占用");
		}else{	
			writer.write("该用户名可以使用");
		}
		return null;
	}
	
	private String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户端传过来的激活码code
		String code = request.getParameter("code");
		//调用业务层方法，根据激活码进行查询该用户
		UserService us = new UserServiceImpl();
		boolean flag = us.active(code);
		//获取session
		HttpSession session = request.getSession();
		//对flag进行判断
		if(flag){
			//激活成功
			session.setAttribute("msg", "激活成功,3秒后跳转至首页");
		}else {
			//激活失败
			session.setAttribute("msg", "激活失败,请重新激活");
			
		}
		//将成员变量置为重定向
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/msg.jsp";
	}
	
	private String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户登录信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		String auto = request.getParameter("auto");
		String remember = request.getParameter("remember");
		
		//从session中获取服务器端验证码原本
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		
		//设置一个返回路径变量
		String path = "/jsp/login.jsp";;
		
		//对验证码进行判断
		if(code.equalsIgnoreCase(vcode)){
			//验证码正确,验证登录
			UserService us = new UserServiceImpl();
			User user = us.login(username,password);
			if(user != null){
				//登录成功,将查询的user对象保存到域对象中
				session.setAttribute("user", user);
				//创建cookie
				Cookie cookie = CookieUtil.createCookie("info", username+"#"+password+"#"+auto, 60*60*3, request.getContextPath());
				//接着判断用户是否需要自动登录和记住用户名
				if(!"rem".equals(remember) && !"at".equals(auto)){
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				result = Constant.REDIRECT;
				path = request.getContextPath()+"/jsp/index.jsp";
			}else {
				//登录失败
				result = Constant.FORWARD;
				request.setAttribute("msg", "用户名或密码错误");
			}
		}else {
			//验证码错误
			result = Constant.FORWARD;
			request.setAttribute("msg", "验证码错误");
		}
		return path;
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//销毁session
		HttpSession session = request.getSession();
		session.invalidate();
		//清除cookie
		Cookie cookie = new Cookie("info", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		result = Constant.REDIRECT;
		return request.getContextPath()+"/jsp/index.jsp";
	}
	
}
