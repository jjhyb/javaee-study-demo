package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.User;

public interface UserDao {

	User findUser(String username) throws SQLException ;

}
