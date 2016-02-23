<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
  <div class="row-fluid">
    <div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"> <a href="javascript:;" class="dj"><s:text name="ui.report.search.expansion"/></a><i class="icon-click"></i></div>
        <div class="di_out">
          <div class="put-results">
          	<span id="search_span_date"></span>
          </div>
        </div>
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
          <%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>
        </form>
      </div>
    </div>
    <!--第一个-->
    <div class="row-fluid">
		<div class="span3">
		<input type="hidden" value="<s:text name="ui.report.submonitor.grossadd"/>">
		  <div class="widget-box chartDiv"  id="grossAddChartDiv" value="grossAdd" >
		    <%-- <div class="widget-header">
		      	<h5><s:text name="ui.report.submonitor.grossadd"/></h5>
		      	<span class="widget-toolbar">
                	<button class="btn btn-mini chartDiv" value="grossAdd" data-rel="tooltip" data-placement="top" data-original-title="Export">
                    	<i class="icon-search"></i>
                    </button>
                </span> 
		    </div> --%>
		    <!-- <div class="widget-body">
		      <div class="widget-body-inner" style="display: block;">
		        <div class="widget-main">
		      </div>
		      </div>
		    </div> -->
		  </div>
		</div>
		<div class="span3">
			<input type="hidden" value="<s:text name="ui.report.submonitor.suspend"/>">
		  	<div class="widget-box chartDiv01"  id="suspendChartDiv" value="suspend">
		    <%-- <div class="widget-header">
		      	<h5><s:text name="ui.report.submonitor.suspend"/></h5>
		      	<span class="widget-toolbar">
	                	<button class="btn btn-mini chartDiv01"  value="suspend" data-rel="tooltip" data-placement="top" data-original-title="Export">
	                    	<i class="icon-search"></i>
	                    </button>
	            </span> 
		    </div>
		    <div class="widget-body">
		      <div class="widget-body-inner" style='display: block;'>
		        <div class="widget-main" id="suspendChartDiv"></div>
		      </div>
		    </div> --%>
		  </div>
	</div>
		<div class="span3">
			<input type="hidden" value="<s:text name="ui.report.submonitor.terminate"/>">
			  <div class="widget-box chartDiv02"  id="terminateChartDiv"  value="terminate">
			    <%-- <div class="widget-header">
			      	<h5><s:text name="ui.report.submonitor.terminate"/></h5>
			      	<span class="widget-toolbar">
		                	<button class="btn btn-mini chartDiv02" value="terminate" data-rel="tooltip" data-placement="top" data-original-title="Export">
		                    	<i class="icon-search"></i>
		                    </button>
		                </span> 
			    </div>
			    <div class="widget-body">
			      <div class="widget-body-inner" style="display: block;">
			        <div class="widget-main" id="terminateChartDiv">
			        </div>
			      </div>
			    </div> --%>
			  </div>
	</div>
	</div>
    <div class="row-fluid">
		<div class="span3">
		<input type="hidden" value="<s:text name="ui.report.submonitor.newaddition"/>">
		  <div class="widget-box chartDiv03" id="newAdditionChartDiv"  value="newAddition">
		    <%-- <div class="widget-header">
		      <h5><s:text name="ui.report.submonitor.newaddition"/></h5>
		      <span class="widget-toolbar">
                <button class="btn btn-mini chartDiv03" value="newAddition" data-rel="tooltip" data-placement="top" data-original-title="Export">
                    <i class="icon-search"></i>
                </button>
            </span>
		    </div>
		    <div class="widget-body">
		      <div class="widget-body-inner" style="display: block;">
		        <div class="widget-main" id="newAdditionChartDiv">
		       </div>
		      </div>
		    </div> --%>
		  </div>
		</div>
		<div class="span3">
			<input type="hidden" value="<s:text name="ui.report.submonitor.newaddition"/>">
			  <div class="widget-box chartDiv04" id="netgrossChartDiv" value="netGross">
			    <%-- <div class="widget-header">
			      	<h5><s:text name="ui.report.submonitor.netgross"/></h5>
			      	<span class="widget-toolbar">
		                	<button class="btn btn-mini chartDiv04" value="netGross" data-rel="tooltip" data-placement="top" data-original-title="Export">
		                    	<i class="icon-search"></i>
		                    </button>
		                </span>
			    </div>
			    <div class="widget-body">
			      <div class="widget-body-inner" style="display: block;">
			        <div class="widget-main" id="netgrossChartDiv">
			         	
			        </div>
			      </div>
			    </div> --%>
			  </div>
	</div>
		<div class="span3">
		<input type="hidden" value="<s:text name="ui.report.submonitor.newaddition"/>">
	  <div class="widget-box chartDiv05"  id="restorationChartDiv" value="restoration">
	    <%-- <div class="widget-header">
	      	<h5><s:text name="ui.report.submonitor.restoration"/></h5>
	      	<span class="widget-toolbar">
                	<button class="btn btn-mini chartDiv05" value="restoration" data-rel="tooltip" data-placement="top" data-original-title="Export">
                    	<i class="icon-search"></i>
                    </button>
                </span> 
	    </div>
	    <div class="widget-body">
	      <div class="widget-body-inner" style="display: block;">
	        <div class="widget-main" id="restorationChartDiv">     	
	        </div>
	      </div>
	    </div> --%>
	  </div>
	</div>
	</div>
  </div>
