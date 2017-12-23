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
    
    <title>json</title>
    
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
  </head>
  
  <body class="sarchbody">
	<div id="div_json">
		<h5>录入数据</h5>
		<br />
		<form action="#" method="post">
			<label for="name">姓名：</label><input type="text" name="name" />
			<label for="age">年龄：</label><input type="text" name="age" />
			<label for="position">职务：</label><input type="text" name="position" />
			<input type="button" class="btn" value="提交结果"/>
		</form>
		<br />
		<h5>显示结果</h5>
		<br />
		<ul>
			<li>姓名：<span id="s_name">赞无数据</span></li>
			<li class="li_layout">年龄：<span id="s_age">暂无数据</span></li>
			<li class="li_layout">职务：<span id="s_position">暂无数据</span></li>
		</ul>
	</div>
	<script type="text/javascript">
		
		/* 提交结果，执行ajax */
		function btn(){
			
			var $btn = $("input.btn");//获取按钮元素
			//给按钮绑定点击事件
			$btn.bind("click",function(){
				
				$.ajax({
					type:"post",
					url:"json/testJson",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
					data:{//设置数据源
						name:$("input[name=name]").val(),
						age:$("input[name=age]").val(),
						position:$("input[name=position]").val()//这里不要加","  不然会报错，而且根本不会提示错误地方
					},
					dataType:"json",//设置需要返回的数据类型
					success:function(data){
						var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
						//得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
						$("#s_name").text("success");
						//$("#s_name").text(""+d.name+"");
						//$("#s_age").text(""+date.map+"");
						$("#s_position").text(""+d.position+"");
						
					},
					 error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                    }
				});
			});
		}
	
		/* 页面加载完成，绑定事件 */
		$(document).ready(function(){			
			btn();//点击提交，执行ajax
		});
	</script>
</body>
</html>
