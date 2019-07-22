<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#input{
		width:500px;
		height:40px;
		font-size:20px;
		border-radius:6px;
	}
	#bt{
		width:80px;
		height:47px;
		font-size: 15px;
		border-radius:6px;
	}
	#dv1{
		margin: 100px;
	}
	
	#dv2{
		margin: 100px;
		
	}
	
	#pro{
		width:160px;
		height:40px;
		font-size: 18px;
		align:center;
		border-radius:6px;
	}
	#city{
		width:100px;
		height:40px;
		font-size: 18px;
		border-radius:6px;
	}
	
	#dis{
		width:100px;
		height:40px;
		font-size: 18px;
		border-radius:6px;
	}
	#dv3{
		width:500px;
		height:100px;
		/* font-size:20px; */
		border-radius:6px;
		border: groove;
		margin-left: 383px;
	}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	$(function(){
		$.getJSON("${pageContext.request.contextPath }/linkServlet",{method:"searchPro"},function(json){
			for(var i=0;i<json.length;i++){
				var option = $("<option>"+json[i].pname+"</option>");
				option.prop("value",json[i].id);
				$("#pro").append(option);
			}
		})
		
		$("#pro").change(function(){
			$("#city").empty();
			$("#city").append("<option>请选择市</option>");
			$.getJSON("${pageContext.request.contextPath }/linkServlet",{method:"searchCity",pid:this.value},function(json){
				for(var i=0;i<json.length;i++){
					var option = $("<option>"+json[i].cname+"</option>");
					option.prop("value",json[i].id);
					$("#city").append(option);
				}
			})
		})
		
		$("#city").change(function(){
			$("#dis").empty();
			$("#dis").append("<option>请选择县</option>");
			$.getJSON("${pageContext.request.contextPath }/linkServlet",{method:"searchDis",cid:this.value},function(json){
				for(var i=0;i<json.length;i++){
					var option = $("<option>"+json[i].dname+"</option>");
					option.prop("value",json[i].id);
					$("#dis").append(option);
				}
			})
		})
	})
	
	$(function(){
		//页面一加载div3隐藏
		$("#dv3").hide();
		//通过on方法给input动态注册事件
		$("#input").on("keyup",function(){
			
			//清空table里面的子元素
			$("#tb").html("");
			//显示div3
			$("#dv3").show();
			//通过this拿到搜索框中的value,去除前后空格后并判断值是否是空字符串
			if(this.value.trim() != ""){
				//如果不是进行异步请求
				$.getJSON("${pageContext.request.contextPath }/linkServlet",{method:"search",name:this.value},function(json){
					for(var i=0;i<json.length;i++){
						if(i>=4){
							break;
						}
						$("#tb").append("<tr><td>"+json[i][0]+"---"+json[i][1]+"---"+json[i][2]+"</td></tr>");
					}
				})
				
			}
			
		})
		
		//如果元素失去焦点，dv3隐藏
		$("#input").on("blur",function(){
			$("#dv3").hide();
		})
		
		//如果元素失去焦点，dv3隐藏
		$("#input").on("focus",function(){
			$("#dv3").show();
		})
	}) 
	
	
</script>
</head>
<body>
<center>
	<h1>义波一下&nbsp;&nbsp;&nbsp;&nbsp;你就知道</h1>
</center>
<center>
	<div id="dv1" >
		<select id="pro" >
			<option>----请选择省----</option>
		</select>
		<select id="city">
			<option>请选择市</option>
		</select>
		<select id="dis">
			<option>请选择县</option>
		</select><br>
	</div>
</center>

<center>
		<input type="text" name="name" id="input"><input type="button" value="义波一下" id="bt">
</center>

	<div id="dv3">
		<table id="tb" border="0" width="100%">
			
		</table>
	</div>
</body>
</html>