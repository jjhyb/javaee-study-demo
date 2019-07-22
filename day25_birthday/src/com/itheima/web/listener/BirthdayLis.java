package com.itheima.web.listener;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

/**
 * Application Lifecycle Listener implementation class BirthdayLis
 *
 */
public class BirthdayLis implements ServletContextListener {

	
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	Timer timer = new Timer();
    	timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				//调用业务层方法获取当天生日的数据
				UserService us = new UserServiceImpl();
				boolean flag = us.findUser();
				if(flag){
					System.out.println("邮件发送成功");
				}else {
					System.out.println("邮件发送失败");
				}
			}
		}, new Date(), 24*60*60*1000);
    }
	
}
