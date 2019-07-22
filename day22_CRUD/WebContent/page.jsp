<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	
</style>
<title>分页商品展示</title>
	<script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
	<script>
		
		function fun(pid){
			//弹出一个确认框
			var flag = confirm("您是否要删除该商品!");
			if(flag){
				//确定要删除
				location.href="${pageContext.request.contextPath}/productServlet?method=delete&pid="+pid;
			}
		}
		
		//获取所有button节点
		$(function(){
			$(".bt").each(function(index){
				//先将所有的按钮背景样式置空
				this.onclick = function(){
					var curPage = $(this).attr("curPage");
					location.href="${pageContext.request.contextPath}/productServlet?method=page&pageSize=${pb.pageSize }&curPage="+curPage;
				};
			});
		});
		
		$(function(){
			//通过curPage=${pb.curPage}属性获取分页显示按钮节点对象
			//在对该节点设置样式
			$("[curPage=${pb.curPage}]").css("background","	#A0A0A0");
		});
	</script>
</head>
<body>
	<table border="1" cellpadding="0" width="80%" align="center">
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
		<c:forEach items="${pb.list }" var="p">
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
				<td width="80">
					<a href="#" onclick="fun('${p.pid }')">删除</a>
					<a href="${pageContext.request.contextPath }/productServlet?method=findProductById&pid=${p.pid }">修改</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="8" align="center">
				当前显示为第${pb.curPage }页|每页显示${pb.pageSize }条数据|总页数${pb.totalPage }|总共有${pb.totalSize }条数据
			</td>
		</tr>
		<tr>
			<td colspan="8" align="center">
				<c:if test="${pb.curPage!=1 }">
					<input type="button" value="首页" curPage="1" class="bt">
					<input type="button" value="上一页" curPage="${pb.curPage-1 }" class="bt">
				</c:if>
				
				<c:if test="${pb.totalPage<=10 }">
					<c:forEach begin="1" end="${pb.totalPage }" var="i">
						<input type="button" value="${i }" curPage="${i }" class="bt">
					</c:forEach>
				</c:if>
				
				
				<c:if test="${pb.totalPage>10 }">
					<c:if test="${pb.curPage<7 }">
						<c:forEach begin="1" end="10" var="i">
							<input type="button" value="${i }" curPage="${i }" class="bt">
						</c:forEach>
					</c:if>
					
					<c:if test="${pb.curPage>=7 && pb.curPage<(pb.totalPage-4) }">
						<c:forEach begin="${pb.curPage-5}" end="${pb.curPage+4}" var="i">
							<input type="button" value="${i }" curPage="${i }" class="bt">
						</c:forEach>
					</c:if>
					
					<c:if test="${pb.curPage<=pb.totalPage && pb.curPage>=(pb.totalPage-4) }">
						<c:forEach begin="${pb.totalPage-10}" end="${pb.totalPage }" var="i">
							<input type="button" value="${i }" curPage="${i }" class="bt">
						</c:forEach>
					</c:if>
				</c:if>
					<%-- <c:forEach begin="1" end="${pb.totalPage }" var="i">
						<input type="button" value="${i }" curPage="${i }" class="bt">
					</c:forEach> --%>
				<c:if test="${pb.curPage!=pb.totalPage }">
					<input type="button" value="下一页" curPage="${pb.curPage+1 }" class="bt">
					<input type="button" value="尾页" curPage="${pb.totalPage }" class="bt">
				</c:if>
			</td>
		</tr>
		<a href="${pageContext.request.contextPath }/index.jsp">回到首页</a>
	</table>
</body>
</html>