<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>食物含量</title>
    
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
    <table id="change0" cellpadding="2" cellspacing="5" class="divmyList" align="center"  style="font-size:15px;">
    <tr><td>食物名称</td>
   		<td>热量</td>
   		<td>碳水化合物(克)</td>
   		<td>脂肪(克)</td>
   		<td>蛋白质(克)</td>
   		<td>纤维素(克)</td>
   		<td>维生素A(微克)</td>
   		<td>维生素C(毫克)</td>
   		<td>维生素E(毫克)</td>
   		<td>胡萝卜素(微克)</td>
   		<td>硫胺素(毫克)</td>
   		<td>核黄素(毫克)</td>
   		<td>烟酸(毫克)</td>
   		<td>胆固醇(毫克)</td>
   		<td>镁(毫克)</td>
   		<td>钙(毫克)</td>
   		<td>铁(毫克)</td>
   		<td>锌(毫克)</td>
   		<td>铜(毫克)</td>
   		<td>锰(毫克)</td>
   		<td>钾(毫克)</td>
   		<td>磷(毫克)</td>
   		<td>钠(毫克)</td>
   		<td>硒(微克)</td>
   		<td>来源</td>
   		 
    </tr>
    <c:forEach items="${foods}" var="f">
    	<tr><td>${f.foodName}</td>
    		<td>${f.calories}</td>
    		<td>${f.carbohydrate}</td>
    		<td>${f.fat}</td>
    		<td>${f.protein}</td>
    		<td>${f.vitamine}</td>
    		<td>${f.vta}</td>
    		<td>${f.vtc}</td>
    		<td>${f.vte}</td>
    		<td>${f.carotene}</td>
    		<td>${f.thiamine}</td>
    		<td>${f.riboflavin}</td>
    		<td>${f.yansuan}</td>
    		<td>${f.cholesterol}</td>
    		<td>${f.mg}</td>
    		<td>${f.ca}</td>
    		<td>${f.iron}</td>
    		<td>${f.zinc}</td>
    		<td>${f.copper}</td>
    		<td>${f.mn}</td>
    		<td>${f.k}</td>
    		<td>${f.p}</td>
    		<td>${f.na}</td>
    		<td>${f.se}</td>
    		<td>${f.source}</td>
    	</tr>
    </c:forEach>
    </table>
    <c:if test="${pn<10}">
    		<c:forEach var="i" begin="1" end="${10}" step="1">	    
	    	<span><a href="Food/list?pn=${i}">${i}</a></span>	    	   
	    	</c:forEach>
	    	<span>....</span>
	    	<span><a href="Food/list?pn=${pc}">${pc}</a></span>
	    	<span><a href="Food/list?pn=${pn+1}">下一页</a></span>
    </c:if>
    
    <c:if test="${pn>=s*10}">
        	<span><a href="Food/list?pn=${pn-1}">上一页</a></span>
	    	<span><a href="Food/list?pn=${1}">1</a></span>
	    	<span>....</span>
    		<c:forEach var="i" begin="0" end="${pc%10+1}" step="1">	    
	    	<span><a href="Food/list?pn=${i+s*10-1}">${i+s*10-1}</a></span>	    	   
	    	</c:forEach>
    </c:if>
    
    <c:forEach var="j" begin="1" end="${pc/10-1}" step="1">
	    <c:if test="${pn>=10*j&&pn<10*(j+1)}">
	    	<span><a href="Food/list?pn=${pn-1}">上一页</a></span>
	    	<span><a href="Food/list?pn=${1}">1</a></span>
	    	<span>....</span>
	    	<c:forEach var="i" begin="0" end="${11}" step="1">	    
	    	<span><a href="Food/list?pn=${i+10*j-1}">${i+10*j-1}</a></span>	    	   
	    	</c:forEach>
	    	<span>....</span>
	    	<span><a href="Food/list?pn=${pc}">${pc}</a></span>
	    	<span><a href="Food/list?pn=${pn+1}">下一页</a></span>
	   </c:if>
   </c:forEach> 
  </body>
</html>
