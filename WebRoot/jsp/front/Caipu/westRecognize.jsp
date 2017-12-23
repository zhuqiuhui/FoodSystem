<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">

<title>搜索</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
<script type="text/javascript">
	    KE.show({
	        id : 'content7',
	        cssPath : './index.css'
	    });
	  </script>
</head>

<body class="sarchbody">
	<input type="hidden" name="msg" id="msg" value="${msg}">
	<c:if test="${!empty requestScope.msg}">
		<script type="text/javascript">
			var msg=document.getElementById("msg").value;
			alert(msg);
			</script>
	</c:if>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>日常菜谱</li>
			<li>西医特性</li>
			<li>菜谱识别</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<form id="form" name="form" method="post" action="Caipu/westRecognizeSubmit">
				<ul class="seachform1">
					<li><label>菜谱名称</label><input name="cpName" type="text"
						class="scinput1" /></li>
				</ul>

				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="识别" /></li>
				</ul>
			</form>
		</div>

		<div class="formtitle">
			<span>识别结果</span>
		</div>
		<table class="tablelist">
			<c:if test="${empty caipuList}">
				<thead>
					<tr>
						<th>菜谱分析详情</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>暂无结果</td>
					</tr>
				</tbody>
			</c:if>
		</table>
	</div>
</body>
</html>

