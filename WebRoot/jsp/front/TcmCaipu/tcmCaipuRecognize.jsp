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

<title>中医菜谱识别</title>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>日常菜谱</li>
			<li>中医特性</li>
			<li>菜谱识别</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">
			<form id="form" name="form" method="post" action="TcmCaipu/tcmRecognizeSubmit">
				<ul class="seachform1">
					<li><label>菜谱名称</label><input name="cpName" type="text"
						class="scinput1" value="${cpName}"/></li>
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
						<c:if test="${! empty tcmCaipuList}">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="recognize" style="height: 400px; width:800px"></div>
							<div id="main" style="height: 400px"></div>
						</c:if>
						<c:if test="${empty tcmCaipuList}">
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
						data : ["${tcmCaipuList[0].name}"]
					},
					xAxis : [ {
						type : 'category',
						data : ["肺","心","脾胃","肝胆","肾","三焦","气","血",
						        "阴","阳","辛","苦","甘","酸","咸","淡","升","降","沉敛","浮散","和中"], //写特征名称 23 维
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
								"name" : "${tcmCaipuList[0].name}",
								"type" : "bar",
								"data" : [ "${tcmCaipuList[0].fei}",
											"${tcmCaipuList[0].xin}",
											"${tcmCaipuList[0].piwei}",
											"${tcmCaipuList[0].gandan}",
											"${tcmCaipuList[0].shen}",
											"${tcmCaipuList[0].sanjiao}",
											"${tcmCaipuList[0].qi}",
											"${tcmCaipuList[0].xue}",
											"${tcmCaipuList[0].yin}",
											"${tcmCaipuList[0].yang}",
											"${tcmCaipuList[0].xing}",
											"${tcmCaipuList[0].ku}",
											"${tcmCaipuList[0].gan}",
											"${tcmCaipuList[0].suan}",
											"${tcmCaipuList[0].xian}",
											"${tcmCaipuList[0].dan}",
											"${tcmCaipuList[0].sheng}",
											"${tcmCaipuList[0].jiang}",
											"${tcmCaipuList[0].chen}",
											"${tcmCaipuList[0].fu}",
											"${tcmCaipuList[0].hezhong}"]
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
						data : ["${tcmCaipuList[0].name}"]
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
								"name" : "${tcmCaipuList[0].name}",
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
