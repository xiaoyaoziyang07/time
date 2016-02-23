<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.opensymphony.xwork2.util.*,cn.amichina.timecomm.group.model.ServiceGroup,cn.amichina.timecomm.report.model.SertjReport"%> 
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
  <div class="row-fluid">
    <div class="toggles" style="float:none">
      <div class="toggle_h">
        <div class="leftposit"> <a href="javascript:;" class="dj"><s:text name="ui.report.search.expansion"/></a><i class="icon-click"></i></div>
        <div class="di_out">
          <div class="put-results">
          	<span id="search_span_id"></span>
          	<span id="search_span_date"></span>
          </div>
        </div>
      </div>
      <div class="toggle_box">
        <form class="form-inline" action="/report/sertj" method="post" id="searchForm">
        	<span class="input-icon tip_icon">
        		<label><s:text name="ui.subreport.servicesid"/>：</label>
          		<input type="text" id="servicesId" value="${servicesId}" />
          		<div class="remidArr" style='display:none'>
                	<div class="tooltip-inner tip_in"><s:text name="ui.subreport.servicesidisnull"/></div>
                	<div class="arrows"></div>
                </div>
        	</span>
          <span class="input-icon">
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
                  <input name="startDate" id="startDate" class="startDate" readonly value="" type="text">
                  <input name="endDate" id="endDate"  setDate="6" class="endDate" readonly  value="" type="text" disabled onChange="datePickers()">
                </div>
              </div>
            </div>
          </div>
          </span>
          
          <button type="button" id="btn1" class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
          <%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>
        </form>
      </div>
    </div>
    <div class="row-fluid">
          	<span class="ami_table_header_btn">
        	<button onclick="exportReport()" class="btn btn-mini btn-info" data-rel="tooltip" data-placement="top" data-original-title="Export"><i class="icon-external-link"></i></button>
        </span>
        <span class="ami_table_header_btn" style="color: #ffc557"><s:text name="ui.unit.title"/>: (MB)</span> 
        <div style="clear:both"></div>
      <div class="ami_table_header" > 
           <div class="page-content-table-top">
              <span class="ami_table_header_title"><s:text name="ui.subreport.usagereport"/></span>
           </div>
      </div>
      <table id="report" class="table table-striped table-bordered table-hover" style="margin-bottom:0">
        <thead>
          <tr>
             <s:iterator value="groupList" var="group">
              <th>${group.groupname}</th>
             </s:iterator>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="rows" var="map">
	        <tr>
	        	<s:iterator value="groupList" id="group">
	        	<%
              		ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");
              		Map<ServiceGroup, SertjReport>  map =(Map<ServiceGroup, SertjReport>)vs.findValue("map");
              		Object group =vs.findValue("group");
              		SertjReport sr =map.get(group);
              		String str_In_NUM ="0";
              		String str_Out_NUM="0";
              		String total_NUM="0";
              		if(sr.getDate()==null&&sr.getTotal()==null){
	              		Long outNum = sr.getOutnum();
	              		Long inNum = sr.getInnum();
	              		Long total=null;
	              		if(inNum!=null){
	              		str_In_NUM =String.format("%.2f", inNum.doubleValue()/1024/1024);
	              		total=inNum;
	              		}
	              		if(outNum!=null){
	              		str_Out_NUM =String.format("%.2f", outNum.doubleValue()/1024/1024);
	              		total+=outNum;
	              		}
	              		if(total!=null){
	              		total_NUM= String.format("%.2f", total.doubleValue()/1024/1024);
	              		}
	              		%><td up="<%=str_Out_NUM%>" down="<%=str_In_NUM%>">
					  	<%=total_NUM%>            	
              			<%
              		}else if(sr.getDate()!=null){
              		SimpleDateFormat format =new SimpleDateFormat("yyyy/MM/dd");
              			out.print("<td>");
              			out.print(format.format(sr.getDate()));
              		}else if(sr.getTotal()!=null){
              		String tot =String.format("%.2f", sr.getTotal().doubleValue()/1024/1024);
              		out.print("<td>"+tot);
              		}
              		%></td>
             </s:iterator>
			</tr>
			
        </s:iterator>
					<tr id="colTotal">

						<td>Total</td>
						<td style="cursor: pointer;">0.00</td>

						<td style="cursor: pointer;">0.00</td>

						<td style="cursor: pointer;">0.00</td>

						<td style="cursor: pointer;">0.00</td>

						<td style="cursor: pointer;">0.00</td>

						<td style="cursor: pointer;">0.00</td>

						<td style="cursor: pointer;">0.00</td>
						<td style="cursor: pointer;">0.00</td>
						<td style="cursor: pointer;">0.00</td>
						<td>0.00</td>
					</tr>
				</tbody>
      </table>
       <div class="page-content-table-bottom">
       
       </div>
    </div>
  </div>
