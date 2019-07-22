package com.yibo.service;

import com.yibo.bean.User;

public interface UserService {

	/**
	 * 用户注册业务
	 * @param user
	 * @return
	 */
	Boolean regist(User user);

	/**
	 * 校验用户名是否可用
	 * @param username
	 * @return
	 */
	User checkName(String username);
	
	/**
	 * 根据激活码查询用户信息
	 * @param code
	 * @return
	 */
	boolean active(String code);

	/**
	 * 用户登录业务
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

}
