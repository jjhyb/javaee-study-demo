package com.yibo.dao;

import java.sql.SQLException;

import com.yibo.domain.User;

public interface UserDao {

	void regist(User user)throws SQLException ;

	User search(String username) throws SQLException ;

	User login(String username, String password) throws SQLException ;

}
