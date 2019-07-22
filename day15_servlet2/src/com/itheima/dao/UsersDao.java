package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.Users;

public interface UsersDao {
	Users findUsers(String username,String password) throws SQLException ;
}
