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

<script type="text/javascript">
	/* 西医数据请求action */
	function westPerdietSearch() {
		document.form.action = "Perdiet/search";
		document.form.submit();
	}
	/* 中医数据请求action */
	function tcmPerdietSearch() {
		document.form.action = "TcmPerdiet/search";
		document.form.submit();
	}
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
			<li>搜索饮食</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<form id="form" name="form" method="post">
				<ul class="seachform1">
					<li><label>饮食名称</label><input name="vname" type="text"
						class="scinput1" value="${vname}" /></li>
				</ul>

				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="营养饮食"
						onClick="westPerdietSearch()" /></li>
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="中医饮食"
						onClick="tcmPerdietSearch()" /></li>
				</ul>
			</form>
		</div>

		<div class="formtitle">
			<span>查询结果</span>
		</div>
		<table class="tablelist">
			<c:if test="${empty perdietList && empty tcmPerdietList}">
				<thead>
					<tr>
						<th>饮食菜谱详情</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>暂无结果</td>
					</tr>
				</tbody>
			</c:if>
			<c:if test="${! empty perdietList}">
				<thead>
					<tr>
						<th>饮食菜谱详情</th>
					</tr>
				</thead>
				<c:forEach items="${perdietList}" var="views">
					<tbody>
						<tr>
							<td><font color="red">饮食名称：</font>${views.vname} <br> <font
								color="red">&nbsp;&nbsp;营养成分：</font> 卡路里：${views.calories}
								碳水化合物：${views.carbohydrate}g 脂肪：${views.fat}g 蛋白质
								：${views.protein}g 纤维素：${views.vitamine}g 维生素A：${views.vta}μg
								维生素C：${views.vtc}mg 维生素E：${views.vte}mg 胡萝卜素：${views.carotene}μg
								硫胺素 ：${views.thiamine}mg 核黄素：${views.riboflavin}mg 烟酸
								：${views.yansuan}mg 胆固醇：${views.cholesterol}mg 镁：${views.mg}mg
								钙：${views.ca}mg 铁：${views.iron}mg 锌：${views.zinc}mg
								铜：${views.copper}mg 锰：${views.mn}mg 钾：${views.k}mg
								磷：${views.p}mg 钠：${views.na}mg 硒：${views.se}μg <br> <font
								color="red">&nbsp;&nbsp;食材：</font> <c:forEach
									items="${views.perrecords}" var="pd">
									<a href="Yuanliao/detail?id=${pd.yuanliao.maId}"
										class="tablelink">${pd.yuanliao.mName}</a>: ${pd.amount}g
									</c:forEach><br> <font color="red">&nbsp;&nbsp;时间：</font>${views.insertdate}
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if>

			<c:if test="${! empty tcmPerdietList}">
				<thead>
					<tr>
						<th>饮食菜谱详情</th>
					</tr>
				</thead>
				<c:forEach items="${tcmPerdietList}" var="t">
					<tbody>
						<tr>
							<td><font color="red">饮食名称：</font>${t.name} <br> <font
								color="red">&nbsp;&nbsp;营养成分：</font> 肺:${t.fei}份 心:${t.xin}份
								脾胃:${t.piwei}份 肝胆:${t.gandan}份 肾:${t.shen}份 三焦:${t.sanjiao}份
								气:${t.qi}份 血:${t.xue}份 阴:${t.yin}份 阳:${t.yang}份 辛:${t.xing}份
								苦:${t.ku}份 甘:${t.gan}份 酸:${t.suan}份 咸:${t.xian}份 淡:${t.dan}份
								升:${t.sheng}份 降:${t.jiang}份 沉敛:${t.chen}份 浮散:${t.fu}份
								和中:${t.hezhong}份 <br /> <font color="red">&nbsp;&nbsp;食材：</font>
								<c:forEach items="${t.tcmPerrecords}" var="pd">
									<a href="TcmYuanliao/detail?id=${pd.tcmYuanliao.tId}"
										class="tablelink">${pd.tcmYuanliao.name}</a>: ${pd.amount}g
									</c:forEach><br> <font color="red">&nbsp;&nbsp;时间：</font>${t.insertdate}
							</td>
						</tr>
					</tbody>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>

