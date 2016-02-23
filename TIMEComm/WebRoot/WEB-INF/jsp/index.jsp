<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Dynamic Policy Management System</title>
<meta name="description" content="overview & stats" />

<meta http-equiv="X-UA-Compatible" content="chrome=1"> 
<!-- basic styles -->
<link rel="stylesheet" type="text/css" href="css/canvascss/default.css">
<link rel="stylesheet" type="text/css" href="css/canvascss/jquery.classycountdown.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="Stylesheet" href="css/loaders.css" type="text/css" />
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="/css/bootstrap-slider.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="shortcut icon" href="img/favicon.png" />
<link rel="stylesheet" href="/css/3dnav.css" />
<!-- ace styles -->
<link rel="stylesheet" href="/css/ace.min.css" />
<link rel="stylesheet" href="css/ace-responsive.min.css" />
<link rel="stylesheet" href="css/ace-skins.min.css" />
<link rel="stylesheet" href="css/ami.css" />
<link rel="stylesheet" href="css/chosen.css" />
<link rel="Stylesheet" href="js/dtree/dtree.css" type="text/css" />
<link rel="stylesheet" href="css/paging.css" />
<link href="/css/animation.css" rel="stylesheet">
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="/js/jquery.nicescroll.js"></script> 
<script src="/js/bootstrap.min.js"></script> 
</head>
<body>
<!-- 顶部导航--->
<div class="navbar">
  <div class="navbar-inner">
    <div class="container-fluid"> 
    <span style="float:left; position:absolute; top:8px;right:60px;color:#707a86">
          Version:2.2.7
    </span>
    	<a href="javascript:;" class="brand"><img src="img/loDpms.png"></a>
    	<a href="javascript:;" class="brand" style="margin: 0 50px;"><img src="img/topCenter.png"></a>
    	<a href="javascript:;" class="brand"><img src="img/loTimeb.png"></a>
    	<span style="float:right; position:absolute; bottom:25px; right:60px">
    		<a href="javascript:;">${username}</a><span style="margin:0 15px;">|</span>
  			<a href="/user/logout" class="logo_f">Sign out</a>
    	</span>
    </div>
  </div>
</div>
<div class="container-fluid nav_big">
	<div class="nav_big" style="position: relative; margin-left:209px">
		<ul class="nav nav_list">
			<s:iterator value="userMenuInfos" >
				<li sid='${sid}'>
				<a href='javascript:;' title='${title}' class='dropdown-toggle maintitle three-d' url='${url}'>
					<img src="/img/nav_${sid}.png" />${title}
					<span class="three-d-box"><span class="front"><img src="/img/nav_${sid}.png" />${title}</span>
					<span class="back"><img src="/img/nav_${sid}.png" />${title}</span></span>
				</a>
				<ul class='submenu'>
					<s:iterator value="subMenus" >
						<li sid='${sid}'>
							<a href='javascript:;' title='${title}' url='${url}' ntype='${ntype}' class='leaf'>
							 	${title}
							</a>
							<span></span>
						</li>
					</s:iterator>
				</ul>
				
			</li>
			</s:iterator>
   		</ul>
	</div>
	
</div>
<!-- 顶部导航 end--->
<div class="main-container container-fluid"> <a href="javascript:;" id="menu-toggler"> <span></span> </a> 
  <!-- menu toggler --> 
  
    <div id="main-content" class="main-content">
  	
          <div class="modal-backdrop fade in ami_Mask" style="opacity:0.37; position: absolute;background:#000" id="ami_MaskDiv">
          <div class="item" style="position:absolute;left:0;right:0;top:0;bottom:0;width:64px;height:64px;margin:auto">
                    <div class="item-inner">
                        <div class="item-loader-container">
                            <div class="la-ball-spin-clockwise la-2x">
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                                <div></div>
                            </div>
                        </div>
                    </div>
                </div>
		  
	</div>
    <!--#breadcrumbs-->
    
    <div id="page-content" class="page-content" style="position:relative"> 
      
      <!-- PAGE CONTENT BEGINS HERE --> 
      <div class="row-fluid" id="PAGECONTENT" style=""></div>
      <div class="row-fluid" id="PAGECONTENT1" style="display:none"></div>
        
        
        
        
        
        
        
  <!--表格的样式     
        <div class="row-fluid">
        <div id="ami_main">
	<div class="row-fluid">
		<div class="toggles"  style="float:none">
      <div class="toggle_h">
        <div class="leftposit"><a href="#" class="dj"><s:text name="ui.report.search.expansion"/></a></div>
      </div>
    </div>
    
    
    <div class="page-content-table">
       <div class="page-content-table-top"></div>
       <table class="table table-bordered">
        <tr><th>Pian id</th><th>Counts</th><th>Avg Remin</th><th>Pian id</th><th>Counts</th><th>Avg Remin</th></tr>
       </table>
    </div>	
	</div>  	
