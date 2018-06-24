<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/tld/options" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>导出数据到EXECL</title>

    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>


    <script type="text/javascript">
        var url;


        $(function () {
            var _master = $('#s_division').combobox({
                url:'role?action=comBoListdivision',
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#s_division').combobox('getValue');
                    //document.getElementById("parentId").value=parentId_sel;

                    $("#s_parentId").val(parentId_sel);//给隐形变量赋值
                    $("#tex_division").val($('#s_division').combobox('getText'));//给隐形变量赋值
                    //alert("当前值部门: " + $("#tex_division").val());

                    $('#s_depart').combobox('clear');
                    $('#s_departmentname').combobox('clear');
                    $('#s_classname').combobox('clear');
                    var url= 'role?action=comBoListdepart&parentId_select='+parentId_sel;
                    $('#s_depart').combobox('reload', url);
                }
            });

            var _slave1 = $('#s_depart').combobox({
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#s_depart').combobox('getValue');
                    //document.getElementById("parentId").value=parentId_sel;
                    $("#s_parentId").val(parentId_sel);//给隐形变量赋值
                    $("#tex_depart").val($('#s_depart').combobox('getText'));//给隐形变量赋值
                    //alert("当前值科室: " + $("#tex_depart").val());

                    $('#s_depart').combobox('reload', url);
                    $('#s_departmentname').combobox('clear');
                    $('#s_classname').combobox('clear');
                    var url = 'role?action=comBoListdepartment&parentId_select='+parentId_sel;
                    $('#s_departmentname').combobox('reload', url);

                }
            });

            var _slave2 = $('#s_departmentname').combobox({
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#s_departmentname').combobox('getValue');
                    //document.getElementById("parentId").value=parentId_sel;
                    $("#s_parentId").val(parentId_sel);//给隐形变量赋值

                    $("#tex_departmentname").val($('#s_departmentname').combobox('getText'));//给隐形变量赋值
                    //alert("当前值系: " + $("#tex_departmentname").val());

                    $('#s_departmentname').combobox('reload', url);
                    $('#s_classname').combobox('clear');
                    var url = 'role?action=comBoListclass&parentId_select='+parentId_sel;
                    $('#s_classname').combobox('reload', url);

                }
            });
            var _slave3 = $('#s_classname').combobox({
                disabled: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#s_classname').combobox('getValue');
                    //document.getElementById("parentId").value=parentId_sel;
                    $("#s_parentId").val(parentId_sel);//给隐形变量赋值

                    $("#tex_classname").val($('#s_classname').combobox('getText'));//给隐形变量赋值
                    //alert("当前值系: " + $("#tex_classname").val());
                }
            });

        });

        function searchmenber(){
            // alert("当前值: " + document.getElementById("s_menberName").value);
            //alert("当前值: " + $("#tex_departmentname").val());
            $('#dg').datagrid('load',{//这些参数是url="attentance?action=attentance_planList&s_parentId=-1时使用到，在attentaceServlet.java调用
                s_menberName:$("#s_menberName").val(),
                // s_roleId:$('#s_roleId').combobox("getValue")
                s_menberNo:$("#s_menberNo").val(),
                s_parentId:$("#s_parentId").val(),

                s_division:$("#tex_division").val(),
                s_depart:$("#tex_depart").val(),
                s_departmentname:$("#tex_departmentname").val(),
                s_classname:$("#tex_classname").val()





            });
        }




        function exportexcel() {
            document.getElementById("exportexcel").submit();
        }



    </script>
</head>
<body style="margin: 1px;">
<table id="dg" title="加班计划申请" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true"  url="attentance?action=attentance_planList&s_parentId=-1" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="attId" width="20" align="center">编号</th>
        <th field="menberName" width="40" align="center">姓名</th>
        <th field="menberNo" width="40" align="center">工号</th>
        <th field="division" width="50" align="center">部门</th>
        <th field="department" width="50" align="center">科室</th>
        <th field="departmentname" width="50" align="center">系</th>
        <th field="classname" width="30" align="center">班组</th>
        <th field="apply_date" width="40" align="center" >加班日期</th>
        <th field="plan_houre" width="50" align="center">加班时长</th>
        <th field="att_project" width="100" align="center">加班项目</th>
        <th field="att_type" width="50" align="center">假日类型</th>
        <th field="plan_type" width="30" align="center">计划类型</th>
        <th field="check_state_department" width="20" align="center">审批</th>

    </tr>
    </thead>
</table>
<div id="tb">

    <div>

        部门：<input class="easyui-combobox" id="s_division" name="s_division" size="15" editable="false"
                              data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName' "/>
        &nbsp;科室：<input class="easyui-combobox" id="s_depart" name="s_depart" size="15"
                              data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        &nbsp;系：<input class="easyui-combobox" id="s_departmentname" name="s_departmentname" size="15"
                             data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        &nbsp;班组：<input class="easyui-combobox" id="s_classname" name="s_classname" size="15"
                              data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>

        &nbsp;姓名：<input type="text" name="s_menberName" id="s_menberName" size="10" onkeydown="if(event.keyCode==13) searchmenber()"/>
        &nbsp;工号：<input type="text" name="s_menberNo" id="s_menberNo" size="10" onkeydown="if(event.keyCode==13) searchmenber()"/>

        <input type="hidden" id="s_parentId" name="s_parentId" />
        <a href="javascript:searchmenber()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
    <div>
        <form id="exportexcel" name="exportexcel" action="excel?action=exportex"   method="post">
            <input type="hidden" id="tex_division" name="tex_division" />
            <input type="hidden" id="tex_depart" name="tex_depart" />
            <input type="hidden" id="tex_departmentname" name="tex_departmentname" />
            <input type="hidden" id="tex_classname" name="tex_classname" />
            <a href="javascript:exportexcel()" class="easyui-linkbutton" iconCls="icon-save" plain="true">导出Excel</a>
        </form>
    </div>
</div>


</body>
</html>