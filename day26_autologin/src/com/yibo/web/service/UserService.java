package com.yibo.web.service;

import com.yibo.domain.User;

public interface UserService {

	boolean register(User user);
	User login(String username, String password);

}
