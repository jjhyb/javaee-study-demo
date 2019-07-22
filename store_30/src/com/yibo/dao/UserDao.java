package com.yibo.dao;

import java.sql.SQLException;

import com.yibo.bean.User;

public interface UserDao {
	
	/**
	 * 用户注册方法
	 * @param user
	 * @throws SQLException 
	 */
	void saveUser(User user) throws SQLException;
	
	/**
	 * 校验用户名是否存在方法
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	User checkName(String username) throws SQLException;
	
	/**
	 * 根据激活码查询用户信息
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	User findUserByCode(String code)throws SQLException;
	
	/**
	 * 修改用户信息，uid不变
	 * @param user
	 * @throws SQLException
	 */
	void updateUser(User user)throws SQLException;
	
	/**
	 * 根据用户名和密码验证用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	User doLogin(String username, String password) throws SQLException;

}
