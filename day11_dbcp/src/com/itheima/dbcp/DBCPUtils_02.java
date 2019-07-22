package com.itheima.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPUtils_02 {
	private static BasicDataSource datasource;
	static {
		datasource = new BasicDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql:///day06");
		datasource.setUsername("root");
		datasource.setPassword("yibo");
	}
	
	/*
	 * 获得连接方法
	 */
	public static Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	/*
	 * 关闭资源的方法
	 */
	public static void closeAll(ResultSet rs,Statement state,Connection conn) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			state = null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
