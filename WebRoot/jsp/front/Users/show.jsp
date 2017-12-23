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

<title>用户信息展示页面</title>
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
.tableBorder1{width:97%;border: 2px #DEDEDE solid; background-color: #FFDCA9;}
}
</style>

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
    <li>用户资料查看</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>用户资料查看</span></div>
    
  		<table align=center class=tableborder1>
		<tr>
			<td valign=middle align=center height=25 background="#FFDCA9" width="35px"><font ><b>序号</b></font></td>
			<td valign=middle align=center height=25 background="#FFDCA9" width=""><font ><b>用户名</b></font></td>
			<td valign=middle align=center height=25 background="#FFDCA9" width=""><font ><b>密码</b></font></td>
			<td valign=middle align=center height=25 background="#FFDCA9" width=""><font ><b>操作</b></font></td>
		</tr> 
		<%int i=1;%>     
        <c:forEach items="${users}" var="user">        
		<tr>
			<form method="post" action="Users/update" name="f1">
			<input type="hidden" name="id" value="${user.userId }">
			<td class=tablebody2 valign=middle align=center><%=i++ %></td>
			<td class=tablebody1 valign=middle align=center><input type="text" name="name" value="${user.userName}"></td>
			<td class=tablebody2 valign=middle align=center><input type="text" name="pswd" value="${user.userPassword}"></td>
			<td class=tablebody1 valign=middle align=center width="">
			<input type="button" value="删除" border="1 solid" onclick="javascript:window.location='Users/delete?bianhao=${user.userId}';"> 
			<input type="submit" value="保存修改" border="1 solid">
			</td>
			</form>
		</tr>
        </c:forEach> 
      </table>
    </div>
</body>
</html>
