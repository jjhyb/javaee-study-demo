<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员注册</title>
<style type="text/css">
	#di{
		width:172px;
		background-color:#FFC0CB;
		margin-left: 53px;
	}
	#sp{
		background-color:#00FF00;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
<script type="text/javascript">
	function fun1(obj){
		$("#di1").html("");
		$("#sp1").html("");
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		xmlhttp.open("POST","${pageContext.request.contextPath }/checkUserServlet");
		
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		
		xmlhttp.send("username="+obj.value);
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				if("此用户名已存在！"==xmlhttp.responseText){
					document.getElementById("di").innerHTML = xmlhttp.responseText;
				}else {
					document.getElementById("sp").innerHTML = xmlhttp.responseText;
				}
				
			}
		}
	}
	function fun2(obj){
		$("#di2").html("");
		$("#sp2").html("");
		$post()
	}
	
	$(function(){
		$("#forid").validate({
			rules:{
				username:{
					required:true
				},
				password:{
					required:true,
					rangelength:[6,16]
				},
				email:{
					required:true,
					email:true
				},
				phone:{
					required:true,
					rangelength:[11,11]
				}
			},
			messages:{
				username:{
					required:"用户名不能为空"
				},
				password:{
					required:"密码不能为空",
					rangelength:"密码为6~16位"
				},
				email:{
					required:"邮箱不能为空",
					email:"请输出正确的邮箱地址"
				},
				phone:{
					required:"手机号不能为空",
					rangelength:"手机号为11位数字"
				}
			}
		});
	});
	
</script>
</head>
<body>
	<h1>会员注册</h1>
	<form action="" method="post" id="forid">
		用户名:<input type="text" name="username" placeholder="用户名不能为空" onblur="fun1(this)"><span id="sp1"></span><br>
		<div id="di1"></div>
		密码:<input type="password" name="password" placeholder="密码长度为6~16位" onblur="fun2(this)"><span id="sp2"><br>
		<div id="di2"></div>
		email:<input type="text" name="email" placeholder="邮箱不能为空" onblur="fun3(this)"><span id="sp3"><br>
		<div id="di3"></div>
		phone:<input type="text" name="phone" placeholder="手机号不能为空" onblur="fun4(this)"><span id="sp4"><br>
		<div id="di4"></div>
		<input type="submit" value="注册" ><br>
	</form>
</body>
</html>