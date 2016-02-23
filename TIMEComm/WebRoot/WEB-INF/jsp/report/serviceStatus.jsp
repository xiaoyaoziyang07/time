<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_company_main">
  <div class="row-fluid">
    <div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"> <a href="javascript:;" class="dj"><s:text name="ui.report.search.expansion"/></a><i class="icon-click"></i></div>
      </div>
      <div class="toggle_box">
        <form class="form-inline" id="searchForm">         
          <label><s:text name="ui.report.search.date"/>：</label>
          <div class="dateOut">
            <input type="text" id="searchDate" class="ui-datepicker-time" readonly value="" />
            <div class="ui-datepicker-css">
              <div class="ui-datepicker-quick">
                <p><s:text name="ui.common.date.fastdate.dayM"/>
                <a class="ui-close-date btn-closez"></a></p>
                <div class="ui-datepicker-input">
                	<div>
                		  <input  class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.today"/>" alt="0"  name=""/>
                		  <input  onOff="true" class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.thisweek"/>" alt="weeks_0"  />
                		  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.thismonth"/>"   alt="month_0" name=""/> 
		                  <%-- <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.pastdays"><s:param>7</s:param></s:text>" alt="-7" name=""/>
		                  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.pastdays"><s:param>14</s:param></s:text>" alt="-14" name=""/>
		                  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.pastdays"><s:param>30</s:param></s:text>" alt="-30" name=""/> --%>
                	</div>
                  	<div>
                  		<input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.yesterday"/>" alt="-1" name=""/>
	                  <!-- <input class="ui-date-quick-button" type="button" value="" id="thisM" alt="month_3" />
	                  <input class="ui-date-quick-button" type="button" value=" " id="lastM"  alt="month_2" /> -->
	                  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.lastweek"/>" alt="weeks_1"  />
	                  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.lastmonth"/>"  id="lastTM" alt="month_1"  />
	                  
                  	</div>
                  
                </div>
              </div>
              <div class="ui-datepicker-choose">
                 <p><s:text name="ui.common.date.footer.title"/></p>
                <div class="ui-datepicker-date">
                  <input name="startDate" id="startDate" class="startDate" readonly value="2014/12/20" type="text">
                  -
                  <input name="endDate" id="endDate"  setDate="29" class="endDate" readonly  value="" type="text" disabled onChange="datePickers()">
                </div>
              </div>
            </div>
          </div>
          <button type="button"   class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
        </form>
      </div>
      <div class="toggle_f">
               <div class="di_out">
          <div class="put-results">
          	<span class="obj_name">Date</span><span id="search_span_date"></span>
          </div>
        </div>
      </div>
    </div>
  </div> 
  <div class="row-fluid">
    <div class="span3">
    	<input type="hidden" value="<s:text name="ui.report.servicestauts.grossadd"/>">
      <div class="widget-box chartDiv" id="grossAddChartDiv" value="grossAdd">
      </div>
    </div>
    <div class="span3">
    	<input type="hidden" value="<s:text name="ui.report.servicestauts.suspend"/>">
      <div class="widget-box chartDiv01" id="suspendChartDiv" value="suspend" >
      </div>
    </div>
    <div class="span3">
    	<input type="hidden" value="<s:text name="ui.report.servicestauts.terminate"/>">
      <div class="widget-box chartDiv02" id="terminateChartDiv" value="terminate">
      </div>
    </div>
    <div class="row-fluid">
    <div class="span3">
    	<input type="hidden" value="<s:text name="ui.report.servicestauts.netgross"/>">
      <div class="widget-box chartDiv04" id="netGrossChartDiv" value="netGross">
      </div>
    </div>
    <div class="span3">
    <input type="hidden" value="<s:text name="ui.report.servicestauts.newaddition"/>">
      <div class="widget-box chartDiv03" id="newAdditionChartDiv" value="newAddition">
      </div>
    </div>
    <div class="span3">
    	<input type="hidden" value="<s:text name="ui.report.servicestauts.restoration"/>">
      <div class="widget-box chartDiv05" id="restorationChartDiv" value="restoration">
      </div>
    </div>
    </div>
  </div>
