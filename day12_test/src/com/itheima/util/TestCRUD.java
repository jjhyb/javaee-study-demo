package com.itheima.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.itheima.bean.Product;

public class TestCRUD {
	
	@Test//(1).添加多条数据
	public void test1_Insert() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i1 = qr.update("insert into product(pname,price,pnum) values(?,?,?)", "笑傲江湖",30,100);
		int i2 = qr.update("insert into product(pname,price,pnum) values(?,?,?)", "倚天屠龙记",50,1000);
		int i3 = qr.update("insert into product(pname,price,pnum) values(?,?,?)", "西游记",30,200);
		int i4 = qr.update("insert into product(pname,price,pnum) values(?,?,?)", "水浒传",20,150);
		int i5 = qr.update("insert into product(pname,price,pnum) values(?,?,?)", "天龙八部",40,500);
		int i6 = qr.update("insert into product(pname,price,pnum) values(?,?,?)", "射雕英雄传",35,300);
	}
	
	@Test//(2).删除pname='XX'的数据
	public void test2_Delete() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i = qr.update("delete from product where pid = ?",7 );
		System.out.println(i);
	}
	
	@Test//修改price<XXX  的数据
	public void test3_Update() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		int i = qr.update("update product set price = ? where price < ?", 40,45);
		System.out.println(i);
	}
	
	@Test//(4).查询所有数据，并创建一个Product对象的集合来存储查询到的数据
	public void test4_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Product> list = qr.query("select * from product", new BeanListHandler<>(Product.class));
		for (Product product : list) {
			System.out.println(product);
		}
	}
	
	@Test//(5).查询一条数据，id=4的数据，存放在Product中	
	public void test5_Select() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Product product = qr.query("select * from product where pid = ?", new BeanHandler<>(Product.class), 4);
		System.out.println(product);
	}
}
