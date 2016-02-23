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
                    <!-- 日历begin-->
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
                	</div>
                  	<div>
                  		<input class="ui-date-quick-button" type="button" value="<s:text name="ui.common.date.fastdate.yesterday"/>" alt="-1" name=""/>
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
          <!-- 日历end-->
         	<!-- 多项选择的radio按钮 begin-->
          <label> 
          	<div class="piaochecked on_check">
			  	<input name="type" checked type="radio" value="0" class="radioclass">
			</div>
          	<span>ALL</span>
          	<div class="piaochecked">
			  	<input name="type" type="radio"  value="1" class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.download"/></span>
          	<div class="piaochecked">
			  	<input name="type" type="radio"  value="2"  class="radioclass">
			</div>
          	<span><s:text name="ui.report.bandwidth.upload"/></span>
          </label>
        	<!-- 多项选择的radio按钮 begin-->
          <%-- <label> 
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
          </label> --%>
          <!-- 多项选择的radio按钮 end-->
          <!-- 带有提示的input框begin-->
          <span class="input-icon tip_icon" style="display:none">
        		<label><s:text name="ui.subreport.servicesid"/>：</label>
          		<input type="text" id="servicesId" value="${servicesId}" />
          		<div class="remidArr" style='display:none'>
                	<div class="tooltip-inner tip_in"><s:text name="ui.subreport.servicesidisnull"/></div>
                	<div class="arrows"></div>
                </div>
        	</span>
        	<!-- 带有提示的input框 end-->
        	<!-- 带有搜索条件select begin-->
          	<select name="name"  id="select_vlanID" data-placeholder="Choose a Vlan..." style="margin-left:10px;border-color:#d5d5d5">
          		<option value='all'>ALL</option>
          		<s:iterator value="vlanlist" var="vlan">
          		<option value='${vlan.vId}'>${vlan.vName}</option>
          		</s:iterator>
          	</select>
          	<!-- 带有搜索条件select end-->
          	
          <button type="button"   class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
          <%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>
        </form>
      </div>
      <div class="toggle_f">
         <div class="di_out">
          <div class="put-results">
          <span class="obj_name">Date</span><span id="search_span_date"></span>
          <span class="obj_name">Throughput</span><span id="search_span_type"></span>
          <span class="obj_name">Vlan type</span><span id="search_span_trend"></span>
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
    	<div id="showTable_1" align="center"></div> 
  	</div>
</div>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/jquery.pagination.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>
<script type="text/javascript" >
	$('#search_span_trend').html($(".piaochecked :checked").parent().next().html());
		var width='100%';				
		var height=($('#main-content').height()-110)/2;
	function seachfor() {
	   		     	$('.ami_Mask').show(); 
		var searchDate = $("#searchDate").val().split("-");
		var args = {
			startDate : searchDate[0],
			endDate : searchDate[1],
			flowType:$(".on_check input").val(),
			vlanId:$("#select_vlanID").val()
		};
		$.post('/report/locationreportdata?a=' + new Date(), args, function(data) {
			FusionCharts.ready(function() {
				
				console.log(data);
				getChart("showTable",null, 0, 0).render();
				getChart("showTable_1",null, 0, 0).render();
				$("#showTable").html("");
				$("#showTable_1").html("");
				if(data.data[0]!=null){
					getChart("showTable",data.data[0], width, height).render();
				}
				if(data.data[1]!=null){
					getChart("showTable_1",data.data[1], width, height).render();
				}
			});
			$('#search_span_trend').html($("#select_vlanID").val());
			$("#search_span_date").html($("#searchDate").val());
			$("#search_span_type").html($(".on_check").next().html());
			$("#search_span_name").html(
					$("[name=vlanId]").find("option:selected").text());
		});
	}

	function getChart(id,data, width, height) {
	   		     	$('.ami_Mask').hide();
		return new FusionCharts({
			"type" : "stackedarea2d",
			"renderAt" : id,
			"width" : width,
			"height" : height,
			"dataFormat" :"json",
			"dataSource":data
			});
		}
		$(function(){
		seachfor();
		})
</script>
