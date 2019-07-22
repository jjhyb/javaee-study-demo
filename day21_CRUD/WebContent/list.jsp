<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全部商品展示</title>
<script type="text/javascript">
	//声明一个函数，用于事件删除点击的操作,并将pid作为参数
	function fun(pid){
		//1.弹出一个确认框
		//javascript的Bom中的5大对象window,screen,hsitory,location,navigator
		var flag = confirm("您确定是要删除该商品吗？");
		if(flag){
			//确定要删除
			//要发送一个请求给服务器，到数据库服务器中删除指定商品信息
			//发送请求到服务器的方法:地址栏直接访问、超链接标签、表单提交
			//使用代码往服务器发送请求，用location发删除请求到服务器的DeleteServlet需要携带商品的pid
			location.href="${pageContext.request.contextPath}/productServlet?method=delete&pid="+pid;
		}
	}
</script>
</head>
<body>
	<table border="1" cellpadding="0" width="90%" align="center">
		<tr>
			<th>商品id</th>
			<th>商品图片</th>
			<th>商品名称</th>
			<th>市场价格</th>
			<th>商城价格</th>
			<th>商品描述</th>
			<th>更新时间</th>
			<th>商品操作</th>
		</tr>
		<c:forEach items="${list }" var="p">
		<tr>
			<td>${p.pid }</td>
			<td>
				<img  src="${p.pimage }" width="80" height="80">
			</td>
			<td>${p.pname }</td>
			<td>${p.market_price }</td>
			<td>${p.shop_price }</td>
			<td>${p.pdesc }</td>
			<td>${p.pdate }</td>
			<td width="80" height="80">
				<a href="#" onclick="fun('${p.pid}')">删除</a>
				<a href="${pageContext.request.contextPath }/productServlet?method=findProductById&pid=${p.pid }">修改</a>
			</td>
		</tr>
		</c:forEach>
		<a href="${pageContext.request.contextPath }/index.jsp">回到首页</a>
	</table>
</body>
</html>