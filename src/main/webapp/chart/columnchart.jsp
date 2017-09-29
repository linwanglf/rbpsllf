<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>柱状图</title>
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
    </script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<tr>
    <a href="javascript:displaychart()" class="easyui-linkbutton" iconCls="icon-cancel" >显示柱图</a>
</tr>
</body>

</html>