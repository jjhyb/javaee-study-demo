package com.yibo.dao.admin;

import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.Category;

public interface AdminCategoryDao {
	/**
	 * 查询所有商品分类的方法
	 * @return
	 * @throws SQLException
	 */
	List<Category> findAll() throws SQLException;

	/**
	 * 添加商品分类的功能
	 * @param cg
	 * @throws SQLException
	 */
	void addCategory(Category cg) throws SQLException;

	/**
	 * 根据商品分类cid删除商品分类
	 * @param cid
	 * @throws SQLException
	 */
	void delete(String cid) throws SQLException;

	/**
	 * 根据商品分类cid查询商品分类
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	Category edit(String cid) throws SQLException;

	/**
	 * 根据商品分类cid更新商品分类
	 * @param cid
	 * @param cname
	 * @throws SQLException 
	 */
	void edit(String cid, String cname) throws SQLException;

}
