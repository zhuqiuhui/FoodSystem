<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    
    <title>食材营养信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>


  </head>
  
  <body>
     <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>首页</li>
    <li>原料信息</li>
    </ul>
    </div>
    <table class="tablelist">
        <c:forEach items="${caipuList}" var="views">
    	<thead>
    	<tr>
		<th>${material.mName}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
		<td>				
			卡路里：${material.calories}
			碳水化合物：${material.carbohydrate}g
		  	脂肪：${material.fat}g
		  	蛋白质 ：${material.protein}g
		  	纤维素 ：${material.vitamine}g
		  	维生素A：${material.vta}μg
		  	维生素C：${material.vtc}mg
		  	维生素E：${material.vte}mg
		  	胡萝卜素：${material.carotene}μg
		  	硫胺素  ：${material.thiamine}mg
		  	核黄素  ：${material.riboflavin}mg
			烟酸   ：${material.yansuan}mg
		  	胆固醇 ：${material.cholesterol}mg
		  	镁：${material.mg}mg
		          钙：${material.ca}mg
		  	铁：${material.iron}mg
		  	锌：${material.zinc}mg       
		          铜：${material.copper}mg
		       锰：${material.mn}mg
		  	钾：${material.k}mg
		  	磷：${material.p}mg        
			钠：${material.na}mg
		  	硒：${material.se}μg 
		  	来源：${material.source} 
		</td>
		
        </tr> 
        </c:forEach>    
        </tbody>
    </table>
  </body>
</html>
