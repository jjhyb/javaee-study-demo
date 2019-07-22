package com.yibo.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {
	
	public MyRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		//从父类获取request对象，并强转为HttpServletRequest类型
		HttpServletRequest request2 = (HttpServletRequest) getRequest();
		//获取客户端请求方法
		String method = request2.getMethod();
		String value = "";
		//判断客户端提交方式
		if("post".equalsIgnoreCase(method)){
			try {
				request2.setCharacterEncoding("UTF-8");
				value = request2.getParameter(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if("get".equalsIgnoreCase(method)){
			try {
				value = new String(request2.getParameter(name).getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		return value;
	}
	
	
}
