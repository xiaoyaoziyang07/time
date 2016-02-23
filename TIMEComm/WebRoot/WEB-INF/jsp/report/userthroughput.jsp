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
    <div class="row-fluid" style="position:absolute;top:108px;bottom:10px">
        <div class="togg_nav">
          <span class="active">Reports</span>
          <span>Configuration</span>
        </div>
        <div class="togg_content" id="conter">
           <div id="area_1"></div>
           <div id="area_2"></div>
           <div id="area_3"></div>
           <div id="area_4"></div>
           <div id="area_5"></div>
           <div id="area_6"></div>        
        </div> 
         <div class="togg_content" style="display:none">
            <table class="table table-bordered">
        </table>
        <p class="togg_foot"><img title="new" src="/img/new_user.png">
        </p> 
        <div id="page_id"></div>
  	</div>  	
</div>
<div id="add_proj" class="modal" style="display:none;height:405px" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
  		<p>chars<span onclick="cal(this)">X</span></p>
  	 <hr style="color:#bfbfbf" />
  	<div class="row-fluid">
      <div id="bigChartDiv"></div>
    </div>
</div>
<div id="modal-backdrop" class="modal-backdrop in" style="display:none"></div>
<div id="new_table">
    <input type="hidden" id="mid" />
    <p>Configauration <span onclick="cal(this)">X</span></p>
    <hr style="color:#bfbfbf" />
    <table  class="table table-bordered" style="margin:0 auto;width:80%;min-width:auto">
    <tr><td>Subscriber ID :</td><td><input type="text" value=""></td></tr>
    <tr><td>Valldation Period :</td>
    <td>
    <div>day <input type="number" max="30" min="0"></div>
    <div>hour <input type="number" max="24" min="0"></div>
    <div>miute <input type="number" max="59" min="0"></div>
    </td></tr>
    <tr><td>Start Time :</td><td><input class="Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',lang:'en',skin:'twoer'})" value=""></td></tr>
   </table>
   <center><a href="#" onclick="add_edit()">Save</a>
   <a href="#" onclick="cal(this)">Cancel</a></center>
</div>
<script type="text/javascript" src="/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script src="js/canvas/jquery.knob.js"></script>
<script src="js/canvas/jquery.throttle.js"></script>
<script src="js/canvas/jquery.classycountdown.js"></script>
<script type="text/javascript" src="/js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="/js/share7.js"></script>
<script type="text/javascript" src="/js/jquery.pagination.js"></script>
<script type="text/javascript" src="/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.paging.js"></script> 
<script>
load();
$('.ami_Mask').hide();
 $('.btn-closez').click(function(){
     $("#add_proj").fadeOut("slow",function(){
     $("#modal-backdrop").fadeOut("slow",function(){
       $('#bigChartDiv').html("");
		});	
	});
 });
   function load(){
            $.ajax({
          async:true,
          url:'/report/realTimeMonitorUserData?hourCount=1',
          dataType:'json',
          success:function(data){
          var obj=data.realTimeMonitorUserData;
          var o=0;
       for (var prop in obj) {
       if (obj.hasOwnProperty(prop)) {  
          var id= $('#conter div').eq(o).attr("id");
           console.log(id);
           getChart(id,'tempdataX',obj[prop],width,height).render();
           o++;
       }
}      
          }
     });
   }
