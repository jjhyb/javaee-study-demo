<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function fun(that){
			that.src = "${pageContext.request.contextPath }/vcodeServlet?"+new Date();
		}
	</script>
</head>
<body>
	
	<div ><font color="red">${msg }</font></div>
	<form action="${pageContext.request.contextPath }/loginServlet" method="post">
		用户名:<input type="text" name="username" value="${cookie.username.value }"><br/>
		密码:<input type="password" name="password" value="${cookie.password.value }"><br/>
		验证码:<input type="text" name="vcode"><img src="${pageContext.request.contextPath }/vcodeServlet" onclick="fun(this)"><br>
		
		<%--如果username和password的值不为null,那么复选框checkbox的checked就为true --%>
		<c:if test="${not empty cookie.username.value&&not empty cookie.password.value }">
			记住用户名和密码:<input type="checkbox" name="remember" value="rem" checked="checked"><br/>
		</c:if>
		<%--如果username和password的值是null,那么复选框checkbox的checked就为false --%>
		<c:if test="${empty cookie.username.value&&empty cookie.password.value }">
			记住用户名和密码:<input type="checkbox" name="remember" value="rem" ><br/>
		</c:if>
		
		<input type="submit" value="登录">
	</form>
</body>
</html>