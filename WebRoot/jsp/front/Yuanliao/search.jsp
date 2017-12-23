<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    
    <title>搜索</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/select.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
	<script type="text/javascript" src="js/select-ui.min.js"></script>
	<script type="text/javascript" src="editor/kindeditor.js"></script>
	<script type="text/javascript">
	    KE.show({
	        id : 'content7',
	        cssPath : './index.css'
	    });
	  </script>
	  
	<script type="text/javascript">
	$(document).ready(function(e) {
	
		$(".select3").uedSelect({
			width : 152
		});
	});
	</script>
  </head>
  
  <body class="sarchbody">
  
  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>首页</li>
    <li>搜索菜谱</li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    
     <form id="form" name="form" method="post" action="Yuanliao/search">
    <ul class="seachform1">
    
    <li><label>关键字</label><input name="mn" type="text" class="scinput1" /></li>
    </div>
    </li>    
    </ul>
   
    <ul class="seachform1">
    <li class="sarchbtn"><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"/></li>  
    </ul>
    </form>
    
    
    
    
    <div class="formtitle"><span>查询结果</span></div>
    
    <table class="tablelist">
	<c:if test="${empty ylList}">
			<thead>
    	<tr>    
        <th>原料名称</th>     
        </tr>
        </thead>	
        <tbody>
        <tr>   
        <td>暂无结果</td>
        </tr>       
        </tbody>				
	</c:if>
	<c:if test="${! empty ylList}">
    	<thead>
    	<tr>    
        <th>原料名称</th>     
        </tr>
        </thead>
        <tbody>
        <tr>   
        <td>
        <c:forEach items="${ylList}" var="yll" varStatus="a" >
			<a href="Caipu/theList?maId=${yll.maId}">${yll.mName}</a>&nbsp;&nbsp;&nbsp;
		</c:forEach>
        </td>
        </tr>       
        </tbody>
    </c:if>
    </table>
           
	</div>     
    
    </div>				
  </body>
</html>
