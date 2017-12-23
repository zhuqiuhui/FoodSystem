<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>">
<title>饮食目录</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/css-table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/style-table.js"></script>
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
			<li>营养饮食</li>
		</ul>
	</div>

	<div class="rightinfo">
		<table class="tablelist">
		<tr>
						<td scope="col" rowspan="2">饮食</td>
						<td scope="col" rowspan="2">时间</td>
						<td scope="col" rowspan="2">食材</td>
						<td scope="col" colspan="23">西医成份</td>
					</tr>
					<tr>
						<td>卡路里（g）</td>
						<td>碳水化合物（g）</td>
						<td>脂肪（g）</td>
						<td>蛋白质（g）</td>
						<td>纤维素（g）</td>
						<td>维生素A（μg）</td>
						<td>维生素C（mg）</td>
						<td>维生素E（mg）</td>
						<td>胡萝卜素（μg）</td>
						<td>硫胺素（mg）</td>
						<td>核黄素（mg）</td>
						<td>烟酸（mg）</td>
						<td>胆固醇（mg）</td>
						<td>镁（mg）</td>
						<td>钙（mg）</td>
						<td>铁（mg）</td>
						<td>锌（mg）</td>
						<td>铜（mg）</td>
						<td>锰（mg）</td>
						<td>钾（mg）</td>
						<td>磷（mg）</td>
						<td>钠（mg）</td>
						<td>硒（μg）</td>
					</tr>
			<c:forEach items="${perdietList}" var="views">
				<tbody>
					
					<tr>
						<td>${views.vname}</td>
						<td>${views.insertdate}</td>
						<td>
							<c:forEach items="${views.perrecords}" var="gx">
								<a href="Yuanliao/detail?id=${gx.yuanliao.maId}"
									class="tablelink">${gx.yuanliao.mName}</a>: ${gx.amount}g
  							</c:forEach>
  						</td>
  						<td>${views.calories}</td>
  						<td>${views.carbohydrate}</td>
  						<td>${views.fat}</td>
  						<td>${views.protein}</td>
  						<td>${views.vitamine}</td>
  						<td>${views.vta}</td>
  						<td>${views.vtc}</td>
  						<td>${views.vte}</td>
  						<td>${views.carotene}</td>
  						<td>${views.thiamine}</td>
  						<td>${views.riboflavin}</td>
  						<td>${views.yansuan}</td>
  						<td>${views.cholesterol}</td>
  						<td>${views.mg}</td>
  						<td>${views.ca}</td>
  						<td>${views.iron}</td>
  						<td>${views.zinc}</td>
  						<td>${views.copper}</td>
  						<td>${views.mn}</td>
  						<td>${views.k}</td>
  						<td>${views.p}</td>
  						<td>${views.na}</td>
  						<td>${views.se}</td>
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
							<li class="paginItem"><a href="Perdiet/list?pn=${i}">${i}</a></li>
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
						<li class="paginItem"><a href="Perdiet/list?pn=${i}">${i}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="Perdiet/list?pn=${11}">...</a></li>
					<li class="paginItem"><a href="Perdiet/list?pn=${pc}">${pc}</a></li>
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
							<li class="paginItem"><a href="Perdiet/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="Perdiet/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="Perdiet/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="Perdiet/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="Perdiet/list?pn=${pc}">${pc}</a></li>
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
					<li class="paginItem"><a href="Perdiet/list?pn=${1}">1</a></li>
					<li class="paginItem"><a href="Perdiet/list?pn=${(s)*10}">...</a></li>
					<c:forEach var="i" begin="1" end="${pc%10}" step="1">
						<li class="paginItem more"><a
							href="Perdiet/list?pn=${(s)*10+i}">${(s)*10+i}</a></li>
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
							<li class="paginItem"><a href="Perdiet/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="Perdiet/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="Perdiet/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="Perdiet/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="Perdiet/list?pn=${pc}">${pc}</a></li>
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
					<li class="paginItem"><a href="Perdiet/list?pn=${1}">1</a></li>
					<li class="paginItem more"><a
						href="Perdiet/list?pn=${(s-1)*10}">...</a></li>
					<c:forEach var="i" begin="0" end="${9}" step="1">
						<li class="paginItem more"><a
							href="Perdiet/list?pn=${i+10*(s-1)+1}">${i+10*(s-1)+1}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="javascript:;"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>
		</c:if>
	</div>


</body>
</html>
