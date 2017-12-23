<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/text.css" type="text/css" />
<script src="js/echarts.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>


<title>近期菜谱偏向</title>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>个人饮食</li>
			<li>中医特性</li>
			<li>近期饮食推荐</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<form id="form" name="form">
				<ul class="seachform1">
					<li>当前日期：${date}</li>
				</ul>
			</form>
			<div>
				<br> 高血压等级：${hypertensionStatus} <br>
				养肠胃等级：${stomachStatus} <br> 糖尿病等级：${diabetesStatus} <br>
				<br> 注：A：饮食正常 B：略影响 C:严重影响
			</div>
		</div>
		<div class="formtitle">
			<span>近期饮食推荐</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>推荐详情</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:if test="${! empty averageTcmPerdiet}">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="recognize" style="height: 400px; width: 800px"></div>

						</c:if> 
						您可以尝试：
						 <c:if test="${! empty recommendTcmCaipuaucList}">
							<c:forEach items="${recommendTcmCaipuaucList}" var="views" varStatus="status">
								<c:if test="${status.index%10==0}">
									<br />
								</c:if>
								<c:if test="${status.last}">
									<a href="TcmCaipu/detail?viewId=${views.tpId}" class="tablelink">${views.name}</a>
								</c:if>
								<c:if test="${!status.last}">
									<a href="TcmCaipu/detail?viewId=${views.tpId}" class="tablelink">${views.name},</a>
								</c:if>
							</c:forEach>
						</c:if> 
						<c:if test="${! empty recommendMsg}">
							${recommendMsg}
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
				var myChart = ec.init(document.getElementById('recognize'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '近期饮食健康指数值分布图',
						subtext : ''
					},
					legend : {
						data : ["${averageTcmPerdiet.name}"]
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
								"name" : "${averageTcmPerdiet.name}",
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
