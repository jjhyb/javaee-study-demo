package com.itheima.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import com.itheima.bean.Users;

public class TestCRUD {
	
	@Test
	public void testQuery() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Users user = qr.query("select * from users where username = ?", new BeanHandler<>(Users.class), "jing");
		System.out.println(user);
	}
	
	@Test
	public void testInsert() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i = qr.update("insert into users values(?,?,?,?,?)", null,"jay","111","jay@163.com","1980-11-01");
		System.out.println(i);
	}
	
	@Test
	public void testUpdate() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i = qr.update("update users set username = ?,email = ?,birthday = ? where id = ?", "wanglihong","wanglihong@163.com","1978-10-09",17);
		System.out.println(i);
	}
	
	@Test 
	public void testDelete() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i = qr.update("delete from users where id = ?", 20);
		System.out.println(i);
	}
}
