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
        <table class="table table-bordered" id="free">

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
  <div id="add_proj" class="modal" style="height:505px"> 
  		<p>Free Boost<span onclick="cal(this)">X</span></p>
  	 <hr style="color:#bfbfbf" />
      <div class="bigChartDiv">
      <table  class="table table-bordered">
    <tr><td>Policy ID :</td><td><input type="text" value="" readonly/></td></tr>
    <tr><td>Policy name :</td><td><input type="text" value="" >
    </td>
    </tr> 
    <tr>
	   <td>Upload Speed :</td>
	   <td class="free_number">
	     <input type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	   </td></tr>
    <tr>	
	    <td>Download Speed :</td>
	    <td class="free_number">
	     <input type="number" placeholder="1~1000" value="">
	     <select style="width:66px">
	       <option>Mbps</option>
	       <option>Kbps</option>   
	     </select>
	    </td></tr>
    <tr><td>Duration :</td><td class="free_number"><input type="number" min="1" max="240" placeholder="1~240hours"></td></tr>
    <tr><td>Claim Time :</td><td class="free_number"><input type="number" min="1" max="240" placeholder="1~240hours"></td></tr>
    <tr><td>Validation :</td><td class="free_number"><input type="number" min="1" max="240" placeholder="1~365days"></td></tr>
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
<script type="text/javascript" src="js/plan-free.js"></script>
<script type="text/javascript">
var ge=Math.floor(($('#ami_main').height()-72-37-93-41)/38);//一页显示几条数据
var ge_new=1;//当前页数
var del_id;//删除一条数据的id
PLAN.free.num=ge;
PLAN.free.load();
//load(1);
$('.ami_Mask').hide();

    function add_edit(){
        var inp=$('#bigChartDiv input');
        var sel=$("#add_proj select");
        if(sel.eq(0).val()=='Kbps'){
         var t1=-1;
        }else{
         var t1=inp.eq(2).val();
        }
        if(sel.eq(1).val()=='Kbps'){
         var t2=-1;
        }else{
         var t2=inp.eq(3).val();
        }
        var ddd={
           "boostInfo.policyid": inp.eq(0).val(),
           "boostInfo.policyname": inp.eq(1).val(),
           "boostInfo.upload":t1,
           "boostInfo.download":t2,
           "boostInfo.duration":inp.eq(4).val(),
           "boostInfo.claimtime":inp.eq(5).val(),
           "boostInfo.validation":inp.eq(6).val()
        };
  $.ajax({
    data:ddd,
    dataType:'json',
    url:'/manager/boostupdate',
    async:true,
    type:'post',
    success:function(obj){
        PLAN.free.load();
        $("#add_proj").removeClass('a-fadeinT');
        $("#modal-backdrop").fadeOut("fast");
		
    }
  });
    }
        function con_ok(a){
        var dd=PLAN.free.del_id;
            $.ajax({
      			data:dd,
			    dataType:'json',
			    url:'/manager/boostdel',
			    async:true,
			    type:'post',
			    success:function(obj){
			      PLAN.free.load();
			     $("#add_proj1").removeClass('a-fadeinT');
			     $("#modal-backdrop").fadeOut("fast");
			    }
   });
    }
function cal(a){
        $(a).parent().parent().removeClass('a-fadeinT');
	    $("#modal-backdrop").fadeOut("fast");
}
$(function(){
      if(Mana_da){
      $('.togg_foot a').show();
      }else{
      $('.togg_foot a').hide();
      }       
      PLAN.free.new_data=function(){
      			  $.ajax({
			    data:' ',
			    dataType:'json',
			    url:'/manager/boostadd?boosttype=FREE&prefix=TIMM',
			    async:true,
			    type:'post',
			    success:function(obj){
			      PLAN.free.new_number="add"
			       PLAN.free.load();
			    }
			  });
      };
		$('.togg_foot img').click(function(){
		   PLAN.free.new_data();
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
      var down=$(a).parent().siblings().eq(4).find('span').html();   
      MANA_DATA_obj[MANA_index]['planBuilder.freeboostid']=id;
      MANA_this.attr('zid',id);
      MANA_this.find('.img-img').hide();
      MANA_this.find('span').eq(0).html(up); 
      MANA_this.find('span').eq(1).html(down); 
      setTimeout(function(){
     	 $('#PAGECONTENT').show();
     	 $('#PAGECONTENT1').hide();
      },500);	  
}
      </script>