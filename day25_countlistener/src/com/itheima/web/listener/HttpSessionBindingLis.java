package com.itheima.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Application Lifecycle Listener implementation class HttpSessionBindingLis
 *
 */
public class HttpSessionBindingLis implements HttpSessionBindingListener {


    public void valueBound(HttpSessionBindingEvent hsbl)  { 
    	HttpSession session = hsbl.getSession();
    	ServletContext sc = session.getServletContext();
    	int userCount = (int) sc.getAttribute("userCount");
    	userCount++;
    	System.out.println("用户登录"+userCount);
    	sc.setAttribute("userCount", userCount);
    }

    public void valueUnbound(HttpSessionBindingEvent hsbl)  { 
    	HttpSession session = hsbl.getSession();
    	ServletContext sc = session.getServletContext();
    	int userCount = (int) sc.getAttribute("userCount");
    	userCount--;
    	int count = (int) sc.getAttribute("count");
    	count--;
    	sc.setAttribute("userCount", userCount);
    	sc.setAttribute("count", count);
    }
	
}
