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
<script type="text/javascript"
	src="/js/Charts/assets/prettify/prettify.js"></script>
<script type="text/javascript" src="/js/Charts/assets/ui/js/json2.js"></script>
<script type="text/javascript" src="/js/Charts/assets/ui/js/lib.js"></script>
</head>

<body>
	<div id="chartContainer01"></div>
	<div id="chartContainer02"></div>
	<script type="text/javascript">
		FusionCharts.ready(function() {
			var revenueChart = new FusionCharts({
				"type" : "msline",
				"renderAt" : "chartContainer01",
				"width" : "800",
				"height" : "600",
				"dataFormat" : "json",
				"dataSource" : {
				    "chart": {
				        "caption": "Number of visitors last week",
				        "subCaption": "Bakersfield Central vs Los Angeles Topanga",
				        "captionFontSize": "14",
				        "subcaptionFontSize": "14",
				        "subcaptionFontBold": "0",
				        "paletteColors": "#0075c2,#1aaf5d",
				        "bgcolor": "#ffffff",
				        "showBorder": "0",
				        "showShadow": "0",
				        "showCanvasBorder": "0",
				        "usePlotGradientColor": "0",
				        "legendBorderAlpha": "0",
				        "legendShadow": "0",
				        "showAxisLines": "0",
				        "showAlternateHGridColor": "0",
				        "divlineThickness": "1",
				        "divLineDashed": "1",
				        "divLineDashLen": "1",
				        "divLineGapLen": "1",
				        "xAxisName": "Day",
				        "showValues": "0"
				    },
				    "categories": [
				        {
				            "category": [
				                {
				                    "label": "Mon"
				                },
				                {
				                    "label": "Tue"
				                },
				                {
				                    "label": "Wed"
				                },
				                {
				                    "label": "National holiday"
				                },
				                {
				                    "label": "Thu"
				                },
				                {
				                    "label": "Fri"
				                },
				                {
				                    "label": "Sat"
				                },
				                {
				                    "label": "Sun"
				                }
				            ]
				        }
				    ],
				    "dataset": [
				        {
				            "seriesname": "Bakersfield Central",
				            "data": [
				                {
				                    "value": "15123"
				                },
				                {
				                    "value": "14233"
				                },
				                {
				                    "value": "25507"
				                },
				                {
				                    "value": "9110"
				                },
				                {
				                    "value": "15529"
				                },
				                {
				                    "value": "20803"
				                },
				                {
				                    "value": "19202"
				                }
				            ]
				        }
				    ],
				    "trendlines": [
				        {
				            "line": [
				                {
				                    "startvalue": "20000",
				                    "endvalue":"2000",
				                    "color": "#6baa01",
				                    "valueOnRight": "1",
				                    "displayvalue": ""
				                }
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
