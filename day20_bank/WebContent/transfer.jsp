<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转账</title>
</head>
<body>
		<h3>欢迎:${user.username }</h3>
		<h3>余额:${user.money }</h3>
		<form action="transferServlet" method="post">
			转入账户:<input type="text" name="username"><br>
			转账金额:<input type="text" name="money" ><br>
			<input type="hidden" name="type" value="3">
			<input type="submit" value="确定">
		</form>
</body>
</html>