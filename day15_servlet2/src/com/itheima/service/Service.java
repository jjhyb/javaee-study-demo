package com.itheima.service;

import com.itheima.domain.Users;

public interface Service {
	Users doLogin(String username,String password);
}
