<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'test02.jsp' starting page</title>
    
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
   <div id="chartContainer"></div>
   <div id="chart"></div>
  <script type="text/javascript">
  FusionCharts.ready(function(){
      var revenueChart = new FusionCharts({
        "type": "realtimestackedarea",
        "renderAt": "chartContainer",
        "width": "800",
        "height": "600",
        "dataFormat": "json",
        "dataSource": {
            "chart": {
            	"caption": "Real-time stock price monitor",
                "subCaption": "Harry's SuperMart",
                "dataStreamUrl":"../TIMEComm/test/test03",
                "xAxisName": "Time",
                "yAxisName": "Stock Price",
                "numberPrefix": "$",
                "refreshinterval": "5",
                "yaxisminvalue": "35",
                "yaxismaxvalue": "36",
                "numdisplaysets": "10",
                "showLabels":"1",
                "labeldisplay": "rotate",
                "showValues": "0",
                "showRealTimeValue": "0",
                "theme": "fint"
            },
            "categories": [
                {
                    "category": [
                            {"label": "Day Start"}
                    ]
                }
            ],
            "dataset": [
                {
                	"seriesname":"vlan001",
                    "data": [
                            {"value":"24"}
                    ]
                },
                {
                	"seriesname":"vlan002",
                    "data": [
                            {"value":"38"}
                    ]
                }
            ]
        }
    });

    revenueChart.render();
});
  
  FusionCharts.ready(function(){
      var revenueChart = new FusionCharts({
        "type": "realtimestackedarea",
        "renderAt": "chart",
        "width": "800",
        "height": "600",
        "dataFormat": "json",
        "dataSource": {
            "chart": {
            	"caption": "Real-time stock price monitor",
                "subCaption": "Harry's SuperMart",
                "dataStreamUrl":"../TIMEComm/test/test04",
                "xAxisName": "Time",
                "yAxisName": "Stock Price",
                "numberPrefix": "$",
                "refreshinterval": "5",
                "yaxisminvalue": "35",
                "yaxismaxvalue": "36",
                "numdisplaysets": "10",
                "showLabels":"1",
                "labeldisplay": "rotate",
                "showValues": "0",
                "showRealTimeValue": "0",
                "theme": "fint"
            },
            "categories": [
                {
                    "category": [
                            {"label": "Day Start"}
                    ]
                }
            ],
            "dataset": [
				{
					"seriesname":"vlan001",
				    "data": [
				            {"value":"26"}
				    ]
				},
                {
                	"seriesname":"vlan002",
                    "data": [
                            {"value":"38"}
                    ]
                }
            ]
        }
    });

    revenueChart.render();
});
  </script>
  </body>
</html>
