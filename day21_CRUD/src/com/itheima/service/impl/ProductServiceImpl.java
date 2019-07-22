package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.dao.ProductDao;
import com.itheima.dao.impl.ProductDaoImpl;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	public List<Product> findAll(){
		//调用dao层方法，到数据库查询所有商品信息
		ProductDao dao = new ProductDaoImpl();
		List<Product> list = new ArrayList<>();
		try {
			list = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addProduct(Product product) {
		//调用dao层方法，将product对象数据存储在数据库中
		ProductDao dao = new ProductDaoImpl();
		boolean flag = false;
		try {
			dao.addProduct(product);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Product findProductById(String pid) {
		//调用dao层方法，通过商品pid查询单条商品信息
		ProductDao dao = new ProductDaoImpl();
		Product product = null;
		try {
			product = dao.findProductById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public boolean updateProduct(Product product) {
		ProductDao dao = new ProductDaoImpl();
		boolean flag = false;
		try {
			dao.updateProduct(product);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteProduct(String pid) {
		//调用dao层方法，通过商品pid删除单条商品信息
		ProductDao dao = new ProductDaoImpl();
		boolean flag = false;
		try {
			dao.deleteProduct(pid);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
