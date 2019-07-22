package com.yibo.web.dao;

import java.sql.SQLException;

import com.yibo.domain.User;

public interface UserDao {

	void register(User user) throws SQLException;

	User login(String username, String password) throws SQLException;

}
