<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
		List<String> list = new ArrayList<String>();
		list.add("张三丰");
		list.add("张无忌");
		list.add("令狐冲");
		list.add("段誉");
		request.setAttribute("list", list);
	%>
	2.使用el表达式获取list类型的数据第2个元素:${list[2] }
</body>
</html>