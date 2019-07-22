package com.yibo.test;

import com.yibo.mytest.MyTest;

public class TestDemo {
	@MyTest
	public void test1(){
		System.out.println("test1");
	}
	
	@MyTest
	public void test2(){
		System.out.println("test2");
	}
	
	public void test3(){
		System.out.println("test3");
	}
}
