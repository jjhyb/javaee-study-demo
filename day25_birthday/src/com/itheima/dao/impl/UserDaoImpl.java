package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.ManagerThreadLocal;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> findUser(String birthday) throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(), "select * from user where birthday like ?", new BeanListHandler<>(User.class), "%"+birthday);
	}

}
