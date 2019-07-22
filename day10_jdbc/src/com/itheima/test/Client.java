package com.itheima.test;

import java.sql.SQLException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws SQLException {
		System.out.println("请录入用户名：");
		Scanner sc = new Scanner(System.in);
		String username = sc.nextLine();
		System.out.println("请录入密码：");
		String password = sc.nextLine();
		
		Server s = new Server();
		boolean flag = s.query(username, password);
		if(flag){
			System.out.println("登录成功！");
		}else {
			System.out.println("登录失败，请重新登录！");
		}
		
		
	}

}
