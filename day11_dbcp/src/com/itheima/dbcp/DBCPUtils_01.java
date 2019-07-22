package com.itheima.dbcp;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils_01 {
	private static BasicDataSource datasource;
	static {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/jdbcconfig.properties"));
			
			
			datasource = (BasicDataSource) BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 获得连接的方法
	 */
	public static Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	/*
	 * 关闭资源的方法
	 */
	public static void closeAll(ResultSet rs,Statement state,Connection conn){
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
