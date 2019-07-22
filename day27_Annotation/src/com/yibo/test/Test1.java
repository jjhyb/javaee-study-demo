package com.yibo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yibo.mytest.MyTest;

public class Test1 {
	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		//获取类的字节码文件
		Class<?> clazz = Class.forName("com.yibo.test.TestDemo");
		//通过字节码文件获得公有的方法
		Method[] methods = clazz.getMethods();
		//遍历方法数组
		for (Method method : methods) {
			//判断，如果该数组中的方法包含该注解
			if(method.isAnnotationPresent(MyTest.class)){
				//那么调用方法执行
				method.invoke(clazz.newInstance());
			}
		}
	}
}
