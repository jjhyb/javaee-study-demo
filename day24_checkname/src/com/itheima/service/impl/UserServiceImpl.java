package com.itheima.service.impl;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User checkUser(String username) {
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			user = dao.findUser(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
