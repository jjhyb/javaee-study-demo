<%@page import="java.net.URLDecoder"%>
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
		User user = (User)request.getAttribute("user");
		String nickname = null;
		if(user != null){
			nickname = user.getNickname();
		}
		String str = "这是你第一次登录！";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie : cookies){
				if("time".equals(cookie.getName()))
					str = cookie.getValue();
					//cookie的value中带有汉字,通过URLEncoder.encode()编码，
					//必须在浏览器中对获取到的value进行手动解码,用URLDecoder.decode()进行解码
					str = URLDecoder.decode(str, "UTF-8");
			}
		}
	%>
	
	<h1>欢迎<%=nickname %></h1>
	<h1><%=str %></h1>
</body>
</html>