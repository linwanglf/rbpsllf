<%--
  Created by IntelliJ IDEA.
  User: xxjs-gd-llf
  Date: 2017/7/17
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/code/highcharts.js"></script>
    <script type="text/javascript" src="/js/code/highcharts-more.js"></script>
    <script type="text/javascript" src="/js/code/modules/exporting.js"></script>
    <script type="text/javascript">
        var oChart = null;
        var oOptions={
            chart :{ type:'',
                     renderTo:'container'
            },
            title : {
                text : 'Dynamic Highcharts chart' //指定图表标题
            },
            subtitle:{ text:'Source: runoob.com' },
            xAxis:{ categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']},
            yAxis:{ title:'Temperature (\xB0C)' },
            plotOptions:{
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series : []
        };

        $(document).ready(function(){
            oChart = new Highcharts.Chart(oOptions);
            alert("here")
            LoadSerie_Ajax();
        });

        //异步读取数据并加载到图表
        function LoadSerie_Ajax() {
            oChart.showLoading();
            $.ajax({
                url : '/linecpu',
                type : 'POST',
                dataType : 'json',
                success : function(result){
                    for(  i=0; i<result.length; i++ ){
                        var oSeries = {
                            name: result[i].name,
                            data: result[i].data
                        };
                        oChart.addSeries(oSeries);
                    }

                }
            });
            oChart.hideLoading();
        }
    </script>

</head>
<body>
<div id="container" style="min-width:400px;width:1200px;height:400px;" >
    这里测试HighChart
</div>
</body>
</html>
