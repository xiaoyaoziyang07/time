var seTime = getService_Time();
var oDate2 = new Date(seTime);
var iYear2 = oDate2.getFullYear();
var iMonth2 = oDate2.getMonth()+1;
var iDay2 = oDate2.getDate();  
(function($){
	
    $.setStartTime = function(){
		//$( ".startDate" ).datepicker( "option","maxDate",dateText);
        $('.startDate').datepicker({
            dateFormat: "mm/dd/yy",
            onClose : function(dateText, inst) {
                $( "#endDate" ).datepicker( "show" );
            },
			maxDate:"-6d",
			monthNames: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
			dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
			onSelect:function(dateText, inst) {
				
				var minDset = parseInt($(".startDate").attr("minDset"));
				var maxDset = parseInt($(".startDate").attr("maxDset"));
				//将当前日期转换格式
				var maxD = new Date(Date.parse(dateText.replace(/-/g, "/")));
				var years = maxD.getFullYear();
				var months = maxD.getMonth()+1;
				var days = maxD.getDate();
				
				var big_date  = new Date(maxD.getFullYear(), maxD.getMonth(), maxD.getDate()+maxDset);
				var sml_date = new Date(maxD.getFullYear(), maxD.getMonth(), maxD.getDate()+minDset);
				var curD = new Date(seTime);
				var curY = curD.getFullYear();
				var curM = curD.getMonth()+1;
				var curDays = curD.getDate();
				var smlY = sml_date.getFullYear();
				var smlM = sml_date.getMonth()+1;
				var smlDays = sml_date.getDate();
				
				var bigY = big_date.getFullYear();
				var bigM = big_date.getMonth()+1;
				var bigDays = big_date.getDate();
				//alert((oDate2).getMonth()+1-months);这个条件时跨月的条件
				//(oDate2).getDate()-days <=6这个条件是在同一个月的时候，剩余的天数小于6天
				var minDate;
				var maxDate;
				if( big_date > (new Date(seTime))){
					//此时+30天大于当前日期
					minDate = ""+smlM+"/"+smlDays+"/"+smlY+"";					
					maxDate = ""+curM+"/"+curDays+"/"+curY+"";
					
				}else{
					maxDate = ""+bigM+"/"+bigDays+"/"+bigY+"";
					minDate = ""+smlM+"/"+smlDays+"/"+smlY+"";				
					
				}
				$( "#endDate" ).datepicker( "option","maxDate",maxDate);
				$( "#endDate" ).datepicker( "option","minDate",minDate);
            },
			
        });
    };
    $.setEndTime = function(){
        $(".endDate").datepicker({
            dateFormat: "mm/dd/yy",
            maxDate: "+d",
            monthNames: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
			dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
			defaultDate : new Date(seTime),

            onClose : function(dateText, inst) {
				
                if (dateText < $("input[name=startDate]").val()){
                  $( "#endDate" ).datepicker( "show" );
				    alert("结束日期不能小于开始日期！");
					//$("#endDate").val(newdate)
                }
            }
        });
    };
    $.date = function(){
        $('.date').datepicker(
            $.extend({showMonthAfterYear:true}, $.datepicker.regional['zh-CN'],
                {'showAnim':'','dateFormat':'mm/dd/yy','changeMonth':'true','changeYear':'true',
                    'showButtonPanel':'true'}
            ));
    };
    $.datepickerjQ = function(){
       $(".ui-datepicker-time").on("click",function(){
		   
           $(".ui-datepicker-css").css("display","block")
        });
        $(".ui-kydtype li").on("click",function(){
            $(".ui-kydtype li").removeClass("on").filter($(this)).addClass("on");
//            getAppCondtion();
        });
		
        $(".ui-datepicker-quick input").on("click",function(){
            var thisAlt = $(this).attr("alt");
			
			if(thisAlt.indexOf("month") !=-1){
				var moNum = parseInt(thisAlt.substr(6,1));
				var monthCofg = mouth(moNum);
				$(".ui-datepicker-time").val(monthCofg);
			}else{
				var dateList = timeConfig(thisAlt);
				$(".ui-datepicker-time").val(dateList);
			}
            $(".ui-datepicker-css").css("display","none");
			 $("#ui-datepicker-div").css("display","none")
//            getAppCondtion()
        });
        $(".ui-close-date").on("click",function(){
            $(".ui-datepicker-css").css("display","none")
			 $("#ui-datepicker-div").css("display","none")
			//inst.dpDiv.css({"display":"none"})
        });
		 $(".startDate").on("click",function(){
            $(".endDate").attr("disabled",false);
        });
	
    }
	
})(jQuery);

