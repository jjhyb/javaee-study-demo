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
	
	/**
	 * 根据pid和pdesc搜索商品信息
	 * @param pid
	 * @param pdesc
	 * @return
	 */
	public List<Product> search(String pname, String pdesc) throws SQLException ;

	/**
	 * 查询商品信息的总条数
	 * @return
	 */
	public int findCount() throws SQLException ;
	
	/**
	 * 根据页数和页面显示数据条数获取商品信息集合
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public List<Product> findPageProduct(int curPage, int pageSize) throws SQLException ;
	
}
