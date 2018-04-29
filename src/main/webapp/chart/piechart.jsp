<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Column柱状图</title>
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
    <script type="text/javascript" src="/js/code/highcharts.js"></script>
    <script type="text/javascript" src="/js/code/highcharts-more.js"></script>
    <script type="text/javascript" src="/js/code/modules/exporting.js"></script>　
    <!--highchart基础图-->

    <script type="text/javascript">
        function displaychart(){
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
    </script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<tr>
    <a href="javascript:displaychart()" class="easyui-linkbutton" iconCls="icon-cancel" >显示饼图</a>
</tr>
</body>

</html>