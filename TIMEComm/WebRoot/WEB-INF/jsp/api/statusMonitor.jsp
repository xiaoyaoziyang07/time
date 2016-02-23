<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
	<div class="row-fluid">
	<div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"> <a href="javascript:;" class="dj"></a><i class="icon-click"></i></div>
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
          <button type="button" id="btn_search"   class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
          <%-- <a class="btn btn-mini btn-small " ><s:text name="ui.report.search.closebutton"/></a> --%>
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
      <div class="ami_table_header" > 
      	<span class="ami_table_header_title"><s:text name="ui.report.apistatuts.failuredlist"/></span>
      	<span class="ami_table_header_btn" style="margin: 0;">
      		<label class="inline">
	    		<input id="onOff" checked="" type="checkbox" class="ace-switch ace-switch-5">
	    		<span for="onOff" class="lbl"><s:text name="ui.report.apistatuts.autorefresh"/></span>
	    	</label>
      	</span>
      </div>
      <table id="failuredTba" class="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th><s:text name="ui.report.apistatuts.no"/></th>
            <th><s:text name="ui.report.apistatuts.apiitems"/></th>
            <th><s:text name="ui.report.apistatuts.alarm"/></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td class="showItme" value="T1001"><s:text name="ui.report.apistatuts.t1001"/></td>
            <td>3 </td>
          </tr>
          <tr>
            <td>2</td>
            <td class="showItme" value="T1002"><s:text name="ui.report.apistatuts.t1002"/></td>
            <td>2</td>
          </tr>
          <tr>
            <td>3</td>
            <td class="showItme" value="T1003" ><s:text name="ui.report.apistatuts.t1003"/></td>
            <td>2</td>
          </tr>
          <tr>
            <td>4</td>
            <td class="showItme" value="T1004"><s:text name="ui.report.apistatuts.t1004"/></td>
            <td>2</td>
          </tr>
         
          <tr>
            <td>5</td>
            <td class="showItme"  value="T1005"> <s:text name="ui.report.apistatuts.t1005"/></td>
            <td>2</td>
          </tr>
          <tr>
            <td>6</td>
            <td class="showItme" value="T1006"><s:text name="ui.report.apistatuts.t1006"/></td>
            <td>2</td>
          </tr>
          <tr>
            <td>7</td>
            <td class="showItme" value="T1007"><s:text name="ui.report.apistatuts.t1007"/></td>
            <td>2</td>
          </tr>
          <tr>
            <td>8</td>
            <td class="showItme" value="T1008"><s:text name="ui.report.apistatuts.t1008"/></td>
            <td>2</td>
          </tr>
          <tr>
            <td>9</td>
            <td class="showItme" value="T1009"><s:text name="ui.report.apistatuts.t1009"/></td>
            <td>2</td>
          </tr>
        </tbody>
      </table>
    </div>
</div>
<!-- Modal 弹出框内容 begin -->
<div id="add_proj" class="modal" style="display:none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body">
  	<div class="ami_table_header">
  		<span id="h_title"  class="ami_table_header_title"></span>
  		<span class="ami_table_header_btn">
  			<i data-dismiss="modal" aria-hidden="true" class="btn-closez"></i>
  		</span>
  	</div>
  	<div class="row-fluid">
      <table id="table_report" class="table table-striped table-bordered table-hover">
        <thead>
          <tr>
            <th><s:text name="ui.report.apistatuts.model.no"/></th>
            <th><s:text name="ui.report.apistatuts.model.userid"/></th>
            <th style="width:100px"><s:text name="ui.report.apistatuts.model.reason"/></th>
            <th><s:text name="ui.report.apistatuts.model.time_stamp"/></th>
          </tr>
        </thead>
        <tbody id="provtypeData">
        </tbody>
      </table>
      <div class="row-fluid Paging">
    	<div class="span6">
    		<div id="pageInfo" class='dataTables_info'>1/5</div>
    	</div>
    	<div class="span6">
    		<div class="dataTables_paginate paging_bootstrap pagination">
    			<ul id="pageCode">
    			</ul>
    		</div>
    	</div>
    	
    </div>
    </div>
    
  </div>
