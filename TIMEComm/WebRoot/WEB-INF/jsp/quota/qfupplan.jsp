<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!-- PAGE CONTENT BEGINS HERE -->
<style>
 .top{height:41px;line-height:15px}
</style>
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
          	<%-- <span id="search_span_date"></span> --%>
          </div>
        </div>
      </div>
    </div>
     </div>
     <div class="row-fluid">
        <table class="table table-bordered" id="qfup">
         
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
  <div id="add_proj" class="modal" style="height:auto"> 
  		<p>QFUP Plan<span onclick="cal(this)">X</span></p>
  	 <hr style="color:#bfbfbf" />
      <div class="bigChartDiv">
      <table  class="table table-bordered">
    <tr><td>Policy ID :</td><td><input type="text" value="" readonly></td></tr>
    <tr><td>Policy name :</td><td><input type="text" value="">
    </td>
 </tr>
    <tr><td>Quota :</td>
    <td class="free_number">
    <input type="number" placeholder="8192" style="width:100px">
    <select style="width:91px">
     <option value='0'>GB</option>
     <option value='-1'>unlimited</option>   
    </select>
    </td>
    </tr>
     <tr><td>FUP1 :</td><td>
		<input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0">
        <span id="ex6SliderVal"></span>
     </td></tr>
      <tr>
       <td>Upload Speed</td>
       <td class="free_number">
         <input type="number" placeholder="0.5" style="width:100px">
        <select style="width:66px">
	     <option>Kbps</option>         
	     <option>Mbps</option>  
        </select>
       </td> 
     </tr>
     <tr>
       <td>Download Speed</td>
       <td class="free_number">
         <input type="number" placeholder="1~1000" value="" style="width:100px">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
       </td> 
     </tr>  
      <tr><td>FUP2 :</td><td>
		<input id="ex2" data-slider-id='ex2Slider' type="text" data-slider-min="0" data-slider-max="100" data-slider-step="1" data-slider-value="0">
        <span id="ex6SliderVal1"></span>
     </td></tr>
     <tr>
       <td>Upload Speed</td>
       <td  class="free_number">
         <input type="number" placeholder="0.5" style="width:100px">
        <select style="width:66px">
	     <option>Kbps</option>         
	     <option>Mbps</option>  
        </select>
       </td> 
     </tr>
     <tr>
       <td>Download Speed</td>
       <td  class="free_number">
         <input type="number" placeholder="1~1000" value="" style="width:100px">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
       </td> 
     </tr>     
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
	<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script> 
    <script type="text/javascript" src="/js/common.js"></script>
    <script type='text/javascript' src="/js/bootstrap-slider.min.js"></script>
    <script type="text/javascript" src="/js/jquery.paging.js"></script> 
	<script type="text/javascript" src="js/plan-qfup.js"></script>
	<script type="text/javascript">
     var mySlider= $('#ex1').slider({
	formatter: function(value) {
	   $('#ex6SliderVal').html(value+'%');
		return 'value: ' + value;
	}
});
     var mySlider2=$('#ex2').slider({
	formatter: function(value) {
	 $('#ex6SliderVal1').html(value+'%');
		return 'value: ' + value;
	}
});
var ge=Math.floor(($('#ami_main').height()-72-37-93-41)/63);//一页显示几条数据
var ge_new=1;//当前页数
var del_id;//删除一条数据的id
   if(Mana_da){
    MANA_img='<img val="0" class="MANA" src="/img/blue_z.png" onclick="MANA_(this)">';
   }else{
    MANA_img='<img class="MANA" src="/img/blue_h.png">';
   }
$('.ami_Mask').hide();
function add_edit(){
        var inp=$('.bigChartDiv input');
        var sel=$('.free_number select').val();
        var quota=(sel!=0)?sel:inp.eq(2).val();
        var ddd={
           'qfupPlan.policyid': inp.eq(0).val(),
           'qfupPlan.policyname': inp.eq(1).val(),
           'qfupPlan.quota':quota,
           'qfupPlan.fup1_percent':inp.eq(3).val(),
           'qfupPlan.fup1_ul':inp.eq(4).val(),
           'qfupPlan.fup1_dl':inp.eq(5).val(),
           'qfupPlan.fup2_percent':inp.eq(6).val(),
           'qfupPlan.fup2_ul':inp.eq(7).val(),
           'qfupPlan.fup2_dl':inp.eq(8).val()     
        };
  $.ajax({
    data:ddd,
    dataType:'json',
    url:'/manager/qfupplanupdate',
    async:true,
    type:'post',
    success:function(obj){
        PLAN.qufp.load();
        $("#modal-backdrop").fadeOut("slow");
		$("#add_proj").removeClass("a-fadeinT");
    }
  });
    }
function con_ok(a){
        var dd=PLAN.qufp.del_id;
            $.ajax({
      			data:dd,
			    dataType:'json',
			    url:'/manager/qfupplandelete',
			    async:true,
			    type:'post',
			    success:function(obj){
			      PLAN.qufp.load();
			     $("#add_proj1").removeClass("a-fadeinT");
			     $("#modal-backdrop").fadeOut("slow");
			    }
   });
    }
function cal(a){
	    $("#modal-backdrop").fadeOut("slow");
		$(a).parent().parent().removeClass("a-fadeinT");
}
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
};

$(function(){
        $(".free_number select").change(function(){
          	var val=$(this).val();
          	if(val<0){
          	  $(this).prev().attr('readonly','readonly');
          	  $(this).prev().val('');
          	}else{
          	  $(this).prev().removeAttr('readonly');
          	  $(this).prev().val(0);
          	}
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
      if(Mana_da){
      $('.togg_foot a').show();
      }else{
      $('.togg_foot a').hide();
      } 
        PLAN.qufp.num=ge;
        PLAN.qufp.load();
        $("#bigChartDiv select").change(function(){
       	   var val= $(this).val();
       	    if(val=='Kbps'){
       	      $(this).siblings().val("512");
       	      $(this).siblings().attr('readonly','readonly');
       	    }
       	    else{
       	      $(this).siblings().removeAttr('readonly');
       	    }
       	});
		$("#resetDate").html(new Date($("#resetDate").html()*1000).Format("MM/dd/yyyy"));
		PLAN.qufp.new_data=function(){
		  var data={
		     prefix:'QFUP'
		   };
			$.ajax({
			    data:data,
			    dataType:'json',
			    url:'/manager/qfupplanadd',
			    async:true,
			    type:'post',
			    success:function(obj){
			      console.log(obj);
			      PLAN.qufp.new_number='add';
			      PLAN.qufp.load();
			    }
			  });
		};
		$('.togg_foot img').click(function(){
             PLAN.qufp.new_data();
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
function MANA_(a){
  var val=$(a).attr('val');
      $(".MANA").removeClass('MANA-active');
      $(".MANA").attr('val','0');
      $(".MANA").attr('src','/img/blue_z.png');
      $(a).attr('val','1');
      $(a).addClass('MANA-active');
      $(a).attr('src','/img/blue_d.png');
      var id=$(a).parent().siblings().eq(1).html();  
      var up=$(a).parent().siblings().eq(3).find('span').html();   
      MANA_DATA_obj[MANA_index]['planBuilder.qfuppolicyid']=id; 
      MANA_this.attr('zid',id);
      MANA_this.find('span').eq(0).html(up); 
      setTimeout(function(){
     	 $('#PAGECONTENT').show();
     	 $('#PAGECONTENT1').hide();
      },500);	  
}
</script> 
