<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>健康养生系统界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <frameset rows="88,*,31" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="Users/top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="Users/left" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="Caipu/list" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
  <frame src="Users/footer" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes>
  <body>
    </noframes>
  </body>
</html>
