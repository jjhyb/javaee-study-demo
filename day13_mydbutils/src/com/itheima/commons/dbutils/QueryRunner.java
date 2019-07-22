package com.itheima.commons.dbutils;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;


public class QueryRunner {
	private DataSource ds;
	public QueryRunner(DataSource ds){
		this.ds = ds;
	}
	
	public<T> T query(String sql,ResultSetHandler<T> rsh,Object...params)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			/*ParameterMetaData md = ps.getParameterMetaData();
			int parameterCount = md.getParameterCount();
			if(parameterCount>0){
				if(params!=null && parameterCount == params.length){
					for (int i = 0; i < params.length; i++) {
						ps.setObject(i+1, params[i]);
					}
				}
			}*/
			setParameter(ps,params);
			rs = ps.executeQuery();
			T handler = rsh.handler(rs);
			return handler;
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}finally{
			release(conn, ps, rs);
		}
	}
	
	
	public int Update(String sql,Object...params) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			setParameter(ps,params);
			int executeUpdate = ps.executeUpdate();
			return executeUpdate;
		} catch (Exception e) {
			throw new SQLException(e.getMessage()); 
		}finally{
			release(conn,ps,null);
		}
	}

	public void setParameter(PreparedStatement ps, Object... params) throws SQLException {
		ParameterMetaData md = ps.getParameterMetaData();
		int parameterCount = md.getParameterCount();
		if(parameterCount>0){
			if(params!=null && parameterCount == params.length){
				for (int i = 0; i < parameterCount; i++) {
					ps.setObject(i+1, params[i]);
				}
			}else{
				throw new SQLException("参数错误");
			}
		}
	}
	
	/**
	 * 释放资源
	 * @param conn
	 * @param state
	 * @param rs
	 */
	public void release(Connection conn,Statement state,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(state!=null){
					state.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
