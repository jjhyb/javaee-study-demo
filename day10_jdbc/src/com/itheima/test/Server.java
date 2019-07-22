package com.itheima.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itheima.utils.JDBCUtils;

public class Server {
	public boolean query(String username,String password) throws SQLException{
		Connection conn = JDBCUtils.getConn();
		PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and password = ?");
		ps.setObject(1, username);
		ps.setObject(2, password);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
}
