package com.yibo.service;

import com.yibo.domain.User;

public interface UserService {

	Boolean regist(User user);

	User search(String username);

	User login(String username, String password);

}
