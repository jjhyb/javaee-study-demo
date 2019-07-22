package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.DateUtil;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public boolean findUser() {
		//调用dao层方法获取当天生日的数据
		UserDao dao = new UserDaoImpl();
		String birthday = DateUtil.getMonthAndDay();
		List<User> list = new ArrayList<>();
		boolean flag = false;
		try {
			list = dao.findUser(birthday);
			System.out.println(flag);
			for (User user : list) {
				MailUtils.sendMail(user.getEmail(), "亲爱的用户:"+user.getUsername()+",在这个特别的节日，我们给你献上最诚挚的祝福，生日快乐", "生日祝福");
			}
			flag = true;
			System.out.println(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

}
