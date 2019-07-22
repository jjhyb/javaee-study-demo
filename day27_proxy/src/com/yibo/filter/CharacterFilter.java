package com.yibo.filter;

import java.io.IOException;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CharacterFilter
 */
public class CharacterFilter implements Filter {


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletRequest proxy = (HttpServletRequest) Proxy.newProxyInstance(CharacterFilter.class.getClassLoader(), req.getClass().getInterfaces(), new MyInvocationHandler(req));
		chain.doFilter(proxy, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
