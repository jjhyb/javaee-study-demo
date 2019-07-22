package com.yibo.web.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.yibo.domain.User;
import com.yibo.utils.ManagerThreadLocal;
import com.yibo.web.dao.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	public void register(User user) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(), "insert into user values(?,?,?,?,?,?,?,?)", user.getId(),user.getUsername(),user.getPassword(),user.getSex(),user.getNickname(),user.getEmail(),user.getBirthday(),user.getHobby());
	}

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from user where username=? and password=?", new BeanHandler<>(User.class), username,password);
	}

}
