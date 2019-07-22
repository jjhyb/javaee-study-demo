<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易记录</title>
</head>
<body>
		<h3>欢迎:${user.username }</h3>
		<h3>余额:${user.money }</h3>
		<table border="1" cellpadding="0" width="1000px" height="400" align="center">
			<tr>
				<th>流水号</th>
				<th>交易方</th>
				<th>交易类型</th>
				<th>交易金额</th>
				<th>交易目标账户</th>
				<th>交易日期</th>
			</tr>
			<c:forEach items="${list }" var="r">
				<tr>
					<td>${r.id }</td>
					<td>${r.userId }</td>
					<td>${r.type }</td>
					<td>${r.money }</td>
					<td>${r.targetUserId }</td>
					<td>${r.tradeDate }</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>