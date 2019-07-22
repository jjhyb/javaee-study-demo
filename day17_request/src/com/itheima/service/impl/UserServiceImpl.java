package com.itheima.service.impl;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean register(User user) {
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
	public User doLogin(String username, String password) {
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			 user = dao.findUser(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
