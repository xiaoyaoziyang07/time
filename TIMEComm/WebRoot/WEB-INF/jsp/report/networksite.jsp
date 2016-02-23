<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
	<div class="row-fluid">
		<div class="toggles">
			<div class="toggle_h">
				<div class="leftposit">
					<a href="javascript:;" class="dj"><s:text
							name="ui.report.search.expansion" /></a><i class="icon-click"></i>
				</div>
			</div>
			<div class="toggle_box">
				<form class="form-inline" id="searchForm">
					<label><s:text name="ui.report.search.date" />：</label>
					<div class="dateOut">
						<input type="text" id="searchDate" class="ui-datepicker-time"
							readonly value="" />
						<div class="ui-datepicker-css">
							<div class="ui-datepicker-quick">
								<p>
									<s:text name="ui.common.date.fastdate.dayM" />
									<a class="ui-close-date btn-closez"></a>
								</p>
								<div class="ui-datepicker-input">
									<div>
										<input class="ui-date-quick-button" type="button"
											value="<s:text name="ui.common.date.fastdate.today"/>"
											alt="0" name="" /> <input onOff="true"
											class="ui-date-quick-button" type="button"
											value="<s:text name="ui.common.date.fastdate.thisweek"/>"
											alt="weeks_0" /> <input class="ui-date-quick-button"
											type="button"
											value="<s:text name="ui.common.date.fastdate.thismonth"/>"
											alt="month_0" name="" />
										<%-- <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.pastdays"><s:param>7</s:param></s:text>" alt="-7" name=""/>
		                  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.pastdays"><s:param>14</s:param></s:text>" alt="-14" name=""/>
		                  <input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.pastdays"><s:param>30</s:param></s:text>" alt="-30" name=""/> --%>
									</div>
									<div>
										<input class="ui-date-quick-button" type="button"
											value="<s:text name="ui.common.date.fastdate.yesterday"/>"
											alt="-1" name="" />
										<!-- <input class="ui-date-quick-button" type="button" value="" id="thisM" alt="month_3" />
	                  <input class="ui-date-quick-button" type="button" value=" " id="lastM"  alt="month_2" /> -->
										<input class="ui-date-quick-button" type="button"
											value="<s:text name="ui.common.date.fastdate.lastweek"/>"
											alt="weeks_1" /> <input class="ui-date-quick-button"
											type="button"
											value="<s:text name="ui.common.date.fastdate.lastmonth"/>"
											id="lastTM" alt="month_1" />

									</div>

								</div>
							</div>
							<div class="ui-datepicker-choose">
								<p>
									<s:text name="ui.common.date.footer.title" />
								</p>
								<div class="ui-datepicker-date">
									<input name="startDate" id="startDate" class="startDate"
										readonly value="2014/12/20" type="text"> - <input
										name="endDate" id="endDate" setDate="29" class="endDate"
										readonly value="" type="text" disabled
										onChange="datePickers()">
								</div>
							</div>
						</div>
					</div>
					<label>Vlan:</label> <select name="vlanId" style="margin-left:10px; width:150px;">
						<option value="all">ALL</option>
						<s:iterator value="vlanList" var="vlan">
							<option value="${vlan.vId}">${vlan.vName}</option>
						</s:iterator>
					</select>
					<button type="button" class="btn btn-mini btn-info rg_bg closehj"
						onclick="seachfor()">
						<s:text name="ui.report.search.searchbutton" />
					</button>
					<%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>

				</form>
			</div>
			<div class="toggle_f">
			   				<div class="di_out">
					<div class="put-results">
					<span class="obj_name">Date</span><span id="search_span_date"></span>
					<span class="obj_name">Vlan Type</span><span id="search_span_name"></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div id="showTable" align="center"></div>
	</div>
</div>
<div id="add_proj" class="modal" style="display:none" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-body" style="max-height:411px;">
		<div class="ami_table_header">
			<span class="ami_table_header_title">${topReportTitle}</span> <span
				class="ami_table_header_btn"> <i data-dismiss="modal"
				aria-hidden="true" class="btn-closez"></i>
			</span> <span class="ami_table_header_btn">
				<button class="btn btn-mini btn-info" onclick="exportReport()">
					<i class="icon-external-link"></i>
				</button>
			</span>
		</div>
		<table id="reportTab"
			class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<s:iterator value="topReportHeaders">
						<th><s:property /></th>
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
<div class="modal-backdrop fade in" id="modal-backdrop"
	style="display:none"></div>
<!-- Modal 弹出框内容  end -->
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>
$(document).ready(function(e) {
	
	//点击弹出表格
	$('#showTable').on('click',function(){
		if(data.dataset.length>0){
			createtab();
			$("#add_proj").show();
			$("#modal-backdrop").show();
		}
	});
	$("[data-dismiss='modal']").click(function(){
		$("#add_proj").hide();
		$("#modal-backdrop").hide();
	});
	seachfor();
});
function seachfor(){
	$('.ami_Mask').show();
	var searchDate = $("#searchDate").val().split("-");
	var args ={startDate:searchDate[0],
				endDate:searchDate[1],
				vlanId:$("[name=vlanId]").val()
// 				'queryInfo.currentPage':1,
// 				'queryInfo.pageSize':50
				};
	$.post('/vlan/hottestsitesdata?a='+ new Date(), args,function(data){
		FusionCharts.ready(function(){
			var width='100%';				
	var autoH = $('#main-content').height();
    var height=autoH-120;
			getChart('showTable',data,width,height).render();
		});
	$("#search_span_date").html($("#searchDate").val()).show();	
	$("#search_span_name").html($("[name=vlanId]").find("option:selected").text()).show();
	});
}
function getChart(id,data,width,height){
	$('.ami_Mask').hide();
	        var da=JSON.parse(data);

          da.chart.plotFillAlpha=plotFillAlpha;
          da.chart.paletteColors=paletteColors;
	 return new FusionCharts({
	        "type": "stackedcolumn2d",
	        "renderAt": "showTable",
	        "width": width,
	        "height": height,
	        "dataFormat": "json",
	        "dataSource": da
		});
}
</script>
