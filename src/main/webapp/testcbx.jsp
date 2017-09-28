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

    <script type="text/javascript">
        function showvalue() {
            alert('here');
            var value = $('#state').combobox('getValue'); //option 里的Value
            var Text =  $('#state').combobox('getText'); //下拉框里看到的Text注意大小写
            alert(value);
            alert(Text);
        }

    </script>
</head>
<body>
<div>
    <table>
        <tr>
            <td style="text-align:left">部省市区四级联动:</td>
            <td>
               区域: <input id="cbxregion" class="easyui-combobox" name="regionId" editable="false" data-options="
                required:true,
                valueField: 'regioncode',<%--对应reion表字段--%>
                textField: 'regionname',<%--对应reion表字段--%>
                url: '/freightTemplate/queryRegionList',<%--对应web.xml配置RegionServlet--%>
                onSelect: function(rec){
                    $('#cbxprovince').combobox('clear');
                    $('#cbxcity').combobox('clear');
                    $('#cbxarea').combobox('clear');
                    var url = '/freightTemplate/queryProvinceList?regioncode='+rec.regioncode; <%--对应web.xml配置ProvinceServlet--%>
                    $('#cbxprovince').combobox('reload', url);
                   }">
               省份: <input id="cbxprovince" class="easyui-combobox" name="provinceId" editable="false" data-options="
                    required:true,
                    valueField: 'provincecode',
                    textField: 'provincename',
                    onSelect: function(rec){
                        $('#cbxprovince').combobox('reload', url);
                        $('#cbxcity').combobox('clear');
                        $('#cbxarea').combobox('clear');
                        var url = '/freightTemplate/queryCityList?provincecode='+rec.provincecode;
                        $('#cbxcity').combobox('reload', url);
                     }">
                城市: <input id="cbxcity" class="easyui-combobox" name="cityId" editable="false" data-options="
                    required:true,
                    valueField: 'citycode',
                    textField: 'cityname',
                    onSelect: function(rec){
                        $('#cbxcity').combobox('reload', url);
                        $('#cbxarea').combobox('clear');
                        var url = '/freightTemplate/queryAreaList?citycode='+rec.citycode;
                        $('#cbxarea').combobox('reload', url);
                     }">
                区:<input id="cbxarea" class="easyui-combobox" name="areaId" editable="false" data-options="
                    required:true,
                    valueField:'areacode',
                    textField:'areaname'">
            </td>
        </tr>
    </table>
</div>

</body>
</html>