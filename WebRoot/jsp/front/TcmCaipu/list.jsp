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

		<table class="tablelist">
			<c:forEach items="${tcmCaipuList}" var="caipu">
				<thead>
					<tr>
						<th>${caipu.name}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>成份：肺：${caipu.fei}份 
						心：${caipu.xin}份
						脾胃：${caipu.piwei}份
						肝胆：${caipu.gandan}份
						肾：${caipu.shen}份
						三焦：${caipu.sanjiao}份
						气：${caipu.qi}份
						血：${caipu.xue}份
						阴：${caipu.yin}份
						阳：${caipu.yang}份
						辛：${caipu.xing}份
						苦：${caipu.ku}份
						甘：${caipu.gan}份
						酸：${caipu.suan}份
						咸：${caipu.xian}份
						淡：${caipu.dan}份
						升：${caipu.sheng}份
						降：${caipu.jiang}份
						沉敛：${caipu.chen}份
						浮散：${caipu.fu}份
						和中：${caipu.hezhong}份
						
						<br>
							食材： <c:forEach items="${caipu.tcmGuanxis}"
								var="gx">
								<a href="TcmYuanliao/detail?id=${gx.tcmYuanliao.tId}"
									class="tablelink">${gx.tcmYuanliao.name}</a>: ${gx.amount}g


  			</c:forEach> <br>来源：${caipu.source}
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
							<li class="paginItem"><a href="TcmCaipu/list?pn=${i}">${i}</a></li>
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
						<li class="paginItem"><a href="TcmCaipu/list?pn=${i}">${i}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="TcmCaipu/list?pn=${11}">...</a></li>
					<li class="paginItem"><a href="TcmCaipu/list?pn=${pc}">${pc}</a></li>
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
							<li class="paginItem"><a href="TcmCaipu/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="TcmCaipu/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="TcmCaipu/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="TcmCaipu/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="TcmCaipu/list?pn=${pc}">${pc}</a></li>
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
					<li class="paginItem"><a href="TcmCaipu/list?pn=${1}">1</a></li>
					<li class="paginItem"><a href="TcmCaipu/list?pn=${(s)*10}">...</a></li>
					<c:forEach var="i" begin="1" end="${pc%10}" step="1">
						<li class="paginItem more"><a
							href="TcmCaipu/list?pn=${(s)*10+i}">${(s)*10+i}</a></li>
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
							<li class="paginItem"><a href="TcmCaipu/list?pn=${1}">1</a></li>
							<li class="paginItem more"><a href="TcmCaipu/list?pn=${j*10}">...</a></li>
							<c:forEach var="i" begin="0" end="${9}" step="1">
								<li class="paginItem more"><a
									href="TcmCaipu/list?pn=${i+10*j+1}">${i+10*j+1}</a></li>
							</c:forEach>
							<li class="paginItem more"><a
								href="TcmCaipu/list?pn=${(j+1)*10+1}">...</a></li>
							<li class="paginItem more"><a href="TcmCaipu/list?pn=${pc}">${pc}</a></li>
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
					<li class="paginItem"><a href="TcmCaipu/list?pn=${1}">1</a></li>
					<li class="paginItem more"><a href="TcmCaipu/list?pn=${(s-1)*10}">...</a></li>
					<c:forEach var="i" begin="0" end="${9}" step="1">
						<li class="paginItem more"><a
							href="TcmCaipu/list?pn=${i+10*(s-1)+1}">${i+10*(s-1)+1}</a></li>
					</c:forEach>
					<li class="paginItem"><a href="javascript:;"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>
		</c:if>
	</div>


</body>
</html>
