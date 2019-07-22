<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/userServlet" method="post">
		<input type="hidden" name="method" value="register">
		用户名:<input type="text" name="username"><br/>
		密码:<input type="password" name="password"><br/>
		性别:<input type="radio" name="sex" value="male">男
		<input type="radio" name="sex" value="female">女<br/>
		昵称:<input type="text" name="nickname"><br/>
		邮箱:<input type="text" name="email"><br/>
		生日:<input type="text" name="birthday"><br/>
		兴趣爱好:<input type="checkbox" name="hobby" value="basketball">篮球
		<input type="checkbox" name="hobby" value="football">足球
		<input type="checkbox" name="hobby" value="yumaoball">羽毛球
		<input type="checkbox" name="hobby" value="pingpang">乒乓球<br/>
		<input type="submit" value="注册">
	</form>
</body>
</html>