package com.yibo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yibo.bean.Category;
import com.yibo.dao.CategoryDao;
import com.yibo.utils.ManagerThreadLocal;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAllCategory() throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from category",new BeanListHandler<>(Category.class));
	}

}
