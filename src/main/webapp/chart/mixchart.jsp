<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>混合图</title>
    <!-- 引入JQuery -->
    <script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
    <!-- 引入EasyUI -->
    <script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
    <script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <!-- 引入EasyUI的样式文件-->
    <link rel="stylesheet" href="../jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css"/>
    <!-- 引入EasyUI的图标样式文件-->
    <link rel="stylesheet" href="../jquery-easyui-1.3.3/themes/icon.css" type="text/css"/>

    <!--highchart基础图 依赖的jquery在上面已经引入-->
    <!--highchart基础图 依赖的jquery在上面已经引入-->
    <script type="text/javascript" src="/js/highcharts.js"></script>
    <script type="text/javascript" src="/js/exporting.js"></script>
    <!--highchart基础图-->

    <script type="text/javascript">
        function displaychart() {
            var options = {
                chart :
                    {
                     renderTo: 'container'
                },
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                title : {
                    text : '混合图Demo' //指定图表标题
                },
                subtitle :{
                    text : ' SubTitle ' //指定副标题
                },
                style :{
                    fontFamily: "",
                    fontSize: '12px',
                    fontWeight: 'bold',
                    color: '#006cee'
                },
                xAxis : {
                    categories :['苹果', ' 橙', '梨', '香蕉', '李子']//指定x轴分组
                },
                yAxis : {
                    title : {
                        text : 'something'   // 指定y轴的标题
                    }
                },
                series : [ ]  //指定数据列,这里设为空，我们从后台拿数据
            };

            //从后台获取json格式的数据,在web.xml中配置了/chart/mixchart
            $.getJSON("/chart/mixchart" , function(data) {
                //插入Json数据
                options.series = data;
                var chart = new Highcharts.Chart(options); //通过options中的render to 绑定容器
            })

        }

        function displaychart_columnchart() {
            var options = {
                chart : {
                    type : 'column', //指定图表的类型，默认是折线图（line）
                    renderTo: 'container'
                },
                title : {
                    text : '柱图Demo' //指定图表标题
                },
                subtitle :{
                    text : ' SubTitle ' //指定副标题
                },
                style :{
                    fontFamily: "",
                    fontSize: '12px',
                    fontWeight: 'bold',
                    color: '#006cee'
                },
                xAxis : {
                    categories : [ 'my', 'first', 'chart' ] //指定x轴分组
                },
                yAxis : {
                    title : {
                        text : 'something'   // 指定y轴的标题
                    }
                },
                series : [ ]  //指定数据列,这里设为空，我们从后台拿数据
            };

            //从后台获取json格式的数据,在web.xml中配置了/testdynamicchart请求，处理类为TestDynamicChartServlet
            $.getJSON("/chart/columnchart" , function(data) {
                //插入Json数据
                options.series = data;
                var chart = new Highcharts.Chart(options); //通过options中的render to 绑定容器
            })

        }


        function displaychart_pie(){
            $.getJSON("/chart/piechart" , function(data) {
//                var json = {"a":1,"b":2,"c":3,"d":4};
                var jsondata = [];
                for (var i in data){
                    jsondata.push([i, data[i]])
                }
                var chart = new Highcharts.Chart({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        renderTo: 'container'
                    },
                    title: {
                        text: '饼图Demo'
                    },
                    credits: {
                        //text: 'www.yechoor.cn'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            },
                            showInLegend: true
                        },
                    },
                    series: [{
                        name: "blog percent",
                        colorByPoint: true,
                        data:jsondata
                    }]
                });
            });
        }


        function displaychart_line(){
            $.getJSON("/chart/linechart" , function(jsondata) {
         //      var jsondata = [{"data": [1, 0, 4, 8, 20, 30, 22, 33, 20, 18, 29], "name": "GuangZhou"}];
                var chart = new Highcharts.Chart('container', {
                    title: {
                        text: '不同城市的月平均气温',
                        x: -20
                    },
                    subtitle: {
                        text: '数据来源: 3f.com',
                        x: -20
                    },
                    xAxis: {
                        categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                    },
                    yAxis: {
                        title: {
                            text: '温度 (°C)'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valueSuffix: '°C'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: jsondata
                });
            });
        }

        function displaychart_zuhe() {
            $(document).ready(function() {
                var chart = {
                    type: 'column'
                };
                var title = {
                    text: '月平均气温'
                };
                var subtitle = {
                    text: 'sigezn'
                };
                var xAxis = {
                    categories: ['一月', '二月', '三月', '四月', '五月', '六月','七月', '八月', '九月', '十月', '十一月', '十二月']

                };
                var yAxis = {
                    title: {
                        text: 'Temperature (\xB0C)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                };

                var tooltip = {
                    valueSuffix: '\xB0C'
                }

                var legend = {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                };

                var plotOptions = {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                };

                var series =  [
                    {
                        name: 'Tokyo',
                        data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,
                            26.5, 23.3, 18.3, 13.9, 9.6]
                    },
                    {
                        name: 'New York',
                        data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8,
                            24.1, 20.1, 14.1, 8.6, 2.5]
                    },
                    {
                        name: 'Berlin',
                        data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6,
                            17.9, 14.3, 9.0, 3.9, 1.0]
                    },
                    {
                        name: 'London',
                        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0,
                            16.6, 14.2, 10.3, 6.6, 4.8]
                    }
                ];

                var json = {};

                json.chart=chart;
                json.title = title;
                json.subtitle = subtitle;
                json.xAxis = xAxis;
                json.yAxis = yAxis;
                json.tooltip = tooltip;
                json.legend = legend;
                json.plotOptions = plotOptions;
                json.series = series;

                $('#container').highcharts(json);
            });
        }
    </script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<tr>
    <a href="javascript:displaychart()" class="easyui-linkbutton" iconCls="icon-cancel" >显示混合图</a>
</tr>
<tr>
    <a href="javascript:displaychart_columnchart()" class="easyui-linkbutton" iconCls="icon-cancel" >显示柱图</a>
</tr>
<tr>
    <a href="javascript:displaychart_pie()" class="easyui-linkbutton" iconCls="icon-cancel" >显示饼图</a>
</tr>
<tr>
    <a href="javascript:displaychart_line()" class="easyui-linkbutton" iconCls="icon-cancel" >显示曲线图</a>
</tr>
<tr>
    <a href="javascript:displaychart_zuhe()" class="easyui-linkbutton" iconCls="icon-cancel" >显示复杂图</a>
</tr>
</body>

</html>