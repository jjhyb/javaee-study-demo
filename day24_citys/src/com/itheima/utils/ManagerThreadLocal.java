package com.itheima.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> tl = new ThreadLocal<>();
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = tl.get();
		if(conn == null){
			try {
				conn = C3P0Util.getConnection();
				tl.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * 开启事务
	 */
	public static void startTransaction(){
		try {
			getConnection().setAutoCommit(false);//从当前线程中取出一个连接，并开启事务
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交
	 */
	public static void commit(){
		try {
			getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 回滚
	 */
	public static void rollback(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将连接放回连接池中
	 */
	public static void close(){
		try {
			getConnection().close();//将连接放回连接池中
			tl.remove(); 		//将当前线程对象对应的Connetion从ThreadLocal中移除
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
