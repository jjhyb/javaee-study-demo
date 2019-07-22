<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript">
/* $(function(){
		//alert(1);
		//发送10000个异步请求
		
			var int1;
			function fun1(){
				int1=window.setInterval("clock()",100);
				console.log(111);
			}
			$("#clock").click(fun1); */
			
/* 
	});
	function clock()
	{
		console.log(2222);
		for(var i=0;i<100;i++){
			 $.ajax({
	             url: "${pageContext.request.contextPath }/productServlet?method=findAll",
	             dataType: "text",
	             success: function(data){
	                      console.log(data);  
	                   }
	         });
		}
	} */
</script>
</head>
<body>
	<button id="clock"  >开始</button>
	<button onclick="int=window.clearInterval(int1)">停止</button>
</body>
</html>