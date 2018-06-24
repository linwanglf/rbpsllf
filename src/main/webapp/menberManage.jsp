<%@ page language="java" contentType="text/html; charset=utf-8"		 pageEncoding="utf-8"%>
<%@ taglib uri="/tld/options" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	$(function(){
		$('#menbertreeGrid').treegrid({
			url:'auth?action=menberTreeGridMenu&parentId=-1',
			onLoadSuccess:function(){
				$("#menbertreeGrid").treegrid('expandAll');
			}
		});
	});
	
	function formatIconCls(value,row,index){
		return "<div class="+value+">&nbsp;</div>";
	}
	
	function openMenberAddDialog(){
		var node=$('#menbertreeGrid').treegrid('getSelected');
		if(node==null){
			$.messager.alert('系统提示','请选择一个父菜单节点！');
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle","添加组织架构");
		url="auth?action=savemenber&parentId="+node.id;
	}
	
	function openMenberModifyDialog(){
		var node=$('#menbertreeGrid').treegrid('getSelected');
		if(node==null){
			$.messager.alert('系统提示','请选择一个要修改的菜单！');
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle","修改组织架构");
		$("#fm").form("load",node);
		$("#menberName").val(node.text);
		url="auth?action=savemenber&menberId="+node.id;
	}
	
	function deleteMenber(){
		var node=$("#menbertreeGrid").treegrid('getSelected');
		if(node==null){
			$.messager.alert('系统提示','请选择一个要删除的菜单节点！');
			return;
		}
		var parentNode=$("#menbertreeGrid").treegrid('getParent',node.id);
		$.messager.confirm("系统提示","您确认要删除这个菜单节点吗?",function(r){
			if(r){
				$.post("auth?action=deletemenber",{menberId:node.id,parentId:parentNode.id},function(result){
					if(result.success){
						$.messager.alert('系统提示',"您已成功删除这个菜单节点！");
						$("#menbertreeGrid").treegrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}

    function getSelectValue(obj) {
        //var sValue = obj.options[obj.options.selectedIndex].value; //这是取值
        //var sText = obj.options[obj.options.selectedIndex].innerHTML; //这是取文本内容
        //document.getElementById("selectValue").innerHTML = sText + "，他的值为：" + sValue; //测试输出
       //document.getElementById("menberName").value=sValue;

    }
    var s_menu_level;
    $(function () {
        s_menu_level=0;
        var _master = $('#menu_level').combobox({
            editable: false,
            onSelect: function (record) {
                s_menu_level=$('#menu_level').combobox('getValue');
                if(s_menu_level==2){
                    $('#iconCls').val("icon-permission") ;
                }
                else if(s_menu_level==3){
                    $('#iconCls').val("icon-userManage") ;
                }
                else if(s_menu_level==4){
                    $('#iconCls').val("icon-student") ;
                }
                else if(s_menu_level==5){
                    $('#iconCls').val("icon-course") ;
                }
            }
        });
    });
    function saveMenber(){
        if(s_menu_level==0){
            alert("当前值: " + s_menu_level);
            $.messager.alert('系统提示','请选择一个架构层级！');
            return;
        }
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
					closeMenberDialog();
					$("#menbertreeGrid").treegrid("reload");
				}
			}
		});
	}
	
	function closeMenberDialog(){
		$("#dlg").dialog("close");
		$("#fm").form('clear');
		$("#iconCls").val("icon-item");
	}
</script>
</head>
<body style="margin: 1px;">


<table id="menbertreeGrid" title="所属管理" class="easyui-treegrid"
  toolbar="#tb" data-options="idField:'id',treeField:'text',fit:true,fitColumns:true,rownumbers:true">
    <thead>
    	<tr>
    		<th field="id" width="30" align="center">编号</th>
    		<th field="text" width="80">名称</th>
            <th field="menberNO" align="center" width="40">工号</th>
    		<th field="iconCls" width="35" align="center" formatter="formatIconCls" >图标</th>
            <th field="positon" width="20" align="center">职务</th>
            <th field="attendacelevel" width="40" align="center">考勤员级别</th>
            <th field="positon" width="20" align="center">类别</th>
            <th field="menberPath" width="40" align="center">入职时间</th>
            <th field="timechange" width="60" align="center">转正时间</th>


    	</tr>
    </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openMenberAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openMenberModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteMenber()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
  closed="true" buttons="#dlg-buttons">
  <form id="fm" method="post">
  	<table cellspacing="5px;">
  		<tr>
            <td>架构层级：</td>
            <td colspan="1">

                <select class="easyui-combobox" id="menu_level" name="menu_level" style="width:100px;" >
                    <html:options dicttype="Menu_level">  </html:options>
                </select>
            </td>
            <td>名称：</td>
            <td><input type="text" id="menberName" name="menberName" class="easyui-validatebox" required="true"/></td>

        </tr>
  		<tr>

            <td>节点样式：</td>
            <td colspan="3"><input type="text" id="iconCls" name="iconCls" value="icon-item" class="easyui-validatebox" required="true"/></td>

        </tr>
  		<tr>
  			<td valign="top">备注：</td>
  			<td colspan="3">
  				<textarea rows="7" cols="50" name="menberDescription" id="menberDescription"></textarea>
  			</td>


        </tr>
  	</table>
  </form>
</div>

<div id="dlg-buttons">
	<a href="javascript:saveMenber()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
	<a href="javascript:closeMenberDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>
</body>
</html>