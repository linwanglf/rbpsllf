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
    $(function() {
        $("#file_form").submit(
            function() {
                //首先验证文件格式
                var fileName = $('#file_input').val();
                if (fileName === '') {
                    alert('请选择文件');
                    return false;
                }
                var fileType = (fileName.substring(fileName
                        .lastIndexOf(".") + 1, fileName.length))
                        .toLowerCase();
                if (fileType !== 'xls' && fileType !== 'xlsx') {
                    alert('文件格式不正确，excel文件！');
                    return false;
                }

                $("#file_form").ajaxSubmit({
                    dataType : "json",
                    success : function(data, textStatus) {
                        if (data['result'] === 'OK') {
                            console.log('上传文件成功');
                        } else {
                            console.log('文件格式错误');
                        }
                        return false;
                    }
                });
                return false;
            });

    });
</script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>

<form action="/excel/uploadimage" method="post" enctype="multipart/form-data">
本地目录：<input type="file" name="uploadFile">
<img src="${image_path}">
<input type="submit" value="上传头像"/>
</form>

<form action="/excel/showimage" method="post" >
    <img src="${show_image_path}">
    <input type="submit" value="显示头像"/>
</form>


</body>

</html>