</div>
<div class="data-inner" style="display: none;" ></div>
<script type="text/javascript" src="/js/jquery.paging.js"></script> 
<script type="text/javascript">
	$(function() {
	var th =undefined;
	$("#report thead th").each(function(index,reth){
		if($(reth).html()=="Date"){
			th=reth;
		};
	});
	if(th==undefined){
	$("#report thead th:eq(0)").before("<th>Date</th>");
	}
		$("#report tbody tr").each(function(trindex, tritem) {//遍历每一行
			$(tritem).find("td").each(function(tdindex, tditem) {
			if (tdindex != 0&&trindex!=$("#report tbody tr").length-1) {
				$("#colTotal td").each(function(toindex, toitem){
				if(toindex==tdindex){
				var sum =parseFloat($(tditem).text());
				if(!isNaN(sum)){
					sum+=parseFloat($(toitem).text());
					$(toitem).text(sum);
				}
					}
				});
				}
				
			});
		});
	});
	function seachfor() {
	    $('.ami_Mask').show();
		var iVal = $("#servicesId");
		if (iVal.val() == "") {
			$(".remidArr").css("display", "block");
		} else {
			var searchDate = $("#searchDate").val().split("-");
			var args ={servicesId:$('#servicesId').val(),startDate:searchDate[0],endDate:searchDate[1]};
			findme($("#page_title_nav").html(),args);
		}
		$("#servicesId").focus(function() {
			$(".remidArr").css("display", "none");
		});
	}
	//悬浮表格td显示上行下行
	$(".table tr td").on("mouseover", function(ev) {
		var iUp = $(this).attr("up");
		var iDown = $(this).attr("down");
		if (typeof iUp == "undefined") {

		} else {
			$(".data-inner").html("UL：" + iUp + " / DL：" + iDown).css({
				"top" : ev.pageY - 100,
				"left" : ev.pageX - 200
			}).show();
			$(this).css({
				"cursor" : "pointer"
			});
		}
	});
	$(".table tr td").on("mousemove", function(ev) {
		$(".data-inner").css({
			"top" : ev.pageY - 100,
			"left" : ev.pageX - 200
		});
	});
	$(".table tr td").on("mouseleave", function(ev) {
		$(".data-inner").hide();
	});
	function exportReport() {
		var data = "";
		if (confirm('<s:text name="ui.subreport.startexporting"/>')) {
			$("#report tbody tr,#report thead tr").each(
					function(trindex, tritem) {//遍历每一行
						$(tritem).find("td,th").each(function(tdindex, tditem) {
							data += $(tditem).text().trim() + ",";//遍历每一个数据，并存入
						});
						data += ";";
					});
			window.open('/download/report?data=' + data);
		}
		$("#search_span_date").html($("#searchDate").val()).show();
		$("#search_span_id").html($("#servicesId").val()).show();
	}
</script> 
<script type="text/javascript" src="js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="js/share7.js"></script>
<script type="text/javascript" src="/js/common.js"></script>