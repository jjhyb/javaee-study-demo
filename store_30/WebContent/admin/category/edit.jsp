<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
		
	</HEAD>
	<script type="text/javascript">
		$(function(){
			$("#but1").click(function(){
				if($("#inp").value != null && this.value != ""){
					$("#for_id").submit();
					return true;
				}else {
					return false;
				}
			})
		})
	</script>
	<body>
		<form id="for_id" name="Form1" action="${pageContext.request.contextPath}/adminCategory" method="post">
			<input type="hidden" name="method" value="edit">
			<input type="hidden" name="cid" value="${cg.cid }">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>编辑一级分类</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						一级分类名称：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="cname" id="inp" class="bg" value="${cg.cname }"/>
						</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="width: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<input type="submit"  value="确定" class="button_ok" id="but1">
						

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<input type="reset" value="重置" class="button_cancel" id="but2">

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>