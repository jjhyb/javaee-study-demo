package com.itheima.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MyConnectionPool {
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	private LinkedList<Connection> pool = new LinkedList<>();
	public MyConnectionPool(){
		ResourceBundle rb = ResourceBundle.getBundle("jdbcconfig");
		driverClass = rb.getString("driverClass");
		url = rb.getString("url");
		username = rb.getString("username");
		password = rb.getString("password");
		
		for(int i=0;i<4;i++){
			try {
				Connection conn1 = DriverManager.getConnection(url, username, password);
				MyConnection conn2 = new MyConnection(conn1,pool);
				pool.add(conn2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//对外提供方法让调用者获得连接
	public Connection getConnection() throws SQLException{
		Connection conn = null;
		if(pool.size()>0){
			conn = pool.removeFirst();
		}else {
			conn = DriverManager.getConnection(url, username, password);
		}
		return conn;
	}
	
	/*
	 * 关闭链接方法
	 * 如果连接池原本就是池子里面的，那么放回就可以了，如果是新创建的，那么用完直接关闭
	 * 
	 */
	public void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
