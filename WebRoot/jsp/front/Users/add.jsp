<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/text.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<title>用户添加页面</title>
<sx:head/>
<style type="text/css">
#div1 {
	border: 1px solid grey;
	width: 200px;
	height: 300px;
	overflow: auto;
	text-align: left;
	background-color: #ffffff;
	padding: 6px;
	position: absolute;
	z-index: 99;
	display: none; /*使div初始化时隐藏*/
}
#interestArea{
	border:1px solid #fff;
	width: 300px;
	display: none;
	display: block;
	position: absolute;
	background: #fff;
	left: 130px;
}
table{
	min-width: 200px;
}
</style>
<script type="text/javascript">
	if (window.attachEvent)
		window.attachEvent("onload", navHover);

	//input获取焦点事件
	function f() {
		//input获取焦点时在其旁边显示div
		$('#input1').click(
				function() {
					var input = $('#input1');
					var offset = input.offset();
					//先后设置div的位置，最后显示出来（渐进效果）
					//第一个.css设置div1在当前窗口的相对偏移(左偏移)，左偏移与input1一样
					//第二个.css设置div1在当前窗口的相对偏移(顶偏移)，在input1顶偏移的基础上再向下偏移input1的高度+4
					//注：相对偏移只有left和top
					//第三个.cc设置div1的宽度为input1宽度-10
					//.fadeIn是渐进效果，也就是渐渐展开div1，详见jquery.js文档
					$('#div1').css('left', offset.left + 'px').css('top',
							offset.top + input.height() + 4 + 'px').css(
							'width', input.width() - 10 + 'px').fadeIn();
				});
	}
	function checkMe() {
   if(document.form1.userName.value == "") {
          alert("请输入用户名！");
          document.login.userid.focus();
          return false;
    }
    if(document.form1.password.value == "") {
          alert("请输入用户名密码！");
          document.login.password.focus();
          return false;
    }
    if(document.form1.pswd2.value == "") {
		alert("请再次输入密码以确认!");
		document.reg.password2.focus();
		return false;
	}
	if(document.form1.password.value != document.form1.pswd2.value) {
		alert("您二次密码输入不一致!");
		document.reg.password.value = "";
		document.reg.password2.value = "";
		document.reg.password.focus();
		return false;
	}
	document.form1.submit();
}
	
</script>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>首页</li>
    <li>添加用户</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>用户添加</span></div>
    <form id="form1" name="form1" action="Users/addac" method="post">
    <ul class="forminfo">	 
    <li><label>用户登录名</label><input id="viewName" name="userName" type="text" class="dfinput" /><i></i></li>
	<li><label>登陆密码</label><input id="pswd" type="password" class="dfinput" name="password"/><i></i></li>
	<li><label>确认密码 </label><input type="password" class="dfinput"  name="pswd2" id="pswd2"/></li>
	<li><label>&nbsp;</label><table id=""></table></li>
    <li><label>&nbsp;</label><input type="button" class="btn" value="提交" onclick="javascript:checkMe()"/></li>
    </ul>
	 </form>
      
    </div>
</body>
</html>