</div>
<div class="data-inner" style="display: none; z-index: 99999" ></div>
<div class="modal-backdrop fade in" id="modal-backdrop" style="display:none"></div>
<!-- Modal 弹出框内容  end -->
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>
var resultDataFailuredList;
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
$(document).ready(function(e) {
	clearInterval(interval);
 	autorefresh();
	seachfor();
	/* $("#btn_search").click(function(){alert(0);}) */
	$(".showItme").click(function(){
	$("#h_title").html($(this).html());
		if($(this).next().html()=='0'){
			return;
		}
		gotoPage(1,$(this).attr("value"),true);
		$(this).next().attr("read",$(this).next().find("span").html());
	 	$(this).next().attr("read",$(this).next().find("span").html());
		$(this).parent().removeClass("error");
		$(this).next().find("span").removeClass("badge badge-important"); 
		$("#add_proj").show();
		$("#modal-backdrop").show();
	});
	//锁定刷新
	$('#onOff').on('click',function(){
		if($(this).is(':checked')){
			refreshData(true,true);
			autorefresh();
		}else{
			clearInterval(interval);
		}
	});
});
function autorefresh(){
clearInterval(interval);
interval=setInterval(function(){
				if(url!="/apistatus/servicestauts"){
					clearInterval(interval);
					return;
				 }
				refreshData_(true);
				},60000); 
				seachfor();
}
function refreshData(isPrompt,isIni){
var searchDate = $("#searchDate").val().split("-");
var args ={startDate:searchDate[0],endDate:searchDate[1]};
	$.post('/apistatus/servicestautsdata?a='+ new Date(), args,
	function (resultData, status){
	console.log("------------");
		console.log(searchDate[0]+"-"+searchDate[1]);
		console.log(resultData);
	console.log("------------");
		 for (var int = 0; int < resultData.list.length; int++) {
		 var $target =$("td[value='"+resultData.list[int].provtype+"'] + td");
		 var val =$target.html();
		   if(resultData.list[int].count.toString()!=val.toString()){
			$target.attr("read",resultData.list[int].count);
			$target.html("<span>"+resultData.list[int].count+"</span>");
			  		$target.parent().removeClass("error");
		   }
			}
	  });
}
function seachfor(){
var $td =$("td[value] + td").html("0");
	$td.html("0");
	refreshData(false);
	 $("#search_span_date").html($("#searchDate").val()).show();
		$("#search_span_id").html($("#servicesId").val()).show();
		
}
function createPageNum(pageBean,provtype){
 	var pageCodeStr="<li class='prev' onclick='gotoPage(1,\"";
 	pageCodeStr+=provtype;
 	pageCodeStr+="\",false)'><a href='javascript:;'><i class='icon-double-angle-left'></i></a></li>";
	for (var int = 0; int < pageBean.pageBar.length; int++) {
	pageCodeStr+="<li";
		if(pageBean.pageBar[int]==pageBean.currentPage){
			pageCodeStr+=" class='active'";
		}
		pageCodeStr+=" onclick='gotoPage("+pageBean.pageBar[int]+",\""+provtype+"\",false)'><a href='javascript:;'>"+pageBean.pageBar[int]+"</a></li>";
	}
	pageCodeStr+="<li class='next'  onclick='gotoPage("+pageBean.totalPage+",\""+provtype+"\",false)'><a href='javascript:;'><i class='icon-double-angle-right'></i></a></li>";
	$("#pageCode").html(pageCodeStr);
	$("#pageInfo").html(pageBean.currentPage+"/"+pageBean.totalPage);
	show(); 
}
function gotoPage(currentPage,provtype,iscreatePageNum){
var searchDate = $("#searchDate").val().split("-");
	var args ={startDate:searchDate[0],endDate:searchDate[1],'provtype':provtype,'queryInfo.currentPage':currentPage};
	$.post('/apistatus/servicestautsprovtype?a='+ new Date(), args,function(resultData){
		console.log("--------debug pageBean begin--------"+new Date());
		console.log(resultData.pageBean);
		createtabdata(resultData.pageBean);
		createPageNum(resultData.pageBean,provtype);
	});
}
function show(){
//悬浮表格td显示上行下行
	$("#table_report tr").each(function(){
		$(this).children("td").eq(2).css({
			"width":"200px",
			"white-space": "normal"
		});
		$(this).children("td").eq(2).on("mouseover", function(ev) {
			var valTd = $(this).attr("val");
			if(valTd !=""){
				$(".data-inner").html(valTd).css({
					"top" : ev.pageY - 100,
					"left" : ev.pageX - 200
				}).show();
				$(this).css({
					"cursor" : "pointer"
				});
			}
		});
		$(this).children("td").eq(2).on("mousemove", function(ev) {
			$(".data-inner").css({
				"top" : ev.pageY - 100,
				"left" : ev.pageX - 200
			});
		});
		$(this).children("td").eq(2).on("mouseleave", function(ev) {
			$(".data-inner").hide();
		});
	});
}

function createtabdata(pageBean){
		var dataStr="";	
		for (var int = 0; int < pageBean.list.length; int++) {
		 	dataStr+=" <tr>";
			dataStr+="<td>";
		 	dataStr+=pageBean.list[int].num;
			dataStr+="</td><td>";
		 	dataStr+=pageBean.list[int].user_id;
			dataStr+="</td>";
			dataStr+="<td val='"+pageBean.list[int].details+"'>";
		 	dataStr+=pageBean.list[int].reason;
			dataStr+="</td>";
			dataStr+="<td>";
		 	dataStr+=new Date(pageBean.list[int].time_stamp).Format("MM/dd/yyyy");
			dataStr+="</td>";
			dataStr+= "</tr>";	
		}
         $("#provtypeData").html(dataStr);
		console.log("----------dataStr----------="+dataStr);
		console.log("----------pageBean end-----------");
}
function refreshData_(isPrompt,isIni){
var searchDate = $("#searchDate").val().split("-");
var args ={startDate:searchDate[0],endDate:searchDate[1]};
	$.post('/apistatus/servicestautsdata?a='+ new Date(), args,
	function (resultData, status){
		 for (var int = 0; int < resultData.list.length; int++) {
		 var $target =$("td[value='"+resultData.list[int].provtype+"'] + td");
		 var val =$target.html();
		   if(resultData.list[int].count.toString()!=val.toString()){
		   		if(isPrompt==true&&$target.attr("read")!=resultData.list[int].count.toString()){
			   		$target.parent().addClass("error");
			  		$target.html("<span class='badge badge-important'>"+resultData.list[int].count+"</span>");
		  		}else{
			  		$target.parent().removeClass("error");
			  		$target.html("<span>"+resultData.list[int].count+"</span>");
		  		}
		   }
			}
	  });
	 
}
</script>
