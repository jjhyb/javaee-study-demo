package com.yibo.web.service.impl;

import java.sql.SQLException;

import com.yibo.domain.User;
import com.yibo.web.dao.UserDao;
import com.yibo.web.dao.impl.UserDaoImpl;
import com.yibo.web.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean register(User user) {
		//调用业务层方法
		UserDao dao = new UserDaoImpl();
		boolean flag = false;
		try {
			dao.register(user);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User login(String username, String password) {
		//调用业务层方法
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			user = dao.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
