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
    
    <title>�˵��б�</title>
    
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
  	<span style="font-weight:bold;">Ӫ����Ϣ��</span>
  	<span>��·�${views.calories}</span>
	<span>̼ˮ�����${views.carbohydrate}g</span>
  	<span>֬����${views.fat}g</span>
  	<span>������ ��${views.protein}g</span>
  	<span>��ά�� ��${views.vitamine}g</span>
  	<span>ά����A��${views.vta}��g</span>
  	<span>ά����C��${views.vtc}mg</span>
  	<span>ά����E��${views.vte}mg</span>
  	<span>���ܲ��أ�${views.carotene}��g</span>
  	<span>����  ��${views.thiamine}mg</span>
  	<span>�˻���  ��${views.riboflavin}mg</span><br/><br/>
	<span>����   ��${views.yansuan}mg</span>
  	<span>���̴� ��${views.cholesterol}mg</span>
  	<span>þ��${views.mg}mg</span>
    <span>�ƣ�${views.ca}mg</span>
  	<span>����${views.iron}mg</span>
  	<span>п��${views.zinc}mg</span>       
    <span>ͭ��${views.copper}mg</span>
    <span>�̣�${views.mn}mg</span>
  	<span>�أ�${views.k}mg</span>
  	<span>�ף�${views.p}mg</span>        
	<span>�ƣ�${views.na}mg</span>
  	<span>����${views.se}��g</span> 
  	<span>��Դ��${views.source}</span> 
  	</div>
  	<br/>
  <div style="font-size:14px">
  	<span style="font-weight:bold">ʳ�ģ�</span>
  	<c:forEach items="${views.materials}" var="mt">
  	<a href="Material/detail?id=${mt.maId}">${mt.mName}</a>: ${mt.amount}g
  	</c:forEach>
  </div>
  	<br/><br/>
  </c:forEach>
  
  	<c:choose>
		<c:when test="${pc<10&&pn==pc}">
		<span><a href="Views/list?pn=${pn-1}">��һҳ</a></span>
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
	    	 
	    	
	    	<span><a href="Views/list?pn=${pn+1}">��һҳ</a></span>
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
			<span><a href="Views/list?pn=${pn+1}">��һҳ</a>
			</span>
		
	</c:if>
	
  
    <c:if test="${pc>10&&pn==pc}">
        	<span><a href="Views/list?pn=${pn-1}">��һҳ</a></span>
	    	<span><a href="Views/list?pn=${1}">1</a></span>
	    	<span>....</span>
    		<c:forEach var="i" begin="0" end="${pc%10+1}" step="1">	    
	    	<span><a href="Views/list?pn=${i+s*10-1}">${i+s*10-1}</a></span>	    	   
	    	</c:forEach>
    </c:if>

  <c:forEach var="j" begin="1" end="${pc/10-1}" step="1">
	    <c:if test="${pc>10&&pn>=10*j&&pn<10*(j+1)}">
	    	<span><a href="Views/list?pn=${pn-1}">��һҳ</a></span>
	    	<span><a href="Views/list?pn=${1}">1</a></span>
	    	<span>....</span>
	    	<c:forEach var="i" begin="0" end="${11}" step="1">	    
	    	<span><a href="Views/list?pn=${i+10*j-1}">${i+10*j-1}</a></span>	    	   
	    	</c:forEach>
	    	<span>....</span>
	    	<span><a href="Views/list?pn=${pc}">${pc}</a></span>
	    	<span><a href="Views/list?pn=${pn+1}">��һҳ</a></span>
	   </c:if>
   </c:forEach> 
  </body>
</html>
