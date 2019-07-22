package com.itheima.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Application Lifecycle Listener implementation class ServletRequestLis
 *
 */
public class ServletRequestLis implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre)  { 
    
    }

    public void requestInitialized(ServletRequestEvent sre)  { 
    	ServletRequest servletRequest = sre.getServletRequest();
    	ServletContext servletContext = servletRequest.getServletContext();
    	int count = (int) servletContext.getAttribute("count");
    	count++;
    	servletContext.setAttribute("count", count);
    }
	
}
