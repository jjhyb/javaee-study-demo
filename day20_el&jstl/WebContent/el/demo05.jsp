<%@page import="com.itheima.domain.User"%>
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
		User user1 = new User("张无忌",25);
		User user2 = new User("令狐冲",23);
		User user3 = new User("段誉",28);
		request.setAttribute("user1",user1);
		request.setAttribute("user2",user2);
		request.setAttribute("user3",user3);
	%>
	5.使用el表达式获取JavaBean对象,获取"user2"的姓名:${user2.name }
</body>
</html>