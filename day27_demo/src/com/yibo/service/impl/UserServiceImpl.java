package com.yibo.service.impl;

import java.sql.SQLException;

import com.yibo.dao.UserDao;
import com.yibo.dao.impl.UserDaoImpl;
import com.yibo.domain.User;
import com.yibo.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public Boolean regist(User user) {
		//调用dao层方法
		UserDao dao = new UserDaoImpl();
		boolean flag = false;
		try {
			dao.regist(user);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User search(String username) {
		//调用dao层方法
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			 user = dao.search(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User login(String username, String password) {
		//调用dao层方法
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
