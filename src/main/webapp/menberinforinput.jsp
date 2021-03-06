<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/tld/options" prefix="html"%>
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

        $(function () {
            var _master = $('#division').combobox({
                url:'role?action=comBoListdivision',
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#division').combobox('getValue');
                    document.getElementById("parentId").value=parentId_sel;
                    //alert("当前值: " + document.getElementById("s_userName").value);
                    $("#tex_division").val($('#division').combobox('getText'));//给隐形变量赋值
                    alert("当前值: " + $("#tex_division").val());

                    $('#depart').combobox('clear');
                    $('#departmentname').combobox('clear');
                    $('#classname').combobox('clear');
                    var url= 'role?action=comBoListdepart&parentId_select='+parentId_sel;
                    $('#depart').combobox('reload', url);
                }
            });

            var _slave1 = $('#depart').combobox({
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#depart').combobox('getValue');
                    document.getElementById("parentId").value=parentId_sel;
                    $("#tex_depart").val($('#depart').combobox('getText'));//给隐形变量赋值


                    $('#depart').combobox('reload', url);
                    $('#departmentname').combobox('clear');
                    $('#classname').combobox('clear');
                    var url = 'role?action=comBoListdepartment&parentId_select='+parentId_sel;
                    $('#departmentname').combobox('reload', url);

                }
            });

            var _slave2 = $('#departmentname').combobox({
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#departmentname').combobox('getValue');
                    document.getElementById("parentId").value=parentId_sel;
                    $("#tex_departmentname").val($('#departmentname').combobox('getText'));//给隐形变量赋值


                    $('#departmentname').combobox('reload', url);
                    $('#classname').combobox('clear');
                    var url = 'role?action=comBoListclass&parentId_select='+parentId_sel;
                    $('#classname').combobox('reload', url);

                }
            });
            var _slave3 = $('#classname').combobox({
                disabled: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#classname').combobox('getValue');
                    document.getElementById("parentId").value=parentId_sel;

                    $("#tex_classname").val($('#classname').combobox('getText'));//给隐形变量赋值
                }
            });



        });


        $(function () {
            var _master = $('#s_division').combobox({
                url:'role?action=comBoListdivision',
                editable: false,
                onSelect: function (record) {
                    var  parentId_sel=$('#s_division').combobox('getValue');
                    //document.getElementById("parentId").value=parentId_sel;
                    $("#s_parentId").val(parentId_sel);//给隐形变量赋值
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

                    //alert("777当前值: " +$("#s_parentId").val());
                }
            });



        });

        function searchmenber(){
           // alert("当前值: " + document.getElementById("s_menberName").value);
            //alert("当前值: " + $("#s_menberName").val());
            $('#dg').datagrid('load',{
                s_menberName:$("#s_menberName").val(),
               // s_roleId:$('#s_roleId').combobox("getValue")
                s_menberNo:$("#s_menberNo").val(),
                s_parentId:$("#s_parentId").val()
            });
        }

        function openmenberAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加人员信息");
            url="auth?action=updatemenber";
        }

        function checkinput(){

            alert('系统提示',$('#devision').combobox('getText'));
            return;
        }

        function openmenberModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert('系统提示','请选择一条要编辑的数据！');
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","修改加班项目信息");
            $("#fm").form("load",row);
           // $("#menberName").attr("readonly","readonly");
            //url="user?action=save&userId="+row.userId;
             //alert("5556666当前值: " +row.menberId);
            url="auth?action=updatemenber&menberId="+row.menberId;
        }

        function savemenber(){
            var selectname;
            selectname = $('#classname').combobox('getText');
            if (selectname.length>1)
            {
              //  alert("当前值: " + selectname);
            }
            else
            {
                selectname = $('#departmentname').combobox('getText');
                if (selectname.length>1)
                {
                    //alert("当前值: " + selectname);
                }else
                {
                    selectname = $('#depart').combobox('getText');
                    if (selectname.length>1)
                    {
                       // alert("当前值: " + selectname);
                    }else
                    {
                        selectname = $('#division').combobox('getText');
                        if (selectname.length>1)
                        {
                            //alert("当前值: " + selectname);
                        }else
                        {
                            alert("当前值: " + "请选择有效的所在部门\科室\系\班组");
                            return;
                        }
                    }
                }
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
                        closemenberAddDialog();
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function closemenberAddDialog(){
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

        function deletemenber(){
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





        //-------------------------日期控件--------------------------------
        function HS_DateAdd(interval,number,date){
            number = parseInt(number);
            if (typeof(date)=="string"){var date = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2])}
            if (typeof(date)=="object"){var date = date}
            switch(interval){
                case "y":return new Date(date.getFullYear()+number,date.getMonth(),date.getDate()); break;
                case "m":return new Date(date.getFullYear(),date.getMonth()+number,checkDate(date.getFullYear(),date.getMonth()+number,date.getDate())); break;
                case "d":return new Date(date.getFullYear(),date.getMonth(),date.getDate()+number); break;
                case "w":return new Date(date.getFullYear(),date.getMonth(),7*number+date.getDate()); break;
            }
        }
        function checkDate(year,month,date){
            var enddate = ["31","28","31","30","31","30","31","31","30","31","30","31"];
            var returnDate = "";
            if (year%4==0){enddate[1]="29"}
            if (date>enddate[month]){returnDate = enddate[month]}else{returnDate = date}
            return returnDate;
        }

        function WeekDay(date){
            var theDate;
            if (typeof(date)=="string"){theDate = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2]);}
            if (typeof(date)=="object"){theDate = date}
            return theDate.getDay();
        }
        function HS_calender(){
            var lis = "";
            var style = "";
            style +="<style type='text/css'>";
            style +=".calender { width:170px; height:auto; font-size:12px; margin-right:14px; background:url(calenderbg.gif) no-repeat right center #fff; border:1px solid #397EAE; padding:1px}";
            style +=".calender ul {list-style-type:none; margin:0; padding:0;}";
            style +=".calender .day { background-color:#EDF5FF; height:20px;}";
            style +=".calender .day li,.calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
            style +=".calender li a { text-decoration:none; font-family:Tahoma; font-size:11px; color:#333}";
            style +=".calender li a:hover { color:#f30; text-decoration:underline}";
            style +=".calender li a.hasArticle {font-weight:bold; color:#f60 !important}";
            style +=".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
            style +=".selectThisYear a, .selectThisMonth a{text-decoration:none; margin:0 2px; color:#000; font-weight:bold}";
            style +=".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
            style +=".calender .LastMonth { float:left;}";
            style +=".calender .NextMonth { float:right;}";
            style +=".calenderBody {clear:both}";
            style +=".calenderTitle {text-align:center;height:20px; line-height:20px; clear:both}";
            style +=".today { background-color:#ffffaa;border:1px solid #f60; padding:2px}";
            style +=".today a { color:#f30; }";
            style +=".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:left}";
            style +=".calenderBottom a {text-decoration:none; margin:2px !important; font-weight:bold; color:#000}";
            style +=".calenderBottom a.closeCalender{float:right}";
            style +=".closeCalenderBox {float:right; border:1px solid #000; background:#fff; font-size:9px; width:11px; height:11px; line-height:11px; text-align:center;overflow:hidden; font-weight:normal !important}";
            style +="</style>";

            var now;
            if (typeof(arguments[0])=="string"){
                selectDate = arguments[0].split("-");
                var year = selectDate[0];
                var month = parseInt(selectDate[1])-1+"";
                var date = selectDate[2];
                now = new Date(year,month,date);
            }else if (typeof(arguments[0])=="object"){
                now = arguments[0];
            }
            var lastMonthEndDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+now.getMonth()+"-01").getDate();
            var lastMonthDate = WeekDay(now.getFullYear()+"-"+now.getMonth()+"-01");
            var thisMonthLastDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-01");
            var thisMonthEndDate = thisMonthLastDate.getDate();
            var thisMonthEndDay = thisMonthLastDate.getDay();
            var todayObj = new Date();
            today = todayObj.getFullYear()+"-"+todayObj.getMonth()+"-"+todayObj.getDate();

            for (i=0; i<lastMonthDate; i++){  // Last Month's Date
                lis = "<li class='lastMonthDate'>"+lastMonthEndDate+"</li>" + lis;
                lastMonthEndDate--;
            }
            for (i=1; i<=thisMonthEndDate; i++){ // Current Month's Date

                if(today == now.getFullYear()+"-"+now.getMonth()+"-"+i){
                    var todayString = now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-"+i;
                    lis += "<li><a href=javascript:void(0) class='today' onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
                }else{
                    lis += "<li><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
                }

            }
            var j=1;
            for (i=thisMonthEndDay; i<6; i++){  // Next Month's Date
                lis += "<li class='nextMonthDate'>"+j+"</li>";
                j++;
            }
            lis += style;

            var CalenderTitle = "<a href='javascript:void(0)' class='NextMonth' onclick=HS_calender(HS_DateAdd('m',1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Next Month'>&raquo;</a>";
            CalenderTitle += "<a href='javascript:void(0)' class='LastMonth' onclick=HS_calender(HS_DateAdd('m',-1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Previous Month'>&laquo;</a>";
            CalenderTitle += "<span class='selectThisYear'><a href='javascript:void(0)' onclick='CalenderselectYear(this)' title='Click here to select other year' >"+now.getFullYear()+"</a></span>年<span class='selectThisMonth'><a href='javascript:void(0)' onclick='CalenderselectMonth(this)' title='Click here to select other month'>"+(parseInt(now.getMonth())+1).toString()+"</a></span>月";

            if (arguments.length>1){
                arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
                arguments[1].parentNode.innerHTML = CalenderTitle;

            }else{
                var CalenderBox = style+"<div class='calender'><div class='calenderTitle'>"+CalenderTitle+"</div><div class='calenderBody'><ul class='day'><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class='date' id='thisMonthDate'>"+lis+"</ul></div><div class='calenderBottom'><a href='javascript:void(0)' class='closeCalender' onclick='closeCalender(this)'>×</a><span><span><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+todayString+"'>Today</a></span></span></div></div>";
                return CalenderBox;
            }
        }
        function _selectThisDay(d){
            var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
            boxObj.targetObj.value = d.title;
            boxObj.parentNode.removeChild(boxObj);
        }
        function closeCalender(d){
            var boxObj = d.parentNode.parentNode.parentNode;
            boxObj.parentNode.removeChild(boxObj);
        }

        function CalenderselectYear(obj){
            var opt = "";
            var thisYear = obj.innerHTML;
            for (i=1970; i<=2020; i++){
                if (i==thisYear){
                    opt += "<option value="+i+" selected>"+i+"</option>";
                }else{
                    opt += "<option value="+i+">"+i+"</option>";
                }
            }
            opt = "<select onblur='selectThisYear(this)' onchange='selectThisYear(this)' style='font-size:11px'>"+opt+"</select>";
            obj.parentNode.innerHTML = opt;
        }

        function selectThisYear(obj){
            HS_calender(obj.value+"-"+obj.parentNode.parentNode.getElementsByTagName("span")[1].getElementsByTagName("a")[0].innerHTML+"-1",obj.parentNode);
        }

        function CalenderselectMonth(obj){
            var opt = "";
            var thisMonth = obj.innerHTML;
            for (i=1; i<=12; i++){
                if (i==thisMonth){
                    opt += "<option value="+i+" selected>"+i+"</option>";
                }else{
                    opt += "<option value="+i+">"+i+"</option>";
                }
            }
            opt = "<select onblur='selectThisMonth(this)' onchange='selectThisMonth(this)' style='font-size:11px'>"+opt+"</select>";
            obj.parentNode.innerHTML = opt;
        }
        function selectThisMonth(obj){
            HS_calender(obj.parentNode.parentNode.getElementsByTagName("span")[0].getElementsByTagName("a")[0].innerHTML+"-"+obj.value+"-1",obj.parentNode);
        }
        function HS_setDate(inputObj){
            var calenderObj = document.createElement("span");
            calenderObj.innerHTML = HS_calender(new Date());
            calenderObj.style.position = "absolute";
            calenderObj.targetObj = inputObj;
            inputObj.parentNode.insertBefore(calenderObj,inputObj.nextSibling);
        }







    </script>
