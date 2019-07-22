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

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	$(function(){
		
		var inte = setInterval(function(){
			$.getJSON("${pageContext.request.contextPath }/countServlet",{msg:"msg"},function(json){
				alert(json);
				if(json != "[]"){
					clearTimeout(inte);
				}
			});
		}, 1000);
	})
</script>
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
	
	
	<h1>欢迎${user.nickname }</h1>
	<h1><%=str %></h1>
	<h1>当前在线人数:${count }</h1>
	<h1>当前登录人数:${userCount }</h1>
	<h1>当前游客人数:${count-userCount }</h1>
	<h1><a href="${pageContext.request.contextPath }/deleteServlet">退出登录</a></h1>
</body>
</html>