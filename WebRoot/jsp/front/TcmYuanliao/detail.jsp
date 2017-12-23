<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			<li>原料信息</li>
		</ul>
	</div>
	<table class="tablelist">
		<thead>
			<tr>
				<th>${material.name}</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>成份：肺：${material.fei}份 
						心：${material.xin}份
						脾胃：${material.piwei}份
						肝胆：${material.gandan}份
						肾：${material.shen}份
						三焦：${material.sanjiao}份
						气：${material.qi}份
						血：${material.xue}份
						阴：${material.yin}份
						阳：${material.yang}份
						辛：${material.xing}份
						苦：${material.ku}份
						甘：${material.gan}份
						酸：${material.suan}份
						咸：${material.xian}份
						淡：${material.dan}份
						升：${material.sheng}份
						降：${material.jiang}份
						沉敛：${material.chen}份
						浮散：${material.fu}份
						和中：${material.hezhong}份</td>

			</tr>
		</tbody>
	</table>
</body>
</html>