</head>
<body style="margin: 1px;">
<table id="dg" title="人员信息录入" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="auth?action=personList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="menberName" width="100" align="center">姓名</th>
        <th field="menberNo" width="50" align="center">工号</th>

        <th field="division" width="50" align="center">部门</th>
        <th field="department" width="50" align="center">科室</th>
        <th field="departmentname" width="50" align="center">系</th>
        <th field="classname" width="50" align="center">班组</th>
        <th field="menberId" width="50" align="center" >记录序号</th>
        <th field="parentId" width="50" align="center">父节点</th>
        <th field="userDescription" width="100" align="center">备注</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openmenberAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openmenberModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deletemenber()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;姓名：&nbsp;<input type="text" name="s_menberName" id="s_menberName" size="10" onkeydown="if(event.keyCode==13) searchmenber()"/>
        &nbsp;工号：&nbsp;<input type="text" name="s_menberNo" id="s_menberNo" size="10" onkeydown="if(event.keyCode==13) searchmenber()"/>

        &nbsp;部门：&nbsp;<input class="easyui-combobox" id="s_division" name="s_division" size="15" editable="false"
               data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName' "/>
        &nbsp;科室：&nbsp;<input class="easyui-combobox" id="s_depart" name="s_depart" size="15"
               data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        &nbsp;系：&nbsp;<input class="easyui-combobox" id="s_departmentname" name="s_departmentname" size="15"
               data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        &nbsp;班组：&nbsp;<input class="easyui-combobox" id="s_classname" name="s_classname" size="15"
               data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
        <input type="hidden" id="s_parentId" name="s_parentId" />
        <a href="javascript:searchmenber()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>


