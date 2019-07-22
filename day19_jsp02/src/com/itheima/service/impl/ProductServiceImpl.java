package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findAll() {
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = new ArrayList<>();
		try {
			list = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
