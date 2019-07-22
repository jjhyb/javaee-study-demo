package com.itheima.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	private static DataSource datasource;
	static {
		datasource = new ComboPooledDataSource();
	}
	/*
	 * 获得数据源方法
	 */
	public static DataSource getDataSource(){
		return datasource;
	}
	/*
	 * 获得连接方法
	 */
	public static Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	/*
	 * 关闭资源方法
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
