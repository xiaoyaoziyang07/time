<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
	<div class="row-fluid">
		<div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"><a href="javascript:;" class="dj"><s:text name="ui.report.search.expansion"/></a><i class="icon-click"></i></div>
        <div class="di_out">
          <div class="put-results">
          	<span id="search_span_date"></span>
          </div>
        </div>
      </div>
      <div class="toggle_box">
        <form class="form-inline" id="searchForm">       	
          <label>vlan：</label>
          	<select id="vlans" name="name" class="chzn-select" id="" data-placeholder="Choose a Vlan...">
          		<option value=""></option>
          		<option value='AL'>Alabama</option>
	            <option value='AK'>Alaska</option>
	            <option value='AZ'>Arizona</option>
          	</select>
          <label>
          	<div class="piaochecked on_check">
			  	<input name="type" checked type="radio" class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.total"/></span>
          	<div class="piaochecked">
			  	<input name="type" type="radio" class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.download"/></span>
          	<div class="piaochecked">
			  	<input name="type" type="radio" class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.upload"/></span>
          </label>
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
	</div>
    <div class="row-fluid">
    	<div id="showTable" align="center"></div>  
  	</div>
  	
</div>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/jquery.pagination.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>
var pageBean;
$(function(){
	$(".chzn-select").chosen();
	seachfor();
	getVlans();
});
function seachfor(){
	$('.ami_Mask').hide();
	var searchDate = $("#searchDate").val().split("-");
	var searchType = $(".on_check input").val();
	$.post("/bandwidth/trending",{startDate:searchDate[0],endDate:searchDate[1],type:searchType},function(result){
		console.log(result);
		var tempdataX =[{category:[]}];
		for (var int = 0; int < result.dateSet.categoris.labels.length; int++) {
			var tmpcategoris={label:result.dateSet.categoris.labels[int]};
			tempdataX[0].category[int]=tmpcategoris;
		}
		var tmpDate =[];
		for (var int = 0; int < result.dateSet.seriesnames.length; int++) {
			var seriesname={seriesname:result.dateSet.seriesnames[int].seriesname,data:[]};
			for (var i = 0; i < result.dateSet.seriesnames[int].values.length; i++) {
					seriesname.data[i]={value:result.dateSet.seriesnames[int].values[i]};
			}
			tmpDate[int]=seriesname;
		}
		console.log("print XDate");
		console.log(tempdataX);
		console.log("print tmpDate");
		console.log(tmpDate);
		var width='100%';				
	var autoH = $('#main-content').height();
    var height=autoH-120;
		getChart('showTable',tempdataX,tmpDate,width,height).render();
	});
	$("#search_span_date").html($("#searchDate").val()).show();
	$('.icon-click').toggleClass('btnaft');
	$('.toggle_box').hide();
}
function getChart(id,dataX,data,width,height){
	$('.ami_Mask').show();
    return  new FusionCharts({
            type: 'stackedarea2d',//column2d滑动数据图形doughnut2d圆形的数据
            renderAt: id,
            width: width,
            height:height,
            dataFormat: 'json',
            dataSource: {
                "chart": {
			        "caption": "",
			        "subCaption": "",
			        "xAxisname": "",
			        "formatNumberScale":"0",
			        "yAxisName": "<s:text name="ui.unit.traffic"/>(Mbps)",
			        "numberPrefix": "",
			        "paletteColors":paletteColors,
			        "bgColor": "#ffffff",
			        "borderAlpha": "20",
			        "showCanvasBorder": "0",
			        "usePlotGradientColor": "0",
			        "plotBorderAlpha": "10",
			        "plotFillAlpha": "80",
			        "legendBorderAlpha": "0",
			        "legendShadow": "0",
			        "showValues": "0",
			        "showBorder": "0",
			        "slantlabels": "1",
        			"rotatelabels": "1",
			        "showXAxisLine": "1",
			        "xAxisLineColor": "#4cada6",
			        "divlineColor": "#999999",
			        "divLineDashed": "1",
			        "showAlternateHGridColor": "0",
			        "subcaptionFontBold": "0",
			        "subcaptionFontSize": "14"
    			},
    			"categories": dataX,
                "dataset": data
            }
        });
  }
  function getVlans(){
  alert('1');
  $.post("/vlan/query",{name:""},function(resultData){
  console.info(resultData);
  for (var int = 0; int < resultData.list.length; int++) {
 		 $("#vlans").append("<option value='"+resultData.list[int].vId+"'>"+resultData.list[int].vName+"</option>");
		}
  });
  }
</script>
