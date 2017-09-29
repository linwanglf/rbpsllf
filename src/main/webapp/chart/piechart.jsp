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
    <script type="text/javascript" src="../highchart/code/highcharts.js"></script>
    <script type="text/javascript" src="../highchart/code/highcharts-more.js"></script>
    <script type="text/javascript" src="../highchart/code/modules/exporting.js"></script>　
    <!--highchart基础图-->

    <script type="text/javascript">
        function displaychart() {
            $('#container').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: '扇区突出演示'
                },
                tooltip: {
                    headerFormat: '{series.name}<br>',
                    pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        },
                        states: {
                            hover: {
                                enabled: false
                            }
                        },
                        slicedOffset: 20,         // 突出间距
                        point: {                  // 每个扇区是数据点对象，所以事件应该写在 point 下面
                            events: {
                                // 鼠标滑过是，突出当前扇区
                                mouseOver: function() {
                                    this.slice();
                                },
                                // 鼠标移出时，收回突出显示
                                mouseOut: function() {
                                    this.slice();
                                },
                                // 默认是点击突出，这里屏蔽掉
                                click: function() {
                                    return false;
                                }
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '浏览器访问量占比',
                    data: [
                        ['Firefox',   45.0],
                        ['IE',       26.8],
                        {
                            name: 'Chrome',
                            y: 12.8,
                            sliced: true, // 突出显示这个点（扇区），用于强调。
                        },//这里为什么又用{}可以
                        ['Safari',    8.5],
                        ['Opera',     6.2],
                        ['其他',   0.7]
                    ]
                }]
            });
        }
    </script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<tr>
    <a href="javascript:displaychart()" class="easyui-linkbutton" iconCls="icon-cancel" >显示图表</a>
</tr>
</body>

</html>