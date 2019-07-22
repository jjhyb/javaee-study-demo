package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.User;

public interface UserDao {
	User login(String username,String password) throws SQLException;
	void updateUser(String username,double money) throws SQLException;
	User findUserByName(String username) throws SQLException;
}
