<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#input1{
		width:500px;
		height:40px;
		font-size:20px;
	}
	#input2{
		width:80px;
		height:47px;
	}
	#dv1{
		width:500px;
		height:100px;
		border: 1px #E6E6FA solid;
		position: absolute;
		margin-left: 382px;
	}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
	 /* function fun(obj){
		$("#tb").html("");
		if(obj.value.trim()!=""){
			$("#dv1").show();
			$.getJSON("${pageContext.request.contextPath }/searchServlet",{msg:obj.value},function(json){
				for(var i=0;i<json.length;i++){
					if(i>=4){
						break;
					}
					$("#tb").append("<tr><td>"+json[i].word+"</td></tr>");
				}
			})
		}
	}  */
	
	
	
	$(function(){
		//先隐藏div
		$("#dv1").hide();
		//通过jquery的on()方法动态注册keyup事件
		$("#input1").on("keyup",function(){
			//keyup执行的时候先清空table里面的元素节点
			$("#tb").html("");
			//接着显示div
			$("#dv1").show();
			//判断输入框中的value在去除前后空格之后的值,如果不为空字符串则进行ajax异步处理
			if(this.value.trim()!=""){
				//jquery的ajax进行异步处理
				$.getJSON("${pageContext.request.contextPath }/searchServlet",{msg:this.value},function(json){
					//遍历结果集
					for(var i=0;i<json.length;i++){
						//如果角标为4的话跳出循环,因为搜索框只显示4条异步请求信息
						if(i>=4){
							break;
						}
						//然后动态的向table中添加元素
						$("#tb").append("<tr><td>"+json[i].word+"</td></tr>");
					}
				})
			}
		});
		
		//通过query的on()方法动态的添加失去焦点事件
		$("#input1").on("blur",function(){
			//如果失去焦点，则隐藏div
			$("#dv1").hide();
		});
		
		//通过query的on()方法动态的添加获得焦点事件
		$("#input1").on("focus",function(){
			//如果获得焦点，则显示div及异步请求数据
			$("#dv1").show();
		});
		
	})
	
</script>
</head>
<body>
<center>
<!-- onkeyup="fun(this)" onblur="fun2()" -->
	<h1>义波</h1>
	<input type="text"  id="input1">
	<input type="button" value="义波一下" id="input2">
	<div id="dv1">
		<table border="0" width="100%" id="tb">
		</table>
	</div>
</center>
</body>
</html>