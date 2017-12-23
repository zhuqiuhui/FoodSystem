<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/text.css" type="text/css" />
<script src="js/echarts.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>

<title>西医菜谱识别</title>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>日常菜谱</li>
			<li>西医特性</li>
			<li>菜谱识别</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<form id="form" name="form" method="post" action="Caipu/westRecognizeSubmit">
				<ul class="seachform1">
					<li><label>菜谱名称</label><input name="cpName" type="text"
						class="scinput1" value="${caipuList[0].viewName}"/></li>
				</ul>

				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="识别" /></li>
				</ul>
			</form>
		</div>
		<div class="formtitle">
			<span>识别结果</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>菜谱分析详情</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<c:if test="${! empty caipuList}">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="recognize" style="height: 400px; width:800px"></div>
							<div id="main" style="height: 400px"></div>
						</c:if>
						<c:if test="${empty caipuList}">
							查询不到结果！
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>


		<script type="text/javascript">
			// 路径配置
			require.config({
				paths : {
					echarts : 'http://echarts.baidu.com/build/dist'
				}
			});

			// 使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('main'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '菜谱元素分布图',
						subtext : ''
					},
					legend : {
						data : ["${caipuList[0].viewName}"]
					},
					xAxis : [ {
						type : 'category',
						data : ["热量","碳水化合物","脂肪","蛋白质","维生素","维生素A","维生素C","维生素E","胡罗卜素","维生素B1","维生素B2","盐酸","胆固醇","镁","钙","铁","锌","铜","锰","钾","磷","钠","硒"], //写特征名称 23 维
						axisLabel : {
							interval : 0,
							rotate : 60,//60度角倾斜显示
							formatter : function(val) {
								return val.split("").join("\n"); //横轴信息文字竖直显示
							}
						}

					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : [
							{
								"name" : "${caipuList[0].viewName}",
								"type" : "bar",
								"data" : [ "${caipuList[0].calories}", "${caipuList[0].carbohydrate}", "${caipuList[0].fat}",
								           "${caipuList[0].protein}", "${caipuList[0].vitamine}", "${caipuList[0].vta}",
								           "${caipuList[0].vtc}", "${caipuList[0].vte}", "${caipuList[0].carotene}",
								           "${caipuList[0].thiamine}", "${caipuList[0].riboflavin}", "${caipuList[0].yansuan}", 
								           "${caipuList[0].cholesterol}", "${caipuList[0].mg}", "${caipuList[0].ca}", 
								           "${caipuList[0].iron}", "${caipuList[0].zinc}", "${caipuList[0].copper}",
								           "${caipuList[0].mn}", "${caipuList[0].k}", "${caipuList[0].p}", 
								           "${caipuList[0].na}", "${caipuList[0].se}"]
							//菜谱对应特征值
							}]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
			
			
			// 使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('recognize'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '常见疾病健康指数分布图',
						subtext : ''
					},
					legend : {
						data : ["${caipuList[0].viewName}"]
					},
					xAxis : [ {
						type : 'category',
						data : [ "高血压", "养肠胃", "糖尿病"],
					} ],
					yAxis : [ {
						type : 'value'
					} ],
					series : [
							{
								"name" : "${caipuList[0].viewName}",
								"type" : "bar",
								"data" : [ "${aucHypertension}", "${aucStomach}", "${aucDiabetes}"]
							//菜谱对应特征值
							}]
				};
				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
		</script>
	</div>
</body>
</html>
