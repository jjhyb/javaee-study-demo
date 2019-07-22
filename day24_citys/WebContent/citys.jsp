<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
		$(function(){
			$.getJSON("${pageContext.request.contextPath }/cityServlet",{method:"searchPro"},function(json){
				for(var i=0;i<json.length;i++){
					//创建option标签
					var option = $("<option>"+json[i].name+"</option>");
					//将遍历的json对象的id赋值给option标签中的value,便于以后传参
					option.prop("value",json[i].id);
					//将option标签添加到<select id="pro">中
					$("#pro").append(option);
				}
			});
			
			$("#pro").change(function(){
				//每一次触发该事件是，将<select id="citys">下面的子元素清空
				$("#citys").empty();
				//然后创建一个option请选择市标签
				$("#citys").append("<option>---请选择市---</option>");
				$.getJSON("${pageContext.request.contextPath }/cityServlet",{method:"searchCity",pid:this.value},function(json){
					for(var i=0;i<json.length;i++){
						//创建option标签
						var option = $("<option>"+json[i].name+"</option>");
						//将遍历的json对象的id赋值给option标签中的value,便于以后传参
						option.prop("value",json[i].id);
						//将option标签添加到<select id="pro">中
						$("#citys").append(option);
					}
				})
			});
		});
</script>
</head>
<body>
	<select id="pro">
		<option>---请选择省---</option>
	</select>
	<select id="citys">
		<option>---请选择市---</option>
	</select>
</body>
</html>