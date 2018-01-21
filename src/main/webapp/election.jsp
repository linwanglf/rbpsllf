<%@ page language="java" contentType="text/html; charset=utf-8"		 pageEncoding="utf-8"%>
<%@ taglib uri="/tld/options" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票系统</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	function reloadpresent() {
        url="present?action=list";
        $("#fm").form("submit",{
            url:url,
            onSubmit:function(){
                return $(this).form("validate");
            },
            success:function(result){
                var result=eval('('+result+')');
                document.getElementById("present01").value=result[0].leftNumber;
                document.getElementById("present02").value=result[1].leftNumber;
                document.getElementById("present03").value=result[2].leftNumber;
            }
        });
    }
	$(function(){
        reloadpresent();
	})


    function present1Selected(){
        url="present?action=save&presentcode="+ $("input[type=radio]:checked").val();
//        alert("url:" +  url );
        $("#fm").form("submit",{
            url:url,
            onSubmit:function(){
                return $(this).form("validate");
            },
            success:function(result){

                  $.messager.alert('系统提示','选择成功');

            }
        });
        reloadpresent();
    }


</script>
</head>
<body style="margin: 1px;">
<div>
   <form id="fm" method="post">
          <table>
              <tr> <td> &nbsp;礼包1(旅行背包):：&nbsp;</td> <td> &nbsp;礼包2(回味坚果):&nbsp;</td>  <td> &nbsp;礼包3(暖心毛毯):&nbsp;</td> </tr>
              <tr>
                  <td><input type="text" name="present01" id="present01" size="20" readonly="readonly" /> </td>
                  <td><input type="text" name="present02" id="present02" size="20" readonly="readonly" /> </td>
                  <td><input type="text" name="present03" id="present03" size="20" readonly="readonly" /> </td>
              </tr>
              <tr>
                  <td>  </td>
                  <td>  </td>
                  <td>  </td>
              </tr>
              <tr>
                  <td>
                      <input type="radio" name="present" value="present01" checked>选择
                  </td>
                  <td>
                      <input type="radio" name="present" value="present02">选择
                  </td>
                  <td>
                      <input type="radio" name="present" value="present03">选择
                  </td>
              </tr>


          </table>

        <a href="javascript:present1Selected()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
  </form>

</div>

</body>
</html>

