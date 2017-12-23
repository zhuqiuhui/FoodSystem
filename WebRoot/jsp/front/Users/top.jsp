<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
</script>
</head>

<body style="background: url(images/topbg.gif) repeat-x;">

	<div class="topleft">
		<ul class="nav">
			<!-- <li><a href="Caipu/add" target="rightFrame"><img
					src="images/icon0.png" title="添加菜谱" />
					<h2>添加菜谱</h2></a></li> -->
			<li><a href="Caipu/list" target="rightFrame"><img
					src="images/icon5.png" title="营养菜谱" />
					<h2>营养菜谱</h2></a></li>
			<li><a href="TcmCaipu/list" target="rightFrame"><img
					src="images/icon5.png" title="中医菜谱" />
					<h2>中医菜谱</h2></a></li>
			<li><a href="Caipu/search" target="rightFrame"><img
					src="images/icon3.png" title="搜素菜谱" />
					<h2>搜素菜谱</h2></a></li>
			<li><a href="Caipu/compare" target="rightFrame"><img
					src="images/icon2.png" title="菜谱对比" />
					<h2>菜谱对比</h2></a></li>
			<li></li>
			<!-- <li><a href="Perdiet/my" target="rightFrame"><img
					src="images/icon09.png" title="添加饮食" />
					<h2>添加饮食</h2></a></li> -->
			<li><a href="Perdiet/list" target="rightFrame"><img
					src="images/icon07.png" title="营养饮食" />
					<h2>营养饮食</h2></a></li>
			<li><a href="TcmPerdiet/list" target="rightFrame"><img
					src="images/icon07.png" title="中医饮食" />
					<h2>中医饮食</h2></a></li>
			<!-- TODO -->
			<li><a href="Perdiet/search" target="rightFrame"><img
					src="images/icon08.png" title="搜索饮食" />
					<h2>搜索饮食</h2></a></li>
			<li><a href="Perdiet/compare" target="rightFrame"><img
					src="images/icon10.png" title="饮食曲线" />
					<h2>饮食曲线</h2></a></li>

		</ul>
	</div>

	<div class="topright">
		<ul>
			<li><span><img src="images/help.png" title="帮助"
					class="helpimg" /></span>帮助</li>
			<li>关于</li>
			<li><a href="Users/loginOut" target="_parent">退出</a></li>
		</ul>

		<div class="user">
			<span><s:property value="#session.user.userName" /></span> <i>消息</i>
			<b>0</b>
		</div>
	</div>

</body>
</html>
