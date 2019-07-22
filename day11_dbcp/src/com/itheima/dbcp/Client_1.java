package com.itheima.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.itheima.bean.Users;

public class Client_1 {

	public static void main(String[] args) throws SQLException {
		Connection conn = DBCPUtils_01.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from users where username = ?");
		ps.setObject(1, "yibo");
		ResultSet rs = ps.executeQuery();
		Users u = null;
		ArrayList<Users> list = new ArrayList<>();
		while(rs.next()){
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String password = rs.getString(3);
			String email = rs.getString(4);
			Date birthday = rs.getDate(5);
			System.out.println(id+":"+username+":"+password+":"+email+":"+birthday);
			u = new Users(id,username,password,email,birthday);
			list.add(u);
		}
		
		DBCPUtils_01.closeAll(rs, ps, conn);
	}

}