var time=setInterval("shishi()",1000);
function shishi(){
   var tr= $(".togg_content table tr");
   var ceshi='';
   for(var i=1;i<tr.length;i++){
    var data=new Date();
    var starD=parseInt(tr.eq(i).find('td').eq(4).attr("miao"));
    var typeD=parseInt(tr.eq(i).find('td').eq(3).attr("type"));
    var endd=parseInt(tr.eq(i).find('td').eq(3).attr("endd"));
    var s=Date.parse(data);
     if(starD<=s && typeD==0){ 
      tr.eq(i).find('td').eq(3).html("");
      tr.eq(i).find('td').eq(3).attr("type",'1');  
          var data=new Date();   
          var s=Date.parse(data);      
      tr.eq(i).find('td').eq(3).ClassyCountdown({
         theme: "flat-colors-very-wide",
         end:endd/1000,
         now:s/1000
          });
     }
   }
};
function add_edit(){
     var da=$('#new_table tr');
     var shijian=$('#new_table tr').eq(2).find('td').eq(1).find('input').val();
     var shijian_sz=shijian.split(' ');
     var shijian_sz1=shijian_sz[0].split('-');
     var shijian_sz2=shijian_sz[1].split(':');
     var date=new Date();
         date.setFullYear(shijian_sz1[0]);
         date.setMonth(shijian_sz1[1]-1);
         date.setDate(shijian_sz1[2]);
         date.setHours(shijian_sz2[0]);
         date.setMinutes(shijian_sz2[1]);
     var starDate=Date.parse(date);   
     var end1=$('#new_table tr').eq(1).find('td').eq(1).find('input').eq(0).val()*24*60*60*1000;
     var end2=$('#new_table tr').eq(1).find('td').eq(1).find('input').eq(1).val()*60*60*1000;
     var end3=$('#new_table tr').eq(1).find('td').eq(1).find('input').eq(2).val()*60*1000;
     var end=end1+end2+end3;
     var data={
          "monitorUser.userId":$('#new_table tr').eq(0).find('td').eq(1).find('input').val(),
          monitorUser_Status:'1',
          monitorUser_IsShow:'0',
          monitorUser_StartTime:starDate,
          monitorUser_EndTime:starDate+end,
          monitorUser_MonitorId:$("#mid").val()         
     };   
     $.ajax({
          async:true,
          data:data,
          url:'/report/updateMonitorUser',
          dataType:'json',
          success:function(obj){
             console.log(obj);
             refresh_data(current);
        	$("#modal-backdrop").fadeOut("slow");
			$("#new_table").fadeOut("slow");
          }
     });
}
var page_num=4;
var Data_table;
var current=1;//当前页数
refresh_data(1);
function refresh_data(num){
 $.ajax({
     url:'/report/monitorUserList',
     type:'get',
     dataType:'json',
     async:false,
     success:function(obj){
     Data_table=obj.monitorUserList;
        console.log(obj);   
		if(num>=Math.ceil((Data_table.length)/4)){
		  num=Math.ceil((Data_table.length)/4);
		}else if(num=='add'){
		  num=Math.ceil((Data_table.length)/4);  
		}
	 output_data(num);
     var data=obj.monitorUserList;
       $("#page_id").pagination({
        items:data.length,
        itemsOnPage:page_num,
        cssStyle: 'light-theme',
        currentPage:num,
        onPageClick: function(pageNumber,event) {
	       output_data(pageNumber);
	       current=pageNumber;
		}
    });
     }
 });
}
function output_data(num){
           if(num==Math.ceil(Data_table.length/4)){
            $('.togg_foot').show();
           }else{
            $('.togg_foot').hide();
           }
     var th='<tr><th>No</th><th>On-Screen</th><th>Subscriber ID</th><th style="width:292px">Valldation Period</th><th>Start Time</th><th>Opeation</th></tr>';
         $('.togg_content table').html(th);
         if(Data_table.length!=0){
             for(var i=page_num*(num-1)+1;i<=page_num*num;i++){
        if(Data_table[i-1]){              
         if(Data_table[i-1].startTime!=0){
	         var d = new Date();
	             d.setTime(Data_table[i-1].startTime);
	         var m=parseFloat(d.getMonth())+1;    
	         var dada=d.getFullYear()+"-"+m+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes();  
	                 }else{
	                 var dada=" ";  
	                 }
	         var endda=Data_table[i-1].endTime-Data_table[i-1].startTime;
	         var enen=conversion(endda);  
	         var span="";
	         var danwei=['days','hours','min'];
         for(var h=0;h<enen.length;h++){
             span+='<span class="wai_rect"><span class="nei_rect'+h+'">'+enen[h]+'<br>'+danwei[h]+'</span></span>';
         }
	         var show=(Data_table[i-1].isShow!=1)?'/img/blue_z.png':'/img/blue_d.png';
	         var uid=Data_table[i-1].userId?Data_table[i-1].userId:" ";
	   		 var tr='<tr>';
	   		 	 tr+='<td>'+i+'</td>';
	   		 	 tr+='<td><img onclick="togg(this)" src='+show+' is_show='+Data_table[i-1].isShow+'></td>';
	   		 	 tr+='<td>'+uid+'</td>';
	   		 	 tr+='<td endD='+Data_table[i-1].endTime+' type="0" end_data='+enen.join(',')+'>'+span+'</td>';
	   		 	 tr+='<td miao='+Data_table[i-1].startTime+'>'+dada+'</td>';
	   		 	 tr+='<td><img mid='+Data_table[i-1].monitorId+' src="/img/_edit.png" onclick="edit(this)"><img mid='+Data_table[i-1].monitorId+' onclick="delt(this)" src="/img/_dele.png"></td></tr>';
	   		 	 $('.togg_content table').append(tr);
	        }

   }
         }else{
          $.ajax({
          type:'get',
          data:'',
          async:true,
          url:'/report/addMonitorUser',
          dataType:'json',
          success:function(obj){
             if(obj.status!='success'){
              alert(obj.message);
             }else{
               refresh_data('add');
             }          
          }
     });
         }
     
}
function togg(a){
       if($(a).parent().siblings().eq(1).html()!=" "){
                    var t=($(a).attr('is_show')==1)?'0':'1';
          var data={
	          monitorUser_MonitorId:$(a).parent().siblings().eq(4).find('img').eq(0).attr("mid"),
	          "monitorUser.userId":$(a).parent().siblings().eq(1).html(),
	          monitorUser_Status:'1',
	          monitorUser_IsShow:t,
	          monitorUser_StartTime:$(a).parent().siblings().eq(3).attr("miao"),
	          monitorUser_EndTime:$(a).parent().siblings().eq(2).attr('endd'),        
            };      
        $.ajax({
          async:true,
          data:data,
          url:'/report/updateMonitorUser',
          dataType:'json',
          success:function(obj){
            if(obj.status!='success'){
                 alert(obj.message);
            }else{
              alert('Modify the success');
             if($(a).attr('is_show')==1){
               $(a).attr('is_show','0');
                 $(a).attr('src','/img/blue_z.png');
             }else{
               $(a).attr('is_show','1');
               $(a).attr('src','/img/blue_d.png');
             }
            }          
          }
     });        
       }else{
         alert("Please set the Subscriber ID！");
       }

}
function ok(){
		$("#modal-backdrop").fadeOut("slow");
		$("#new_table").fadeOut("slow");
}
function conversion(shijiancha){
   var days= shijiancha / 1000 / 60 / 60 / 24;
   var daysRound= Math.floor(days);
   var hours= shijiancha/ 1000 / 60 / 60 - (24 * daysRound);
   var hoursRound= Math.floor(hours);
   var minutes= shijiancha / 1000 /60 - (24 * 60 * daysRound) - (60 * hoursRound);
   var minutesRound= Math.floor(minutes);
   return [daysRound,hoursRound,minutesRound];
}
function cal(a){
	    $("#modal-backdrop").fadeOut("slow");
		$(a).parent().parent().fadeOut("slow");
}
function edit(a){  
        for(var j=0;i<$("#new_table input").length;j++){
        $("#new_table input").eq(j).val("");
        }    
     	$("#modal-backdrop").fadeIn("slow");
		$("#new_table").fadeIn("slow");
		$("#mid").val($(a).attr("mid"));
     var da=$('#new_table tr');
     var uid=$(a).parent().siblings().eq(2).html();
     var end='';
     var star=$(a).parent().siblings().eq(4).html();
        $('#new_table tr:eq(0)').find('td').eq(1).find('input').val(uid);
        console.log(typeof(uid),uid.length,22211111111);
        if(uid!=" "){
        $('#new_table tr:eq(0)').find('td').eq(1).find('input').attr('disabled','disabled');
        }else{
         $('#new_table tr:eq(0)').find('td').eq(1).find('input').removeAttr('disabled');
        }
        $('#new_table tr:eq(2)').find('td').eq(1).find('input').val(star);
     var p=$('#new_table tr:eq(1)').find('td').eq(1).find('input');   
     var q=$(a).parent().siblings().eq(3).attr('end_data');
     var qq=q.split(',');
     for(var i=0;i<p.length;i++){
        p.eq(i).val(qq[i]);
     }
}
function delt(a){
    var t=confirm("Sure to delete?");
    if(!t){
      return
    }
         var data={
          monitorUser_MonitorId:$(a).attr("mid"),
          "monitorUser.userId":$(a).parent().siblings().eq(2).html(),
          monitorUser_Status:'0',
          monitorUser_IsShow:'0',
          monitorUser_StartTime:$(a).parent().siblings().eq(4).attr('miao'),
          monitorUser_EndTime:$(a).parent().siblings().eq(3).attr('endd'),        
     };   
     $.ajax({
          async:true,
          data:data,
          url:'/report/updateMonitorUser',
          dataType:'json',
          success:function(obj){
            if(obj.status!='success'){
                 alert(obj.message);
            }else{
              alert('Delete the success');
              refresh_data(current);
            }          
          }
     });
}
$(".togg_foot img").click(function(){   
     $.ajax({
          type:'get',
          data:'',
          async:true,
          url:'/report/addMonitorUser',
          dataType:'json',
          success:function(obj){
             if(obj.status!='success'){
              alert(obj.message);
             }else{
               alert('New success');
               refresh_data('add');
             }          
          }
     });
     

});
$('.togg_nav span').click(function(){
  $(this).addClass('active');
  $(this).siblings().removeClass('active');
  $('.togg_content').hide();
  $('.togg_content').eq($(this).index()).show();
  if($(this).index()==0){
     load();
  }
});
		var width=$("#area_1").width();				
		var height=$("#area_1").height();
   //   getChart('area_1','tempdataX','tmpDate',width,height).render();
	//	getChart('area_2','tempdataX','tmpDate',width,height).render();
	//	getChart('area_3','tempdataX','tmpDate',width,height).render();
	//	getChart('area_4','tempdataX','tmpDate',width,height).render();
	//	getChart('area_5','tempdataX','tmpDate',width,height).render();
	//	getChart('area_6','tempdataX','tmpDate',width,height).render();
