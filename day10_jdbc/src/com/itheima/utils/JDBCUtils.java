package com.itheima.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	static{
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/jdbcconfig.properties"));
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void close(Connection conn,Statement state,ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(state != null){
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			state = null;
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
