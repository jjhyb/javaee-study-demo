package com.yibo.service.impl;

import java.sql.SQLException;

import javax.mail.MessagingException;

import com.yibo.bean.User;
import com.yibo.constant.Constant;
import com.yibo.dao.UserDao;
import com.yibo.dao.impl.UserDaoImpl;
import com.yibo.service.UserService;
import com.yibo.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public Boolean regist(User user) {
		//调用dao层方法进行用户注册
		UserDao dao = new UserDaoImpl();
		boolean flag = false;
		try {
			dao.saveUser(user);
			flag = true;
			//注册成功给用户发送激活邮件
			MailUtils.sendMail(user.getEmail(),"亲爱的:"+user.getName()+",欢迎注册黑马商城，请尽快点击下面的链接进行激活<a href='localhost:8080/store_30/user?method=active&code="+user.getCode()+"'>用户激活</a>", "用户激活");
		} catch (SQLException | MessagingException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User checkName(String username) {
		//调用dao层方法进行校验
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			user = dao.checkName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean active(String code) {
		//调用dao层方法根据激活码code查询用户信息
		UserDao dao = new UserDaoImpl();
		boolean flag = false;
		try {
			User user = dao.findUserByCode(code);
			//如果查询到的用户不为null
			if(user != null){
				//则这证明该用户需要执行激活业务
				//将激活状态码改为激活状态，并且将激活码置为null防止用户重复激活
				user.setState(Constant.ACTIVED);
				user.setCode(null);
				//将设置后的用户信息存储到数据库中
				dao.updateUser(user);
				//如果成功将flag置为true
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public User login(String username, String password) {
		//调用业务层方法进行验证登录
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			user = dao.doLogin(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
