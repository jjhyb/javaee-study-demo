package com.yibo.mysession;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class MySessionContext {
	
	private static HashMap<String,HttpSession> map = new HashMap<>();

	public String str = "";
    public static synchronized void AddSession(HttpSession session) {
    	//session对象添加进map集合中
        if (session != null) {
            map.put(session.getId(), session);
        }
    }
    
    //将session从map中移除
    public static synchronized void DelSession(HttpSession session) {
    	//如果session不等于null将其移除
        if (session != null) {
            map.remove(session.getId());
        }
    }
    
    //通过id获取session
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
        	return null;
        return (HttpSession) map.get(session_id);
    }
}
