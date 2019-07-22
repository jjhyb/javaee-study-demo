<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("cou", "java");
	%>
	<c:choose>
		<c:when test="${cou == 'python' }">
			<h1>学习人工智能</h1>
		</c:when>
		<c:when test="${cou == 'c++' }">
			<h1>c++</h1>
		</c:when>
		<c:when test="${cou == 'java' }">
			<h1>学习java</h1>
		</c:when>
		<c:otherwise>
			<h1>其他编程语言</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>