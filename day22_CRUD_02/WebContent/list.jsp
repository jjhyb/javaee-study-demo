<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全部商品展示</title>
<script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
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
	
	//声明一个全选和全不选功能
	function checkAll(obj){
		//这是事件不传参数的方法
		//$(".check").prop("checked",$("#checone").prop("checked"));
		//这是事件传参数的方法
		$(".check").prop("checked",obj.checked);
	}
	
	//声明一个方法完成子选框控制全选框的选中状态
	function fun2(obj){
		//定义一个计数器
		var count = 0;
		$(".check").each(function(index,element){
			if(!element.checked){
				$("#checone").prop("checked",false);
			}else {
				element.checked = true;
				count++;
			}
		});
		if(count == $(".check").length)
			$("#checone").prop("checked",true);
	}
	
	//声明一个反选功能方法
	function reverse(){
		/* $(".check").each(function(index,element){
			element.checked = !element.checked;
		}); */
		
		$(".check").click();
	}
	
	function deleteChecked(){
		var flag = confirm("您确定是要删除所选商品吗？");	
		if(flag){
			$("#fm").submit();
		}
	}
	
	
</script>
</head>
<body>
	<table border="1" cellpadding="0" width="90%" align="center">
		<tr>
			<td colspan="9">
				<form action="${pageContext.request.contextPath }/productServlet" method="post">
					<input type="hidden" name="method" value="search">
					商品名:<input type="text" name="pname">&nbsp;&nbsp;&nbsp;&nbsp;
					关键词:<input type="text" name="pdesc">
					<input type="submit" value="搜索">
				</form>
			</td>
		</tr>
		<tr>
			<td colspan="8">
				<input type="button" onclick="reverse()" value="反选">
			</td>
			<td >
				<input type="button" onclick="deleteChecked()" value="删除选中的商品">
			</td>
		</tr>
		<tr>
			<th>
				<input type="checkbox" id="checone" onclick="checkAll(this)">
			</th>
			<th>商品id</th>
			<th>商品图片</th>
			<th>商品名称</th>
			<th>市场价格</th>
			<th>商城价格</th>
			<th>商品描述</th>
			<th>更新时间</th>
			<th>商品操作</th>
		</tr>
		<form action="${pageContext.request.contextPath }/productServlet" method="post" id="fm">
		<input type="hidden" name = method value="deleteChecked">
		<c:forEach items="${list }" var="p">
		<tr>
			<td>
				
				<input type="checkbox" name="pid" value="${p.pid }" class="check" onclick="fun2(this)">
			</td>
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
		</form>
		<a href="${pageContext.request.contextPath }/index.jsp">回到首页</a>
	</table>
</body>
</html>