$(function(){
	//初始的时候获得对应的月份
	var nowDate = new Date(seTime);
	var curYear = nowDate.getFullYear();
	var curMonth = nowDate.getMonth()+1;
	var chgYear;
	var curMonths;
		if(curMonth ==1){
			chgYear = curYear-1;
			curMonth = 12;
			$("#thisM").val(curMonth +"/"+chgYear);
			$("#lastM").val((curMonth-1)+"/"+chgYear);
			$("#lastTM").val((curMonth-2)+"/"+chgYear);
		}else if(curMonth == 2){
			chgYear = curYear-1;
			curMonth = 12;
			$("#thisM").val((curMonth-1)+"/"+ chgYear);
			$("#lastM").val(curMonth+"/" +chgYear);
			$("#lastTM").val("1"+"/"+curYear);
		}else if(curMonth == 3){
			chgYear = curYear-1;
			curMonths = 12;
			$("#thisM").val(curMonths +"/"+chgYear);
			$("#lastM").val((curMonth-2) +"/" +curYear);
			$("#lastTM").val((curMonth-1) +"/"+curYear);
		}else{
			$("#thisM").val((curMonth-3) +"/"+curYear);
			$("#lastM").val((curMonth-2)+"/"+curYear);
			$("#lastTM").val((curMonth-1) +"/"+curYear);
		}
        $.setStartTime();
        $.setEndTime();
        $.datepickerjQ();
        $(".ui-datepicker-quick input").each(function(index, element) {
            var flag = $(this).attr("onOff");
			if(flag){
				//$(this).val()
				$(this).click();
			}
        });
        
        timeStr = goTo((nowDate.getMonth()+1))+'/'+ goTo( nowDate.getDate())+ '/' +nowDate.getFullYear();
        nowDate.setDate(nowDate.getDate()+parseInt(-1));
        var endDateStr = goTo((nowDate.getMonth()+1))+ '/'+ goTo(nowDate.getDate()) + '/' + nowDate.getFullYear();
		
        
        //$(".ui-datepicker-time").attr("value",endDateStr +"-"+ timeStr)
		$("#startDate").attr("value",endDateStr)
		$("#endDate").attr("value",timeStr)
    });


    function timeConfig(time){
		//快捷菜单的控制
    	var nowDate = new Date(seTime);
		var nowD = nowDate.getTime()-1000*60*60*24*1;
		var nD = new Date(seTime);
		nD.setTime(nowD);
		//alert(nD.getDate())
        timeStr = '-' + goTo((nD.getMonth()+1)) + '/' +  goTo(nD.getDate()) + '/' + nD.getFullYear();
        nowDate.setDate(nowDate.getDate()+parseInt(time));
        var endDateStr = goTo((nowDate.getMonth()+1))+ '/'+goTo(nowDate.getDate())+ '/'+nowDate.getFullYear();
		
        if(time == -1){
            endDateStr += '-' + endDateStr;
        }else if(time == 0){
            endDateStr += '-' + endDateStr;
        }else{
			endDateStr += timeStr;
		}
        return endDateStr;
    }
	//对过去几个月的写法
	function mouth( num ){
		
		var oDateMup = new Date(seTime);
		oDateMup.setMonth(oDateMup.getMonth()-num,1);	
		var iYearMup = oDateMup.getFullYear();
		var iMonthMup = oDateMup.getMonth()+1;
		var iDayMup = oDateMup.getDate();
		var monStr = "";
		
		if(num==0){
			monStr =goTo(iMonthMup)+ '/' + '01' + '/'+ iYearMup  +' - '  + goTo(iMonthMup)+ '/' + goTo((oDate2).getDate())+ '/'+ iYear2;
		}else{
			monStr =goTo(iMonthMup)+ '/' + '01'+ '/' + iYearMup +' - '  + goTo(iMonthMup)+ '/' + getCountDays(oDateMup) + '/'+ iYearMup;
		}
		return monStr;
	}
	//补零操作
	function goTo(n){
		if(n<10){
			return '0'+n;
		}else{
			return n;	
		}
	}
	function getCountDays( oDateM ) {
		
		//var curDate = oDate2;
		/* 获取当前月份 */
		var curMonth = oDateM.getMonth();
	   /*  生成实际的月份: 由于curMonth会比实际月份小1, 故需加1 */
	   oDateM .setMonth(curMonth + 1);
	   /* 将日期设置为0, 这里为什么要这样设置, 我不知道原因, 这是从网上学来的 */
	   oDateM .setDate(0);
	   /* 返回当月的天数 */
	   return oDateM.getDate();
	}
	
	//例如,  获取当前月份(现在是3月)的总天数: 
	//getCountDays()      // 返回31
    function datePickers(){
		//自定义菜单
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
		
        var dateList = startDate +'-'+ endDate;
        $(".ui-datepicker-time").val(dateList);
        $(".ui-datepicker-css").css("display","none");
//        getAppCondtion()
    }