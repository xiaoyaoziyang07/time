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
          	<span id="search_span_id"></span>
          	<%-- <span id="search_span_date"></span> --%>
          </div>
        </div>
      </div>
      <div class="toggle_box">
        <form class="form-inline" action="" method="post" id="searchForm">
        	<span class="input-icon tip_icon">
        		<label><s:text name="ui.quota.userid"/>：</label>
          		<input type="text" id="userId" value="${userId}" />
          		<div class="remidArr" style='display:none'>
                	<div class="tooltip-inner tip_in"><s:text name="ui.quota.userid.isnull"/></div>
                	<div class="arrows"></div>
                </div>
        	</span>
          <button type="button" id="btn1" class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
          <%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>
        </form>
      </div>
    </div>
     </div>
     <%@taglib prefix="s" uri="/struts-tags"  %>
     	<s:if test="userId !=null && userId!=''">
      <s:if test="quota==null || quota.status==-1">
      	<script type="text/javascript" >
      	alert('<s:text name="ui.quota.user.notexist"/>');
      	</script>
      </s:if>
      </s:if>
     <s:if test="quota!=null || quota.status!=-1">
    <div class="row-fluid" style="margin-bottom: 10px;" >
    	<div class="ami_table_header infobox_header">
    		<span class="infobox_header_title"><s:text name="ui.quota.basicinfo"/></span>
    	</div>
    	 <div class="infobox-container">
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.userid"/></span>  	 			 		
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content">${quota.userid}</span>
    	 	</div>
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.serviceid"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content">${quota.serviceid}</span> 		 		
    	 	</div>
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.servicename"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content">${quota.splanid}</span> 		 		
    	 	</div>
    	 </div>
   	 	<div class="infobox-container">
   	 		<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.resetdate"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span id="resetDate" class="infobox-content">${quota.endtime}</span> 		 		
    	 	</div>
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.status"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content">
    	 			<s:property value="@cn.amichina.timecomm.sys.dao.FinalString@SERVICE_STAUTS[quota.status]"/>
    	 		</span> 		 		
    	 	</div> 
   	 	</div>
    	</div>
    	<s:if test='quota.types=="V"' >
    	 <!--Quota type part  -->
   		<div class="row-fluid" >
	    	<div class="ami_table_header infobox_header">
	    		<span class="infobox_header_title"><s:text name="ui.quota.quotatypepart"/></span>
	    		<span class="infobox_header_titles"><s:text name="ui.unit.title"/>:(MB)</span>
	    	</div>
    	 <div class="infobox-container">
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.averagefup"/></span>  	 			 		
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content toMB">${quota.quotas}</span>
    	 	</div>
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.totalusage"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content toMB">${quota.totalusage}</span> 		 		
    	 	</div>
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.remainfup"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content toMB">${quota.remain1}</span> 		 		
    	 	</div>
    	 </div>
   	 	<div class="infobox-container" >
   	 		<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.topupusage"/></span>
    	 	</div>
    	 	<div class="infobox ">
    	 		<span class="infobox-content toMB">${quota.remain3}</span> 		 		
    	 	</div>
    	 	<div class="infobox infobox-grey">
    	 		<span class="infobox-content"><s:text name="ui.quota.remaintopup"/></span>
    	 	</div>
    	 	<div class="infobox">
    	 		<span class="infobox-content toMB">${quota.remain2}</span> 		 		
    	 	</div>
   	 	</div>  	 	
    	</div>
  </div>
  
  <script type="text/javascript">
  	$(function(){
  	$(".toMB").each(function(index,span){
  		$(span).html((($(span).html()*1.0)/1024/1024).toFixed(2));
  	});
  	})
  </script>
    	</s:if>
    	<s:if test='quota.types=="T"'>
    	 <!--Time type part  -->
    	<!-- fix type part -->
  		<div class="row-fluid">
		   	<div class="ami_table_header infobox_header">
		   		<span class="infobox_header_title"><s:text name="ui.quota.timetypepart"/></span>
		   		<span class="infobox_header_titles"><s:text name="ui.unit.title"/>:(s)</span>
		   	</div>
		   	 <div class="infobox-container" >
		   	 	<div class="infobox infobox-grey">
		   	 		<span class="infobox-content"><s:text name="ui.quota.totalfreeboost"/></span>
		   	 	</div>
		   	 	<div class="infobox">
		   	 		<span class="infobox-content">${quota.quotas}</span> 		 		
		   	 	</div>
		   	 	<div class="infobox infobox-grey">
		   	 		<span class="infobox-content"><s:text name="ui.quota.noactivefreeboost"/></span>
		   	 	</div>
		   	 	<div class="infobox">
		   	 		<span class="infobox-content">${quota.remain2}</span> 		 		
		   	 	</div>
		   	 	<s:if test="quota.status==5">
		  	 		<div class="infobox infobox-grey">
		   	 			<span class="infobox-content"><s:text name="ui.quota.remainfreeboost"/></span>
		   	 		</div>
		   	 		<div class="infobox">
		   	 			<span class="infobox-content">${quota.remain1}</span> 		 		
		   	 		</div>
	  	 		</s:if>
	  	 		<s:if test="quota.status==6">			  	 		
		  	 		<div class="infobox infobox-grey">
			   	 		<span class="infobox-content"><s:text name="ui.quota.remainpayboost"/></span>
			   	 	</div>
			   	 	<div class="infobox">
			   	 		<span class="infobox-content">${quota.remain1}</span> 		 		
			   	 	</div>
		   	 	</s:if> 
		   	 </div>  	 		 	
		   	</div>
    	</s:if>
    	<s:if test='quota.types=="N"'>
    	  <!-- fix type part -->
		  <div class="row-fluid">
		    	<div class="ami_table_header infobox_header">
		    		<span class="infobox_header_title"><s:text name="ui.quota.fixtypepart"/></span>
		    		<span class="infobox_header_titles"><s:text name="ui.unit.title"/>:(MB)</span>
		    	</div>
		    	<div class="infobox-container">
		   	 		<div class="infobox infobox-grey">
		    	 		<span class="infobox-content"><s:text name="ui.quota.totalusage"/></span>
		    	 	</div>
		    	 	<div class="infobox ">
		    	 		<span class="infobox-content toMB">${quota.totalusage}</span> 		 		
		    	 	</div>
		   	 	</div>
		   </div>
		    <script type="text/javascript">
		  	$(function(){
		  	$(".toMB").each(function(index,span){
		  		$(span).html((($(span).html()*1.0)/1024/1024).toFixed(2));
		  	});
		  	})
 				 </script>
    	</s:if>
  </div>
  </s:if>
  <script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
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

$(function(){
		$("#resetDate").html(new Date($("#resetDate").html()*1000).Format("MM/dd/yyyy"));
});
	function seachfor() {
		var iVal = $("#userId");
		if (iVal.val() == "") {
			$(".remidArr").css("display", "block");
		} else {
			var args ={userId:iVal.val()};
			findme($("#page_title_nav").html(),args);
			$("#search_span_id").html($("#searchDate").val()).show();
			
		}
		$("#userId").focus(function() {
			$(".remidArr").css("display", "none");
		});
		
		}
		$('.ami_Mask').hide();
</script> 
<script type="text/javascript" src="js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="js/share7.js"></script>