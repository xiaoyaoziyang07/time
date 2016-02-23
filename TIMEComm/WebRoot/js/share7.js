var seTime = getService_Time();
var oDate2 = new Date(seTime);
var iYear2 = oDate2.getFullYear();
var iMonth2 = oDate2.getMonth()+1;
var iDay2 = oDate2.getDate();
var dateMessage=getCommonDateMessage();
var monthArr =dateMessage.months;
var weekArr = dateMessage.weeks;
function fase(){	
	var th = window.event || arguments.callee.caller.arguments[0];
	 if (th) {
	 th.stopPropagation();
	 } else {
	 th.cancelBubble = true;
	 }
	 return false;
}
(function($){
    $.setStartTime = function(){
        $('.startDate').datepicker({
            dateFormat: "mm/dd/yy",
            maxDate:null,
			minDate: "-90d",
			monthNames: monthArr,
			dayNamesMin: weekArr,
            onClose : function(dateText, inst) {
                $( "#endDate" ).datepicker( "show" );
            },
			onSelect:function(dateText, inst) {
				var setDates=parseInt($(".endDate").attr("setDate"));
				var maxD = new Date(Date.parse(dateText.replace(/-/g, "/")));
				var max_date = new Date(maxD.getFullYear(), maxD.getMonth(), maxD.getDate()+setDates);
				var mYear = max_date.getFullYear();
				var years = max_date.getFullYear();
				var mMonth = max_date.getMonth()+1;
				var mDays = max_date.getDate();
				
				var curD = new Date(seTime);
				var curY = curD.getFullYear();
				var curM = curD.getMonth()+1;
				var curDays = curD.getDate();
				if( max_date > (new Date(seTime))){
					
					var maxData = ""+curM+"/"+curDays+"/"+curY+"";
					
				}else{
					var maxData = ""+mMonth+"/"+mDays+"/"+mYear+"";
				}				
                $( "#endDate" ).datepicker( "option","minDate",dateText);
				$( "#endDate" ).datepicker( "option","maxDate",maxData);
				
            },
			
        });
    };
    $.setEndTime = function(){
        $(".endDate").datepicker({
            dateFormat: "mm/dd/yy",
            maxDate: "+d",
            monthNames: monthArr,
			dayNamesMin: weekArr,
			defaultDate : new Date(seTime),
            onClose : function(dateText, inst) {
				
                if (dateText < $("input[name=startDate]").val()){
                  $( "#endDate" ).datepicker( "show" );
				    alert("Please select a date again?");
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
           $(".ui-datepicker-css").show();
           fase();
       });
        $(".ui-kydtype li").on("click",function(){
            $(".ui-kydtype li").removeClass("on").filter($(this)).addClass("on");
            fase();
//            getAppCondtion();
        });
		
        $(".ui-datepicker-quick input").on("click",function(){
            var thisAlt = $(this).attr("alt");
            var moNum = parseInt(thisAlt.substr(6,1));
			if(thisAlt.indexOf("month") !=-1){
				
				
				var monthCofg = mouth(moNum);
				
				$(".ui-datepicker-time").val(monthCofg);
				
			}else if(thisAlt.indexOf("weeks") !=-1){
				//执行获取周的日历
				var weekDay = getWeek(moNum);
				$(".ui-datepicker-time").val(weekDay);
				
			}else{
				var dateList = timeConfig(thisAlt);
				//alert(dateList);
				$(".ui-datepicker-time").val(dateList);
				//alert($(".ui-datepicker-time").val())
			}
            $(".ui-datepicker-css").css("display","none");
			 $("#ui-datepicker-div").css("display","none")
//            getAppCondtion() 
			 fase();
        });
        $(".ui-close-date").on("click",function(){
            $(".ui-datepicker-css").fadeOut("fast");
			 $("#ui-datepicker-div").fadeOut("fast");
			//inst.dpDiv.css({"display":"none"})
			  fase();
        });
		 $(".startDate").on("click",function(){
            $(".endDate").attr("disabled",false);
            fase();
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
		//alert(timeStr)
        nowDate.setDate(nowDate.getDate()+parseInt(-1));
        var endDateStr = goTo((nowDate.getMonth()+1))+ '/'+ goTo(nowDate.getDate()) + '/' + nowDate.getFullYear();
		
		//alert(timeStr);
		//$(".ui-datepicker-time").attr("value",endDateStr +"-"+ timeStr)
		$("#startDate").attr("value",'Start Day');
		$("#endDate").attr("value",'End Day');
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
			
			monStr =goTo(iMonthMup)+ '/' + '01' + '/'+ iYearMup  +'-'  + goTo(iMonthMup)+ '/' + goTo((new Date()).getDate())+ '/'+ iYear2;
		}else{
			monStr =goTo(iMonthMup)+ '/' + '01'+ '/' + iYearMup +'-'  + goTo(iMonthMup)+ '/' + getCountDays(oDateMup) + '/'+ iYearMup;
		}
		return monStr;
	}
	//获取对应的上几周
	function getWeek(num){
		//alert(num)
		var d=new Date(seTime);
		 var newS = new Date(d-((d.getDay() || 7) -1+(num||0) *7 )*864E5);
		 var nowD = newS.getTime()+1000*60*60*24*6;
		 var nd = new Date(seTime);
		 nd.setTime(nowD);
		 var weeks7 = nd.getDate();
		 var weekss7 = nd.getMonth()+1;
		 var weeksss7 = nd.getFullYear();
			//alert(nd.getDate())
		 var weeks =newS.getDate();
		 var weekss = newS.getMonth()+1;
		 var weeksss = newS.getFullYear();
		 var weekstr = "";
		if(num == 0){
			weekstr =goTo(weekss) +'/'+ goTo(weeks)+'/'+weeksss+'-'+goTo(d.getMonth()+1) +'/'+ goTo(d.getDate())+'/'+d.getFullYear();
		}else{
			weekstr  = goTo(weekss) +'/'+ goTo(weeks)+'/'+weeksss+'-'+goTo(weekss7) +'/'+ goTo(weeks7)+'/'+weeksss7;
		}
		 
		 
		 return weekstr;
		 
		
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
		//alert(oDateM)
		//var oDateM = new Date();
		/* 获取当前月份 */
		//var md = new Date(oDateM.getFullYear(), oDateM.getMonth(), oDateM.getDate());
		
		var curMonth = oDateM.getMonth();
	   /*  生成实际的月份: 由于curMonth会比实际月份小1, 故需加1 */
	   oDateM .setMonth(curMonth + 1);
	   /* 将日期设置为0, 这里为什么要这样设置, 我不知道原因, 这是从网上学来的 */
	   oDateM .setDate(0);
	   /* 返回当月的天数 */
	   return oDateM.getDate();
	}
	
	//例如,  获取当前月份(现在是3月)的总天数: 
	//getCountDays()
	// 返回31
    function datePickers(){
		//自定义菜单
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var dateList = startDate +'-'+ endDate;
        $(".ui-datepicker-time").val(dateList);
        $(".ui-datepicker-css").css("display","none");
//        getAppCondtion()
    }