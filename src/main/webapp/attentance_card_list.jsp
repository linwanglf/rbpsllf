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
    <script type="text/javascript">
        var url;
        $(function(){
            $("#dg2").datagrid({
                onDblClickRow:function(rowIndex,rowData){
                    chooseRole();
                }
            });
        })

        function searchUser(){
            $('#dg').datagrid('load',{
                s_userName:$("#s_userName").val(),
                s_roleId:$('#s_roleId').combobox("getValue")
            });
        }

        function openUserAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加加班项目信息");
            $("#userName").removeAttr("readonly");
            url="user?action=save";
        }

        function openUserModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert('系统提示','请选择一条要编辑的数据！');
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","修改加班项目信息");
            $("#fm").form("load",row);
            $("#userName").attr("readonly","readonly");
            url="user?action=save&userId="+row.userId;
        }

        function saveUser(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    return $(this).form("validate");
                },
                success:function(result){
                    var result=eval('('+result+')');
                    if(result.errorMsg){
                        $.messager.alert('系统提示',"<font color=red>"+result.errorMsg+"</font>");
                        return;
                    }else{
                        $.messager.alert('系统提示','保存成功');
                        closeUserAddDialog();
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function closeUserAddDialog(){
            $("#dlg").dialog("close");
            $("#fm").form('clear');
        }


        function openRoleChooseDialog(){
            $("#dlg2").dialog("open").dialog("setTitle","选择项目人员");
        }

        function searchRole(){
            $('#dg2').datagrid('load',{
                s_roleName:$("#s_roleName").val()
            });
        }

        function closeRoleDialog(){
            $("#s_roleName").val("");
            $('#dg2').datagrid('load',{
                s_roleName:""
            });
            $("#dlg2").dialog("close");
        }

        function chooseRole(){
            var selectedRows=$("#dg2").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert('系统提示','请选择一个角色！');
                return;
            }
            var row=selectedRows[0];
            $("#roleId").val(row.roleId);
            $("#roleName").val(row.roleName);
            closeRoleDialog();
        }

        function deleteUser(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert('系统提示','请选择要删除的数据！');
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].userId);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("user?action=delete",{delIds:ids},function(result){
                        if(result.success){
                            $.messager.alert('系统提示',"您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert('系统提示',result.errorMsg);
                        }
                    },"json");
                }
            });
        }




        function openUser_planDialog(){
            var selectedRows=$("#userName");
            if(selectedRows.length!=1){
                $.messager.alert('系统提示','请选择一条要授权的角色！');
                return;
            }
            //var row=selectedRows[0];
            //roleId=row.roleId;
            //roleName=row.roleName;
            roleId=1;
            $("#dlg2").dialog("open").dialog("setTitle","选择人员");
            url="auth?action=menberList&parentId=-1&roleId="+roleId;//需要将 parentId=-1修改为登录人员在表t_menber中对应的parentId

            $("#tree_menberlist").tree({
                lines:true,
                url:url,
                checkbox:true,
                cascadeCheck:false,
                onLoadSuccess:function(){
                    //$("#tree_menberlist").tree('expandAll');
                    $("#tree_menberlist").tree('');
                },
                onCheck:function(node,checked){
                    if(checked){
                        checkNode($('#tree_menberlist').tree('getParent',node.target));
                    }
                }
            });
        }

        function checkNode(node){
            if(!node){
                return;
            }else{
                checkNode($('#tree_menberlist').tree('getParent',node.target));
                $('#tree_menberlist').tree('check',node.target);
            }
        }


        function saveplayer(){
            var nodes=$('#tree_menberlist').tree('getChecked');
            var authArrIds=[];
            for(var i=0;i<nodes.length;i++){
                authArrIds.push(nodes[i].text);
            }
            var authIds=authArrIds.join(",");

            document.getElementById("playername").value=authIds;
            closeplayerDialog();
        }

        function closeplayerDialog(){
            $("#dlg2").dialog("close");
           // $("#fm").form('clear');
            $("#iconCls").val("icon-item");
        }
    </script>
</head>
<body style="margin: 1px;">
<table id="dg" title="加班记录" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="user?action=list" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="userId" width="50" align="center">工号</th>
        <th field="userName" width="100" align="center">姓名</th>
        <th field="password" width="100" align="center">开始时间</th>
        <th field="roleId" width="150" align="center" >离开时间</th>
        <th field="roleName" width="150" align="center">项目内容</th>
        <th field="userDescription" width="300" align="center">备注</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;用户名：&nbsp;<input type="text" name="s_userName" id="s_userName" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
        &nbsp;用户角色：&nbsp;<input class="easyui-combobox" id="s_roleId" name="s_roleId" size="20" data-options="editable:false,panelHeight:'auto',valueField:'roleId',textField:'roleName',url:'role?action=comBoList'"/>
        <a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>
function openRoleChooseDialog(){
$("#dlg2").dialog("open").dialog("setTitle","选择项目相关人员");
}

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="userName" name="userName" class="easyui-validatebox" required="true"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>用户密码：</td>
                <td><input type="text" id="password" name="password" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>作业人员：</td>
                <td><input type="text" id="playername" name="playername" /></td>

                <td>当前操作人员;${currentUser.userName }</td>
                <td colspan="2"><a href="javascript:openUser_planDialog()" class="easyui-linkbutton" >选择人员</a></td>

            </tr>
            <tr>
                <td>班次：</td>
                <td colspan="4">
                    <select name="classname" >
                        <option value="1">正常班</option>
                        <option value="2">早班</option>
                        <option value="3">中班</option>
                        <option value="4">晚班</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td valign="top">备注：</td>
                <td colspan="4">
                    <textarea rows="7" cols="50" name="userDescription" id="userDescription"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
    <a href="javascript:closeUserAddDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>


<div id="dlg2" class="easyui-dialog" style="width: 300px;height: 450px;padding: 10px 20px"
     closed="true" buttons="#dlg2-buttons">
    <ul id="tree_menberlist" class="easyui-tree"></ul>
</div>

<div id="dlg2-buttons">
    <a href="javascript:saveplayer()" class="easyui-linkbutton" iconCls="icon-ok" >选择</a>
    <a href="javascript:closeplayerDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>
</body>
</html>