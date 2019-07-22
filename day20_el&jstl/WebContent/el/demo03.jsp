<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String[] arr = {"张无忌","令狐冲","段誉","韦小宝"};
		request.setAttribute("arr", arr);
	%>
	3.使用el表达式获取数组类型的数据,第3个元素的数据:${arr[2] }
</body>
</html>