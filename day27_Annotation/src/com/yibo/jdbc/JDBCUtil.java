package com.yibo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//"jdbc:mysql://localhost:3306/day17"//中间那一部分可以省略
@JDBCInfo(url="jdbc:mysql:///day17",password="yibo")
public class JDBCUtil {
	private static String driverClassName;
	private static String url;
	private static String username;
	private static String password;
	static{
		Class clazz = JDBCUtil.class;
		JDBCInfo info = (JDBCInfo) clazz.getAnnotation(JDBCInfo.class);
		driverClassName = info.driverClassName();
		url = info.url();
		username = info.username();
		password = info.password();
		
		try {
			//初始化驱动
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String[] args) throws SQLException{
		Connection conn = getConnection();
		System.out.println(conn);
	}
}
