package com.itheima.reflect;

public class Student {
	public String name;
	private int age;
	public Student() {
		System.out.println("无参构造函数!");
	}
	public Student(String name) {
		System.out.println("姓名:"+name);
	}
	private Student( int age) {
		System.out.println("年龄:"+age);
	}
	Student(String name,int age){
		System.out.println("姓名:"+name+" 年龄:"+age);
	}
	
	public void m1(){
		System.out.println("m1");
	}
	
	public void m2(String name){
		System.out.println(name);
	}
	
	public String m3(String name){
		return name;
	}
	
	private void m4(int age){
		System.out.println(age);
	}
	
	public static void m5(String name,int age){
		System.out.println(name+":"+age);
	}
	
	public static void m6(String[] args){
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
	}
	
}
