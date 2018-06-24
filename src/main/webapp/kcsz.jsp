<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>出勤记录</title>

    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

</head>
<body style="margin: 1px;">
<div id="groupForm" class="easyui-dialog" style="width:425px;height:350px;" closed="true" buttons="#dlg-buttons" data-options="modal:true">
    <div class="easyui-panel" style="width:410px">
        <div style="padding:10px 60px 20px 60px">
            <form id="userGroupForm" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>名称:</td>
                        <td><input class="easyui-validatebox textbox" type="text" name="name" id="name" data-options="required:true"></input></td>
                    </tr>
                    <tr>
                        <td>说明:</td>
                        <td><input class="easyui-validatebox textbox" type="text" id="description" name="description"></input></td>
                    </tr>
                    <tr>
                        <td>用户权限:</td>
                        <td>

                            <select id="groupCombobox" name="groupCombobox" class="easyui-combobox" style="width:200px;" data-options="required:true">
                                <option value="1" selected="selected">系统管理员</option>
                                <option value="2">普通用户</option>
                                <option value="3">自定义权限</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>

                            <input type="checkbox" value="1" checked name="userGroupRight">查询本人导入的检查记录</input><br>

                            <input type="checkbox" value="2" checked name="userGroupRight">查询所有检查记录</input><br>

                            <input type="checkbox" value="3" checked name="userGroupRight">查询本人的操作日志</input><br>

                            <input type="checkbox" value="4" checked name="userGroupRight">查询所有操作日志</input><br>

                            <input type="checkbox" value="5" checked name="userGroupRight">修改用户密码</input><br>

                            <input type="checkbox" value="6" checked name="userGroupRight">管理用户</input>
                        </td>
                    </tr>
                </table>
            </form>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="userGroupsubmitForm()">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="userGroupclearForm()">取消</a>
            </div>
        </div>
    </div>
    <script>
        function userGroupsubmitForm(){
            $('#userGroupForm').form('submit', {
                url:'groupController.do?addgroup',
                onSubmit: function(){
                    return $(this).form('validate');
                }
            });
            /* $('#groupForm').dialog('close');
             $('#userTreegrid').treegrid('reload'); */
        }
        function userGroupclearForm(){
            $('#groupForm').dialog('close');
        }

        //选择后触发事件
        $(function(){
            $("#groupCombobox").combobox({
                onSelect:function(record){
                    var curValue=$('#groupCombobox').combobox('getValue');
                    if(curValue==1){
                        $("[name='userGroupRight']").attr("checked",'true');
                    }else if(curValue==2){
                        $("[name='userGroupRight']").removeAttr("checked");
                        $("[name='userGroupRight']").each(function(){
                            if($(this).val()==1){
                                $(this).attr("checked",'true');
                            }
                            if($(this).val()==3){
                                $(this).attr("checked",'true');
                            }
                            if($(this).val()==5){
                                $(this).attr("checked",'true');
                            }
                        })
                    }else{
                        $("[name='userGroupRight']").removeAttr("checked");
                    }
                }
            });
        });
    </script>
</div>