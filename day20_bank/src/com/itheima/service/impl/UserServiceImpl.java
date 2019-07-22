package com.itheima.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.itheima.dao.RecordDao;
import com.itheima.dao.UserDao;
import com.itheima.dao.impl.RecordDaoImpl;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.Record;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.ManagerThreadLocal;

public class UserServiceImpl implements UserService {

	@Override
	public User login(String username, String password) {
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			user = dao.login(username, password);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean deposit(String username, double money, int type) {
		boolean flag = false;
		try {
			//开启事务
			ManagerThreadLocal.startTransaction();
			UserDao dao = new UserDaoImpl();
			User user = dao.findUserByName(username);
			user.setMoney(user.getMoney()+money);
			dao.updateUser(user.getUsername(), user.getMoney());
			
			//在存款的同时将记录写进数据库
			Record record = fun(money, type, user);
			RecordDao rd = new RecordDaoImpl();
			rd.addRecord(record);
			ManagerThreadLocal.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
		return flag;
	}

	private Record fun(double money, int type, User user) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormat = sdf.format(date);
		
		String id = UUID.randomUUID().toString();
		
		Record record = new Record();
		record.setId(id);
		record.setUserId(user.getId());
		record.setType(type);
		record.setMoney(money);
		record.setTargetUserId(user.getId());
		record.setTradeDate(dateFormat);
		return record;
	}
	private Record fun(double money, int type, User fromUser,User toUser) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateFormat = sdf.format(date);
		
		String id = UUID.randomUUID().toString();
		
		Record record = new Record();
		record.setId(id);
		record.setUserId(fromUser.getId());
		record.setType(type);
		record.setMoney(money);
		record.setTargetUserId(toUser.getId());
		record.setTradeDate(dateFormat);
		return record;
	}

	@Override
	public User findUserByName(String username) {
		UserDao dao = new UserDaoImpl();
		User user = null;
		try {
			user = dao.findUserByName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean withdraw(String username, double money, int type) {
		//开启事务
		boolean flag = false;
		try {
			ManagerThreadLocal.startTransaction();
			UserDao dao = new UserDaoImpl();
			User user = dao.findUserByName(username);
			if(user.getMoney()>=money){
				user.setMoney(user.getMoney()-money);
				dao.updateUser(user.getUsername(), user.getMoney());
				//在存款的同时将记录写进数据库
				Record record = fun(money, type, user);
				RecordDao rd = new RecordDaoImpl();
				rd.addRecord(record);
				ManagerThreadLocal.commit();
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
		return flag;
	}

	@Override
	public boolean transfer(String fromname, String toname, double money, int type) {
		//开启事务
		boolean flag = false;
		try {
			ManagerThreadLocal.startTransaction();
			UserDao dao = new UserDaoImpl();
			//分别获取转出账户和转入账户信息
			User fromUser = dao.findUserByName(fromname);
			User toUser = dao.findUserByName(toname);
			
			if(fromUser.getMoney()>=money){
				fromUser.setMoney(fromUser.getMoney()-money);
				toUser.setMoney(toUser.getMoney()+money);
				
				dao.updateUser(fromUser.getUsername(), fromUser.getMoney());
				dao.updateUser(toUser.getUsername(), toUser.getMoney());
				
				//将转账记录写入数据库
				Record record = fun(money,type,fromUser,toUser);
				RecordDao redao = new RecordDaoImpl();
				redao.addRecord(record);
				ManagerThreadLocal.commit();
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
		return flag;
	}


	
	
}