var pageBean;
	
$(function(){
	$(".chzn-select").chosen({allow_single_deselect:true});
	seachfor();
	getVlans();
});
function seachfor(){
	var searchDate = $("#searchDate").val().split("-");
	var searchType =  $(".on_check input").val();
	$("#search_span_date").html($("#searchDate").val())
	//var vlanId=$("#vlanlist  option:selected").val();
	/*
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
		var height;
		var autoH = $(window).height();
		if( autoH>570 && autoH<770){
			height = "500px";
		}else if(autoH<570){
		
			height ="200px";
		}else if(autoH>770){
			height="520px";
		} 
		getChart('showTable',tempdataX,tmpDate,width,height);
	});
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
            type: 'realtimestackedarea',
            renderAt: id,
            width: width,
            height:height,
            dataFormat: 'json',
            dataSource: data,
             "events": {
                "linkClicked": function (eventObj, dataObj) {
                 console.log(JSON.parse(eventObj.sender.args.dataSource).chart.appids);
			    $.ajax({
			          async:true,
			          url:JSON.parse(eventObj.sender.args.dataSource).chart.appids,
			          dataType:'json',
			          success:function(data){
			           var obj=data.realTimeMonitorUserData;    
			            for(var p in obj){
			               if(obj[p]){
			               $("#modal-backdrop").fadeIn("slow",function(){
			               	 $("#add_proj").fadeIn("slow",function(){
		                     getChart_1('bigChartDiv','dataX',obj[p],'100%','350');
		                });	
			               });		            
			               }
			            }
			          }
			     });
              var DA= eventObj.sender.args.dataSource;
             //
            }
        }
        }).render();
  }
  function getChart_1(id,dataX,data,width,height){
     

    return  new FusionCharts({
            type: 'realtimestackedarea',
            renderAt: id,
            width: width,
            height:height,
            dataFormat: 'json',
            dataSource:data,
        }).render();
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
