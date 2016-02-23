<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="/js/Charts/FusionCharts.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/prettify/prettify.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/json2.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/lib.js" ></script>
  </head>
  
  <body>
  <input id="form_button1" type="button" value="加载单线图">
  <input id="form_button2" type="button" value="加载单堆积柱图">
  <input id="form_button3" type="button" value="加载单饼图">
  <input id="form_button4" type="button" value="加载单堆积面积">
  
  <input id="form_button5" type="button" value="加载多线">
  <input id="form_button6" type="button" value="加载多堆积柱图">
  <input id="form_button7" type="button" value="加载多堆积面积图">
  <table>
  <tr>
  	<td><div id="linechart1"></div></td>
  	<td><div id="linechart2"></div></td>
  	<td><div id="linechart5"></div></td>
  	<td><div id="linechart6"></div></td>
  </tr>
  <tr>
  	<td><div id="linechart3"></div></td>
  	<td><div id="linechart4"></div></td>
  	<td><div id="linechart7"></div></td>
  </tr>
  </table>
  <script type="text/javascript">
  $("#form_button1").on("click",function(){
	  $.post('/test2/test01?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "line",
		        "renderAt": "linechart1",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  $("#form_button2").on("click",function(){
	  $.post('/test2/test01?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "stackedcolumn2d",
		        "renderAt": "linechart2",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  $("#form_button3").on("click",function(){
	  $.post('/test2/test01?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "doughnut2d",
		        "renderAt": "linechart3",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  $("#form_button4").on("click",function(){
	  $.post('/test2/test01?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "stackedarea2d",
		        "renderAt": "linechart4",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  $("#form_button5").on("click",function(){
	  $.post('/test2/test02?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "msline",
		        "renderAt": "linechart5",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  $("#form_button6").on("click",function(){
	  $.post('/test2/test02?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "stackedcolumn2d",
		        "renderAt": "linechart6",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  $("#form_button7").on("click",function(){
	  $.post('/test2/test02?a='+ new Date(),function(data){
		  FusionCharts.ready(function(){
		      var revenueChart = new FusionCharts({
		        "type": "stackedarea2d",
		        "renderAt": "linechart7",
		        "width": "400",
		        "height": "300",
		        "dataFormat": "json",
		        "dataSource": data
			});
		      revenueChart.render();
		});
	  });
  });
  </script>
  </body>
</html>
