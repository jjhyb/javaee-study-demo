<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	
	<c:if test="${empty user }">
		<h1>欢迎来到义波首页</h1>
		<a href="${pageContext.request.contextPath }/register.jsp">注册</a>
		<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
	</c:if>
	
	<c:if test="${not empty user }">
		<h3>欢迎${user.nickname }重新回到义波首页</h3>
		<a href="${pageContext.request.contextPath }/bbs.jsp">发帖</a>
		<a href="${pageContext.request.contextPath }/userServlet?method=cart">购物车</a>
		<a href="${pageContext.request.contextPath }/userServlet?method=logout">退出登录</a>
	</c:if>
</body>
</html>