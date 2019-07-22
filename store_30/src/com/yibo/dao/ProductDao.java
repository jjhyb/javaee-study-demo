package com.yibo.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.Product;

public interface ProductDao {

	List<Product> findHotProducts() throws SQLException;

	List<Product> findLatestProduct() throws SQLException;

	Product getProductById(String pid) throws SQLException, IllegalAccessException, InvocationTargetException;

	int getProductCount(String cid) throws SQLException;

	List<Product> pageProducts(String cid, int curPage) throws SQLException;

}
