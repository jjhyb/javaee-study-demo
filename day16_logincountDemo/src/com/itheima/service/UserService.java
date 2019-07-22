package com.itheima.service;

import com.itheima.domain.Users;

public interface UserService {

	Users doLogin(String username, String password);

}
