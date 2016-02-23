<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
	<div class="row-fluid">
		<div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"><a href="javascript:;" class="dj"></a><i class="icon-click"></i></div>

      </div>
      <div class="toggle_box">
        <form class="form-inline" id="searchForm">  
             	
           <!-- <label>vlan：</label> -->
          	<%-- <select id="vlanlist" name="name" class="chzn-select"  data-placeholder="">
          		<option value=""></option>
          		<s:iterator value="vlanlist" var="vlan" >
          			<option value="${vlan.vId}">${vlan.vName}</option>
          		</s:iterator>
          	</select> --%>
          <label><s:text name="ui.report.search.date"/>：</label>
          <div class="dateOut">
            <input type="text" id="searchDate" class="ui-datepicker-time" readonly value=""  onchange="alert(0)" />
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
          <%--   <select>
            	<option>1</option>
            	<option>2</option>
            	<option>2</option>
            </select> --%>
          </div>
                    <label> 
          	<div class="piaochecked on_check">
			  	<input name="type" checked type="radio" value="0" class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.total"/></span>
          	<div class="piaochecked">
			  	<input name="type" type="radio"  value="1" class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.download"/></span>
          	<div class="piaochecked">
			  	<input name="type" type="radio"  value="2"  class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.upload"/></span>
          </label>
          <button type="button"   class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
          <%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>
        </form>
      </div>
      <div class="toggle_f">
          <div class="di_out">
          <div class="put-results">
          <span class="obj_name">Date</span><span id="search_span_date"></span>
          <span class="obj_name">Throughput</span><span id="search_span_trend"></span>        
          <%-- <select id="hourReport" style="float:left; margin-top:7px; margin-right:5px;">
          <s:iterator  value="hourReports">
          	<option><s:property/></option>
          </s:iterator>
          </select>
          <button type="button" onclick="downloaddayreport()" class="btn btn-mini btn-info"><i class="icon-external-link"></i></button> --%>
          </div>
        </div>
      </div>
    </div>
	</div>
    <div class="row-fluid">
    	<div id="showTable" align="center"></div>  
  	</div>
  	
</div>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/jquery.pagination.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>


<script>
var pageBean;
$(function(){
	$(".chzn-select").chosen({allow_single_deselect:true});
	seachfor();
	getVlans();
});
function seachfor(){
	$('.ami_Mask').show();
	var searchDate = $("#searchDate").val().split("-");
	var searchType =  $(".on_check input").val();
	console.log(searchType);
	//var vlanId=$("#vlanlist  option:selected").val();
	$.post("/bandwidth/customerplan",{startDate:searchDate[0],endDate:searchDate[1],type:searchType},function(result){
	console.log(" customerplan --------");
	console.log(result);
	console.log(" customerplan --------");
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
			var width='100%';				
	var autoH = $('#main-content').height();
	var autoW = $('#main-content').width();
	   // $('#PAGECONTENT').width(autoW+'px');
    var height=autoH-120;
		getChart('showTable',tempdataX,tmpDate,width,height).render();
	});
	$("#search_span_date").html($("#searchDate").val()).show();
	//alert($('radio').attr('value',searchType).next().html());
	/* var trend = '';
	if(searchType == 0){
		trend='<s:text name="ui.report.bandwidth.total"/>';
	}if(searchType == 1){
		trend = "<s:text name="ui.report.bandwidth.download"/>";
	}if(searchType == 2){
		trend = "<s:text name="ui.report.bandwidth.upload"/>";
	} */
	var trends = $(".on_check").next().html();
	$("#search_span_trend").html(trends).show();
}
function getChart(id,dataX,data,width,height){
   	$('.ami_Mask').hide();

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
			        "yAxisName": "<s:text name="ui.unit.traffic"/>(GB)",
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
        			labelStep:getStep(),
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
  return ;
  $.post("/vlan/query",{name:""},function(resultData){
  console.info('vlanJson Result:');
  console.info(resultData);
  for (var int = 0; int < resultData.list.length; int++) {
 		 $("#vlanlist").append("<option value='"+resultData.list[int].vId+"'>"+resultData.list[int].vName+"</option>");
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
	function downloaddayreport(){
		if(confirm('<s:text name="ui.subreport.startexporting"/>')){
		window.open('/customerplanhourdown?date=' + $("#hourReport").find("option:selected").text());
		}
	} 
</script>
