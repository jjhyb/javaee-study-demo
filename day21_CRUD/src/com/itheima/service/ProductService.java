package com.itheima.service;

import java.util.List;

import com.itheima.domain.Product;

public interface ProductService {
	/**
	 * 查询所有商品信息方法
	 * @return
	 */
	public List<Product> findAll();

	/**
	 * 添加商品信息
	 * @param product
	 * @return
	 */
	public boolean addProduct(Product product);

	/**
	 * 通过商品pid查询单个商品信息
	 * @param pid
	 * @return
	 */
	public Product findProductById(String pid);

	/**
	 * 通过商品pid，更新单条商品信息
	 * @param product
	 * @return
	 */
	public boolean updateProduct(Product product);

	/**
	 * 通过商品pid删除一条商品信息
	 * @param pid
	 * @return
	 */
	public boolean deleteProduct(String pid);
}
