package com.yibo.dao.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.yibo.bean.Category;
import com.yibo.dao.admin.AdminCategoryDao;
import com.yibo.utils.ManagerThreadLocal;

public class AdminCategoryDaoImpl implements AdminCategoryDao {

	@Override
	public List<Category> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from category",new BeanListHandler<>(Category.class));
	}

	@Override
	public void addCategory(Category cg) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"insert into category values(?,?)", cg.getCid(),cg.getCname());
	}

	@Override
	public void delete(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"delete from category where cid=?", cid);
	}

	@Override
	public Category edit(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from category where cid=?", new BeanHandler<>(Category.class), cid);
	}

	@Override
	public void edit(String cid, String cname) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update category set cname=? where cid=?", cname,cid);
	}

}
