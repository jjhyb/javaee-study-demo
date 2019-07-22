<%@page import="com.yibo.mysession.MySessionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<!-- 一访问就直接从jsp页面内置的session中获取其id -->
		<%
			/* String jsessionid = "";
			if("" == jsessionid){
				jsessionid = session.getId();
				session.setAttribute("sesisonid", jsessionid);
			} */
			String sesisonid=session.getId();
			session.setAttribute("sesisonid", sesisonid); 
			
		%>

		<button onclick="fun1()">点击跳转</button>
		
	<!-- 用js判断浏览器是否禁用Cookie
	navigator.cookieEnabled 该属性是一个只读的布尔值
	如果浏览器启用了cookie，该属性值为true。如果禁用了cookie，则值为false -->
	
		<script type="text/javascript">
			function fun1(){
				if (navigator.cookieEnabled){
					alert("浏览器允许使用Cookie");
				}else{
					alert("浏览器禁用Cookie");
					location.href="${pageContext.request.contextPath}/acountServlet?jsessionid=${jsessionid}";
				}
			}
		</script>
</body>
</html>