</div>

<!-- Modal 弹出框内容 begin -->
<div id="add_proj" class="modal" style="display:none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body">
  	<div class="ami_table_header">
  		<span  id="titleName"  class="ami_table_header_title">Table</span>
  		<span class="ami_table_header_btn"><i data-dismiss="modal" aria-hidden="true" class="btn-closez"></i></span>
  	</div>
  	<div class="row-fluid tabPad">
 		<div class="tabTitle">
 			<ul>
 				<li class="wh90"><s:text name="ui.report.submonitor.no"/></li>
 				<li class="wh280"><s:text name="ui.report.submonitor.servicenames"/></li>
 				<li class="wh90"><s:text name="ui.report.submonitor.noofsubs"/></li>
 				<li class="wh90">%</li>
 			</ul>
 		</div>
 		<div class="tabBody" id="table_report">
 			
 		</div>
      <%-- <table id="table_report" class="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th><s:text name="ui.report.submonitor.no"/></th>
            <th><s:text name="ui.report.submonitor.servicenames"/></th>
            <th><s:text name="ui.report.submonitor.noofsubs"/></th>
            <th>%</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table> --%>
    </div>
  </div>
</div>
<div class="modal-backdrop in" id="modal-backdrop" style="display:none"></div>
<script type="text/javascript" src="/js/common.js"></script>
<!-- Modal 弹出框内容  end -->
<script>
$(document).ready(function() {
	$(".chartDiv, .chartDiv01, .chartDiv02, .chartDiv03, .chartDiv04, .chartDiv05").click(function(){
	var centerTit = $(this).prev("input:hidden").val();
	$('#titleName').html(centerTit);
	var searchDate =$("#searchDate").val().split("-");
	$.ajax({url:"/report/plusrservicetable?a="+new Date(),
				type:"POST",
				data:{startDate:searchDate[0],endDate:searchDate[1],stautsType:$(this).attr("value")},
				success:function(resultdata){
				createTable(resultdata);
				}});
	});
});
					
