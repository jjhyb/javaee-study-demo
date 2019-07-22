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
	<table border="1" cellpadding="0" heigth="300" width="500" align="center">
		<tr>
			<th>下标</th>
			<th>计数</th>
			<th>数据姓名</th>
			<th>数据年龄</th>
			<th>是否是第一个</th>
			<th>是否是最后一个</th>
		</tr>
			<c:forEach items="${list }" var="name" varStatus="vs">
				<tr>
					<td>${vs.index }</td>
					<td>${vs.count }</td>
					<%--下面这两行代码，本来石封装到vs对象中，但是通过vs对象无法取出 --%>
					<td>${name.name }</td>
					<td>${name.age }</td>
					<td>${vs.first }</td>
					<td>${vs.last }</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>