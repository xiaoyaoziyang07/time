<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<div id="ami_main">
  <div class="row-fluid">
    <div class="toggles">
      <div class="toggle_h">
        <div class="leftposit"> <a href="javascript:;" class="dj"><s:text name="ui.report.search.expansion"/></a><i class="icon-click"></i></div>
      </div>
      <div class="toggle_box">
        <form class="form-inline" action="" method="post" id="searchForm">
        	<span class="input-icon tip_icon">
        		<label><s:text name="ui.quota.userid"/>:</label>
          		<input type="text" id="userId" value="${userId}" />
        	</span>
          <button type="button" id="btn1" class="btn btn-mini btn-info rg_bg closehj"  onclick="seachfor()"><s:text name="ui.report.search.searchbutton"/></button>
          <%-- <a class="btn btn-mini btn-small closehj" ><s:text name="ui.report.search.closebutton"/></a> --%>
        </form>
      </div>
      <div class="toggle_f">
               <div class="di_out">
          <div class="put-results">
          	<span class="obj_name">User Id</span><span id="search_span_id"></span>
          </div>
        </div>
      </div>
    </div>
     </div>
     <div class="row-fluid">
        <table class="table table-bordered" id="ibod">
        </table>
        <p class="togg_foot"><img title="new" src="/img/new_user.png">
   
        </p> 
        <div id="page_id"></div>        
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
  <div id="add_proj" class="modal" style="height:auto;margin-top:-320px"> 
  		<p>Time Base Plan<span onclick="cal(this)">X</span></p>
  	 <hr style="color:#bfbfbf" />
      <div class="bigChartDiv">
      <table  class="table table-bordered">
    <tr><td>Policy ID :</td><td><input data-name='policyId' type="text" value="" readonly="readonly"></td></tr>
    <tr><td>Policy name :</td><td><input data-name='policyName' type="text" value="">
    </td>
 </tr>
   <tr><td colspan='2'>
      <ul class="nav nav-tabs" id="myTab">
  		<li class="active"><a href="#">Time1 of day</a></li>
  		<li><a href="#">Day of week</a></li>
  		<li><a href="#">Specific Period</a></li>
	  </ul>
   </td></tr>
   
   <tr class="Time_of_day time_sj"><td>Time1 of day :</td>
   <td>
   <input class="input1" data-name='startTime1' id="start_time" placeholder="Start time" type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end_time\',{H:-1})}',dateFmt:'HH:00',lang:'en',skin:'twoer'})" />
   <span style="border:0">—</span>
   <input class="input1" data-name='endTime1' id="end_time" placeholder="End time" type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start_time\',{H:1})}',dateFmt:'HH:00',lang:'en',skin:'twoer'})" />
   </td></tr>
       <tr class="Time_of_day time_sj">
	   <td>Upload Speed :</td>
	   <td class="free_number">
	     <input data-name='upload1' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	   </td></tr>
    <tr class="Time_of_day time_sj">	
	    <td>Download Speed :</td>
	    <td class="free_number">
	     <input data-name='download1' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	    </td></tr>
    <tr class="Time_of_day time_sj">	
    <td>Time2 of day :</td>
    <td>
    <input class="input1" data-name='startTime2'  id="start_time_1" placeholder="Start time 1" type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end_time_1\',{H:-1})}',dateFmt:'HH:00',lang:'en',skin:'twoer'})" />
   <span style="border:0">—</span>
    <input class="input1" data-name='endTime2' id="end_time_1" placeholder="End time 1" type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start_time_1\',{H:1})}',dateFmt:'HH:00',lang:'en',skin:'twoer'})" />  
    </td></tr>
        <tr class="Time_of_day time_sj">
	   <td>Upload Speed :</td>
	   <td class="free_number">
	     <input data-name="upload2" type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	   </td></tr>
    <tr class="Time_of_day time_sj">	
	    <td>Download Speed :</td>
	    <td class="free_number">
	     <input  data-name='download2' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	    </td></tr>
     <tr class="Day_of_week time_sj">
     <td>Day of week :</td>
     <td>
     <span class="week week1" data-name='mon' value='0'><span>Mon.</span></span>
     <span class="week week2" data-name='tues' value='0'><span>Tues.</span></span>
     <span class="week week3" data-name='wed' value='0'><span>Wed.</span></span>
     <span class="week week4" data-name='thur' value='0'><span>Thur.</span></span>
     <span class="week week5" data-name='fri' value='0'><span>Fri.</span></span>
     <span class="week week6" data-name='sat' value='0'><span>Sat.</span></span>
     <span class="week week7" data-name='sun' value='0'><span>Sun.</span></span>
     </td></tr>
         <tr class="Day_of_week time_sj">
	   <td>Upload Speed :</td>
	   <td class="free_number">
	     <input data-name='upload1' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	   </td></tr>
	       <tr class="Day_of_week time_sj">	
	    <td>Download Speed :</td>
	    <td class="free_number">
	     <input data-name='download1' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	    </td></tr>
      <tr class="specific_Period time_sj"><td>specific Period :</td><td>
   <input class="input2" data-name='startTime1' id="start_time3" placeholder="Start time" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'en',skin:'twoer'})" />
   <span class="" style="border:0">—</span>
  <input class="input2" data-name='endTime1' id="end_time3" placeholder="End time" type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start_time3\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'en',skin:'twoer'})" />	
      </td></tr>
      <tr class="specific_Period time_sj">
	   <td>Upload Speed :</td>
	   <td class="free_number">
	     <input data-name='upload1' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	   </td></tr>
    <tr class="specific_Period time_sj">	
	    <td>Download Speed :</td>
	    <td class="free_number">
	     <input data-name='download1' type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	    </td></tr>
   </table>
      </div>
            <center>
        <a href="#" onclick="add_edit()">Save</a>
        <a href="#" onclick="cal(this)">Cancel</a></center>
