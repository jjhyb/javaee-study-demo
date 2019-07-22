package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.domain.User;

public interface UserDao {

	List<User> findUser(String birthday) throws SQLException ;

}
