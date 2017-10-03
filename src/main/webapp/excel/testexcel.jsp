<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>测试Excel导出数据</title>
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
    function exportexcel() {
        document.getElementById("exportexcel").submit();
    }
</script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<form id="exportexcel" name="exportexcel" action="/excel/exportexcel" method="post">
    <tr>
        <a href="javascript:exportexcel()" class="easyui-linkbutton" iconCls="icon-cancel" >导出Excel</a>
    </tr>
</form>


</body>

</html>