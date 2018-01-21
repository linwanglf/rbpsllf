<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广汽传祺动力总成</title>
<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

//    $(function () {
//        var _master = $('#division').combobox({
//            url:'select?action=selectStoreCount',
//            editable: false,
//            onSelect: function (record) {
//                var  parentId_sel=$('#division').combobox('getValue');
//            }
//        });
//
//    });


//    $(function () {
//        $('#division').combobox({
//            url:'select?action=selectStoreCount',
//            editable: false,
//        });
//    });

var url;
function reloadpresent() {
    url="select?action=selectStoreCount";
    $("#fm").form("submit",{
        url:url,
        onSubmit:function(){
            return $(this).form("validate");
        },
        success:function(result){
            var result=eval('('+result+')');
            document.getElementById("s_preNO1").value=result[0].product_store_Count_Input;
            document.getElementById("s_preNO2").value=result[1].product_store_Count_Input;
            document.getElementById("s_preNO3").value=result[2].product_store_Count_Input;
            document.getElementById("s_preNO4").value=result[3].product_store_Count_Input;
        }
    });
}

$(function(){
    reloadpresent();
})

    $(function(){
        //document.getElementById("s_preNO1").value=parentId_sel;//给文本框赋值,测试OK
        //$("#s_preNO1").val(parentId_sel);//给文本框赋值,测试OK

		function logout(){
			$.messager.confirm('系统提示','您确定要退出系统吗？',function(r){
				if(r){
					window.location.href='user?action=logout';
				}
			});
		}

	});


function present1Selected(){
    var num_pre=$("input[type=radio]:checked").val();
    if (num_pre=="000001"){
        var num_preval=document.getElementById("s_preNO1").value;
    }
    if (num_pre=="000002"){
        var num_preval=document.getElementById("s_preNO2").value;
    }
    if (num_pre=="000003"){
        var num_preval=document.getElementById("s_preNO3").value;
    }
    if (num_pre=="000004"){
        var num_preval=document.getElementById("s_preNO4").value;
    }

    if (num_preval=="000"){
        alert("抱歉，你所在科室的这项礼品已经被选完毕，请选择其他礼品");
    }
    else{
    $.messager.confirm('慕容姐姐提示','您确定要选择此礼品包吗？',function(r){
        if(r){

    url="select?action=update&product_store_NO="+ $("input[type=radio]:checked").val();
    $("#fm").form("submit",{
        url:url,
        success:showResponse,
        dataType: 'json',
        onSubmit:function(){
            return $(this).form("validate");
        },


        });

        }
    });

    }//数量为0判断结束
  }
/**
 * 保存后，执行回调
 * @param responseText
 * @param statusText
 * @param xhr
 * @param $form
 */
function showResponse(responseText, statusText, xhr, $form){
    var Strresult=responseText[2]+responseText[3]+responseText[4]+responseText[5]+responseText[6];
    //if(responseText.status == "success"){
    if(Strresult == "succe"){
            /**
             * 请求成功后的操作
             */
            //alert(responseText.msg);
            $.messager.alert('系统提示','选择成功', "info", function () {
                reloadpresent();//刷新记录
            });
        }
        else if(Strresult == "error"){
            //alert(responseText.msg);
        $.messager.alert('系统提示','您已选择，不能重复操作', "warning", function () {
                reloadpresent();//刷新记录
            });
        }
    else if(Strresult == "zeroo"){
        //alert(responseText.msg);
        $.messager.alert('系统提示','您已选择的对象已经被抢，请选择其他，加油哦', "warning", function () {
            reloadpresent();//刷新记录
        });
    }


}


function openTicketlistDialog(){
    url="select?action=personList";
    //$("#dg").dialog("open").dialog("setTitle","");
    $("#ticketlistdlg").dialog("open").dialog("setTitle","");
}

