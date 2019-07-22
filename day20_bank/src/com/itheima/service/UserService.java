package com.itheima.service;

import com.itheima.domain.User;

public interface UserService {
	User login(String username,String password);

	boolean deposit(String username, double money, int type);
	
	User findUserByName(String username);
	
	boolean withdraw(String username,double money,int type);

	boolean transfer(String fromname, String toname, double money, int type);

}
