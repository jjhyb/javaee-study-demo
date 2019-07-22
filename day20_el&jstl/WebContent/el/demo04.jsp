<%@page import="java.util.HashMap"%>
<%@page import="com.itheima.domain.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Map<String,User> map = new HashMap<String,User>();
		map.put("user1", new User("张无忌",23));
		map.put("user2", new User("令狐冲",25));
		map.put("user3", new User("段誉",22));
		request.setAttribute("map", map);
	%>
	4.使用el表达式获取map类型的数据,获取key为"2"的人的姓名:${map['user2'] }
</body>
</html>