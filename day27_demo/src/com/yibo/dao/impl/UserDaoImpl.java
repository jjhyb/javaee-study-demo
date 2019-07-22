package com.yibo.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.yibo.dao.UserDao;
import com.yibo.domain.User;
import com.yibo.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{

	@Override
	public void regist(User user) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(DataSourceUtils.getConnection(), "insert into user values(?,?,?,?,?,?)", null,user.getUsername(),user.getPassword(),user.getEmail(),user.getSex(),user.getBirthday());
		
	}

	@Override
	public User search(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(DataSourceUtils.getConnection(), "select * from user where username=?", new BeanHandler<>(User.class), username);
	}

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(DataSourceUtils.getConnection(), "select * from user where username=? and password=?", new BeanHandler<>(User.class), username,password);
	}

}
