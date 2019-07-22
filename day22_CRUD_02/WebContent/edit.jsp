<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/productServlet?method=edit" method="post">
		<input type="hidden" name="pid" value="${p.pid }">
		<table>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="pname" value="${p.pname }"></td>
			</tr>
			<tr>
				<td>市场价格</td>
				<td><input type="text" name="market_price" value="${p.market_price }"></td>
			</tr>
			<tr>
				<td>商城价格</td>
				<td><input type="text" name="shop_price" value="${p.shop_price }"></td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><input type="text" name="pimage" value="${p.pimage }"></td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><input type="text" name="pdesc" value="${p.pdesc }"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="更新"></td>
			</tr>
		</table>
	</form>
</body>
</html>