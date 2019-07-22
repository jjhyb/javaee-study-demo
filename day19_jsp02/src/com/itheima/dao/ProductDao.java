package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Product;

public interface ProductDao {
	List<Product> findAll() throws SQLException ;
}
