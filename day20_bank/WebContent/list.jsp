<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function(){
		document.getElementById("bt1").onclick = function(){
			location.href="/day20_bank/deposit.jsp";
		};
		document.getElementById("bt2").onclick = function(){
			location.href="/day20_bank/withdraw.jsp";
		};
		document.getElementById("bt3").onclick = function(){
			location.href="/day20_bank/transfer.jsp";
		};
		document.getElementById("bt4").onclick = function(){
			location.href="/day20_bank/recordServlet";
		};
	};
</script>
</head>
<body>
	<h3>欢迎:${user.username }</h3>
	<h3>你的账户余额为:${user.money }</h3>
	<input type="button" value="存款" id="bt1">
	<input type="button" value="取款" id="bt2">
	<input type="button" value="转账" id="bt3">
	<input type="button" value="明细" id="bt4">
</body>
</html>