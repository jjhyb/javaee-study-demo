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
						<img src="img/hot/small02.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>显示器</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small03.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>电炖锅</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>

				<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small04.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>冬瓜</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>
				
				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small05.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>圆白菜</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small06.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>洗衣机</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>

				<div class="col-md-2 yes-right-border" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small07.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>有机大黄瓜</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>
				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small08.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>高压锅</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small09.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>电烤箱</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>

				<div class="col-md-2" style="text-align:center;height:200px;padding:10px 0px;">
					<a href="product_info.htm">
						<img src="img/hot/small01.jpg" width="130" height="130" style="display: inline-block;">
					</a>
					<p><a href="product_info.html" style='color:#666'>榨汁机</a></p>
					<p><font color="#E4393C" style="font-size:16px">&yen;299.00</font></p>
				</div>
			</div>
		</div>
	</body>
</html>
