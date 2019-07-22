package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.Product;

public interface ProductDao {
	/**
	 * 查询所有商品
	 * @return
	 * @throws SQLException
	 */
	public List<Product> findAll() throws SQLException;

	/**
	 * 添加单条商品
	 * @param product
	 * @throws SQLException
	 */
	public void addProduct(Product product) throws SQLException ;

	/**
	 * 通过商品pid查询单条商品信息
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public Product findProductById(String pid) throws SQLException ;
	
	/**
	 * 通过商品pid，更新单条商品信息
	 * @param product
	 * @throws SQLException
	 */
	public void updateProduct(Product product) throws SQLException ;

	/**
	 * 通过商品pid，删除单条商品信息
	 * @param pid
	 * @throws SQLException
	 */
	public void deleteProduct(String pid) throws SQLException ;
}
