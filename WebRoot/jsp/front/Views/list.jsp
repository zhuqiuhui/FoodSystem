<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>菜单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <c:forEach items="${viewList}" var="views">
  	<span style="font-weight:bold;font-size:18px">${views.viewName}</span><br/><br/>
  	<div style="font-size:14px;">
  	<span style="font-weight:bold;">营养信息：</span>
  	<span>卡路里：${views.calories}</span>
	<span>碳水化合物：${views.carbohydrate}g</span>
  	<span>脂肪：${views.fat}g</span>
  	<span>蛋白质 ：${views.protein}g</span>
  	<span>纤维素 ：${views.vitamine}g</span>
  	<span>维生素A：${views.vta}μg</span>
  	<span>维生素C：${views.vtc}mg</span>
  	<span>维生素E：${views.vte}mg</span>
  	<span>胡萝卜素：${views.carotene}μg</span>
  	<span>硫胺素  ：${views.thiamine}mg</span>
  	<span>核黄素  ：${views.riboflavin}mg</span><br/><br/>
	<span>烟酸   ：${views.yansuan}mg</span>
  	<span>胆固醇 ：${views.cholesterol}mg</span>
  	<span>镁：${views.mg}mg</span>
    <span>钙：${views.ca}mg</span>
  	<span>铁：${views.iron}mg</span>
  	<span>锌：${views.zinc}mg</span>       
    <span>铜：${views.copper}mg</span>
    <span>锰：${views.mn}mg</span>
  	<span>钾：${views.k}mg</span>
  	<span>磷：${views.p}mg</span>        
	<span>钠：${views.na}mg</span>
  	<span>硒：${views.se}μg</span> 
  	<span>来源：${views.source}</span> 
  	</div>
  	<br/>
  <div style="font-size:14px">
  	<span style="font-weight:bold">食材：</span>
  	<c:forEach items="${views.materials}" var="mt">
  	<a href="Material/detail?id=${mt.maId}">${mt.mName}</a>: ${mt.amount}g
  	</c:forEach>
  </div>
  	<br/><br/>
  </c:forEach>
  
  	<c:choose>
		<c:when test="${pc<10&&pn==pc}">
		<span><a href="Views/list?pn=${pn-1}">上一页</a></span>
			<c:forEach var="i" begin="1" end="${pc-1}" step="1">	    
	    	<span><a href="Views/list?pn=${i}">${i}</a></span>	    	   
	    	</c:forEach>
	    	
	    	<span>....</span>
	    	<span><a href="Views/list?pn=${pc}">${pc}</a></span>
		</c:when>
		<c:when test="${pc<10&&pn!=pc}">
	  	    <c:forEach var="i" begin="1" end="${pn}" step="1">	    
	    	<span><a href="Views/list?pn=${i}">${i}</a></span>	    	   
	    	</c:forEach>
	    	
	    	<span>....</span>
	    	<span><a href="Views/list?pn=${pc}">${pc}</a></span>
	    	 
	    	
	    	<span><a href="Views/list?pn=${pn+1}">下一页</a></span>
	  	</c:when>
	</c:choose>


 
   <c:if test="${pc>10&&pn<10}">		
			<c:forEach var="i" begin="1" end="${10}" step="1">
				<span><a href="Views/list?pn=${i}">${i}</a>
				</span>
			</c:forEach>
			<span>....</span>
			<span><a href="Views/list?pn=${pc}">${pc}</a>
			</span>
			<span><a href="Views/list?pn=${pn+1}">下一页</a>
			</span>
		
	</c:if>
	
  
    <c:if test="${pc>10&&pn==pc}">
        	<span><a href="Views/list?pn=${pn-1}">上一页</a></span>
	    	<span><a href="Views/list?pn=${1}">1</a></span>
	    	<span>....</span>
    		<c:forEach var="i" begin="0" end="${pc%10+1}" step="1">	    
	    	<span><a href="Views/list?pn=${i+s*10-1}">${i+s*10-1}</a></span>	    	   
	    	</c:forEach>
    </c:if>

  <c:forEach var="j" begin="1" end="${pc/10-1}" step="1">
	    <c:if test="${pc>10&&pn>=10*j&&pn<10*(j+1)}">
	    	<span><a href="Views/list?pn=${pn-1}">上一页</a></span>
	    	<span><a href="Views/list?pn=${1}">1</a></span>
	    	<span>....</span>
	    	<c:forEach var="i" begin="0" end="${11}" step="1">	    
	    	<span><a href="Views/list?pn=${i+10*j-1}">${i+10*j-1}</a></span>	    	   
	    	</c:forEach>
	    	<span>....</span>
	    	<span><a href="Views/list?pn=${pc}">${pc}</a></span>
	    	<span><a href="Views/list?pn=${pn+1}">下一页</a></span>
	   </c:if>
   </c:forEach> 
  </body>
</html>
