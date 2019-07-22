<%@page import="java.util.ArrayList"%>
<%@page import="com.itheima.domain.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		List<User> list = new ArrayList<User>();
		list.add(new User("张无忌",25));
		list.add(new User("令狐冲",27));
		list.add(new User("段誉",24));
		list.add(new User("虚竹",23));
		request.setAttribute("list", list);
	%>
	<table border="1" cellspacing="0" width="400" height="400" align="center">
		<tr>
			<th>姓名</th>
			<th>年龄</th>
		</tr>
		<c:forEach items="${list }" var="name">
			<tr>
				<td>${name.name }</td>
				<td>${name.age }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>