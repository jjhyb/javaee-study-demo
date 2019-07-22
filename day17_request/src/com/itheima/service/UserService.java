package com.itheima.service;

import com.itheima.domain.User;

public interface UserService {

	boolean register(User user);

	User doLogin(String username, String password);

}
