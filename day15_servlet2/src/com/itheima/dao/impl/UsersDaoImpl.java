package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.UsersDao;
import com.itheima.domain.Users;
import com.itheima.utils.C3P0Util;

public class UsersDaoImpl implements UsersDao {

	@Override
	public Users findUsers(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Users user = qr.query("select * from users where username=? and password=?", new BeanHandler<>(Users.class), username,password);
		return user;
	}

}
