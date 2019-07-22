package com.itheima.service.impl;

import java.sql.SQLException;

import com.itheima.dao.UserDao2;
import com.itheima.dao.impl.UserDaoImpl2;
import com.itheima.domain.Users;
import com.itheima.service.UserService2;

public class UserServiceImpl2 implements UserService2 {

	@Override
	public Users doLogin(String username, String password) {
		UserDao2 dao = new UserDaoImpl2();
		Users user = null;
		try {
			user = dao.findUser(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
