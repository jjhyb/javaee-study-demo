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
	<center>
		<h2>
			<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
			<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
		</h2>
		<c:if test="${not empty user }">
			<h2>${user.username }:欢迎回来!</h2>
		</c:if>
	</center>
</body>
</html>