function seachfor(){
    $('.ami_Mask').show();
	var h;
	var autoH = $(window).height();
	if( autoH>570 && autoH<770){
		h = "240px";
	}else if(autoH<570){
		h ="200px";
	}else if(autoH>770){
		h="240px";
	}
	var searchDate = $("#searchDate").val().split("-");
		getChart('grossAddChartDiv',h,null).render();
		getChart('suspendChartDiv',h, null).render();
		getChart('terminateChartDiv',h, null).render();
		getChart('newAdditionChartDiv',h, null).render();
		getChart('netgrossChartDiv',h, null).render();
		getChart('restorationChartDiv',h, null).render();
		loadData({
			startDate : searchDate[0],
			endDate : searchDate[1],
		});
		
		$("#search_span_date").html($("#searchDate").val());
	}
	function showModel(){
	$("#add_proj").fadeIn("slow");
		$("#modal-backdrop").fadeIn("slow");
	}
	function loadData(args) {
		var data = new Array(6);
		$.ajax({
			url : "/report/plusrservicedata?a=" + new Date(),
					type : "POST",
					data : args,
					success : function(resultdata) {
						FusionCharts
								.ready(function(FusionCharts) {
									var chartdata = new Array(3);
									for (var i = 0; i < resultdata.grossAddPageBean.list.length; i++) {
										chartdata[i] = {
											"label" : resultdata.grossAddPageBean.list[i].server_name,
											"value" : resultdata.grossAddPageBean.list[i].ratio,
											 link:"javascript:;"
										};
									}
									data[0] = chartdata;
									chartdata = new Array(3);
									for (var i = 0; i < resultdata.suspendPageBean.list.length; i++) {
										chartdata[i] = {
											"label" : resultdata.suspendPageBean.list[i].server_name,
											"value" : resultdata.suspendPageBean.list[i].ratio,
											 link:"javascript:;"
										};
									}
									data[1] = chartdata;
									chartdata = new Array(3);
									for (var i = 0; i < resultdata.terminatePageBean.list.length; i++) {
										chartdata[i] = {
											"label" : resultdata.terminatePageBean.list[i].server_name,
											"value" : resultdata.terminatePageBean.list[i].ratio,
											 link:"javascript:;"
										};
									}
									data[2] = chartdata;
									chartdata = new Array(3);
									for (var i = 0; i < resultdata.newAdditionPageBean.list.length; i++) {
										chartdata[i] = {
											"label" : resultdata.newAdditionPageBean.list[i].server_name,
											"value" : resultdata.newAdditionPageBean.list[i].ratio,
											 link:"javascript:;"
										};
									}
									data[3] = chartdata;
									chartdata = new Array(3);
									for (var i = 0; i < resultdata.netGrossPageBean.list.length; i++) {
										chartdata[i] = {
											"label" : resultdata.netGrossPageBean.list[i].server_name,
											"value" : resultdata.netGrossPageBean.list[i].ratio,
											 link:"javascript:;"
										};
									}
									data[4] = chartdata;
									chartdata = new Array(3);
									for (var i = 0; i < resultdata.restorationPageBean.list.length; i++) {
										chartdata[i] = {
											"label" : resultdata.restorationPageBean.list[i].server_name,
											"value" : resultdata.restorationPageBean.list[i].ratio,
											 link:"javascript:;"
										};
									}
									data[5] = chartdata;
									console.log(data);
									var h;
									var autoH = $(window).height();
									if( autoH>570 && autoH<770){
										h = "240px";
									}else if(autoH<570){
										h ="200px";
									}else if(autoH>770){
										h="250px";
									}
									getChart('grossAddChartDiv',h, data[0],'<s:text name="ui.report.submonitor.grossadd"/>')
											.render();
									getChart('suspendChartDiv',h, data[1],'<s:text name="ui.report.submonitor.suspend"/>')
											.render();
									getChart('terminateChartDiv',h, data[2],'<s:text name="ui.report.submonitor.terminate"/>')
											.render();
									getChart('newAdditionChartDiv',h, data[3],'<s:text name="ui.report.submonitor.newaddition"/>')
											.render();
									getChart('netgrossChartDiv',h, data[4],'<s:text name="ui.report.submonitor.netgross"/>')
											.render();
									getChart('restorationChartDiv',h, data[5],'<s:text name="ui.report.submonitor.restoration"/>')
											.render();
								});
					}
				});
				
	}
	function getChart(target, height, date, centerLabel) {
	    $('.ami_Mask').hide();
		var chart = new FusionCharts(
				{
					type : 'doughnut2d',
					renderAt : target,
					width : '100%',
					height : height,
					dataFormat : 'json',
					dataSource : {
						"chart" : {
							"caption": centerLabel,
			                "subCaption": "",
			                 "clickURL":'javascript:showModel()',
			                "numberPrefix": "",
			                "paletteColors" :paletteColors,
			                "bgColor" : "#ffffff", 
			                "showBorder" : "0",  
			                "use3DLighting" : "0", 
			                "showShadow" : "0",   
			                "formatNumberScale":"0",
			                 "pieRadius":"55",
			                 "anchorRadius":"200",
			                 "smartLabelClearance":"0",
			                 "pieSliceDepth":"20",
			                 "pieYScale":"5",
							//"enableSmartLabels" : "0",         
			                "startingAngle": "310",
			                "decimals": "0",                
			                //"defaultCenterLabel":centerLabel,
							"showPercentValues" : "1",
							//"showLegend" : "1",
							"legendShadow" : "0",
							//"legendBorderAlpha" : "0",
							"centerLabelBold" : "1",
							//"showTooltip" : "0",
			               // "centerLabel": "$label: $value",                
			                "decimals" : "2",
							//"captionFontSize" : "6",
							
							//"subcaptionFontSize" : "14",
							//"subcaptionFontBold" : "0",
							//"useDataPlotColorForLabels" : "1"
/* 							"subCaption" : "",//副标题ebf2f7
							"numberPrefix" : "",
							"paletteColors" : paletteColors,
							"bgColor" : "#ebf1f6",
							"showBorder" : "0",
							"use3DLighting" : "0",
							"showShadow" : "0",
							"formatNumberScale":"0",
							"enableSmartLabels" : "0",
							"startingAngle" : "310",
							"showLabels" : "0",
							"showPercentValues" : "1",
							"showLegend" : "1",
							"legendShadow" : "0",
							"legendBorderAlpha" : "0",
							"defaultCenterLabel" : centerLabel,
							"centerLabel" : "",
							"centerLabelBold" : "1",
							"showTooltip" : "0",
							"decimals" : "2",
							"captionFontSize" : "14",
							"subcaptionFontSize" : "14",
							"subcaptionFontBold" : "0",
							"useDataPlotColorForLabels" : "1" */
						},
						"data" : date
					}
				});
		return chart;
	}
	//动态的创建一个table，同时将后台获取的数据动态的填充到相应的单元格
	function createTable(data) {
		var tabStr = "";
		for (var i = 0; i < data.pageBean.list.length; i++) {
			tabStr += "<div class='tabLsit'>";
			tabStr += "<ul>";
			tabStr += "<li class='wh90'>";
			tabStr += i + 1;
			tabStr += "</li>";
			tabStr += "<li class='wh280'>";
			tabStr += data.pageBean.list[i].server_name;
			tabStr += "</li>";
			tabStr += "<li class='wh90'>";
			tabStr += data.pageBean.list[i].total;
			tabStr += "</li>";
			tabStr += "<li class='wh90'>";
			tabStr += toDecimal(data.pageBean.list[i].ratio, 2);
			tabStr += "</li>";
			tabStr += "</ul>";
			tabStr += "</div>";
		}
		
		$('#table_report').html(tabStr);
	}
	/**
		四舍五入
	 */
	function toDecimal(num, x) {
		var n = 1;
		for (var i = 0; i < x; i++) {
			n = n * 10;
		}
		return Math.round(num * n) / n;
	}
</script>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/Charts/FusionCharts.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/prettify/prettify.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/json2.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/lib.js" ></script>
<script type="text/javascript">
$(function(){
	//seachfor();
})
</script> 