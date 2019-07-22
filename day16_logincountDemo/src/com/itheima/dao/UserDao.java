package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.Users;

public interface UserDao {

	Users findUser(String username, String password) throws SQLException;

}
