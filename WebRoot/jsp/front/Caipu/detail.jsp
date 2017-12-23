<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">

<title>食材营养信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>


</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>菜谱信息</li>
		</ul>
	</div>
	<table class="tablelist">
		
			<thead>
				<tr>
					<th>${caipu.viewName}</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${caipu.guanxis}" var="gx">
				<tr>
					<td><a href="Yuanliao/detail?id=${gx.yuanliao.maId}"
						class="tablelink">${gx.yuanliao.mName}</a>: ${gx.amount}g</td>
				</tr>
				</c:forEach>
			</tbody>
		
	</table>
</body>
</html>
