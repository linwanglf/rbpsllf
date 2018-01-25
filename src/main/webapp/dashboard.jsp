<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--<%@ include file="/WEB-INF/tlds/taglib.jsp"%>--%>

<!DOCTYPE html>
<html>
	<head>
		<title>设备运营监控</title>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="/style/system/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="/static/layui2/css/layui.css">
		<link rel="stylesheet" type="text/css" href="/static/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="/style/temp/dashboard.css">
	</head>

	<body>
		<div class="bg-container">
			<div class="layui-layout layui-layout-admin">
				<header class="layui-header header">
					<div class="layui-main">
						<div class="layui-form component">

					    </div>
					    <h1 class="title">发动机本部设备运营大盘监控</h1>
					</div>
				</header>
			</div>

			<select name="ipaddress" id="ipaddress"  >
				<option value="192.168.3.1" selected>192.168.3.1</option>
				<option value="192.168.3.2" > 192.168.3.2</option>
				<option value="192.168.3.3" >192.168.3.3</option>
			</select>

			<table align="center">
				<tr>
			<td>
				<div id="temperature" style="width: 1000px;height:400px; display: inline"></div>
			</td>
				<td>
					<div id="cpurate" style="width: 1000px;height:400px; display: inline"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="memoryrate" style="width: 1000px;height:400px; display: inline"></div>
				</td>
				<td>
					<div id="gacprocess" style="width: 1000px;height:400px; display: inline"></div>
				</td>
			</tr>
			</table>
    	</div>
	<script type="text/javascript" src="/script/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="/script/kapok.js"></script>
	<script type="text/javascript" src="/static/layui2/layui.js"></script>
	<script type="text/javascript" src="/static/echart/echarts.min.js"></script>
	<script type="text/javascript" src="/script/temp/common.js"></script>
	<script>
        $( function() {
                var temperatureChart = echarts.init(document.getElementById('temperature'));
                var cpuRateChart = echarts.init(document.getElementById('cpurate'));
                var memoryRateChart = echarts.init(document.getElementById('memoryrate'));
                var gacProcessChart = echarts.init(document.getElementById('gacprocess'));
                var interval =3000;
                //温度
                var temperaturesuccess=function(result){
                    //CPU温度
                    var xDateTimes = [];    //类别数组（实际用来盛放X轴坐标值）
                    var yCpuTemperatures = [];    //销量数组（实际用来盛放Y坐标值）
                    var temperatureOption = {
                        title: {
                            text: 'CPU温度变化',
                            textStyle: {
                                //文字颜色
                                color: '#FFFF00',
                                //字体风格,'normal','italic','oblique'
                                fontStyle: 'normal',
                                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                                fontWeight: 'bold',
                                //字体系列
                                fontFamily: 'sans-serif',
                                //字体大小
                                fontSize: 18
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data: xDateTimes,
                            axisLine: {
                                lineStyle: {
                                    color: '#FF0000',
                                    width: 1,//这里是为了突出显示加上的
                                }
                            }
                        },
                        yAxis: {
                            type: 'value',
                            axisLine: {
                                lineStyle: {
                                    color: '#FFFF00',
                                    width: 1,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                formatter: '{value} °C'
                            }
                        },
                        series: [{
                            type: 'line',
                            data: yCpuTemperatures

                        }]
                    };
                    if (result) {
                        for (var i = 0; i < result.length; i++) {
                            xDateTimes.push(result[i].xKey);
                        }
                        for (var i = 0; i < result.length; i++) {
                            yCpuTemperatures.push(result[i].yValue);
                        }
                        temperatureChart.setOption(temperatureOption);
                    }
                };
                setInterval(function () {
                    var ipAddress = document.getElementById("ipaddress").value;
                    $.ajax({ type: "post", async: true, url: "/temperature", data: {ipAddress:ipAddress}, dataType: "json",success:temperaturesuccess })
                }, interval);

                //CPU使用率
                var cpuRateFunction = function(result) {
                    var xCpuDateTimes=[];    //类别数组（实际用来盛放X轴坐标值）
                    var yCpuRate=[];    //销量数组（实际用来盛放Y坐标值）
                    var cpuRateOption={
                        title: {
                            text: 'CPU使用率',
                            textStyle:{
                                //文字颜色
                                color:'#FFFF00',
                                //字体风格,'normal','italic','oblique'
                                fontStyle:'normal',
                                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                                fontWeight:'bold',
                                //字体系列
                                fontFamily:'sans-serif',
                                //字体大小
                                fontSize:18
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data:xCpuDateTimes,
                            axisLine:{
                                lineStyle:{
                                    color:'#FF0000',
                                    width:1,//这里是为了突出显示加上的
                                }
                            }
                        },
                        yAxis: {
                            type: 'value',
                            axisLine:{
                                lineStyle:{
                                    color:'#FFFF00',
                                    width:1,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                formatter: '{value} %'
                            }
                        },
                        series: [{
                            type: 'line',
                            data: yCpuRate

                        }]
                    };
                    if (result) {
                        for(var i=0;i<result.length;i++){
                            xCpuDateTimes.push(result[i].xKey);
                        }
                        for(var i=0;i<result.length;i++){
                            yCpuRate.push(result[i].yValue);

                        }
                        cpuRateChart.setOption(cpuRateOption);
                    }
                };
                setInterval(function() {
                    var ipAddress = document.getElementById("ipaddress").value;
                    $.ajax({type : "post",async : true,url : "/cpu",data : {ipAddress:ipAddress},	dataType : "json",success : cpuRateFunction
                })},interval);

                //内存使用率
                var memoryRateFunction = function(result) {
                    var xMemoryDateTimes=[];    //类别数组（实际用来盛放X轴坐标值）
                    var yMemoryRate=[];    //销量数组（实际用来盛放Y坐标值）
                    var memoryRateOption={
                        title: {
                            text: '内存使用率变化' ,
                            textStyle:{
                                //文字颜色
                                color:'#FFFF00',
                                //字体风格,'normal','italic','oblique'
                                fontStyle:'normal',
                                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                                fontWeight:'bold',
                                //字体系列
                                fontFamily:'sans-serif',
                                //字体大小
                                fontSize:18
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data:xMemoryDateTimes,
                            axisLine:{
                                lineStyle:{
                                    color:'#FF0000',
                                    width:1,//这里是为了突出显示加上的
                                }
                            }
                        },
                        yAxis: {
                            type: 'value',
                            axisLine:{
                                lineStyle:{
                                    color:'#FFFF00',
                                    width:1,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                formatter: '{value} %'
                            }
                        },
                        series: [{
                            type: 'line',
                            data: yMemoryRate

                        }]
                    };
                    if (result) {
                        for(var i=0;i<result.length;i++){
                            xMemoryDateTimes.push(result[i].xKey);
                        }
                        for(var i=0;i<result.length;i++){
                            yMemoryRate.push(result[i].yValue);

                        }
                        memoryRateChart.setOption(memoryRateOption);
                    }
                };
                setInterval(function() {
                    var ipAddress = document.getElementById("ipaddress").value;
                    $.ajax({type : "post",	async : true,url : "/memory",data : {ipAddress:ipAddress},dataType : "json",success : memoryRateFunction
                })},interval);

                //GAC进程
                var gacProcessFunction =function(result) {
                    var xGacProcessDateTimes=[];    //类别数组（实际用来盛放X轴坐标值）
                    var yGacProcess=[];    //销量数组（实际用来盛放Y坐标值）
                    var gacProcessOption={
                        title: {
                            text: 'GAC进程监控(1正常,0异常)',
                            textStyle:{
                                //文字颜色
                                color:'#FFFF00',
                                //字体风格,'normal','italic','oblique'
                                fontStyle:'normal',
                                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                                fontWeight:'bold',
                                //字体系列
                                fontFamily:'sans-serif',
                                //字体大小
                                fontSize:18
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data:xGacProcessDateTimes,
                            axisLine:{
                                lineStyle:{
                                    color:'#FF0000',
                                    width:1,//这里是为了突出显示加上的
                                }
                            }
                        },
                        yAxis: {
                            type: 'value',
                            axisLine:{
                                lineStyle:{
                                    color:'#FFFF00',
                                    width:1,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                formatter: '{value}'
                            }
                        },
                        series: [{
                            type: 'line',
                            data: yGacProcess

                        }]
                    };
                    if (result) {
                        for(var i=0;i<result.length;i++){
                            xGacProcessDateTimes.push(result[i].xKey);
                        }
                        for(var i=0;i<result.length;i++){
                            yGacProcess.push(result[i].yValue);
                        }
                        gacProcessChart.setOption(gacProcessOption);
                    }
                };
                setInterval(function() {
                    var ipAddress = document.getElementById("ipaddress").value;
                    $.ajax({type : "post",	async : true,	url : "/gacprocess",data : {ipAddress:ipAddress},dataType : "json",	success : gacProcessFunction
                })},interval);

            }
		);
//        }
	</script>

	</body>
	
</html>
