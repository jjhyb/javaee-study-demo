package com.yibo.filter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class MyInvocationHandler implements InvocationHandler {
	private Object obj;
	public MyInvocationHandler(Object obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//先将传过来的obj向下强转成HttpServletRequest
		HttpServletRequest request = (HttpServletRequest) obj;
		//获取方法名
		String name = method.getName();
		
		//定义一个变量value,用于接收request.getParameter()方法的返回值
		String value = "";
		//判断方法名是否和要代理的方法一样
		if("getParameter".equals(name)){
			//如果一样就获取该传入对象带过来的参数提交方式
			String md = request.getMethod();
			//判断请求方式
			if("post".equalsIgnoreCase(md)){
				//如果是get请求方式
				request.setCharacterEncoding("UTF-8");
				value = request.getParameter((String) args[0]);
			}else {
				//如果是get请求方式
				value = new String(request.getParameter((String) args[0]).getBytes("ISO-8859-1"),"UTF-8");
			}
			return value;
		}//else if()后面可以跟上其他条件表达式,
		
		//如果是不需要代理增强的方法，则直接放行让其自己调用
		return method.invoke(obj, args);
	}

}
