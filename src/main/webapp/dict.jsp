<%@ page language="java" contentType="text/html; charset=utf-8"		 pageEncoding="utf-8"%>
<%@ taglib uri="/tld/options" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据字典管理</title>
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
            s_dictType:$("#s_dictType").val(),
//			s_roleId:$('#s_roleId').combobox("getValue")
		});
	}
	
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加字典信息");
//		$("#userName").removeAttr("readonly");
		url="dict?action=save";
	}
	
	function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert('系统提示','请选择一条要编辑的数据！');
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","修改用户信息");
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
		$("#dlg2").dialog("open").dialog("setTitle","选择角色");
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
		    console.log("selectedRows[i].dict_id " +  selectedRows[i].dict_id );
			strIds.push(selectedRows[i].dict_id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("dict?action=delete",{dictId:ids},function(result){
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
</script>
</head>
<body style="margin: 1px;">
<table id="dg" title="数据字典管理" class="easyui-datagrid" fitColumns="true"
    pagination="true" rownumbers="true" url="dict?action=list" fit="true" toolbar="#tb">
    <thead>
    	<tr>
    		<th field="cb" checkbox="true" align="center"></th>
    		<th field="dict_id" width="50" align="center">编号</th>
    		<th field="dict_type" width="100" align="center">字典类型</th>
    		<th field="dict_name" width="100" align="center">字典名</th>
    		<th field="dict_desc" width="150" align="center">字典值</th>
			<th field="state" width="150" align="center" >状态</th>
    		<th field="SHOW_SEQ" width="150" align="center">显示顺序</th>
    		<th field="IS_DEFAULT" width="300" align="center">是否默认</th>
			<th field="CAN_MODIFY" width="300" align="center">能否修改</th>
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
		&nbsp;字典类型：&nbsp;<input type="text" name="s_dictType" id="s_dictType" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
		<%--&nbsp;用户角色：&nbsp;<input class="easyui-combobox" id="s_roleId" name="s_roleId" size="20" data-options="editable:false,panelHeight:'auto',valueField:'roleId',textField:'roleName',url:'role?action=comBoList'"/>--%>
		&nbsp;支付状态：&nbsp;<select class="easyui-combobox" name="paystat" style="width:100px;"> <html:options dicttype="PAYSTAT">  </html:options></select>
		<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="5px;">
  		<tr>
  			<td>字典类型：</td>
  			<td><input type="text" id="dictType" name="dictType" class="easyui-validatebox" required="true"/></td>
  			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  			<td>字典名称：</td>
  			<td><input type="text" id="dictName" name="dictName" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td>字典值：</td>
  			<td><input type="hidden" id="dictId" name="dictId" />
				<input type="text" id="dictDesc" name="dictDesc" class="easyui-validatebox" required="true"/></td>
  		</tr>
  		<tr>
  			<td valign="top">默认：</td>
  			<td colspan="4">
  				<textarea rows="7" cols="50" name="isDefault" id="isDefault"></textarea>
  			</td>
  		</tr>
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeUserAddDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>


<div id="dlg2" class="easyui-dialog" iconCls="icon-search" style="width: 500px;height: 480px;padding: 10px 20px"
  closed="true" buttons="#dlg2-buttons">
  <div style="height: 40px;" align="center">
  	角色名称：<input type="text" id="s_roleName" name="s_roleName" onkeydown="if(event.keyCode==13) searchRole()"/>
  	<a href="javascript:searchRole()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
  </div>
  <div style="height: 350px;">
  	<table id="dg2" title="查询结果" class="easyui-datagrid" fitColumns="true"
    pagination="true" rownumbers="true" url="role?action=list" singleSelect="true" fit="true" >
    <thead>
    	<tr>
    		<th field="roleId" width="50" align="center">编号</th>
    		<th field="roleName" width="100" align="center">角色名称</th>
    		<th field="roleDescription" width="200" align="center">备注</th>
    	</tr>
    </thead>
</table>
  </div>
</div>

<div id="dlg2-buttons">
	<a href="javascript:chooseRole()" class="easyui-linkbutton" iconCls="icon-ok" >确定</a>
	<a href="javascript:closeRoleDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>
</body>
</html>