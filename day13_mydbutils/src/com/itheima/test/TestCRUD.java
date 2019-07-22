package com.itheima.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.itheima.bean.Users;
import com.itheima.commons.dbutils.BeanHandler;
import com.itheima.commons.dbutils.BeanListHandler;
import com.itheima.commons.dbutils.QueryRunner;
import com.itheima.commons.dbutils.ScalarHandler;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestCRUD {
	@Test
	public void test1_Insert() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		int update = qr.Update("insert into users values(?,?,?,?,?)", null,"wu","0306","wu@163.com","1996-02-19");
		System.out.println(update);
	}
	
	@Test
	public void test2_Update() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		int update1 = qr.Update("update users set username = ?,email = ? where id < ?", "wuyujing","wuyujing@163.com",13);
		int update2 = qr.Update("update users set username = ?,email = ? where id > ?", "huangyibo","huangyibo@163.com",12);
		System.out.println(update1);
		System.out.println(update2);
	}
	
	@Test
	public void test3_Delete() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		int update = qr.Update("delete from users where id = ?", 14);
		System.out.println(update);
	}
	
	@Test
	public void test4_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		List<Users> list = qr.query("select * from users", new BeanListHandler<>(Users.class));
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	@Test
	public void test5_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		Users user = qr.query("select * from users where id = ?", new BeanHandler<>(Users.class), 19);
		System.out.println(user);
	}
	
	@Test
	public void test6_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		Object obj = qr.query("select username from users where id = ?", new ScalarHandler(), 1);
		System.out.println(obj);
	}
	
	@Test
	public void test7_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		Object obj = qr.query("select count(*) from users", new ScalarHandler());
		System.out.println(obj);
	}
}