<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="7px;">
            <tr>
                <td with="40">姓名：</td>
                <td><input type="text" id="menberName" name="menberName" class="easyui-validatebox" required="true"/></td>
                <td>工号：</td>
                <td><input type="text" id="menberNo" name="menberNo" class="easyui-validatebox" required="true" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" placeholder="6位数字"  maxlength="6" minlength="6"/></td>
            </tr>
            <tr>
                <td>部门：</td>
                <td>
                    <input class="easyui-combobox" id="division" name="division" size="20" editable="false"
                           data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName' "/>

                </td>
                <td>科室：</td>
                <td colspan="1">
                    <input class="easyui-combobox" id="depart" name="depart"
                           data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
                </td>

            </tr>
            <tr>
                <td>系：</td>
                <td>
                    <input class="easyui-combobox" id="departmentname" name="departmentname"
                           data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
                </td>
                <td>班组;${currentUser.userName }</td>
                <td colspan="1">
                    <input class="easyui-combobox" id="classname" name="classname"
                           data-options="panelHeight:'auto',valueField:'menberId',textField:'menberName'"/>
                </td>

            </tr>
            <tr>
                <td>出生：</td>
                <td colspan="1">
                    <input type="text"  style="width:150px;" name="birthday"   onfocus="HS_setDate(this)">
                </td>
                <td>工龄：</td>
                <td colspan="1">
                    <input type="text" id="work_ege" name="work_ege" required="true" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"/>
                </td>
            </tr>

            <td>入职：</td>
            <td colspan="1">
                <input type="text" style="width:150px;" name="entercompany_date" onfocus="HS_setDate(this)">
            </td>
            <td>职位：</td>
            <td colspan="1">
                <input type="text" id="useName" name="position" />
            </td>
            </tr>

            <tr>
                <td>费用中心：</td>
                <td>
                    <select class="easyui-combobox" id="expense_center" name="expense_center" style="width:155px;"  panelHeight="100">
                        <html:options dicttype="Expense_center">  </html:options>
                    </select>
                </td>
                <td>人员类别;</td>
                <td colspan="1">
                    <select class="easyui-combobox" id="menbertype" name="menbertype" style="width:155px;" panelHeight="100">
                        <html:options dicttype="Menbertype">  </html:options>
                    </select>
                </td>

            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <select class="easyui-combobox" id="sex" name="sex" style="width:155px;"  panelHeight="100">
                        <html:options dicttype="Sex">  </html:options>
                    </select>
                </td>
                <td>婚姻;</td>
                <td colspan="1">
                    <select class="easyui-combobox" id="marriage" name="marriage" style="width:155px;" panelHeight="100">
                        <html:options dicttype="Marriage">  </html:options>
                    </select>
                </td>
            </tr>
            <tr>
                <td>身份证：</td>
                <td>   <input type="text" id="IDcode" name="iDcode" class="easyui-validatebox" required="false" onkeyup="(this.v=function(){this.value=this.value.replace(/[^\w\.\/]/ig,'');}).call(this)" onblur="this.v();" placeholder="老证15位，新证18位长度"  maxlength="18" minlength="15"/></td>
                <td>电话;</td>
                <td>   <input type="text" id="phone" name="phone" class="easyui-validatebox" required="false" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" placeholder="11位手机号码"  maxlength="11" minlength="11"/></td>

            </tr>
            <tr>
                <td>邮箱：</td>
                <td>   <input type="text" id="email" name="email" class="easyui-validatebox" required="false"/></td>
                <td>紧急电话;</td>
                <td>   <input type="text" id="phoneEMG" name="phoneEMG" class="easyui-validatebox" required="false" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" placeholder="11位手机号码或带区号电话号码" /></td>

            </tr>
            <tr>
                <td>毕业学校：</td>
                <td>   <input type="text" id="shcool" name="shcool" /></td>
                <td>专业;</td>
                <td>   <input type="text" id="profession" name="profession" /></td>

            </tr>
            <tr>
                <td>学历：</td>
                <td>   <input type="text" id="certify" name="certify" /></td>
                <td>家庭住地;</td>
                <td>   <input type="text" id="homeaddress" name="homeaddress" /></td>

            </tr>
            <tr>
                <td>政治面貌：</td>
                <td>   <input type="text" id="PoliticalStatus" name="PoliticalStatus" /></td>
                <td>爱好特长;</td>
                <td>   <input type="text" id="pursuits_talents" name="pursuits_talents" /></td>

            </tr>
            <tr>
                <td>原单位：</td>
                <td>   <input type="text" id="companyold" name="companyold" /></td>
                <td>班制;</td>
                <td>   <input type="text" id="classtype" name="classtype"/></td>

            </tr>
            <tr>
                <td>相片：</td>
                <td>   <input type="text" id="photo" name="photo" /></td>
                <td>获奖;</td>
                <td>   <input type="text" id="prize" name="prize" /></td>

            </tr>
            <tr>
                <td>父节点：</td>
                <td>   <input type="text" id="parentId" name="parentId" class="easyui-validatebox" required="false" readonly /></td>
                <td>
                    <input type="hidden" id="tex_division" name="tex_division" />
                    <input type="hidden" id="tex_depart" name="tex_depart" />
                    <input type="hidden" id="tex_departmentname" name="tex_departmentname" />
                    <input type="hidden" id="tex_classname" name="tex_classname" />
                </td>
                <td>   </td>

            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:savemenber()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
    <a href="javascript:closemenberAddDialog()" class="easyui-linkbutton" iconCls="icon-cancel" >关闭</a>
</div>


</body>
</html>