</div>
<!-- Modal 弹出框内容 begin -->
<div id="add_proj" class="modal" style="display:none;width:700px;height:400px;margin-left:-350px" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<p><b  id="titleName">chars</b><span onclick="cal(this)">X</span></p>
  	 <hr style="color:#bfbfbf" />
  	<div class="row-fluid">
      <div id="bigChartDiv"></div>
    </div>

</div>
<div class="modal-backdrop in" id="modal-backdrop" style="display:none"></div>

<!-- Modal 弹出框内容  end -->
<script type="text/javascript" src="/js/common.js"></script>
<script>
function cal(a){
	    $("#modal-backdrop").fadeOut("slow");
		$(a).parent().parent().fadeOut("slow");
}
var chartData ;
$(document).ready(function() {
chartData=null;
	$(".chartDiv, .chartDiv01, .chartDiv02, .chartDiv03, .chartDiv04, .chartDiv05").click(function(){
		var centerTit = $(this).prev("input:hidden").val();
		$("#titleName").html(centerTit);
		var aciton =$(this).attr("value");
		var actionData=[{}];
		for (var i = 0; i < chartData.list.length; i++) {
			actionData[i]={label:chartData.list[i].stat1,value:0};
			switch (aciton) {
		case "grossAdd":
			actionData[i].value=chartData.list[i].grossAdd;
			break;
		case "suspend":
			actionData[i].value=chartData.list[i].stat3;
			break;
		case "terminate":
			actionData[i].value=chartData.list[i].stat5;
			break;
		case "newAddition":
			actionData[i].value=chartData.list[i].stat2;
			break;
		case "netGross":
			actionData[i].value=chartData.list[i].netGross;
			break;
		case "restoration":
			actionData[i].value=chartData.list[i].stat4;
			break;
		default:
			break;
		}
		if(actionData[i].value==null)actionData[i].value=0;
		}		
		$("#modal-backdrop").fadeIn("slow",function(){
		$("#add_proj").fadeIn("slow",function(){
		 getChart("bigChartDiv", actionData,'100%','350').render();
		});
		});
	});      
});
function seachfor(){
    $('.ami_Mask').show();
var searchDate = $("#searchDate").val().split("-");
loadData(searchDate[0],searchDate[1]);
}
	function loadData(startDate, endDate) {
		args = {
			"startDate" : startDate,
			"endDate" : endDate
		};
		$.ajax({
			url : "/report/plusrservicestatisticsdata?a=" + new Date(),
			type : "POST",
			data : args,
			success : function(resultData) {
			chartData=resultData;
			var grossAddData=[{}];
			var suspendData=[{}];
			var terminateData=[{}];
			var newAdditionData=[{}];
			var netGrossData=[{}];
			var restorationData=[{}];
				for (var i = 0; i < resultData.list.length; i++) {
					grossAddData[i]={value:0};    
					suspendData[i]={value:0};     
					terminateData[i]={value:0};   
					newAdditionData[i]={value:0}; 
					netGrossData[i]={value:0};    
					restorationData[i]={value:0};
					if(resultData.list[i].grossAdd!=null){
					grossAddData[i].value=resultData.list[i].grossAdd;
					}
					if(resultData.list[i].stat3!=null){
					suspendData[i].value=resultData.list[i].stat3;
					}
					if(resultData.list[i].stat5!=null){
					terminateData[i].value=resultData.list[i].stat5; 
					}
					if(resultData.list[i].stat2!=null){
					newAdditionData[i].value=resultData.list[i].stat2; 
					}
					if(resultData.list[i].netGross!=null){
					netGrossData[i].value=resultData.list[i].netGross;
					}
					if(resultData.list[i].stat4!=null){
					restorationData[i].value=resultData.list[i].stat4; 
					}
					
				}
				var width='100%';				
				var autoH =($('#main-content').height()-110)/2;
				var height=autoH-5;
				var max=chartData.list[0].grossAddMax;
				var min =chartData.list[0].grossAddMin;
				getChart('grossAddChartDiv',grossAddData,width,height,max,min,'<s:text name="ui.report.servicestauts.grossadd"/>').render();
				min=chartData.list[0].suspendMax;
				getChart('suspendChartDiv',suspendData,width,height,max,min,'<s:text name="ui.report.servicestauts.suspend"/>').render();
				min=chartData.list[0].terminateMin;
				getChart('terminateChartDiv',terminateData,width,height,max,min,'<s:text name="ui.report.servicestauts.terminate"/>').render();
				min=chartData.list[0].newAdditionMin;
				getChart('newAdditionChartDiv',newAdditionData,width,height,max,min,'<s:text name="ui.report.servicestauts.newaddition"/>').render();
				min=chartData.list[0].netGrossMin;
				getChart('netGrossChartDiv',netGrossData,width,height,max,min,'<s:text name="ui.report.servicestauts.netgross"/>').render();
				min=chartData.list[0].restorationMin;
				getChart('restorationChartDiv',restorationData,width,height,max,min,'<s:text name="ui.report.servicestauts.restoration"/>').render();
			}
		});
		$("#search_span_date").html($("#searchDate").val());
	}
	
	function getChart(id,data,width,height,max,min,title){
	if(max==undefined){
	max=100;
	}
	if(min==undefined){
	min=100;
	}
	    $('.ami_Mask').hide();
    return  new FusionCharts({
            type: 'line',//stackedarea2d滑动数据图形doughnut2d圆形的数据
            renderAt: id,
            width: width,
            height:height,
            dataFormat: 'json',
            dataSource: {//数据
                "chart": {
        "compactdatamode": "0",
        "dataseparator": "",
        "caption": title,
        "subcaption": "",
        "axis": "",
        "numberprefix": "",
        "formatNumberScale":"0",
        "allowpinmode": "1",
        "yAxisMaxValue":10,
        "yAxisMinValue":min,
        "enableiconmousecursors": "1",
        "dynamicaxis": "0",
        "showlegend": "1",
        "slantlabels": "1",
        "rotatelabels": "1",
        "bgcolor": "FFFFFF",
        "showalternatehgridcolor": "1",
        "showplotborder": "1",
        "showvalues": "0",
        "divlinecolor": "CCCCCC",
        "showcanvasborder": "1",
        "linecolor": "008ee4",
        labelStep:getStep(),
        "showshadow": "0",
        "linethickness": "1",
        "captionpadding": "20",
        "canvasbottommargin": "30",
        "yaxisvaluespadding": "10",
        "scrollcolor": "CCCCCC",
        "canvasborderalpha": "1",
        "anchorradius": "3",
        "showborder": "0"
    },
                "dataset": [{
                    "data":data
                }]
            }
        });
    }
        function getStep(){
  var searchDate = $("#searchDate").val().split("-");
  	var startDate=new Date(searchDate[0].substring(6,10),searchDate[0].substring(0,2),searchDate[0].substring(3,5),0,0,0);
  	var endDate=new Date(searchDate[1].substring(6,10),searchDate[1].substring(0,2),searchDate[1].substring(3,5),0,0,0);;
  	var days =Math.abs((startDate - endDate))/(1000*60*60*24);
	days++;
			//是否为周报
		if (days == 7) {
			return 12;
			//是否为月报，//是否为日报
		} else if (days == 1||(days >= 28 && days <= 31)) {
			return 2;
			//是否小于7天
		} else if (days <= 7) {
			return parseInt(days * 24 / 12);
			//自定义日期超过7天
		} else if (days > 7 ) {
			return 1;
		}
	}
</script> 
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/Charts/FusionCharts.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/prettify/prettify.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/json2.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/lib.js" ></script>
<script>
$(function(){
	seachfor();
});
</script>