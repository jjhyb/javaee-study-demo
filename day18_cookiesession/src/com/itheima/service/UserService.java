package com.itheima.service;

import com.itheima.domain.User;

public interface UserService {
	User doLogin(String username,String password);
	boolean register(User user);
}
