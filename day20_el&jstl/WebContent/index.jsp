<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>作业一:对Servlet中传递的数据进行获取</h1>
	<a href="${pageContext.request.contextPath }/el/demo01.jsp">1.使用el表达式获取简单的字符串类型的数据</a><br>
	<a href="${pageContext.request.contextPath }/el/demo02.jsp">2.使用el表达式获取list类型的数据</a><br>
	<a href="${pageContext.request.contextPath }/el/demo03.jsp">3.使用el表达式获取数组类型的数据</a><br>
	<a href="${pageContext.request.contextPath }/el/demo04.jsp">4.使用el表达式获取map类型的数据</a><br>
	<a href="${pageContext.request.contextPath }/el/demo05.jsp">5.使用el表达式获取JavaBean对象</a><br>
	<a href="${pageContext.request.contextPath }/el/demo06.jsp">6.使用el表达式做非空判断</a><br>
	<h1>作业二:对Servlet中传递的数据进行判断和遍历</h1>
	<a href="${pageContext.request.contextPath }/jstl/demo01.jsp">1.使用jstl对数据进行判断</a><br>
	<a href="${pageContext.request.contextPath }/jstl/demo02.jsp">1.使用jstl对数据进行循环</a><br>
	<a href="${pageContext.request.contextPath }/jstl/demo03.jsp">1.使用jstl对数据进行判断,choose和when标签的使用</a><br>
	<a href="${pageContext.request.contextPath }/jstl/demo04.jsp">1.使用jstl对数据进行遍历，并封装到表格里</a><br>
	<a href="${pageContext.request.contextPath }/jstl/demo05.jsp">1.使用jstl对数据进行遍历，并封装到表格里——复杂方式</a><br>
</body>
</html>