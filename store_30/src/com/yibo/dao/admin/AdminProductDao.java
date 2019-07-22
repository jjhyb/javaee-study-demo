package com.yibo.dao.admin;

import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.Product;

public interface AdminProductDao {

	int findProCount() throws SQLException;

	List<Product> findProAll(int curPage, Integer adminProductPagesize) throws SQLException;

	void saveProduct(Product pro) throws SQLException;

	void delete(String pid) throws SQLException;

}