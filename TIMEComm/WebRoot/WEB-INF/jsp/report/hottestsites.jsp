<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
	<div class="row-fluid">
		<div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"><a href="javascript:;" class="dj"><s:text name="ui.report.search.expansion"/></a><i class="icon-click"></i> </div>
        <div class="di_out">
          <div class="put-results">
          <span id="search_span_name"></span>
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
           <label>Vlan：</label>
          <select name="customerPlanType" style="margin-left:10px; width:150px;">
          <option value="all">All</option>
          <s:iterator value="customerPlanList">
          	<option value="${customerPlanId}">${customerPlanName}</option>
          </s:iterator>
          </select> 
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
<div id="add_proj" class="modal" style="display:none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body" style="max-height:411px;">
  	<div class="ami_table_header">
  		<span  class="ami_table_header_title">${topReportTitle}</span>
  		<span class="ami_table_header_btn">
  			<i data-dismiss="modal" aria-hidden="true" class="btn-closez"></i>
  		</span>
  		<span class="ami_table_header_btn">
  			<button class="btn btn-mini btn-info" onclick="exportReport()"><i class="icon-external-link"></i></button>
  		</span>
  	</div>
  	<table id="reportTab" class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
                <s:iterator value="topReportHeaders">
                	<th><s:property/></th>
                </s:iterator>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
            <div class="row-fluid Paging">
		    	<div class="span6">
		    		<div id="pageInfo" class='dataTables_info'></div>
		    	</div>
		    	<div class="span6">
		    		<div class="dataTables_paginate paging_bootstrap pagination">
		    			<ul id="pageBar">
		    			</ul>
		    		</div>
		    	</div>
		    </div> 
  </div>
</div>
<div class="modal-backdrop fade in" id="modal-backdrop" style="display:none"></div>
<!-- Modal 弹出框内容  end -->
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>




	var width='100%';				
	var autoH = $('#main-content').height();
    var height=autoH-120;
	getChart('showTable','data','dataul','labels',width,height).render();
    $('.ami_Mask').hide();




var pageBean;
$(document).ready(function(e) {
	//点击弹出表格
	$('#showTable').on('click',function(){
		if(pageBean.list.length>0){
			createtab();
			$("#add_proj").show();
			$("#modal-backdrop").show();
		}
	});
	
//	seachfor();
});
function seachfor(){
	$('.ami_Mask').show();
	var searchDate = $("#searchDate").val().split("-");
	var args ={startDate:searchDate[0],
				endDate:searchDate[1],
				target:'${target}',
				customerPlanType:$("[name=customerPlanType]").val(),
				'queryInfo.currentPage':1,
				'queryInfo.pageSize':50};
	$.post('/report/topbytopdata?a='+ new Date(), args,function(resultData){
	pageBean=resultData.pageBean;
	loadData();
		$("#search_span_date").html($("#searchDate").val()).show();
		
		//alert($("[name=customerPlanType]").val());
		$("#search_span_name").html($("[name=customerPlanType]").val()).show();
		
	});
}
function getChart(id,data,dataul,labels,width,height){
	$('.ami_Mask').hide();
    return  new FusionCharts({
            type: 'column2d',//column2d滑动数据图形doughnut2d圆形的数据
            renderAt: id,
            width: width,
            height:height,
            dataFormat: 'json',
            dataSource: {
            "chart": {
                "caption": "Average Page Load Time (hsm.com)",
                "subCaption": "Last Week",
                "showBorder": "0",
                "xAxisName": "Day",
                "yAxisName": "Time (In Sec)",
                "numberSuffix": "s",
                "baseFontColor" : "#333333",
                "baseFont" : "Helvetica Neue,Arial",
                "paletteColors" :paletteColors,
                "bgColor" : "#ffffff",
                "canvasBgColor" : "#ffffff",                                 
                "captionFontSize" : "14",
                "subcaptionFontSize" : "14",
                "subcaptionFontBold" : "0",
                "showBorder" : "0",
                "showPlotBorder": "0",
                "showXAxisLine" : "1",
                "showLegend": "0",
                "showShadow" : "0",
                "showCanvasBorder" : "0",
                "showAlternateHGridColor" : "0",
                "usePlotGradientColor" :"0",
                "divlineColor" : "#999999",
                "divlineThickness" : "1",
                "divLineIsDashed" : "1",
                "divLineDashLen" : "1",
                "divLineGapLen" : "1",
                "xAxisLineThickness" : "1",
                "xAxisLineColor" : "#999999",
                "toolTipColor": "#ffffff",
                "toolTipBorderThickness": "0",
                "toolTipBgColor": "#000000",
                "toolTipBgAlpha": "80",
                "toolTipBorderRadius": "2",
                "toolTipPadding": "5"
            },
            "categories": [
                {
                   "category": [
                        { "label": "Mon" },
                        { "label": "Tue" },
                        { "label": "Wed" },
                        { "label": "Thu" },
                        { "label": "Fri" },
                        { "label": "Sat" },
                        { "label": "Sun" }
                    ]
                }
            ],
            "dataset": [
                {
                    "seriesname": "Loading Time",
                    "allowDrag": "0",
    "data": [
        {
            "label": "Batman",
            "value": "71000"
        },
        {
            "label": "Thor",
            "value": "66000"
        },
        {
            "label": "Iron Man",
            "value": "20000"
        },
        {
            "label": "Superman",
            "value": "14000"
        },
        {
            "label": "Wolverine",
            "value": "7800"
        },
        {
            "label": "Spider Man",
            "value": "7400"
        },
        {
            "label": "Captain America",
            "value": "4900"
        }
    ]
                }
            ]
                
            }
        });
  }
  function loadData(){
  var data =[{}];
  var dataul = [{}];
  var labels = [{}];
  var valdl,valul;
  console.log("topbytop pageBean----------");
  console.log(pageBean);
  console.log("topbytop pageBean----------");
  for (var int = 0; int < (pageBean.list.length<11?pageBean.list.length:10); int++) {
  		labels[int] =  {'label':pageBean.list[int].top};
  	    valdl =((pageBean.list[int].download*1.0)/1024/1024/1024).toFixed(2);
  		data[int]={'value':valdl};
  		valul=((pageBean.list[int].upload*1.0)/1024/1024/1024).toFixed(2);
  		dataul[int]={'value':valul};
	}
	var width='100%';				
	var height;
	var autoH = $('#main-content').height();
    var height=autoH-120;
	getChart('showTable',data,dataul,labels,width,height).render();
  }
