package com.itheima.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

public class Reflect2Method {
	
	@Test//public void m1()
	public void test1() throws Exception{
		Class<Student> clazz = (Class<Student>) Class.forName("com.itheima.reflect.Student");
		Student stu = clazz.newInstance();
		Method method = clazz.getMethod("m1", null);
		method.invoke(stu, null);
	}
	
	@Test//public void m2(String name)
	public void test2() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod("m2", String.class);
		method.invoke(obj, "张三丰");
	}
	
	@Test//public String m3(String name)
	public void test3() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod("m3", String.class);
		String name = (String) method.invoke(obj, "张无忌");
		System.out.println(name);
	}
	
	@Test//private void m4(int age)
	public void test4() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Object obj = clazz.newInstance();
		Method method = clazz.getDeclaredMethod("m4", int.class);
		method.setAccessible(true);
		method.invoke(obj, 22);
	}
	
	@Test//public static void m5(String name,int age)
	public void test5() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Method method = clazz.getMethod("m5", String.class,int.class);
		method.invoke(null, "张无忌",22);//静态方法，不需要对象即可执行
	}
	
	@Test//public static void m6(String[] args)
	public void test6() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Method method = clazz.getMethod("m6", String[].class);
//		method.invoke(null, (Object)new String[]{"jing","love","you"});
		method.invoke(null, new Object[]{new String[]{"jing","love","you"}});
	}
	
	@Test//得到所有方法,得到本类中所有公共方法，包含父类和父级接口公共方法
	public void test7() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Method[] methods = clazz.getMethods();
		System.out.println(methods.length);
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
		}
	}
	
	@Test//得到本类中所有的方法(包含私有)，但不包括不包括父类或父级接口
	public void test8() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Method[] methods = clazz.getDeclaredMethods();
		System.out.println(methods.length);
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
		}
	}
}
