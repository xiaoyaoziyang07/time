// JavaScript Document
$(function(){
	//弹出框的隐藏
	$('.toggle_box').hide();
	$("[data-dismiss='modal']").click(function(){
		$("#add_proj").fadeOut("slow");
		$("#modal-backdrop").fadeOut("slow");
		  var th = window.event || arguments.callee.caller.arguments[0];
		  if (th) {
		  th.stopPropagation();
		  } else {
		  th.cancelBubble = true;
		  }
		  return false;
	});
	//radio模拟按钮
	$(":input[name=type]").on("click",function(){
		$(":input[name=type]").each(function(index, element) {
			if($(this).is(':checked')){
				$(this).parent().siblings().removeClass('on_check');
				$(this).parent().addClass('on_check');
				$("#search_span_type").html($(this).parent().next().html())
			}else{
				//alert(0);
			}
		});
		  var th = window.event || arguments.callee.caller.arguments[0];
		  if (th) {
		  th.stopPropagation();
		  } else {
		  th.cancelBubble = true;
		  }
		  return false;
	});
	//折叠展开的应用
	var openOff = true;
	
	$("#vlans").on('change',function(){		
		openOff = false;
		
	});
	$("#searchDate").on("focus",function(){
		openOff = false;
	});
	$('select[name=customerPlanType]').on('click',function(){
		openOff = false;
	});
	$(".toggle_h").on('click',function(){
		openOff = true;
	});
	$('.closehj').on('click',function(){
		openOff = true;
		//$('.icon-click').toggleClass('btnaft');
		$('.icon-click').toggleClass('btnaft');
		$('.toggle_box').hide();				
	});
	
	function slideUp(){
		if(openOff){
			$('.icon-click').toggleClass('btnaft');
			$('.toggle_box').slideUp();
		}	
	}
	function slideDown(){
		if(openOff){
			$('.icon-click').toggleClass('btnaft');
			$('.toggle_box').slideDown();
		}		
	}
	//$('.toggle_h').bind("mouseenter",function(){slideDown();});
	//$('.toggles').bind("mouseleave",function(){slideUp();});
	$(".icon-click").click(function(){
		$('.icon-click').toggleClass('btnaft');
		$('.toggle_box').slideToggle();
		var th = window.event || arguments.callee.caller.arguments[0];
		 if (th) {
		 th.stopPropagation();
		 } else {
		 th.cancelBubble = true;
		 }
		 return false;
	})
})