package com.yibo.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.yibo.bean.User;
import com.yibo.dao.UserDao;
import com.yibo.utils.ManagerThreadLocal;

public class UserDaoImpl implements UserDao{

	@Override
	public void saveUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(ManagerThreadLocal.getConnection(),sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	}

	@Override
	public User checkName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.query(ManagerThreadLocal.getConnection(), "select * from user where username=?", new BeanHandler<>(User.class), username);
		return null;
	}

	@Override
	public User findUserByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from user where code=?", new BeanHandler<>(User.class), code);
		
	}

	@Override
	public void updateUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		qr.update(ManagerThreadLocal.getConnection(), sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid());
	}

	@Override
	public User doLogin(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from user where username=? and password=?", new BeanHandler<>(User.class), username,password);
	}

}
