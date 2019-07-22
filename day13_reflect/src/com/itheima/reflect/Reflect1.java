package com.itheima.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class Reflect1 {
	
	@Test
	public void test1() throws ClassNotFoundException{
		//得到字节码文件对象的三种方式
		Class<?> clazz1 = Class.forName("com.itheima.reflect.Student");
		Class clazz2 = Student.class;
		Class clazz3 = new Student().getClass();
	}
	
	@Test//public Student()
	public void test2() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Constructor<?> cons = clazz.getConstructor();//得到无参构造函数
		Object obj = cons.newInstance();
		System.out.println(obj);
	}
	
	@Test//public Student(String name)
	public void test3() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Constructor<?> cons = clazz.getConstructor(String.class);
		cons.newInstance("张三");
	}
	
	@Test//private Student( int age)
	public void test4() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Constructor<?> cons = clazz.getDeclaredConstructor(int.class);
		cons.setAccessible(true);//暴力反射
		cons.newInstance(20);
	}
	
	@Test//Student(String name,int age)
	public void test5() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Constructor<?> cons = clazz.getDeclaredConstructor(String.class,int.class);
		cons.setAccessible(true);
		cons.newInstance("张无忌",22);
	}
	
	@Test//获得全部公共的构造函数
	public void test6() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Constructor<?>[] cons = clazz.getConstructors();
		System.out.println(cons.length);
	}
	
	@Test//获得全部构造函数，包含私有
	public void test7() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Constructor<?>[] cons = clazz.getDeclaredConstructors();
		System.out.println(cons.length);
	}
}
