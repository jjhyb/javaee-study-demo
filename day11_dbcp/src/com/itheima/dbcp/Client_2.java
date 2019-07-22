package com.itheima.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client_2 {

	public static void main(String[] args) throws SQLException {
		Connection conn = DBCPUtils_02.getConnection();
		PreparedStatement ps = conn.prepareStatement("update users set username = ?,password = ?,email = ?,birthday = ? where id = ?");
		ps.setObject(1, "jing");
		ps.setObject(2, "0101");
		ps.setObject(3, "jing@163.com");
		ps.setObject(4, "1996-01-01");
		ps.setObject(5, 8);
		int i = ps.executeUpdate();
		System.out.println(i);
		
		DBCPUtils_02.closeAll(null, ps, conn);
	}

}
