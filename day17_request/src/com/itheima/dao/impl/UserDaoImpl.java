package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.C3P0Util;

public class UserDaoImpl implements UserDao{

	@Override
	public void register(User user) throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
//		qr.update("insert into user values(?,?,?,?,?,?,?,?)", user.getId(),user.getUsername(),user.getPassword(),user.getSex(),user.getNickname(),user.getEmail(),user.getBirthday(),user.getHobby());
		String sql = "insert into user(id,username,password,sex,nickname,email,birthday,hobby) values(?,?,?,?,?,?,?,?)";
		qr.update(sql, user.getId(),user.getUsername(),user.getPassword(),user.getSex(),user.getNickname(),user.getEmail(),user.getBirthday(),user.getHobby());
	}

	@Override
	public User findUser(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		User user = qr.query("select * from user where username=? and password=?", new BeanHandler<>(User.class), username,password);
		return user;
	}

}
