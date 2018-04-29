<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>line曲线图</title>
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
    <%--<script type="text/javascript" src="/js/highcharts-more.js"></script>　--%>
    <!--highchart基础图-->

    <script type="text/javascript">
        function displaychart(){
            $.getJSON("/chart/linechart" , function(jsondata) {
                var chart = new Highcharts.Chart('container', {
                    title: {
                        text: '不同城市的月平均气温',
                        x: -20
                    },
                    subtitle: {
                        text: '数据来源: WorldClimate.com',
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
                    series:jsondata
                });
            });
        }
    </script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<tr>
    <a href="javascript:displaychart()" class="easyui-linkbutton" iconCls="icon-cancel" >显示曲线图</a>
</tr>
</body>

</html>