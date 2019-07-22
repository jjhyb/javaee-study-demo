package com.yibo.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.yibo.mysession.MySessionContext;

public class MySessionListener implements HttpSessionListener {
	
	//session初始化
    public void sessionCreated(HttpSessionEvent httpSessionEvent)  {
    	//session一初始化就将其保存到map集合中
    	MySessionContext.AddSession(httpSessionEvent.getSession());
    }

    //session销毁
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)  { 
    	//在session销毁前将其从map集合中移除
    	HttpSession session = httpSessionEvent.getSession();
        MySessionContext.DelSession(session);
    }
	
}
