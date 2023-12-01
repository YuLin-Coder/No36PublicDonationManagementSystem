<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>利润统计</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/js/echarts.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		showtable();
	});
	
 	function showtable(){
 		$.ajax({
 			type: "POST",
             url: '${ctx}/biz/bizMateriel/getStatisticsData',
             data: $('#searchForm').serialize(),
             dataType: "json",
             success : function (data){ 
 				var x = new Array();
 				var y = new Array();
 				for(var i=0;i<data.length;i++){	
 					x.push(data[i].name);
 					y.push(data[i].priceSum);
 				}
 				createTable(x,y); 
 			}
 		});
 	}
	
	function createTable(x,y){
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));

		// 指定图表的配置项和数据
		option = {
		    title : {
		        text: '物资利润统计图',
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['物资利润']
		    },
		    toolbox: {
		        show : true,
		        feature : {
		        	mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : x,
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        },
		    ],
		    dataZoom: [
		        {
		            type: 'slider',
		            xAxisIndex: 0,
		            filterMode: 'empty'
		        }
		    ],
		    series : [
		        {
		            name: '物资利润',
		            type:'bar',
		            data: y,
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">利润统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bizMateriel" action="${ctx}/biz/bizMateriel/showStatisticsChart" method="post" class="breadcrumb form-search ">
		<ul class="ul-form">
			<li><label>物资类型：</label>
				<form:select path="supplyOrigin" class="input-xlarge">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('supply_origin_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<li class="btns"> <input value="打印" class="btn btn-primary" type="button" onclick="window.print()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="main" style="width: 100%;height:400px;"></div>
</body>
</html>