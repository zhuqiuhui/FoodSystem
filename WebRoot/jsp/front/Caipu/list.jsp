<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
<title>菜谱列表</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
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
			<li>菜谱列表</li>
		</ul>
	</div>

	<div class="rightinfo">

		<%-- <div class="tools">
    
    	<ul class="toolbar">
        <li><span><a href="Caipu/add"><img src="images/t01.png" /></span>添加</a></li>
        <li><span><a href="Caipu/delete"><img src="images/t03.png" /></span>删除</a></li>
        <!-- 这个可以执行的前提是必须在RStudio中通过runapp将其打开 -->
        <li><span><a href="http://127.0.0.1:3802/"><img src="images/t04.png" /></span>统计</a></li>
        </ul>
        
        
  
    </div> --%>

		<table class="tablelist">
			<c:forEach items="${caipuList}" var="views">
				<thead>
					<tr>
						<th>${views.viewName}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>成份：卡路里：${views.calories} 碳水化合物：${views.carbohydrate}g
							脂肪：${views.fat}g 蛋白质 ：${views.protein}g 纤维素：${views.vitamine}g
							维生素A：${views.vta}μg 维生素C：${views.vtc}mg 维生素E：${views.vte}mg
							胡萝卜素：${views.carotene}μg 硫胺素 ：${views.thiamine}mg
							核黄素：${views.riboflavin}mg 烟酸 ：${views.yansuan}mg
							胆固醇：${views.cholesterol}mg 镁：${views.mg}mg 钙：${views.ca}mg
							铁：${views.iron}mg 锌：${views.zinc}mg 铜：${views.copper}mg
							锰：${views.mn}mg 钾：${views.k}mg 磷：${views.p}mg 钠：${views.na}mg
							硒：${views.se}μg <br>
							食材： <c:forEach items="${views.guanxis}"
								var="gx">
								<a href="Yuanliao/detail?id=${gx.yuanliao.maId}"
									class="tablelink">${gx.yuanliao.mName}</a>: ${gx.amount}g


  			</c:forEach> <br>来源：${views.source}
						</td>
					</tr>
			</c:forEach>

			</tbody>
		</table>

		<c:choose>
			<c:when test="${pc<10&&pc==1}">
				<div class="pagin">
					<div class="message">
						共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
					</div>
				</div>
			</c:when>
			<c:when test="${pc<=10}">
				<div class="pagin">
					<div class="message">
						共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
					</div>
					<ul class="paginList">
						<li class="paginItem"><a href="javascript:;"><span
								class="pagepre"></span></a></li>
						<c:forEach var="i" begin="1" end="${pc}" step="1">
							<li class="paginItem"><a href="Caipu/list?pn=${i}">${i}</a></li>
						</c:forEach>
						<li class="paginItem"><a href="javascript:;"><span
								class="pagenxt"></span></a></li>
					</ul>
				</div>
			</c:when>
		</c:choose>

		<c:if test="${pc>10&&pn<=10}">
			<div class="pagin">
				<div class="message">
					共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
				</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span
							class="pagepre"></span></a></li>
					<c:forEach var="i" begin="1" end="${10}" step="1">
						<li class="paginItem"><a href="Caipu/list?pn=${i}">${i}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="Caipu/list?pn=${11}">...</a></li>
					<li class="paginItem"><a href="Caipu/list?pn=${pc}">${pc}</a></li>
					<li class="paginItem"><a href="javascript:;"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>

		</c:if>
		<c:if test="${pc>10}">
			<c:forEach var="j" begin="1" end="${s-1}" step="1">
				<c:if test="${pc>10&&pn>10*j&&pn<=10*(j+1)&&pc%10!=0}">
					<div class="pagin">
						<div class="message">
							共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
						</div>
						<ul class="paginList">
							<li class="paginItem"><a href="javascript:;"><span
									class="pagepre"></span></a></li>
							<li class="paginItem"><a href="Caipu/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="Caipu/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="Caipu/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="Caipu/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="Caipu/list?pn=${pc}">${pc}</a></li>
							<li class="paginItem"><a href="javascript:;"><span
									class="pagenxt"></span></a></li>
						</ul>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${pc>10&&pn>10*s}">
			<div class="pagin">
				<div class="message">
					共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
				</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span
							class="pagepre"></span></a></li>
					<li class="paginItem"><a href="Caipu/list?pn=${1}">1</a></li>
					<li class="paginItem"><a href="Caipu/list?pn=${(s)*10}">...</a></li>
					<c:forEach var="i" begin="1" end="${pc%10}" step="1">
						<li class="paginItem more"><a
							href="Caipu/list?pn=${(s)*10+i}">${(s)*10+i}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="javascript:;"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${pc>10}">
			<c:forEach var="j" begin="1" end="${s-2}" step="1">
				<c:if test="${pc>10&&pn>10*j&&pn<=10*(j+1)&&pc%10==0}">
					<div class="pagin">
						<div class="message">
							共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
						</div>
						<ul class="paginList">
							<li class="paginItem"><a href="javascript:;"><span
									class="pagepre"></span></a></li>
							<li class="paginItem"><a href="Caipu/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="Caipu/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="Caipu/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="Caipu/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="Caipu/list?pn=${pc}">${pc}</a></li>
							<li class="paginItem"><a href="javascript:;"><span
									class="pagenxt"></span></a></li>
						</ul>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${pc>10&&pn>10*(s-1)&&pc%10==0}">
			<div class="pagin">
				<div class="message">
					共<i class="blue">${count}</i>条记录，当前显示第&nbsp;<i class="blue">${pn}&nbsp;</i>页
				</div>
				<ul class="paginList">
					<li class="paginItem"><a href="javascript:;"><span
							class="pagepre"></span></a></li>
					<li class="paginItem"><a href="Caipu/list?pn=${1}">1</a></li>
					<li class="paginItem more"><a href="Caipu/list?pn=${(s-1)*10}">...</a></li>
					<c:forEach var="i" begin="0" end="${9}" step="1">
						<li class="paginItem more"><a
							href="Caipu/list?pn=${i+10*(s-1)+1}">${i+10*(s-1)+1}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="javascript:;"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>
		</c:if>
	</div>


</body>
</html>
