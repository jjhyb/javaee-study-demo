package com.yibo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	/**
	 * 设置Cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param time
	 * @param path
	 */
	public static void setCookie(HttpServletResponse response,String name,String value,int time,String path){
		//1.创建Cookie对象
		Cookie cookie = new Cookie(name, value);
		//cookie可以手动设置有效期
		cookie.setMaxAge(time);
		
		//设置cookie的有效范围
		cookie.setPath(path);
		
		//2.将cookie存放到客户端
		response.addCookie(cookie);
	}
	/**
	 * 创建并设置Cookie对象
	 * @param name
	 * @param value
	 * @param time
	 * @param path
	 * @return
	 */
	public static Cookie createCookie(String name,String value,int time,String path){
		//1.创建Cookie对象
		Cookie cookie = new Cookie(name, value);
		//cookie可以手动设置有效期
		cookie.setMaxAge(time);
		
		//设置cookie的有效范围
		cookie.setPath(path);
		return cookie;
	}
	/**
	 * 获取指定Cookie对象中的值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request,String name){
		Cookie[] cookies = request.getCookies();
		String value = null;
		//增强程序的健壮性
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				//指向获取name为"nickname"的cookie
				if (name.equals(cookie.getName())) {
					value = cookie.getValue();
				}
			}
		}
		return value;
	}
}
