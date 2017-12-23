<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
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

<script type="text/javascript">
	/* 西医数据请求action */
	function westCaipuSearch() {
		document.form.action = "Caipu/search";
		document.form.submit();
	}
	/* 中医数据请求action */
	function tcmCaipuSearch() {
		document.form.action = "TcmCaipu/search";
		document.form.submit();
	}
</script>

<%-- <script type="text/javascript">
	$(document).ready(function(e) {
	
		$(".select3").uedSelect({
			width : 152
		});
	});
	</script> --%>
</head>

<body class="sarchbody">
	<input type="hidden" name="msg" id="msg" value="${msg}">
	<c:if test="${!empty requestScope.msg}">
		<script type="text/javascript">
			var msg = document.getElementById("msg").value;
			alert(msg);
		</script>
	</c:if>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>搜索菜谱</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<form id="form" name="form" method="post">
				<ul class="seachform1">
					<li><label>菜谱名称</label><input name="cpName" type="text"
						class="scinput1" value="${cpName}" /></li>
				</ul>
				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="营养菜谱" onClick='westCaipuSearch()'/></li>
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="中医菜谱" onClick='tcmCaipuSearch()'/></li>
				</ul>
			</form>
		</div>

		<div class="formtitle">
			<span>查询结果</span>
		</div>
		<table class="tablelist">
			<c:if test="${empty caipuList && empty tcmCaipuList}">
				<thead>
					<tr>
						<th>菜谱详情</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>暂无结果</td>
					</tr>
				</tbody>
			</c:if>
			<c:if test="${! empty caipuList}">
				<thead>
					<tr>
						<th>菜谱详情</th>
					</tr>
				</thead>
				<c:forEach items="${caipuList}" var="views">
					<tbody>
						<tr>
							<td>
								<form id="form1" name="form1" method="post"
									action="Caipu/update">
									<font color="red">菜谱名称：</font><input name="viewName_new"
										value="${views.viewName}" type="text"> <input
										name="viewName" value="${views.viewName}" type="hidden">
									<br> <font color="red">&nbsp;&nbsp;营养成分：</font>
									卡路里：${views.calories} 碳水化合物：${views.carbohydrate}g
									脂肪：${views.fat}g 蛋白质 ：${views.protein}g 纤维素：${views.vitamine}g
									维生素A：${views.vta}μg 维生素C：${views.vtc}mg 维生素E：${views.vte}mg
									胡萝卜素：${views.carotene}μg 硫胺素 ：${views.thiamine}mg
									核黄素：${views.riboflavin}mg 烟酸 ：${views.yansuan}mg
									胆固醇：${views.cholesterol}mg 镁：${views.mg}mg 钙：${views.ca}mg
									铁：${views.iron}mg 锌：${views.zinc}mg 铜：${views.copper}mg
									锰：${views.mn}mg 钾：${views.k}mg 磷：${views.p}mg 钠：${views.na}mg
									硒：${views.se}μg <br> <font color="red">&nbsp;&nbsp;来源：</font>${views.source}<br />
									<font color="red">&nbsp;&nbsp;食材：</font>
									<c:forEach items="${views.guanxis}" var="gx">
										<a href="Yuanliao/detail?id=${gx.yuanliao.maId}"
											class="tablelink">${gx.yuanliao.mName}</a>: <input
											name="amount" value="${gx.amount}" type="text">
										<input type="hidden" name="ylId" value="${gx.yuanliao.maId}">
									</c:forEach>
									<ul>
										<li><input type="submit" class="scbtn" value="修改菜谱">
										</li>
									</ul>
								</form>
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if>
			<!-- 中医菜谱显示 -->
			<c:if test="${! empty tcmCaipuList}">
				<thead>
					<tr>
						<th>菜谱详情</th>
					</tr>
				</thead>
				<c:forEach items="${tcmCaipuList}" var="caipu">
					<tbody>
						<tr>
							<td>
								菜谱名称：${caipu.name}
									<br> <font color="red">&nbsp;&nbsp;营养成分：</font>
									肺:${caipu.fei}份 
						心:${caipu.xin}份
						脾胃:${caipu.piwei}份
						肝胆:${caipu.gandan}份
						肾:${caipu.shen}份
						三焦:${caipu.sanjiao}份
						气:${caipu.qi}份
						血:${caipu.xue}份
						阴:${caipu.yin}份
						阳:${caipu.yang}份
						辛:${caipu.xing}份
						苦:${caipu.ku}份
						甘:${caipu.gan}份
						酸:${caipu.suan}份
						咸:${caipu.xian}份
						淡:${caipu.dan}份
						升:${caipu.sheng}份
						降:${caipu.jiang}份
						沉敛:${caipu.chen}份
						浮散:${caipu.fu}份
						和中:${caipu.hezhong}份 <br> <font color="red">&nbsp;&nbsp;来源：</font>${caipu.source}<br />
									<font color="red">&nbsp;&nbsp;食材：</font>
									<c:forEach items="${caipu.tcmGuanxis}" var="gx">
									${gx.tcmYuanliao.name}
									${gx.amount}g
									</c:forEach>
									<!-- <ul>
										<li><input type="submit" class="scbtn" value="修改菜谱">
										</li>
									</ul> -->
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>

