<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>My JSP 'left.jsp' starting page</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
			
			
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	
</script>
</head>

<body style="background: #fff3e1;">
	<div class="lefttop">
		<span></span>导航
	</div>

	<dl class="leftmenu">

		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>个人信息
			</div>
			<ul class="menuson">

				<li class="active"><cite></cite><a href="Users/password"
					target="rightFrame">修改密码</a><i></i></li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>日常菜谱
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a target="rightFrame">西医特性</a> <!-- 内容可以对近三天的饮食分析，三种疾病的偏向 -->
						<i></i>
					</div>
					<ul class="sub-menus">
					    <li><a href="Caipu/add" target="rightFrame">添加菜谱</a></li>	
					    <li><a href="Caipu/westRecognize" target="rightFrame">菜谱识别</a></li>
					</ul>
				</li>
				<li>
					<div class="header">
						<cite></cite> <a target="rightFrame">中医特性</a>
					</div>
					<ul class="sub-menus">
						<li><a href="TcmCaipu/add" target="rightFrame">添加菜谱</a></li>
						<li><a href="TcmCaipu/tcmRecognize" target="rightFrame">菜谱识别</a></li>
					</ul>
				</li>
			</ul>
		</dd>
		
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>个人饮食
			</div>
			<ul class="menuson">
				<li>
					<div class="header">
						<cite></cite> <a target="rightFrame">西医特性</a> <!-- 内容可以对近三天的饮食分析，三种疾病的偏向 -->
						<i></i>
					</div>
					<ul class="sub-menus">
					    <li><a href="Perdiet/my" target="rightFrame">添加个人饮食</a></li>
						<li><a href="Perdiet/bias" target="rightFrame">饮食偏向分析</a></li>
						<li><a href="Caipuauc/recommend" target="rightFrame">近期饮食推荐</a></li>
						<!-- <li><a href="Caipuauc/save" target="rightFrame">生成菜谱auc</a></li> -->
					</ul>
				</li>
				<li>
					<div class="header">
						<cite></cite> <a target="rightFrame">中医特性</a> <i></i>
					</div>
					<ul class="sub-menus">
						<li><a href="TcmPerdiet/add" target="rightFrame">添加个人饮食</a></li>
						<li><a href="TcmPerdiet/bias" target="rightFrame">饮食偏向分析</a></li>
						<li><a href="TcmCaipuauc/recommend" target="rightFrame">近期饮食推荐</a></li>
					</ul>
				</li>
			</ul>
		</dd>
	</dl>

</body>
</html>
