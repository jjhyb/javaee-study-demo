<%@page import="com.itheima.domain.User"%>
<%@page import="java.util.HashMap"%>
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
		String str = "";
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("jay", "周杰伦");
		
		User user = null;
		request.setAttribute("str", str);
		request.setAttribute("map", map);
		request.setAttribute("user", user);
	%>
	6.使用el表达式做非空判断<br>
	判断字符串是否为空字符串:${empty str }
	判断字符串是否为空字符串:${empty map }
	判断字符串是否为空字符串:${empty user }
	
</body>
</html>