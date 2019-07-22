package com.itheima.service.impl;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.Users;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public Users doLogin(String username, String password) {
		UserDao dao = new UserDaoImpl();
		Users user = null;
		try {
			user = dao.findUser(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
