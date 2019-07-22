package com.yibo.dao;

import java.sql.SQLException;
import java.util.List;

import com.yibo.bean.Category;

public interface CategoryDao {
	/**
	 * 查询全部的商品分类信息
	 * @return
	 * @throws SQLException 
	 */
	List<Category> findAllCategory() throws SQLException;

}
