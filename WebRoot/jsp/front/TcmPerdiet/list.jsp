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
			<li>中医饮食</li>
		</ul>
	</div>

	<div class="rightinfo">
		<table class="tablelist">
		<tr>
						<td scope="col" rowspan="2">饮食</td>
						<td scope="col" rowspan="2">时间</td>
						<td scope="col" rowspan="2">食材</td>
						<td scope="col" colspan="23">中医成份（份）</td>
					</tr>
					<tr>
						<td>肺</td>
						<td>心</td>
						<td>脾胃</td>
						<td>肝胆</td>
						<td>肾</td>
						<td>三焦</td>
						<td>气</td>
						<td>血</td>
						<td>阴</td>
						<td>阳</td>
						<td>辛</td>
						<td>苦</td>
						<td>甘</td>
						<td>酸</td>
						<td>咸</td>
						<td>淡</td>
						<td>升</td>
						<td>降</td>
						<td>沉敛</td>
						<td>浮散</td>
						<td>和中</td>
					</tr>
			<c:forEach items="${tcmPerdietList}" var="t">
				<tbody>
					
					<tr>
						<td>${t.name}</td>
						<td>${t.insertdate}</td>
						<td>
							<c:forEach items="${t.tcmPerrecords}" var="gx">
								<a href="TcmYuanliao/detail?id=${gx.tcmYuanliao.tId}"
									class="tablelink">${gx.tcmYuanliao.name}</a>: ${gx.amount}g
  							</c:forEach>
  						</td>
  						<td>${t.fei}</td>
  						<td>${t.xin}</td>
  						<td>${t.piwei}</td>
  						<td>${t.gandan}</td>
  						<td>${t.shen}</td>
  						<td>${t.sanjiao}</td>
  						<td>${t.qi}</td>
  						<td>${t.xue}</td>
  						<td>${t.yin}</td>
  						<td>${t.yang}</td>
  						<td>${t.xing}</td>
  						<td>${t.ku}</td>
  						<td>${t.gan}</td>
  						<td>${t.suan}</td>
  						<td>${t.xian}</td>
  						<td>${t.dan}</td>
  						<td>${t.sheng}</td>
  						<td>${t.jiang}</td>
  						<td>${t.chen}</td>
  						<td>${t.fu}</td>
  						<td>${t.hezhong}</td>
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
							<li class="paginItem"><a href="TcmPerdiet/list?pn=${i}">${i}</a></li>
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
						<li class="paginItem"><a href="TcmPerdiet/list?pn=${i}">${i}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="TcmPerdiet/list?pn=${11}">...</a></li>
					<li class="paginItem"><a href="TcmPerdiet/list?pn=${pc}">${pc}</a></li>
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
							<li class="paginItem"><a href="TcmPerdiet/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="TcmPerdiet/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="TcmPerdiet/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="TcmPerdiet/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="TcmPerdiet/list?pn=${pc}">${pc}</a></li>
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
					<li class="paginItem"><a href="TcmPerdiet/list?pn=${1}">1</a></li>
					<li class="paginItem"><a href="TcmPerdiet/list?pn=${(s)*10}">...</a></li>
					<c:forEach var="i" begin="1" end="${pc%10}" step="1">
						<li class="paginItem more"><a
							href="TcmPerdiet/list?pn=${(s)*10+i}">${(s)*10+i}</a></li>
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
							<li class="paginItem"><a href="TcmPerdiet/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="TcmPerdiet/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="TcmPerdiet/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="TcmPerdiet/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="TcmPerdiet/list?pn=${pc}">${pc}</a></li>
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
					<li class="paginItem"><a href="TcmPerdiet/list?pn=${1}">1</a></li>
					<li class="paginItem more"><a
						href="TcmPerdiet/list?pn=${(s-1)*10}">...</a></li>
					<c:forEach var="i" begin="0" end="${9}" step="1">
						<li class="paginItem more"><a
							href="TcmPerdiet/list?pn=${i+10*(s-1)+1}">${i+10*(s-1)+1}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="javascript:;"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>
		</c:if>
	</div>


</body>
</html>
