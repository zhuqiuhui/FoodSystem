<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
    
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
     <form id="form1" name="form1" method="post" action="Users/regSubmit">
     
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
              								<tr>
                								<td width="29%" height="22"><span class="STYLE4">用户名</span></td>
                								<td width="71" height="22">
													<input class="input" tabindex="1" type="text" style="width:100px; height:16px; border:solid 1px #000000; color:#666666" name="users.userName" />
												</td>
              								</tr>
              								<tr>
                								<td height="22"><span class="STYLE4">密&nbsp;&nbsp;码</span></td>
                								<td height="22">
													<input class="input" tabindex="2" type="password" style="width:100px; height:16px; border:solid 1px #000000; color:#666666" name="users.userPassword" />
												</td>
              								</tr>
              								<tr>
                								<td height="25">&nbsp;</td>
                								<td height="25">
													<button type="submit" style="width:42px; height:22px " >提交</button>
													<button type="reset" style="width:42px; height:22px " >重置</button>
												</td>
                							</tr>
            							</table>
     
     </form>
  </body>
</html>
