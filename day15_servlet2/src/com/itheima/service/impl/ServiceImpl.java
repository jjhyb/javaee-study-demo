package com.itheima.service.impl;

import java.sql.SQLException;

import com.itheima.dao.UsersDao;
import com.itheima.dao.impl.UsersDaoImpl;
import com.itheima.domain.Users;
import com.itheima.service.Service;

public class ServiceImpl implements Service {

	@Override
	public Users doLogin(String username, String password) {
		UsersDao dao = new UsersDaoImpl();
		Users user = null;
		try {
			user = dao.findUsers(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
