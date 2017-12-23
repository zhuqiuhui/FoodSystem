<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/text.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<title>菜谱添加页面</title>
<sx:head />
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

#interestArea {
	border: 1px solid #fff;
	width: 300px;
	display: none;
	display: block;
	position: absolute;
	background: #fff;
	left: 130px;
}

table {
	min-width: 200px;
}
</style>
<script type="text/javascript">
	//下拉菜单
	navHover = function() {
		var lis = document.getElementById("navmenu").getElementsByTagName("LI");
		for (var i = 0; i < lis.length; i++) {
			lis[i].onmouseover = function() {
				this.className += " iehover";
			}
			lis[i].onmouseout = function() {
				this.className = this.className.replace(new RegExp(
						" iehover\\b"), "");
			}
		}
	}
	if (window.attachEvent)
		window.attachEvent("onload", navHover);

	$(function() {
		$("#add")
				.click(
						function() {

							var interest = $("#interest").val();
							var amount = $("#amount").val();

							var str = "<tr>";

							str += "<td>" + interest + "</td>";
							str += "<td>" + amount + "g</td>";
							str += "<td onclick=\"$(this).parent().remove()\"><a class=\"tablelink\"> 删除</a></td>";
							str += "</tr>";
							var interestvll = $("#interestvll").val();
							var interestId = $("#interest").data("maId");
							if (interest != "" && amount != ""
									&& interestId != null) {
								$("table").append(str);
							} else if (interest != "" && amount != ""
									&& interestId == null) {
								alert("请选择正确的原料");
							} else {
								alert("原料以及用量不能为空");
							}

							$("#interestvll").val(
									interestvll == "" ? interestId
											: (interestvll + "," + interestId));
							var amountvll = $("#amountvll").val();
							$("#amountvll").val(
									amountvll == "" ? amount
											: (amountvll + "," + amount));

							$("#interest").val("");
							$("#amount").val("");
						});
		$("#search").click(
				function() {
					$.post("json/searchYuanliao", {
						keyword : $("#interest").val()
					}, function(result) {
						var list = result.yuanliaoList;
						var str = "";
						for (var i = 0; i < list.length; i++) {
							var yuanliao = list[i];
							str += "<div onclick=\"selectInterest(this,"
									+ yuanliao.maId + ");\">" + yuanliao.mName
									+ "</div>";
						}
						$("#interestArea").html(str);
						$("#interestArea").show();
					});
				});
	});
	function selectInterest(t, maId) {
		$("#interest").val($(t).html());
		$("#interest").data("maId", maId);
		$("#interestArea").hide();
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
			<li>日常菜谱</li>
			<li>西医特性</li>
			<li>添加菜谱</li>
		</ul>
	</div>

	<div class="formbody">

		<div class="formtitle">
			<span>菜谱添加</span>
		</div>
		<form id="form1" name="form1" action="Caipu/addSubmit" method="post">
			<ul class="forminfo">
				<li><label>菜谱名称</label><input id="viewName" name="viewName"
					type="text" class="dfinput" /><i></i></li>
				<%-- <li id="td1" onmouseleave="l()"><label>原料</label>
    <input id="input1" onfocus="f()" readonly="readonly" name="interest" type="text" class="dfinput" />
    <div id="div1">
		<c:forEach items="${yllist}" var="yll">
		<input type="checkbox" name="ylId" value="${yll.maId}" size="20px"
			onclick="c1()"><span>${yll.mName}</span><br/>
		<br/>
		</c:forEach>
	</div>
    <i></i>
    </li> --%>
				<li><label>原料</label> <input id="interest" type="text"
					class="dfinput" /> <input id="search" type="button" class="scbtn"
					value="search" />
					<div id="interestArea"></div></li>
				<li><label>原料用量</label><input id="amount" type="text"
					class="dfinput" />g<i></i></li>
				<li><label>来源</label><input name="source" type="text"
					class="dfinput" /><i></i></li>
				<li><label>&nbsp;</label>
					<table id=""></table></li>
				<li><label>&nbsp;</label><input id="add" type="button"
					class="scbtn" value="确认添加" /><i></i></li>
				<li><label>&nbsp;</label><input type="submit" class="btn"
					value="提交" /></li>
			</ul>
			<input id="interestvll" name="ylId" type="hidden" class="dfinput" />
			<input id="amountvll" name="amount" type="hidden" class="dfinput" />
		</form>
	</div>
</body>
</html>
