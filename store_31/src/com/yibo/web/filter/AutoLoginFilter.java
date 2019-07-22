package com.yibo.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yibo.bean.User;
import com.yibo.service.UserService;
import com.yibo.service.impl.UserServiceImpl;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
public class AutoLoginFilter implements Filter {

    public AutoLoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//判断session中是否有登录信息
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		//判断用户是否已登录
		if(user == null){
			//未登录,则看客户端是否有与自动登录相对应的cookie
//			CookieUtil.getCookieValue(req, "info");//此方法封装了我下面的方法
			//获取到所有数组
			Cookie[] cookies = req.getCookies();
			//如果数组不为null
			if(cookies != null){
				for (Cookie cookie : cookies) {
					//如果cookie的name为info
					if("info".equals(cookie.getName())){
						//获取cookie的value
						String value = cookie.getValue();
						//切割value
						String[] values = value.split("#");
						//如果values数组长度小于3
						if(values.length<3){
							//那么证明用户只勾选了记住用户名和密码，那么将用户名和密码存入session，让jsp页面获取到
							String username = value.split("#")[0];
							String password = value.split("#")[1];
							session.setAttribute("username", username);
							session.setAttribute("password", password);
						}else {
							//如果values数组长度大于3
							//那么证明用户只勾选了记住用户名和密码，那么将用户名和密码以及auto存入session，让jsp页面获取到
							String username = value.split("#")[0];
							String password = value.split("#")[1];
							String auto = value.split("#")[0];
							/*session.setAttribute("username", username);
							session.setAttribute("password", password);
							session.setAttribute("auto", auto);*/
							//调用业务层方法进行登录
							UserService us = new UserServiceImpl();
							user = us.login(username, password);
							if(user != null){
								//表示登录成功那么将值存到域对象中
								session.setAttribute("user", user);
							}
						}
						
					}
				}
			}
		}
		//如果用户已登录，那么直接放行，如果未登录，执行自动登录，然后将user对象存到session中，然后放行
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
