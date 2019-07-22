<%@page import="java.util.List"%>
<%@page import="com.itheima.domain.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%--这时使用的include指令进行的静态包含 --%>
<%-- <%@ include file="header.jsp" %> --%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>商城首页</title>

		<!-- 引入Bootstrap核心样式文件 -->
		<link rel="stylesheet" href="css/bootstrap.css" />
		<!-- 引入jQuery核心js文件 -->
		<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
		<!-- 引入BootStrap核心js文件 -->
		<script type="text/javascript" src="js/bootstrap.js"></script>
	</head>
	<body>
	<%
		Product p1 = (Product)request.getAttribute("1");
		Product p2 = (Product)request.getAttribute("2");
		Product p3 = (Product)request.getAttribute("3");
		Product p4 = (Product)request.getAttribute("4");
		Product p5 = (Product)request.getAttribute("5");
		Product p6 = (Product)request.getAttribute("6");
		Product p7 = (Product)request.getAttribute("7");
		Product p8 = (Product)request.getAttribute("8");
		Product p9 = (Product)request.getAttribute("9");
		
		
	%>
	<%--这是使用include标签进行的动态包含 --%>
	<jsp:include page="header.jsp"></jsp:include>
		<div class="container-fluid">
			<div class="col-md-12">
				<h2>热门商品&nbsp;&nbsp;<img src="img/hot/title2.jpg"/></h2>
			</div>
			<div class="col-md-2" style="border:1px solid #E7E7E7;border-right:0;padding:0;">
				<img src="img/hot/big01.jpg" width="205" height="404" style="display: inline-block;"/>
			</div>
			<div class="col-md-10">
				<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">
					<a href="product_info.htm">
						<img src="img/hot/middle01.jpg" width="516px" height="200px" style="display: inline-block;">
					</a>
				</div>
			
				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p1.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p1.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p1.getShop_price() %></font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p2.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p2.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p2.getShop_price() %></font></p>
				</div>

				<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p3.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p3.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p3.getShop_price() %></font></p>
				</div>
				
				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p4.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p4.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p4.getShop_price() %></font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p5.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p5.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p5.getShop_price() %></font></p>
				</div>

				<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p6.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p6.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p6.getShop_price() %></font></p>
				</div>
				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p7.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p7.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p7.getShop_price() %></font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p8.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p8.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p8.getShop_price() %></font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="<%=p9.getPimage() %>" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'><%=p9.getPname() %></a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;<%=p9.getShop_price() %></font></p>
				</div>
			</div>
		</div>
	</body>
</html>