</div></div>-->  




      <!-- PAGE CONTENT ENDS HERE --> 
      <!--/row--> 
    </div>
    <!--/#page-content--> 
    
  </div>
  <!-- 左边导航 --->
  <div id="sidebar" class="sidebar">     
        <img togg="1" src="/img/j_left.png" style="position:absolute;right:0;top:30%;z-index:999;width:17px;cursor: pointer;">     
	    <div id="submenu">
	    	<!--此处复制菜单-->
	    </div>
  </div>
  
  <!-- 左边航  end---> 
  
  <!-- 右边整体内容--->
  

  <!-- #main-content --> 
  <!-- 右边整体内容 end---> 
  
</div>
<!--/.fluid-container#main-container end -->



<script type="text/javascript" src="js/watch.js"></script>
<!-- page specific plugin scripts --> 
<script type="text/javascript" src="/js/jquery-ui-1.10.2.custom.min.js"></script> 
<script type="text/javascript" src="/js/jquery.ui.touch-punch.min.js"></script> 
<script type="text/javascript" src="/js/jquery.slimscroll.min.js"></script> 
<script type="text/javascript" src="/js/jquery.easy-pie-chart.min.js"></script> 
<script type="text/javascript" src="/js/jquery.sparkline.min.js"></script> 
<script type="text/javascript" src="/js/jquery.form.js"></script> 
<!--<script language="javascript" type="text/javascript" src="js/My97DatePicker_2/WdatePicker.js"></script> 
--><script src="/js/chosen.jquery.min.js"></script> 
<script src="/js/bootstrap-colorpicker.min.js"></script> 
<script src="/js/jquery.knob.min.js"></script> 
<script src="/js/jquery.autosize-min.js"></script> 
<script src="/js/jquery.inputlimiter.1.3.1.min.js"></script> 
<script src="/js/jquery.maskedinput.min.js"></script> 
<script src="/js/bootbox.min.js"></script> 
<script src="/js/ami.js"></script> 
<!-- ace scripts --> 
<script src="/js/ace-elements.min.js"></script> 
<script src="/js/ace.min.js"></script> 
<!-- inline scripts related to this page --> 
<script type="text/javascript" src="/js/My97/WdatePicker.js"></script>
<script src="js/dtree/dtree.js"></script> 
<script type="text/javascript" src="/js/Charts/FusionCharts.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/prettify/prettify.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/json2.js"></script> 
<script type="text/javascript" src="/js/Charts/assets/ui/js/lib.js" ></script>
<script>
var PLAN={};
document.getElementById('main-content').style.width=$("html body").width()-28+'px';
//Quota Management模块所需的所有参数
var Management='';//模块planbuilder 状态代码
var Mana_da=false;//进入二级模块的方式
var MANA_DATA;//二级模块所保存的数据
var MANA_this;//回调位置
var MANA_ID;//对比id
var MANA_img;//img
var MANA_index;//位置的id
var MANA_select;//进入二级的标准 
var MANA_DATA_obj=[];//plan 修改时提交的数据
var MANA_DATA_arr=['planid',
     'paymenttype',
     'plantype',
     'source',
     'isactived',
     'packageid',
     'timepolicyid', 
	 'topuppolicyid', 
	 'qfuppolicyid',  
	 'freeboostid',
	 'paidboost',     
	 'tierpolicyid',
	 'planname',
	 'plannum'];
