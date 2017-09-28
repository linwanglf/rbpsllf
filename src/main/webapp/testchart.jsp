<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>测试四级下拉级联</title>
    <!-- 引入JQuery -->
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <!-- 引入EasyUI -->
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <!-- 引入EasyUI的样式文件-->
    <link rel="stylesheet" href="jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css"/>
    <!-- 引入EasyUI的图标样式文件-->
    <link rel="stylesheet" href="jquery-easyui-1.3.3/themes/icon.css" type="text/css"/>

    <!--highchart基础图 依赖的jquery在上面已经引入-->
    <script type="text/javascript" src="highchart/code/highcharts.js"></script>
    <script type="text/javascript" src="highchart/code/highcharts-more.js"></script>
    <script type="text/javascript" src="highchart/code/modules/exporting.js"></script>　
    <!--highchart基础图-->


</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script language="JavaScript">
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
            categories: ['一月', '二月', '三月', '四月', '五月', '六月'
                ,'七月', '八月', '九月', '十月', '十一月', '十二月']
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
</script>
</body>

</html>