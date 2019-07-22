package com.itheima.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class Reflect3Field {
	
	@Test
	public void test1() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Field field = clazz.getField("name");
		Object obj = clazz.newInstance();
		field.set(obj, "张无忌");
		String str = (String) field.get(obj);
		System.out.println(str);
	}
	
	@Test
	public void test2() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Field field = clazz.getDeclaredField("age");
		Object obj = clazz.newInstance();
		field.setAccessible(true);
		field.set(obj, 22);
		int age = (int) field.get(obj);
		System.out.println(age);
	}
	
	@Test//得到字段的修饰符和字段类型
	public void test3() throws Exception{
		Class<?> clazz = Class.forName("com.itheima.reflect.Student");
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(Modifier.toString(fields[i].getModifiers())+"\t"+
					fields[i].getType().getSimpleName()+"\t"+fields[i].getName());
		}
	}
}
