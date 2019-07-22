<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/productServlet?method=add" method="post">
		<table>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="pname" ></td>
			</tr>
			<tr>
				<td>市场价格</td>
				<td><input type="text" name="market_price" ></td>
			</tr>
			<tr>
				<td>商城价格</td>
				<td><input type="text" name="shop_price" ></td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><input type="text" name="pimage" ></td>
			</tr>
			<tr>
				<td>商品描述</td>
				<td><input type="text" name="pdesc" ></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="添加"></td>
			</tr>
		</table>
	</form>
</body>
</html>