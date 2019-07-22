package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.C3P0Util;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from product", new BeanListHandler<>(Product.class));
	}
	
}
