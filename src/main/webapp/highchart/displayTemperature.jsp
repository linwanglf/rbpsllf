<%--
  Created by IntelliJ IDEA.
  User: xxjs-gd-llf
  Date: 2017/7/18
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>曲线图表</title>
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/code/highcharts.js"></script>
    <script type="text/javascript" src="/js/code/highcharts-more.js"></script>
    <script type="text/javascript" src="/js/code/modules/exporting.js"></script>　
    <%--下载控件图标加载的JS--%>

    <script type="text/javascript">
        $(function(){
            var options={
                chart :{ type:'',
                    renderTo:'container'},
                title : {
                    text : '设备CPU温度监测' //指定图表标题
                },
                subtitle:{ text:'www.sigezn.com' }, //子标题
                xAxis:{ categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']}, //X轴
                yAxis:{ title:'Temperature (\xB0C)' },
                plotOptions:{
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series : [{
                    name: 'Tokyo',
                    data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                }, {
                    name: 'London',
                    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                }
                ]
            } ;


            window.setInterval( function(){ $.getJSON("Ｆ?ip="+ document.getElementById("ip").value, function(data) {
                options.series = data;
                var chart = new Highcharts.Chart(options);
            })},5000);


            var optionsCpuUsedRate={
                chart :{ type:'',
                    renderTo:'containercpu'},
                title : {
                    text : '设备CPU使用率监测' //指定图表标题
                },
                subtitle:{ text:'www.sigezn.com' }, //子标题
                xAxis:{ categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']}, //X轴
                yAxis:{ title:'Temperature (\xB0C)' },
                plotOptions:{
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series : [{
                    name: 'Tokyo',
                    data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                }, {
                    name: 'London',
                    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
                }
                ]
            } ;

            window.setInterval( function(){ $.getJSON("/cpuusedrate?ip="+ document.getElementById("ip").value, function(data) {
                optionsCpuUsedRate.series = data;
                var chartCpuUsedRate = new Highcharts.Chart(optionsCpuUsedRate);
            })},5000);
        });
        /*
         通过getJSON函数请求后台数据，并将获取到的数据回调给function(Data).
         options.series = data;渲染数据
         window.setInterval(function(){},5000) 每隔5S请求一次后台数据
         */
    </script>

</head>
<body>
<tr>
    <form>
        IP: <input type="text" name="ip" id="ip" value="192.168.3.2">
    </form>

</tr>

<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<div id="containercpu" style="width: 550px; height: 400px; margin: 0 auto"></div>
</body>
</html>
