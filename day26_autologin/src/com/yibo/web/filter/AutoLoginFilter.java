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

import com.yibo.domain.User;
import com.yibo.web.service.UserService;
import com.yibo.web.service.impl.UserServiceImpl;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
public class AutoLoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest rqs = (HttpServletRequest) request;
		MyRequest myRequest = new MyRequest(rqs);
		HttpSession session = myRequest.getSession();
		
		String uri = myRequest.getRequestURI();
		String path = myRequest.getContextPath();
		path = uri.substring(path.length());
		if(!"/login.jsp".equals(path) || !"/register/jsp".equals(path)){
			if(session.getAttribute("user")==null){
				Cookie[] cookies = myRequest.getCookies();
				if(cookies != null){
					for (Cookie cookie : cookies) {
						if("info".equals(cookie.getName())){
							String value = cookie.getValue();
							String[] values = value.split("#");
							if(values.length==3){
								System.out.println("你好1");
								if("at".equals(values[2])){
									UserService us = new UserServiceImpl();
									String username = values[0];
									String password = values[1];
									User user = us.login(username, password);
									if(user!=null){
										session.setAttribute("username", values[0]);
										session.setAttribute("password", values[1]);
										session.setAttribute("user", user);
									}
									
								}else {
									session.setAttribute("username", values[0]);
									session.setAttribute("password", values[1]);
								}
							}else{
								session.setAttribute("username", values[0]);
								session.setAttribute("password", values[1]);
							}
								
						}
					}
				}
			}
		}
		chain.doFilter(myRequest, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {
		
	}

}
