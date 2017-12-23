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

<script type="text/javascript">
	/* 西医数据请求action */
	function perdietSubmit() {
		document.form.action = "Perdiet/bias";
		document.form.submit();
	}
</script>

<title>近期菜谱偏向</title>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>个人饮食</li>
			<li>西医特性</li>
			<li>饮食偏向分析</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">

			<form id="form" name="form">
				<ul class="seachform1">
					<li><label>选择日期：</label> <input type="date" class="dfinput"
						name="targetDate" value="${date}" /></li>
				</ul>

				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="偏向分析" onClick='perdietSubmit()' /></li>
				</ul>
			</form>
			<br>
			<form id="form" name="form">
				<ul class="seachform1">
					<li><label>开始时间：</label> <input type="date" class="dfinput"
						name="startDate" value="${date}" /></li>
				</ul>

				<ul class="seachform1">
					<li><label>结束时间：</label> <input type="date" class="dfinput"
						name="endDate" value="${date}" /></li>
				</ul>

				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="偏向分析" onClick='perdietSubmit()' /></li>
				</ul>
			</form>

		</div>
		<div class="formtitle">
			<span>菜谱偏向分析</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>分析详情</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<c:if test="${! empty averagePerdiet}">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="recognize" style="height: 400px; width:800px"></div>
							<div id="main" style="height: 400px"></div>
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
						data : ["${averagePerdiet.vname}"]
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
								"name" : "${averagePerdiet.vname}",
								"type" : "bar",
								"data" : [ "${averagePerdiet.calories}", "${averagePerdiet.carbohydrate}", "${averagePerdiet.fat}",
								           "${averagePerdiet.protein}", "${averagePerdiet.vitamine}", "${averagePerdiet.vta}",
								           "${averagePerdiet.vtc}", "${averagePerdiet.vte}", "${averagePerdiet.carotene}",
								           "${averagePerdiet.thiamine}", "${averagePerdiet.riboflavin}", "${averagePerdiet.yansuan}", 
								           "${averagePerdiet.cholesterol}", "${averagePerdiet.mg}", "${averagePerdiet.ca}", 
								           "${averagePerdiet.iron}", "${averagePerdiet.zinc}", "${averagePerdiet.copper}",
								           "${averagePerdiet.mn}", "${averagePerdiet.k}", "${averagePerdiet.p}", 
								           "${averagePerdiet.na}", "${averagePerdiet.se}"]
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
						data : ["${averagePerdiet.vname}"]
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
								"name" : "${averagePerdiet.vname}",
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
