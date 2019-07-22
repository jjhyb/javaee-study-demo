package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.C3P0Util;
import com.itheima.utils.ManagerThreadLocal;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from user where username=? and password=?", new BeanHandler<>(User.class), username,password);
	}

	@Override
	public void updateUser(String username, double money) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "update user set money=? where username=?",money,username);
	}

	@Override
	public User findUserByName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from user where username=?", new BeanHandler<>(User.class), username);
	}
	
	

}