</div>
      <div id="add_proj1" class="modal" style="height:200px"> 
  		<p>Prompt<span onclick="cal(this)">X</span></p>
  	 <hr style="color:#bfbfbf" />
      <div id="bigChartDiv1">
      Are you sure you want to delete this item?
    </div>
          <center>
        <a href="#" onclick="con_ok()">Save</a>
        <a href="#" onclick="cal(this)">Cancel</a></center>
</div>
<script type="text/javascript" src="/js/common.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="js/share7.js"></script>
<script type="text/javascript" src="js/watch.js"></script>
<script type="text/javascript" src="/js/jquery.paging.js"></script> 
<script type="text/javascript" src="js/plan-time.js"></script>
<script type="text/javascript">
PLAN.time.num=Math.floor(($('#ami_main').height()-72-37-93-41)/150);//一页显示几条数据
var ge_new=1;//当前页数
var del_id;//删除一条数据的id
   if(Mana_da){
    MANA_img='<img val="0" class="MANA" src="/img/blue_z.png" onclick="MANA_(this)">';
   }else{
    MANA_img='<img class="MANA" src="/img/blue_h.png">';
   }
function load(c){

}
function add_edit(){
  var index=$('#myTab .active').index();
  var ddd={};
  ddd['ruleType']=index+1;
  ddd['policyId']=$('.bigChartDiv input').eq(0).val();
  ddd['policyName']=$('.bigChartDiv input').eq(1).val();
  var sel=$('#add_proj select');
  for(var e=0;e<sel.length;e++){
     if(sel.eq(e).val()=="Kbps")
     sel.eq(e).siblings().val("-1");
  }
  if(index==0){
     var time_day=$('.Time_of_day input');
     console.log(index,time_day);
       for(var i=0;i<time_day.length;i++){
           var name=time_day.eq(i).attr('data-name');
           var value=time_day.eq(i).val();
            ddd[name]=value;
       }
  }else if(index==1){ 
      var time_day=$('.Day_of_week input');
       for(var i=0;i<time_day.length;i++){
           var name=time_day.eq(i).attr('data-name');
           var value=time_day.eq(i).val();
            ddd[name]=value;
       }
       var week=$('#add_proj .week');
       for(var k=0;k<week.length;k++){
           ddd[week.eq(k).attr('data-name')]=week.eq(k).attr('value');
       }
  }else{
      var time_day=$('.specific_Period input');
          for(var i=0;i<time_day.length;i++){
           var name=time_day.eq(i).attr('data-name');
           var value=time_day.eq(i).val();
            ddd[name]=value;
       }
  }
 $.ajax({
    data:ddd,
    dataType:'json',
    url:'manager/timebaseplanedit',
    async:true,
    type:'post',
    success:function(obj){
        PLAN.time.load();
        $("#modal-backdrop").fadeOut("slow");
		$("#add_proj").removeClass('a-fadeinT');
    }
  });
    }
