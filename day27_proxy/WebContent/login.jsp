<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	$(function(){
		$("#input1").click(function(){
			if($("#input1").prop("checked"))
				$("#input2").prop("checked",$("#input1").prop("checked"));
		});
		
		$("#input2").click(function(){
			if(!$("#input2").prop("checked"))
				$("#input1").prop("checked",$("#input2").prop("checked"));
		});
	})
	
</script>

</head>
<body>
	<h1>义波一下，你就知道</h1>
	<div>${msg }</div>
	<form action="${pageContext.request.contextPath }/userServlet" method="get">
		<!-- <input type="hidden" name="method" value="login"> -->
		用户名:<input type="text" name="username" value="${username }"><br/>
		密码:<input type="password" name="password" value="${password }"><br/>
		
		<%--如果auto的值不为null,那么复选框checkbox的checked就为false --%>
		<c:if test="${not empty cookie.info }">
		自动登录:<input type="checkbox" name="auto" value="at" checked="checked">
		记住用户名和密码:<input type="checkbox" name="remember" value="rem" checked="checked">
		</c:if>
		
		<%--如果auto的值是null,那么复选框checkbox的checked就为true --%>
		<c:if test="${empty cookie.info }">
		自动登录:<input type="checkbox" name="auto" value="at" id="input1">
			
			<%--如果username和password的值不为null,那么复选框checkbox的checked就为true --%>
			<c:if test="${not empty cookie.username.value&&not empty cookie.password.value }">
			记住用户名和密码:<input type="checkbox" name="remember" value="rem" checked="checked">
			</c:if>
			
			<%--如果username和password的值是null,那么复选框checkbox的checked就为false --%>
			<c:if test="${empty cookie.username.value&&empty cookie.password.value }">
			记住用户名和密码:<input type="checkbox" name="remember" value="rem" id="input2">
			</c:if>
		</c:if>'
		
		<br/>
		<input type="submit" value="登录">
	</form>
</body>
</html>