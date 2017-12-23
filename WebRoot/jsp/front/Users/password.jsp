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

	<link href="css/style.css" rel="stylesheet" type="text/css" />
    <title>My JSP 'password.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">修改密码</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>修改密码</span></div>
    
    <ul class="forminfo">	 
    <li><label>旧密码</label><input name="" type="password" class="dfinput" /><i></i></li>
    <li><label>新密码</label><input name="" type="password" class="dfinput" /><i></i></li>
	<li><label>确认密码</label><input name="" type="password" class="dfinput" /><i></i></li>
	 <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="确认修改"/></li>
	 
    </ul>  
    </div>
  </body>
</html>
