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
	function westCompareSubmit() {
		document.form.action = "Perdiet/compare";
		document.form.submit();
	}
	/*中医数据请求action */
	function tcmCompareSubmit() {
		document.form.action = "TcmPerdiet/compare";
		document.form.submit();
	}
</script>

<title>饮食曲线</title>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>饮食曲线</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">

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
						type="submit" class="scbtn" value="营养曲线"
						onClick="westCompareSubmit()" /></li>
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="中医曲线"
						onClick="tcmCompareSubmit()" /></li>
				</ul>
			</form>
		</div>
		<div class="formtitle">
			<span>对比曲线</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>曲线详情</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:if test="${! empty averageDayPerdietMap}">
							<div id="westCurve" style="height: 400px"></div>
						</c:if> <c:if test="${! empty averageDayTcmPerdietMap}">
							<div id="tcmCurve" style="height: 400px"></div>
						</c:if> <c:if
							test="${empty averageDayPerdietMap && empty averageDayTcmPerdietMap}">
							暂无结果
					</c:if></td>
				</tr>
			</tbody>
		</table>

		<c:if test="${! empty averageDayPerdietMap}">
			<script type="text/javascript">
				// 设置数据 '卡路里','碳水化合物','脂肪','蛋白质','钠'
				var legendData = [ '卡路里', '碳水化合物', '脂肪', '蛋白质', '钠' ]; // legend data
				var timeData = []; // time data (xAxis)
				var caloriesData = []; // calories data
				var carbohydrateData = []; // carbohydrate data
				var fatData = []; // fat data
				var proteinData = []; // protein data
				var naData = []; // na data
				<c:forEach items="${averageDayPerdietMap}" var="adpm">
				timeData.push('${adpm.key}');
				caloriesData.push('${adpm.value.calories}');
				carbohydrateData.push('${adpm.value.carbohydrate}');
				fatData.push('${adpm.value.fat}');
				proteinData.push('${adpm.value.protein}');
				naData.push('${adpm.value.na}');
				</c:forEach>

				// 路径配置
				require.config({
					paths : {
						echarts : 'http://echarts.baidu.com/build/dist'
					}
				});

				// 使用
				require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
						function(ec) {
							// 基于准备好的dom，初始化echarts图表
							var myChart = ec.init(document
									.getElementById('westCurve'));

							var option = {
								title : {
									text : '西医菜谱元素曲线图',
									subtext : ''
								},
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									// 填写饮食名
									data : legendData
								},
								//右上角工具条
								toolbox : {
									show : true,
									feature : {
										mark : {
											show : true
										},
										dataView : {
											show : true,
											readOnly : false
										},
										magicType : {
											show : true,
											type : [ 'line', 'bar' ]
										},
										restore : {
											show : true
										},
										saveAsImage : {
											show : true
										}
									}
								},
								calculable : true,
								xAxis : [ {
									type : 'category',
									boundaryGap : false,
									// 填写饮食日期
									data : timeData
								} ],
								yAxis : [ {
									type : 'value',
									axisLabel : {
										formatter : '{value} g'
									}
								} ],
								series : [ {
									name : '卡路里',
									type : 'line',
									data : caloriesData
								/* markPoint : {
								    data : [
								        {type : 'max', name: '最大值'},
								        {type : 'min', name: '最小值'}
								    ]
								},
								markLine : {
								    data : [
								        {type : 'average', name: '平均值'}
								    ]
								} */
								}, {
									name : '碳水化合物',
									type : 'line',
									data : carbohydrateData
								/* markPoint : {
								    data : [
								//				                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
								        {type : 'min', name: '周最低'}
								    ]
								},
								markLine : {
								    data : [
								        {type : 'average', name : '平均值'}
								    ]
								} */
								}, {
									name : '脂肪',
									type : 'line',
									data : fatData
								/* markPoint : {
								    data : [
								//				                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
								        {type : 'min', name: '周最低'}
								    ]
								},
								markLine : {
								    data : [
								        {type : 'average', name : '平均值'}
								    ]
								} */
								}, {
									name : '蛋白质',
									type : 'line',
									data : proteinData
								/* markPoint : {
								    data : [
								//				                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
								        {type : 'min', name: '周最低'}
								    ]
								}, */
								/* markLine : {
								    data : [
								        {type : 'average', name : '平均值'}
								    ]
								} */
								}, {
									name : '钠',
									type : 'line',
									data : naData
								/* markPoint : {
								    data : [
								//				                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
								        {type : 'min', name: '周最低'}
								    ]
								},
								markLine : {
								    data : [
								        {type : 'average', name : '平均值'}
								    ]
								} */
								}

								]
							};

							// 为echarts对象加载数据 
							myChart.setOption(option);
						});
			</script>
		</c:if>

		<c:if test="${! empty averageDayTcmPerdietMap}">
			<script type="text/javascript">
				// 设置数据 '卡路里','碳水化合物','脂肪','蛋白质','钠'
				var legendData = [ '气', '血', '阴', '阳', '辛', '苦', '甘', '酸', '咸',
						'淡' ]; // legend data
				var timeData = []; // time data (xAxis)
				var qiData = [];
				var xueData = [];
				var yinData = [];
				var yangData = [];
				var xingData = [];
				var kuData = [];
				var ganData = [];
				var suanData = [];
				var xianData = [];
				var danData = [];
				<c:forEach items="${averageDayTcmPerdietMap}" var="adpm">
				timeData.push('${adpm.key}');
				qiData.push('${adpm.value.qi}');
				xueData.push('${adpm.value.xue}');
				yinData.push('${adpm.value.yin}');
				yangData.push('${adpm.value.yang}');
				xingData.push('${adpm.value.xing}');
				kuData.push('${adpm.value.ku}');
				ganData.push('${adpm.value.gan}');
				suanData.push('${adpm.value.suan}');
				xianData.push('${adpm.value.xian}');
				danData.push('${adpm.value.dan}');
				</c:forEach>

				// 路径配置
				require.config({
					paths : {
						echarts : 'http://echarts.baidu.com/build/dist'
					}
				});

				// 使用
				require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载
				],
						function(ec) {
							// 基于准备好的dom，初始化echarts图表
							var myChart = ec.init(document
									.getElementById('tcmCurve'));

							var option = {
								title : {
									text : '中医菜谱元素曲线图',
									subtext : ''
								},
								tooltip : {
									trigger : 'axis'
								},
								legend : {
									// 填写饮食名
									data : legendData
								},
								//右上角工具条
								toolbox : {
									show : true,
									feature : {
										mark : {
											show : true
										},
										dataView : {
											show : true,
											readOnly : false
										},
										magicType : {
											show : true,
											type : [ 'line', 'bar' ]
										},
										restore : {
											show : true
										},
										saveAsImage : {
											show : true
										}
									}
								},
								calculable : true,
								xAxis : [ {
									type : 'category',
									boundaryGap : false,
									// 填写饮食日期
									data : timeData
								} ],
								yAxis : [ {
									type : 'value',
									axisLabel : {
										formatter : '{value} 份'
									}
								} ],
								series : [ {
									name : '气',
									type : 'line',
									data : qiData
								}, {
									name : '血',
									type : 'line',
									data : xueData
								}, {
									name : '阴',
									type : 'line',
									data : yinData
								}, {
									name : '阳',
									type : 'line',
									data : yangData
								}, {
									name : '辛',
									type : 'line',
									data : xingData
								}, {
									name : '苦',
									type : 'line',
									data : kuData
								}, {
									name : '甘',
									type : 'line',
									data : ganData
								}, {
									name : '酸',
									type : 'line',
									data : suanData
								}, {
									name : '咸',
									type : 'line',
									data : xianData
								}, {
									name : '淡',
									type : 'line',
									data : danData
								} ]
							};

							// 为echarts对象加载数据 
							myChart.setOption(option);
						});
			</script>
		</c:if>
	</div>
</body>
</html>