function searchmenber(){
    // alert("当前值: " + document.getElementById("s_menberName").value);
    //alert("当前值: " + $("#s_menberName").val());
    $('#dg').datagrid('load',{
        s_menberName:$("#s_menberName").val(),
        // s_roleId:$('#s_roleId').combobox("getValue")
        s_menberNo:$("#s_menberNo").val(),

        s_parentId:$("#s_parentId").val(),

        s_division:$("#sel_division").val(),
        s_depart:$("#sel_depart").val(),
        s_departmentname:$("#sel_departmentname").val(),
        s_classname:$("#sel_classname").val()

    });
}
function clears_menberNo(){
    document.getElementById("s_menberNo").value="";
}
function clears_menberName(){
    document.getElementById("s_menberName").value="";
}
$(function () {
    var _master = $('#s_division').combobox({
        url:'role?action=comBoListdivision',
        editable: false,
        onSelect: function (record) {
            var  parentId_sel=$('#s_division').combobox('getValue');
            //document.getElementById("parentId").value=parentId_sel;
            $("#s_parentId").val(parentId_sel);//给隐形变量赋值
            $("#sel_division").val($('#s_division').combobox('getText'));//给隐形变量赋值
            //alert("444当前值: " +$("#s_parentId").val());

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
            $("#sel_depart").val($('#s_depart').combobox('getText'));//给隐形变量赋值
            //alert("555当前值: " +$("#s_parentId").val());
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
            $("#sel_departmentname").val($('#s_departmentname').combobox('getText'));//给隐形变量赋值
            // alert("555当前值: " +$("#s_parentId").val());

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
            $("#sel_classname").val($('#s_classname').combobox('getText'));//给隐形变量赋值

            //alert("777当前值: " +$("#s_parentId").val());
        }
    });



});

//-------自动刷新页面
function myrefresh()
{
    window.location.reload();
}
//setTimeout('myrefresh()',3000); //指定3秒刷新一次


</script>
</head>
<body class="easyui-layout">


<div region="center">
	<div class="easyui-tabs" fit="true" border="false" id="tabs">
		<div title="MainPage" data-options="iconCls:'icon-home'">
            <table>
                <tr>
                    <td></td>
                    <td valign="bottom">
                        欢迎：${currentUser.userName }&nbsp;『${currentUser.roleName }』;<%=session.getAttribute("currentUser")%>
                        <a href="javascript:openTicketlistDialog()" class="easyui-linkbutton" iconCls="icon-color" plain="true">记录查询</a>
                    </td>

                </tr>
            </table>

            <form id="fm" method="post">
            <table>
                <tr><td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan="4"><img src="images/hechi.jpg"/></td></tr>


                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>礼包1（休闲背包）</td>
                    <td>礼包2（回味坚果）</td>
                    <td>礼包3（旅行拉箱）</td>
                    <td>礼包4（商务背包）</td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>背著自己的背包，走在熟悉的街头</td>
                    <td>这个休闲时刻</td>
                    <td>旅行吧，为梦想而走</td>
                    <td>商务伴侣</td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>有时并行，有时说话</td>
                    <td>怎么能没有你的陪伴</td>
                    <td>随心灵一起去经历一场浪漫游记</td>
                    <td>简约而不简单</td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>有时沉默，无需言语！</td>
                    <td>一切源于生活的滋味！</td>
                    <td>品味精彩生活！</td>
                    <td>成功人士佳选！</td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>库存量:&nbsp;<input type="text" style="width:100px;" id="s_preNO1" name="s_preN01"  size="10" readonly="readonly"></td>
                    <td>库存量:&nbsp;<input type="text"  style="width:100px;" id="s_preNO2" name="s_preN02" size="10" readonly="readonly"></td>
                    <td>库存量:&nbsp;<input type="text"  style="width:100px;" id="s_preNO3" name="s_preN03"  size="10" readonly="readonly"></td>
                    <td>库存量:&nbsp;<input type="text"  style="width:100px;" id="s_preNO4" name="s_preN03"  size="10" readonly="readonly"></td>

                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>温馨提示：放心选择</td>
                    <td>温馨提示：放心选择</td>
                    <td>温馨提示：颜色规格随机</td>
                    <td>温馨提示：放心选择</td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>

                    <td><img src="images/bag01.jpg"/></td>
                    <td><img src="images/nut.jpg"/></td>

                    <td><img src="images/maotan.jpg"/></td>
                    <td><img src="images/bag02.jpg"/></td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>

                    <td style="vertical-align:middle; text-align:center; ">
                        <input type="radio" name="present" value="000001" checked>选择
                    </td>
                    <td style="vertical-align:middle; text-align:center; ">
                        <input type="radio" name="present" value="000002">选择
                    </td>
                    <td style="vertical-align:middle; text-align:center; ">
                        <input type="radio" name="present" value="000003">选择
                    </td>
                    <td style="vertical-align:middle; text-align:center; ">
                        <input type="radio" name="present" value="000004">选择
                    </td>
                </tr>
                <tr>
                    <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>

                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td  height="100">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td colspan="4" style="vertical-align:middle; text-align:center; ">
                    <a href="javascript:present1Selected()" class="easyui-linkbutton" iconCls="icon-ok" ><font size=5>确定</font></a>

                    </td>

                </tr>

            </table>

            </form>
		</div>

	</div>

