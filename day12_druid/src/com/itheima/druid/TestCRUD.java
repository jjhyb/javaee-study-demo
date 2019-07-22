package com.itheima.druid;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.itheima.bean.Users;

public class TestCRUD {

	@Test//BeanListHandler:适合取多条数据，先将数据封装到对应的Bean实体类中,在将实体类封装到list集合中
	public void test1_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		List<Users> list = qr.query("select * from users", new BeanListHandler<>(Users.class));
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	@Test//BeanHandler:适合取单行数据
	public void test2_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		Users user = qr.query("select * from users where username = ?", new BeanHandler<>(Users.class), "jing");
		System.out.println(user);
	}
	
	@Test//ScalarHandler:适合取单行单列数据
	public void test3_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		String str = (String) qr.query("select username from users where id = ?", new ScalarHandler(), 8);
		System.out.println(str);
	}
	
	@Test//ArrayHandler:适合取1条记录，把该条记录的每列值封装到一个数组中Object[]
	public void test4_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		Object[] obj = qr.query("select * from users where username = ?", new ArrayHandler(), "jing");
		for (Object o : obj) {
			System.out.print(o+"\t");
		}
	}
	
	@Test//ArrayListHandler:适合取多条记录，把每一条记录的每列值封装到一个数组中Object[],把数组封装到一个List中
	public void test5_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		List<Object[]> list = qr.query("select * from users", new ArrayListHandler());
		for (Object[] objects : list) {
			for (Object obj : objects) {
				System.out.print(obj+"\t");
			}
			System.out.println();
		}
	}
	
	@Test//ColumnListHandler:取某一列的数据，封装到list集合中
	public void test6_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		List<Object> list = qr.query("select * from users", new ColumnListHandler("birthday"));
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	@Test//KeyedHandler:取多条记录，每一条记录封装到一个Map中，在把这个Map封装到另一个Map中，Key为指定的字段值
	public void test7_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		Map<Object, Map<String, Object>> map = qr.query("select * from users", new KeyedHandler("username"));
		for (Entry<Object, Map<String, Object>> en : map.entrySet()) {
			System.out.println(en.getKey()+"\t");
			for (Entry<String, Object> e : en.getValue().entrySet()) {
				System.out.println(e.getKey()+"\t"+e.getValue());
			}
			System.out.println("--------------------");
		}
	}
	
	@Test//MapHandler:适合取一行记录，把当前记录的列名和列值放到一个Map中
	public void test8_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		Map<String, Object> map = qr.query("select * from users where username = ?", new MapHandler(), "jing");
		for (Entry<String, Object> en : map.entrySet()) {
			System.out.println(en.getKey()+"\t"+en.getValue());
		}
	}
	
	@Test//MapListHandler:适合取多条记录，把每条记录封装到一个Map中，在把Map封装到list中
	public void test9_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		List<Map<String, Object>> list = qr.query("select * from users", new MapListHandler());
		for (Map<String, Object> map : list) {
			for (Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey()+"\t"+m.getValue());
			}
			System.out.println("--------------------");
		}
	}
	
	@Test
	public void test10_Insert() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		int i = qr.update("insert into users(username,password,email,birthday) values(?,?,?,?)", "huang","222","huang@163.com","1991-04-20");
		System.out.println(i);
	}
	
	@Test
	public void test11_Update() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		int i = qr.update("update users set username = ? where id = ?", "huangyi",21);
		System.out.println(i);
	}
	
	@Test
	public void test12_Delete() throws SQLException{
		QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
		int i = qr.update("delete from users where username = ?", "huangyi");
		System.out.println(i);
	}

}
