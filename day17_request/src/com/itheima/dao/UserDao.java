package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.User;

public interface UserDao {
	void register(User user) throws SQLException;

	User findUser(String username, String password) throws SQLException;
}
