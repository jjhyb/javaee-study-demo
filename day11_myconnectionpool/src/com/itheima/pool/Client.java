package com.itheima.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Client {

	public static void main(String[] args) throws SQLException {
		MyConnectionPool mcp = new MyConnectionPool();
		Connection conn = mcp.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from users where username = ?");
		ps.setObject(1, "yibo");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			String email = rs.getString(4);
			Date birthday = rs.getDate(5);
			System.out.println(id+":"+username+":"+password+":"+email+":"+birthday);
		}
		
	}

}
