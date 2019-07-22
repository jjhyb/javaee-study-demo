package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.ManagerThreadLocal;

public class UserDaoImpl implements UserDao {

	@Override
	public User findUser(String username) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from user where username = ?", new BeanHandler<>(User.class), username);
		
	}

}
