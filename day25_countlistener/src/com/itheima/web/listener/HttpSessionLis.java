package com.itheima.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class HttpSessionLis
 *
 */
public class HttpSessionLis implements HttpSessionListener {


    public void sessionCreated(HttpSessionEvent hse)  { 
    	HttpSession session = hse.getSession();
    	ServletContext sc = session.getServletContext();
    	int count = (int) sc.getAttribute("count");
    	count++;
    	sc.setAttribute("count", count);
    }
    public void sessionDestroyed(HttpSessionEvent hse)  { 
    	/*HttpSession session = hse.getSession();
    	ServletContext sc = session.getServletContext();
    	int count = (int) sc.getAttribute("count");
    	count--;
    	sc.setAttribute("count", count);*/
    }
	
}
