<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加数据库页面</title>
    
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
    <div class="warp">
		<div class="header"></div>
   	    <div class="con">
		
			<div class="con03_right">
			
			<div class="staff">
			<div >
			
			<h2>
        	添加食物信息 <br/>
        	</h2>
			<form id="form1" name="form1" action="Food/addSubmit" method="post">
        	<table id="change0" cellpadding="2" cellspacing="5" class="divlist" style="margin-left: 80px;">
        	
        	<tr>
        		<td align="center">食物名称</td>
        		<td><input type="text" name="food.foodName"/></td> 		     		
        	</tr>
        	
        	<tr>        		
        		<td align="center">热量热量(大卡)</td>         		
        		<td><input type="text" name="food.calories"/></td>
        	</tr>
        
        	<tr>        		
        		<td align="center">碳水化合物(克)</td>         		
        		<td><input type="text" name="food.carbohydrate"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">脂肪(克)</td>         		
        		<td><input type="text" name="food.fat"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">蛋白质(克)</td>         		
        		<td><input type="text" name="food.protein"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">纤维素(克)</td>         		
        		<td><input type="text" name="food.vitamine"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">维生素A(微克)</td>         		
        		<td><input type="text" name="food.vta"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">维生素C(毫克)</td>         		
        		<td><input type="text" name="food.vtc"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">维生素E(毫克)</td>         		
        		<td><input type="text" name="food.vte"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">胡萝卜素(微克)</td>         		
        		<td><input type="text" name="food.carotene"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">硫胺素(毫克)</td>         		
        		<td><input type="text" name="food.thiamine"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">核黄素(毫克)</td>         		
        		<td><input type="text" name="food.riboflavin"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">烟酸(毫克)</td>         		
        		<td><input type="text" name="food.yansuan"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">胆固醇(毫克)</td>         		
        		<td><input type="text" name="food.cholesterol"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">镁(毫克)</td>         		
        		<td><input type="text" name="food.mg"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">钙(毫克)</td>         		
        		<td><input type="text" name="food.ca"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">铁(毫克)</td>         		
        		<td><input type="text" name="food.iron"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">锌(毫克)</td>         		
        		<td><input type="text" name="food.zinc"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">铜(毫克)</td>         		
        		<td><input type="text" name="food.copper"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">锰(毫克)</td>         		
        		<td><input type="text" name="food.mn"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">钾(毫克)</td>         		
        		<td><input type="text" name="food.k"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">磷(毫克)</td>         		
        		<td><input type="text" name="food.p"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">钠(毫克)</td>         		
        		<td><input type="text" name="food.na"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">硒(微克)</td>         		
        		<td><input type="text" name="food.se"/></td>
        	</tr>
        	<tr>        		
        		<td align="center">来源</td>         		
        		<td><input type="text" name="food.source"/></td>
        	</tr>

				<td><input type="submit" value="确定添加"/></td>
            </table>
            </form>
            </div>
            </div>
            
			</div>
    	</div>
		<div class="footer">--  CopyRight 2014 </div>
	</div>
  </body>
</html>
