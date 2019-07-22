package com.itheima.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Client {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("select * from users where username = ?");
			ps.setString(1, "jing");
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				Date birthday = rs.getDate(5);
				System.out.println(id+":"+username+":"+password+":"+email+":"+birthday);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			C3P0Util.closeAll(rs, ps, conn);
		}
		
	}

}
