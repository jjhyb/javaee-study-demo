<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/productServlet?method=findAll">查看所有商品</a><br>
	<a href="${pageContext.request.contextPath }/add.jsp">添加商品</a><br>
	<form action="${pageContext.request.contextPath }/productServlet?method=page" method="post">
		<input type="hidden" name="curPage" value="1">
		每页显示的数据数:<input type="radio" name="pageSize" value="4" checked="checked">4
		<input type="radio" name="pageSize" value="6">6
		<input type="radio" name="pageSize" value="8">8
		<input type="radio" name="pageSize" value="10">10<br>
		<input type="submit" value="分页显示">
	</form>
</body>
</html>