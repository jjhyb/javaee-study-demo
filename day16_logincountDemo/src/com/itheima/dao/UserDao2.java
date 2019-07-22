package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.domain.Users;

public interface UserDao2 {
	Users findUser(String username,String password) throws SQLException ;
}
