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
	function westCaipuCompare() {
		document.form.action = "Caipu/compare";
		document.form.submit();
	}
	/* 中医数据请求action */
	function tcmCaipuCompare() {
		document.form.action = "TcmCaipu/compare";
		document.form.submit();
	}
</script>

<title>菜谱营养成分对比</title>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>菜谱对比</li>
		</ul>
	</div>

	<div class="formbody">
		<div id="usual1" class="usual">

			<form id="form" name="form" method="post">
				<ul class="seachform1">
					<li><label>菜谱一：</label><input name="cpName1" type="text"
						class="scinput1" value="${cpName1}" /></li>
					<li><label>菜谱二：</label><input name="cpName2" type="text"
						class="scinput1" value="${cpName2}" /></li>
				</ul>

				<ul class="seachform1">
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="营养菜谱"
						onClick='westCaipuCompare()' /></li>
					<li class="sarchbtn"><label>&nbsp;</label><input name=""
						type="submit" class="scbtn" value="中医菜谱"
						onClick='tcmCaipuCompare()' /></li>
				</ul>
			</form>
		</div>
		<div class="formtitle">
			<span>对比结果</span>
		</div>

		<table class="tablelist">
			<thead>
				<tr>
					<th>对比详情</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:if test="${! empty caipuList1 || ! empty caipuList2}">
							<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
							<div id="westCaipu" style="height: 400px"></div>
							<script type="text/javascript">
			// 路径配置
			require.config({
				paths : {
					echarts : 'http://echarts.baidu.com/build/dist'
				}
			});

			// 西医使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('westCaipu'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '菜谱元素对比图',
						subtext : ''
					},
					legend : {
						data : [ "${caipuList1[0].viewName}",
								"${caipuList2[0].viewName}" ]
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
								"name" : "${caipuList1[0].viewName}",
								"type" : "bar",
								"data" : [ "${caipuList1[0].calories}",
										"${caipuList1[0].carbohydrate}",
										"${caipuList1[0].fat}",
										"${caipuList1[0].protein}",
										"${caipuList1[0].vitamine}",
										"${caipuList1[0].vta}",
										"${caipuList1[0].vtc}",
										"${caipuList1[0].vte}",
										"${caipuList1[0].carotene}",
										"${caipuList1[0].thiamine}",
										"${caipuList1[0].riboflavin}",
										"${caipuList1[0].yansuan}",
										"${caipuList1[0].cholesterol}",
										"${caipuList1[0].mg}",
										"${caipuList1[0].ca}",
										"${caipuList1[0].iron}",
										"${caipuList1[0].zinc}",
										"${caipuList1[0].copper}",
										"${caipuList1[0].mn}",
										"${caipuList1[0].k}",
										"${caipuList1[0].p}",
										"${caipuList1[0].na}",
										"${caipuList1[0].se}" ]
							//菜谱一对应特征值
							},
							{
								"name" : "${caipuList2[0].viewName}",
								"type" : "bar",
								"data" : [ "${caipuList2[0].calories}",
										"${caipuList2[0].carbohydrate}",
										"${caipuList2[0].fat}",
										"${caipuList2[0].protein}",
										"${caipuList2[0].vitamine}",
										"${caipuList2[0].vta}",
										"${caipuList2[0].vtc}",
										"${caipuList2[0].vte}",
										"${caipuList2[0].carotene}",
										"${caipuList2[0].thiamine}",
										"${caipuList2[0].riboflavin}",
										"${caipuList2[0].yansuan}",
										"${caipuList2[0].cholesterol}",
										"${caipuList2[0].mg}",
										"${caipuList2[0].ca}",
										"${caipuList2[0].iron}",
										"${caipuList2[0].zinc}",
										"${caipuList2[0].copper}",
										"${caipuList2[0].mn}",
										"${caipuList2[0].k}",
										"${caipuList2[0].p}",
										"${caipuList2[0].na}",
										"${caipuList2[0].se}" ]
							//菜谱二对应特征值
							} ]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
		</script>
						</c:if> <c:if test="${! empty tcmCaipuList1 || ! empty tcmCaipuList2 }">
							<div id="tcmCaipu" style="height: 400px"></div>
							<script type="text/javascript">
			// 路径配置
			require.config({
				paths : {
					echarts : 'http://echarts.baidu.com/build/dist'
				}
			});

			// 中医使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('tcmCaipu'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '菜谱元素对比图',
						subtext : ''
					},
					legend : {
						data : [ "${tcmCaipuList1[0].name}",
								"${tcmCaipuList2[0].name}" ]
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
								"name" : "${tcmCaipuList1[0].name}",
								"type" : "bar",
								"data" : [ "${tcmCaipuList1[0].fei}",
										"${tcmCaipuList1[0].xin}",
										"${tcmCaipuList1[0].piwei}",
										"${tcmCaipuList1[0].gandan}",
										"${tcmCaipuList1[0].shen}",
										"${tcmCaipuList1[0].sanjiao}",
										"${tcmCaipuList1[0].qi}",
										"${tcmCaipuList1[0].xue}",
										"${tcmCaipuList1[0].yin}",
										"${tcmCaipuList1[0].yang}",
										"${tcmCaipuList1[0].xing}",
										"${tcmCaipuList1[0].ku}",
										"${tcmCaipuList1[0].gan}",
										"${tcmCaipuList1[0].suan}",
										"${tcmCaipuList1[0].xian}",
										"${tcmCaipuList1[0].dan}",
										"${tcmCaipuList1[0].sheng}",
										"${tcmCaipuList1[0].jiang}",
										"${tcmCaipuList1[0].chen}",
										"${tcmCaipuList1[0].fu}",
										"${tcmCaipuList1[0].hezhong}" ]
							//菜谱一对应特征值
							},
							{
								"name" : "${tcmCaipuList2[0].name}",
								"type" : "bar",
								"data" : [ "${tcmCaipuList2[0].fei}",
											"${tcmCaipuList2[0].xin}",
											"${tcmCaipuList2[0].piwei}",
											"${tcmCaipuList2[0].gandan}",
											"${tcmCaipuList2[0].shen}",
											"${tcmCaipuList2[0].sanjiao}",
											"${tcmCaipuList2[0].qi}",
											"${tcmCaipuList2[0].xue}",
											"${tcmCaipuList2[0].yin}",
											"${tcmCaipuList2[0].yang}",
											"${tcmCaipuList2[0].xing}",
											"${tcmCaipuList2[0].ku}",
											"${tcmCaipuList2[0].gan}",
											"${tcmCaipuList2[0].suan}",
											"${tcmCaipuList2[0].xian}",
											"${tcmCaipuList2[0].dan}",
											"${tcmCaipuList2[0].sheng}",
											"${tcmCaipuList2[0].jiang}",
											"${tcmCaipuList2[0].chen}",
											"${tcmCaipuList2[0].fu}",
											"${tcmCaipuList2[0].hezhong}" ]
							//菜谱二对应特征值
							} ]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
		</script>
						</c:if> <c:if
							test="${empty caipuList1 && empty caipuList2 && empty tcmCaipuList1 && empty tcmCaipuList2}">
						暂无结果
						</c:if></td>
				</tr>
			</tbody>
		</table>


		<%-- <script type="text/javascript">
			// 路径配置
			require.config({
				paths : {
					echarts : 'http://echarts.baidu.com/build/dist'
				}
			});

			// 西医使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('westCaipu'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '菜谱元素对比图',
						subtext : ''
					},
					legend : {
						data : [ "${caipuList1[0].viewName}",
								"${caipuList2[0].viewName}" ]
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
								"name" : "${caipuList1[0].viewName}",
								"type" : "bar",
								"data" : [ "${caipuList1[0].calories}",
										"${caipuList1[0].carbohydrate}",
										"${caipuList1[0].fat}",
										"${caipuList1[0].protein}",
										"${caipuList1[0].vitamine}",
										"${caipuList1[0].vta}",
										"${caipuList1[0].vtc}",
										"${caipuList1[0].vte}",
										"${caipuList1[0].carotene}",
										"${caipuList1[0].thiamine}",
										"${caipuList1[0].riboflavin}",
										"${caipuList1[0].yansuan}",
										"${caipuList1[0].cholesterol}",
										"${caipuList1[0].mg}",
										"${caipuList1[0].ca}",
										"${caipuList1[0].iron}",
										"${caipuList1[0].zinc}",
										"${caipuList1[0].copper}",
										"${caipuList1[0].mn}",
										"${caipuList1[0].k}",
										"${caipuList1[0].p}",
										"${caipuList1[0].na}",
										"${caipuList1[0].se}" ]
							//菜谱一对应特征值
							},
							{
								"name" : "${caipuList2[0].viewName}",
								"type" : "bar",
								"data" : [ "${caipuList2[0].calories}",
										"${caipuList2[0].carbohydrate}",
										"${caipuList2[0].fat}",
										"${caipuList2[0].protein}",
										"${caipuList2[0].vitamine}",
										"${caipuList2[0].vta}",
										"${caipuList2[0].vtc}",
										"${caipuList2[0].vte}",
										"${caipuList2[0].carotene}",
										"${caipuList2[0].thiamine}",
										"${caipuList2[0].riboflavin}",
										"${caipuList2[0].yansuan}",
										"${caipuList2[0].cholesterol}",
										"${caipuList2[0].mg}",
										"${caipuList2[0].ca}",
										"${caipuList2[0].iron}",
										"${caipuList2[0].zinc}",
										"${caipuList2[0].copper}",
										"${caipuList2[0].mn}",
										"${caipuList2[0].k}",
										"${caipuList2[0].p}",
										"${caipuList2[0].na}",
										"${caipuList2[0].se}" ]
							//菜谱二对应特征值
							} ]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
			
			
			// 中医使用
			require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
			], function(ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('tcmCaipu'));

				var option = {
					tooltip : {
						show : true
					},
					title : {
						text : '菜谱元素对比图',
						subtext : ''
					},
					legend : {
						data : [ "${tcmCaipuList1[0].name}",
								"${tcmCaipuList2[0].name}" ]
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
								"name" : "${tcmCaipuList1[0].name}",
								"type" : "bar",
								"data" : [ "${tcmCaipuList1[0].fei}",
										"${tcmCaipuList1[0].xin}",
										"${tcmCaipuList1[0].piwei}",
										"${tcmCaipuList1[0].gandan}",
										"${tcmCaipuList1[0].shen}",
										"${tcmCaipuList1[0].sanjiao}",
										"${tcmCaipuList1[0].qi}",
										"${tcmCaipuList1[0].xue}",
										"${tcmCaipuList1[0].yin}",
										"${tcmCaipuList1[0].yang}",
										"${tcmCaipuList1[0].xing}",
										"${tcmCaipuList1[0].ku}",
										"${tcmCaipuList1[0].gan}",
										"${tcmCaipuList1[0].suan}",
										"${tcmCaipuList1[0].xian}",
										"${tcmCaipuList1[0].dan}",
										"${tcmCaipuList1[0].sheng}",
										"${tcmCaipuList1[0].jiang}",
										"${tcmCaipuList1[0].chen}",
										"${tcmCaipuList1[0].fu}",
										"${tcmCaipuList1[0].hezhong}" ]
							//菜谱一对应特征值
							},
							{
								"name" : "${tcmCaipuList2[0].name}",
								"type" : "bar",
								"data" : [ "${tcmCaipuList2[0].fei}",
											"${tcmCaipuList2[0].xin}",
											"${tcmCaipuList2[0].piwei}",
											"${tcmCaipuList2[0].gandan}",
											"${tcmCaipuList2[0].shen}",
											"${tcmCaipuList2[0].sanjiao}",
											"${tcmCaipuList2[0].qi}",
											"${tcmCaipuList2[0].xue}",
											"${tcmCaipuList2[0].yin}",
											"${tcmCaipuList2[0].yang}",
											"${tcmCaipuList2[0].xing}",
											"${tcmCaipuList2[0].ku}",
											"${tcmCaipuList2[0].gan}",
											"${tcmCaipuList2[0].suan}",
											"${tcmCaipuList2[0].xian}",
											"${tcmCaipuList2[0].dan}",
											"${tcmCaipuList2[0].sheng}",
											"${tcmCaipuList2[0].jiang}",
											"${tcmCaipuList2[0].chen}",
											"${tcmCaipuList2[0].fu}",
											"${tcmCaipuList2[0].hezhong}" ]
							//菜谱二对应特征值
							} ]
				};

				// 为echarts对象加载数据 
				myChart.setOption(option);
			});
		</script> --%>
	</div>
</body>
</html>