</div>

<div region="south" style="height: 25px;padding: 5px;" align="center">
    广汽传祺动力总成设备科 3F开发团队</a>
</div>



<div id="ticketlistdlg" class="easyui-dialog" style="width:800px;height: 620px;padding: 0px 0px"
     closed="true" buttons="#dlg-buttons" data-options="iconCls:'icon-modifyPassword'">


    <div>
        &nbsp;姓名：&nbsp;<input class="easyui-textbox" name="s_menberName" id="s_menberName" size="10" onkeydown="if(event.keyCode==13) searchmenber();clears_menberNo()"/>
        &nbsp;工号：&nbsp;<input class="easyui-textbox" name="s_menberNo" id="s_menberNo" size="10" onkeydown="if(event.keyCode==13) searchmenber();clears_menberName()"/>
    </div>
    <div>
        &nbsp;部门：&nbsp;<input class="easyui-combobox" id="s_division" name="s_division" size="15" editable="false"
                              data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName' "/>
        &nbsp;科室：&nbsp;<input class="easyui-combobox" id="s_depart" name="s_depart" size="15"
                              data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        &nbsp;系：&nbsp;<input class="easyui-combobox" id="s_departmentname" name="s_departmentname" size="15"
                             data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        &nbsp;班组：&nbsp;<input class="easyui-combobox" id="s_classname" name="s_classname" size="15"
                              data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>

        <input type="hidden" id="s_parentId" name="s_parentId" />
        <input type="hidden" id="sel_division" name="sel_division" />
        <input type="hidden" id="sel_depart" name="sel_depart" />
        <input type="hidden" id="sel_departmentname" name="sel_departmentname" />
        <input type="hidden" id="sel_classname" name="sel_classname" />

        <a href="javascript:searchmenber()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>

    <table id="dg" title="选择结果" class="easyui-datagrid" fitColumns="true"
           pagination="true" rownumbers="true" url="select?action=personList" fit="true" toolbar="#tb">
        <thead>
        <tr>
            <th field="ticket_record_Name" width="50" align="center">物品名称</th>
            <th field="ticket_record_NO" width="30" align="center">选择顺序号</th>

            <th field="ticket_record_menbername" width="30" align="center">选择人</th>
            <th field="ticket_record_menberNO" width="30" align="center">选择人工号</th>
            <th field="ticket_record_selectNO" width="30" align="center">物料编号</th>
            <th field="ticket_record_ClassNO" width="30" align="center">班组</th>
            <th field="ticket_record_date" width="30" align="center" >选择时间</th>
        </tr>
        </thead>
    </table>
</div>


<div id="dlg" class="easyui-dialog" style="width: 400px;height: 220px;padding: 10px 20px" 
 closed="true" buttons="#dlg-buttons" data-options="iconCls:'icon-modifyPassword'">

</div>
<div id="dlg-buttons">
	<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>