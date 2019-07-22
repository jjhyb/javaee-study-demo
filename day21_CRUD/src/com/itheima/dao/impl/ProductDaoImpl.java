package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.ManagerThreadLocal;

public class ProductDaoImpl implements ProductDao {
	public List<Product> findAll() throws SQLException{
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from product", new BeanListHandler<>(Product.class));
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "insert into product values(?,?,?,?,?,?,?)",product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdesc(),product.getPdate());
	}

	@Override
	public Product findProductById(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from product where pid=?", new BeanHandler<>(Product.class), pid);
		
	}

	@Override
	public void updateProduct(Product p) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdesc=?,pdate=? where pid=?",p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),p.getPdesc(),p.getPdate(),p.getPid());
	}

	@Override
	public void deleteProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "delete from product where pid=?", pid);
	}
}