//数据过滤函数 
function Filter_Data(a,b,c){
   for(var i=0;i<b.length;i++){
     a[b[i]]=(a[b[i]]!=null)?a[b[i]]:"";     
   }
   for(var k in a){
     a[k]=(a[k]!=null)?a[k]:0;
   }
   for(var m in c){
     a[c[m]]=(a[c[m]]==-1)?0.5:a[c[m]];
   }  
}

   var font_t=0;
    var time=0;
  //图表的分类颜色
  var paletteColors="#a301a1,#f64ffa,#fe0b00,#fe4d01,#fb7b1b,#f7c645,#c4b42e,#61b42e,#acd718,#e4f208,#4d3fc9,#0122af,#0491c9,#7adbf2,#6fffff";
  //图表的透明度
  var plotFillAlpha=80;
	$("#sidebar").niceScroll({  
	cursorcolor:"#b5b1b1",  
	cursoropacitymax:0,  
	touchbehavior:false,  
	cursorwidth:"5px",  
	cursorborder:"0",  
	cursorborderradius:"5px"  
}); 
	$("#main-content").niceScroll({  
	cursorcolor:"#b5b1b1",  
	cursoropacitymax:0,  
	touchbehavior:false,  
	cursorwidth:"5px",  
	cursorborder:"0",  
	cursorborderradius:"5px"  
}); 
	var server_time;
	var interval;
	var title = '';
	var page_title = title;
	var url=null;
	
	$("#submenu").html($('.submenu :first').clone(true));//新加
	$('.maintitle').eq(0).parent().addClass('active');
	//默认点击新子项
	function firstk(){
		var le=$('.planactive').length;
	if(le==0){
          
	  }else{
          if(PLAN.plan){
            if(PLAN.plan.to_com()){
				PLAN.plan.pop('plan_',function(){
				 $("#plan_").remove();
				 PLAN.plan.save(PLAN.plan.dom);
				},function(){
				 $("#plan_").remove();
				 $("#modal-backdrop").fadeOut("slow");
				 PLAN.plan.load();
				 });
				  return false;
			}else{
			
			
			}
          }
	  }
		Mana_da=false;
		$('.active').removeClass('active');
		$(this).parent().addClass('active');
		url = $(this).attr('url');
		var title = $(this).attr('title');
		
		nav(url, title);
			if(font_t){clearInterval(font_t)};
		    if(time){clearInterval(time)};
	}
	$("#submenu li").click(function(){
		$('.active').removeClass('active');
		$(this).addClass('active');
		  clearInterval(font_t);
	});	
	//点击父级元素 maintitle
	$('.maintitle').each(function(index){
			$(this).click(function() {
			if(font_t){clearInterval(font_t)};
			if(time){clearInterval(time)};
				var le=$('.planactive').length;
	if(le==0){
          
	  }else{
          if(PLAN.plan){
            if(PLAN.plan.to_com()){
				PLAN.plan.pop('plan_',function(){
				 $("#plan_").remove();
				 PLAN.plan.save(PLAN.plan.dom);
				},function(){
				 $("#plan_").remove();
				 $("#modal-backdrop").fadeOut("slow");
				 PLAN.plan.load();
				 });
				  return false;
			}else{
			
			
			}
          }
	  }
				//$('#moName').html($(this).find("span").html());
				//$('.maintitle').eq(2);			
				$(this).parent().siblings().removeClass('open');
				$(this).parent().addClass('open');				
				$("#submenu").html($(this).next().clone(true));//新加
				//getWidth();
				
				$('#submenu .leaf').on('click',firstk);
				$('#submenu .leaf :first').click();
				//区分鼠标悬停样式
				$('.submenu li>a').each(function(){
					//alert(typeof $(this).attr('ntype'));//string
					if( $(this).attr('ntype')== '-2' ){
						
						$(this).hover(function(){
							$(this).css({
								'color':'#6e7b84',
								'textShadow':'none',
								
							});
						});
						$(this).off('click');
						
					}
				});
				
			});
		
	});
	
	function nav(url, title,args,type,wei) {
	   var le=$('.planactive').length;
	   $('#PAGECONTENT').show();
	   $('#PAGECONTENT1').hide();
	   $('#PAGECONTENT1').html("");
	    var posi=(wei)?wei:'#PAGECONTENT';
	    clearInterval(time);
		$('.ami_Mask').show();
		url += "?random=" + Math.random();
		//alert(url);
		/* $('#page_title_nav').html(title); */
		$.ajax({
			url : url,
			type:type,
			data:args,
			success : function(data) {
				$(posi).html(data);
				$('.dj').html(title);
			},
			error : function(data) {
				$('.ami_Mask').hide();
			},
			dataType : 'html'
		});
		
	}
	$('.maintitle :first').click();
	function findme(args,type){
	console.log('------------');
		console.log(args);
	console.log('------------');
		if(type==undefined){
		nav(url, args,"POST");
		}else{
		nav(url, args,type);
		}
	}
	function getService_Time(){
		return ${service_time};
	}
	function getCommonDateMessage(){
	var dateMessage={
		months:'${commonDateMonthsMessage}'.split(","),
		weeks:'${commonDateWeeksMessage}'.split(",")
			};
	return dateMessage;
	}
	console.log(getCommonDateMessage());
	$(function(){
	  
	  $("#sidebar").hover(
		  function () {
		    var a=this;
		      $(a).addClass('sidebar_hover');
		      $(a).find("img").attr("src",'/img/j_left.png');
		      $('#showTable').width($('#showTable').width()+'px');
		      $('.span3').width($('.span3').width()+'px');
		      $("#main-content").addClass('shousuo');		 
		  },
		  function () {  
		  var a=this;
		  	 $(a).find("img").attr("src",'/img/j_right.png');
		     $(a).removeClass('sidebar_hover');
		     $('#showTable').width($('#showTable').width()+'px');
		     $('.span3').width($('.span3').width()+'px');
             $("#main-content").removeClass('shousuo');
		  }
);
	});
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
function removeHTMLTag(str) {
            str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
            str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
            //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
            str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
            return str;
    }
</script>
<div id="modal-backdrop" class="modal-backdrop in" style="display:none"></div>
</body>
</html>
