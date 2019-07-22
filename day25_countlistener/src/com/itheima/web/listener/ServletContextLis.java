package com.itheima.web.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

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
    	ServletContext sc = sce.getServletContext();
    	int count = 0;
    	int userCount = 0;
    	sc.setAttribute("count", count);
    	sc.setAttribute("userCount", userCount);
    	Map<String,Long> map = new HashMap<>();
    	sc.setAttribute("map", map);
    	
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				long time = new Date().getTime();
				
				Iterator<Entry<String, Long>> it = map.entrySet().iterator();
				while(it.hasNext()){
					Entry<String, Long> entry = it.next();
					if((time-entry.getValue())>4000){
						it.remove();
						int count = (int) sc.getAttribute("count");
						count--;
						sc.setAttribute("count", count);
					}
					
				}
				
				/*for (String sessionid : map.keySet()) {
					Long idTime = map.get(sessionid);
					if((time-idTime)>1000*10){
						map.remove(sessionid);
						int count = (int) sc.getAttribute("count");
						count--;
						sc.setAttribute("count", count);
					}
				}*/
			}
		}, new Date(), 1000*5);
    }
	
}
