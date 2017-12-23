<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入食疗养生</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎进入食疗养生</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    <form id="form1" action="Users/loginSubmit" name="loginForm" method="post">
    <div class="loginbody">
    
    <div class="systemlogonopicture"></div> 
       
    <div class="loginbox">
    
    <ul>
    <li><input name="users.userName" type="text" class="loginuser" onclick="JavaScript:this.value=''"/></li>
    <li><input name="users.userPassword" type="password" class="loginpwd"  onclick="JavaScript:this.value=''"/></li>
    <li><input name="submit" type="submit" class="loginbtn" value="登录"   /><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
    </ul>
    
    
    </div>
    
    </div>           
    <div class="loginbm">  </div>
</from>	    
</body>
</html>
