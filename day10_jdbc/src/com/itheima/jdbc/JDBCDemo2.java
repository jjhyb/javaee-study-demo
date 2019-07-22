package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.itheima.domain.Users;
import com.itheima.utils.JDBCUtils;

public class JDBCDemo2 {

	public static void main(String[] args) throws SQLException {
//		demo1_Query();
//		demo1_insert();
//		demo1_Update();
		demo1_delete();
	}
	
	public static void demo1_Query() throws SQLException{
		Connection conn = JDBCUtils.getConn();
		PreparedStatement ps = conn.prepareStatement("select * from users where username = ?");
		ps.setObject(1, "yibo");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");
			Date birthday = rs.getDate("birthday");
			System.out.println(id+":"+password+":"+email+":"+birthday);
			
			Users u = new Users();
			u.setId(id);
			u.setUsername(username);
			u.setPassword(password);
			u.setEmail(email);
			u.setBirthday(birthday);
		}
		
		JDBCUtils.close(conn, ps, rs);
	}
	
	public static void demo1_insert() throws SQLException{
		Connection conn = JDBCUtils.getConn();
		PreparedStatement ps = conn.prepareStatement("insert into users values(?,?,?,?,?)");
		ps.setObject(1, 19);
		ps.setObject(2, "jay");
		ps.setObject(3, "123456");
		ps.setObject(4, "jay@163.com");
		ps.setObject(5, "1981-11-12");
		int i = ps.executeUpdate();
		System.out.println(i);
		
		JDBCUtils.close(conn, ps, null);
	}
	
	public static void demo1_Update() throws SQLException{
		Connection conn = JDBCUtils.getConn();
		PreparedStatement ps = conn.prepareStatement("update users set email = ? where username = ?");
		ps.setObject(1, "jay@111.com");
		ps.setObject(2, "jay");
		int i = ps.executeUpdate();
		System.out.println(i);
		
		JDBCUtils.close(conn, ps, null);
	}
	
	public static void demo1_delete() throws SQLException{
		Connection conn = JDBCUtils.getConn();
		PreparedStatement ps = conn.prepareStatement("delete from users where username = ?");
		ps.setObject(1, "jay");
		int i = ps.executeUpdate();
		System.out.println(i);
		
		JDBCUtils.close(conn, ps, null);
	}

}
