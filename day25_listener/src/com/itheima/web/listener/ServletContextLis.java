package com.itheima.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ServletContextLis
 *
 */
public class ServletContextLis implements ServletContextListener {


    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext servletContext = sce.getServletContext();
    	int count = 0;
    	servletContext.setAttribute("count", count);
    }
}