function createtab(){
	var tabStr="";
	var startIndex=pageBean.currentPage * 10-10;
	for (var int = 0; int < 10; int++) {
		if((startIndex+int)>=pageBean.list.length){
			break;
		}
		var down =((pageBean.list[startIndex+int].download*1.0)/1024/1024/1024).toFixed(2);
	  	var up =((pageBean.list[startIndex+int].upload*1.0)/1024/1024/1024).toFixed(2);
	  		tabStr+="<tr>";
			tabStr+="<td>"+(startIndex+int+1)+"</td>";
			if(pageBean.list[startIndex+int].top==null||pageBean.list[startIndex+int].top=="null")
				pageBean.list[startIndex+int].top="";
			tabStr+="<td>"+pageBean.list[startIndex+int].top+"</td>";
			tabStr+="<td>"+down+"</td>";
			tabStr+="<td>"+up+"</td>";
			if('${topReportTitle}'!='Top URL'){
			tabStr+="<td>"+((pageBean.list[startIndex+int].total*1.0)/1024/1024/1024).toFixed(2)+"</td>";
			}else{
			tabStr+="<td>"+pageBean.list[startIndex+int].total+"</td>";
			}
			tabStr+="</tr>";
	}
	$("#reportTab tbody").html(tabStr);
	createPageBar();
}
function createPageBar(){
	var pageBarStr="";
	if(pageBean.currentPage==1){
	  pageBarStr="<li class='prev disabled' onclick='gotoPage("+pageBean.previousPage+")'><a href='javascript:;'><i class='icon-double-angle-left'></i></a></li>";
	}else{
	  pageBarStr="<li class='prev' onclick='gotoPage("+pageBean.previousPage+")'><a href='javascript:;'><i class='icon-double-angle-left'></i></a></li>";
	}
	if(pageBean.pageBar.length>5)pageBean.pageBar.length=5;
	for (var int = 0; int < pageBean.pageBar.length; int++) {
		if(pageBean.currentPage==pageBean.pageBar[int]){
	    pageBarStr+="<li class='active' onclick='gotoPage("+pageBean.pageBar[int]+")'><a href='javascript:;'>"+pageBean.pageBar[int]+"</a></li>";
		}else{
	  	pageBarStr+="<li><a href='javascript:;' onclick='gotoPage("+pageBean.pageBar[int]+" )'>"+pageBean.pageBar[int]+"</a></li>";
		}
	}
	if(pageBean.nextPage>5||pageBean.currentPage>=5||pageBean.currentPage>=pageBean.totalPage){
	 pageBarStr+="<li class='next disabled' onclick='gotoPage("+pageBean.nextPage+" )'><a href='javascript:;'><i class='icon-double-angle-right'></i></a></li>";
	}else{
	 pageBarStr+="<li class='next' onclick='gotoPage("+pageBean.nextPage+" )'><a href='javascript:;'><i class='icon-double-angle-right'></i></a></li>";
	 }
	  $("#pageBar").html(pageBarStr);
	  if(pageBean.totalPage>5){
	   $("#pageInfo").html(pageBean.currentPage+"/5");
	  }else{
	   $("#pageInfo").html(pageBean.currentPage+"/"+pageBean.totalPage);
	  }
}
function gotoPage(num){
	if(num>5){
		num=5;
		pageBean.nextPage=num;
	}else{
		pageBean.nextPage=(num+1);
	}
	if(num>1){
		pageBean.previousPage=(num-1);
	}
	pageBean.currentPage=num;
	createPageBar();
	createtab();
}
//导出
function exportReport() {
	var data = "";
	if (confirm('<s:text name="ui.subreport.startexporting"/>')) {
		$("#reportTab thead tr th").each(function(this_index,this_val){
			data +=$(this_val).html()+",";
		});
		data+=";";
		for(var i=0; i<pageBean.list.length; i++){
			var down =((pageBean.list[i].download*1.0)/1024/1024/1024).toFixed(2);
	  		var up =((pageBean.list[i].upload*1.0)/1024/1024/1024).toFixed(2);
			data +=(i+1)+","+pageBean.list[i].top+","+down+","+up +",";
			if('${topReportTitle}'!='Top URL'){
			data+=((pageBean.list[i].total*1.0/1024/1024/1024).toFixed(2));
			}else{
			data+=pageBean.list[i].total;
			}
			data+=";";
		}
		window.open('/download/report?data=' + data);
	}
}
</script>
