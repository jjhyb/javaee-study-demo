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
			that.src = "/day18_cookiesession/vcodeServlet?"+new Date();
		}
	</script>
</head>
<body>
	<%--
		String msg = (String)request.getAttribute("msg");
		if(msg == null){
			msg = "";
		}
		
		Cookie[] cookies = request.getCookies();
		String username = "";
		String password = "";
		if(cookies!=null){
			for(int x=0;x<cookies.length;x++){
				if("username".equals(cookies[x].getName())){
					username = cookies[x].getValue();
				}
				if("password".equals(cookies[x].getName())){
					password = cookies[x].getValue();
				}
			}
		}
		String check="";
		if(!"".equals(username) && !"".equals(password)){
			check = "checked='checked'";
		}
	--%>
	<div ><font color="red">${msg }</font></div>
	<form action="loginServlet" method="post">
		用户名:<input type="text" name="username" value="${cookie.username.value }"><br/>
		密码:<input type="password" name="password" value="${cookie.password.value }"><br/>
		验证码:<input type="text" name="vcode"><img src="/day18_cookiesession/vcodeServlet" onclick="fun(this)"><br>
		
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