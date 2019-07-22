package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class JDBCDemo1 {

	public static void main(String[] args) throws Exception {
//		demo1_Query();
//		demo2_Insert();
//		demo3_delete();
		demo4_Update();
	}

	private static void demo1_Query() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day06", "root", "yibo");
		
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery("select * from users");
		while(rs.next()){
			String username = rs.getString(2);
			String password = rs.getString(3);
			String email = rs.getString(4);
			Date birthday = rs.getDate(5);
			System.out.println(username+":"+password+":"+email+":"+birthday);
		}
		rs.close();
		state.close();
		conn.close();
	}
	

	public static void demo2_Insert() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day06", "root", "yibo");
		Statement state = conn.createStatement();
		int i = state.executeUpdate("insert into users values(18,'yibo','333','yibo@163.com','2017-01-01')");
		System.out.println(i);
		
		//关闭资源
		state.close();
		conn.close();
	}
	
	public static void demo3_delete() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day06", "root", "yibo");
		Statement state = conn.createStatement();
		int i = state.executeUpdate("delete from users where id = 15");
		System.out.println(i);
		
		//关闭资源
		state.close();
		conn.close();
	}
	
	public static void demo4_Update() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day06", "root", "yibo");
		Statement state = conn.createStatement();
		int i = state.executeUpdate("update users set birthday ='1991-03-06' where username = 'yibo'");
		System.out.println(i);
		
		//关闭资源
		state.close();
		conn.close();
	}

}
