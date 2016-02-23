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
				<form class="form-horizontal" id="searchForm">
                    <span>Users id: <input type="text" id="Uid"></span>
                    <span>Users ip: <input type="text" id="Uip"></span>
                    <span>Vlan id : <input type="text" id="Pid"></span>                     
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
          <span class="obj_name">Users id: </span><span id="f_Uid"></span>
          <span class="obj_name">Users ip: </span><span id="f_Uip"></span>
          <span class="obj_name">Vlan id : </span><span id="f_Pid"></span>
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
		<div id="showTable_1" align="center">
		<table class="table table-bordered">
        </table>
		<div class="table-foot"></div>
		<span class="foot-right">
		  Total on-line number : <span id='foot-right'>0</span>
		</span>
		</div>
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
<script type="text/javascript" src="/js/jquery.paging.js"></script> 	
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script>

   var page_now=1;
   var togg=false;//标识符
   var page_num=Math.floor(($("#main-content").height()-137-37-69)/37);
    Ajax(1);
   function set_time(a){
       clearInterval(font_t);
     font_t=setInterval(function(){ 
     var foot=parseInt($("#foot-right").html());
     var time=a-foot;
     var leng=time.toString().length;
        console.log(a,time,$("#foot-right").html(),leng);
     if(time>0){
     $("#foot-right").html(foot+Math.pow(10,leng-1));
     }else if(time==0){
        clearInterval(font_t);
     } else{
     $("#foot-right").html(foot-Math.pow(10,leng-2));   
     }   
      },50);
         
   }
   function Ajax(num){
   var data={
     'userId':$("#f_Uid").html(),
    	 'ip':$("#f_Uip").html(),
     	 'planId':$("#f_Pid").html(),
     	 'pageNum':num,  
    	 'pageSize':page_num
   };
     var url="/vlan/idmappingdata";
        $.ajax({
     type:'post',
     data:data,
     url:url,
     async:true,
     dataType:'json',
     success:function(obj){
     var d=Math.ceil(obj.totalNum/page_num);   
     console.log(d,num,d-num);
     if((d-num)<0){
       Ajax(d);
     }else{
        $(".table-foot").pagination({
        items: obj.totalNum,
        currentPage:num,
        itemsOnPage:page_num,
        cssStyle: 'light-theme',
        onPageClick: function(pageNumber, event) {
        page_now=pageNumber;
		var data={
		 'userId':$("#f_Uid").html(),
    	 'ip':$("#f_Uip").html(),
     	 'planId':$("#f_Pid").html(),
     	 'pageNum':pageNumber,  
    	 'pageSize':page_num
		}; 
        $.ajax({
           type:'post',
           data:data,
           url:url,
    	   async:false,
   		   dataType:'json',
   		   success:function(obj){
   		     refresh_data(obj,pageNumber);
         }
        });
	},
    });
         set_time(obj.totalNum);
     refresh_data(obj,data.pageNum);
       	$('.ami_Mask').hide();
     }
     }
   });
   } 

   
   function refresh_data(obj,page){ 
     if(obj.recordList.length>0){
            var num=(page==1)?0:(page-1)*page_num;
       var tr='<tr><th>No</th><th>Users id</th><th>IPv4</th><th>IPv6</th><th>Policy Id</th><th>Plan Id</th><th>Vlan Id</th><th>Last Login/Policy update</th></tr>';
       for(var i=0;i<obj.recordList.length;i++){
        num++;
        tr+='<tr>';
        tr+='<td>'+num+'</td>';
        tr+='<td>'+obj.recordList[i].userid+'</td>';  
        tr+='<td>'+obj.recordList[i].frame_ipv4+'</td>';  
        tr+='<td>'+obj.recordList[i].frame_ipv6+'</td>';   
        tr+='<td>'+obj.recordList[i].policyid+'</td>'; 
        tr+='<td>'+obj.recordList[i].splanid+'</td>'; 
        tr+='<td>'+obj.recordList[i].vlan+'</td>'; 
        tr+='<td>'+obj.recordList[i].login_time+'</td>';     
        tr+='</tr>';
       }    
       $('#showTable_1 table').html(tr);
     }  else{
       $('#showTable_1 table').html("");
       alert("There is no data!!");
     } 
   }
   function seachfor(){
     page_now=1;
     $('.ami_Mask').show();
     togg=true;
     $("#f_Uid").html($("#Uid").val());
     $("#f_Uip").html($("#Uip").val());
     $("#f_Pid").html( $("#Pid").val());
     Ajax(1);
   }
    time=setInterval(function(){
         Ajax(page_now);
      },60000);
</script>
