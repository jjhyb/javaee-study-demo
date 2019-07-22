package com.itheima.druid;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidUtil {
	private static DataSource datasource;
	static {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/druidconfig.properties"));
			datasource = DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获取数据源的方法
	 */
	public static DataSource getDataSource(){
		return datasource;
	}
	
	/*
	 * 获取连接方法
	 */
	public static Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	/*
	 * 关闭资源方法
	 */
	public static void close(ResultSet rs,Statement state,Connection conn){
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