function del(a){

 }
function con_ok(a){
        var dd=PLAN.time.del_id;
            $.ajax({
      			data:dd,
			    dataType:'json',
			    url:'/manager/timebaseplandel',
			    async:true,
			    type:'post',
			    success:function(obj){
			     PLAN.time.load();
			     $("#add_proj1").removeClass('a-fadeinT');
			     $("#modal-backdrop").fadeOut("slow");
			    }
   });
    }
function pro_data(a,b){
    
}
function edit(a){    

}
function cal(a){
	    $("#modal-backdrop").fadeOut("slow");
		$(a).parent().parent().removeClass('a-fadeinT');
}
function act(a){
 		   $("#myTab li").removeClass("active");
		   $("#myTab li").eq(a).addClass("active");
		   if(a==0){
		      $('.time_sj').hide();
		      $('.Time_of_day').show();
		   }else if(a==1){
		      $('.time_sj').hide();
		      $('.Day_of_week').show();
		   }else{
		      $('.time_sj').hide();
		      $('.specific_Period').show();
		   }
}
$(function(){
      if(Mana_da){
      $('.togg_foot a').show();
      }else{
      $('.togg_foot a').hide();
      } 
              PLAN.time.load();
			  $('.togg_foot img').click(function(){
			  $.ajax({
			    data:' ',
			    dataType:'json',
			    url:'/manager/timebaseplanadd',
			    async:true,
			    type:'post',
			    success:function(obj){
			      console.log(obj);
			      PLAN.time.new_number='add';
			      PLAN.time.load();
			    }
			  });
		});
		 $("#myTab li").click(function(){
		   var index=$(this).index();
		   act(index);
		 });
		 $(".free_number input").keyup(function (){   
    			var nubmer=parseInt($(this).val());
    			var number=$(this).val();
    			if(number.charAt(0)!='0'){
     			  $(this).val(nubmer);
   				 }
   				 else{
     				  $(this).val("");
   					 }
        });
       	$(".free_number select").change(function(){
       	   var val= $(this).val();
       	    if(val=='Kbps'){
       	      $(this).siblings().val("512");
       	      $(this).siblings().attr('readonly','readonly');
       	    }
       	    else{
       	      $(this).siblings().removeAttr('readonly');
       	    }
       	});
       	$('#add_proj .week').click(function(){
       	     if($(this).attr('value')==0){
       	        $(this).attr('value','1');
       	        $(this).addClass('active');
       	     }else{
       	        $(this).attr('value','0');
       	        $(this).removeClass('active');
       	     }
       	});
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
function MANA_(a){
  var val=$(a).attr('val');
      $(".MANA").removeClass('MANA-active');
      $(".MANA").attr('val','0');
      $(".MANA").attr('src','/img/blue_z.png');
      $(a).attr('val','1');
      $(a).addClass('MANA-active');
      $(a).attr('src','/img/blue_d.png');
      var id=$(a).parent().siblings().eq(1).html();  
      var up=$(a).parent().siblings().eq(1).attr('ul');
      var down=$(a).parent().siblings().eq(1).attr('dl');  
      var ruletype=$(a).parent().siblings().eq(1).attr('ruletype');  
      MANA_DATA_obj[MANA_index]['planBuilder.timepolicyid']=id;  
      MANA_this.attr('zid',id);
      MANA_this.find('span').eq(0).html(up); 
      MANA_this.find('span').eq(1).html(down); 
      console.log(  MANA_this.find('.img-img'))
             if(ruletype==0){
		         MANA_this.find('.img-img').hide();
		        }else if(ruletype==1){
		         MANA_this.find('.img-img').show();
		         MANA_this.find('.img-img').attr('src','/img/img-red-time.png');
		        }
		        else if(ruletype==2){
		         MANA_this.find('.img-img').show();
		         MANA_this.find('.img-img').attr('src','/img/img-red-week.png');
		        }
		        else if(ruletype==3){
		         MANA_this.find('.img-img').show();
		         MANA_this.find('.img-img').attr('src','/img/img-red-date.png');
		        }
      MANA_this.find('.img-img').attr
      setTimeout(function(){
     	 $('#PAGECONTENT').show();
     	 $('#PAGECONTENT1').hide();
      },500);	  
}
      </script>