<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存款</title>
</head>
<body>
	<h3>欢迎:${user.username }</h3>
	<h3>余额:${user.money }</h3>
	<form action="depositServlet" method="post">
		存款金额:<input type="text" name="money" >
		<input type="hidden" name="type" value="1">
		<input type="submit" value="确定">
	</form>
</body>
</html>