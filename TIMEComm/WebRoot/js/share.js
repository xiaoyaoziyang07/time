var oDate2 = new Date();
var iYear2 = oDate2.getFullYear();
var iMonth2 = oDate2.getMonth()+1;
var iDay2 = oDate2.getDate();  
(function($){
	
    $.setStartTime = function(){
        $('.startDate').datepicker({
            dateFormat: "yy/mm/dd",
            maxDate: "+d",
            minDate: "-90d",
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
				
				var curD = new Date();
				var curY = curD.getFullYear();
				var curM = curD.getMonth()+1;
				var curDays = curD.getDate();
				if( max_date > (new Date())){
					
					var maxData = ""+curY+"/"+curM+"/"+curDays+"";
					
				}else{
					var maxData = ""+mYear+"/"+mMonth+"/"+mDays+"";
				}				
                $( "#endDate" ).datepicker( "option","minDate",dateText);
				$( "#endDate" ).datepicker( "option","maxDate",maxData);
				
            },
			
        });
    };
    $.setEndTime = function(){
        $(".endDate").datepicker({
            dateFormat: "yy/mm/dd",
            maxDate: "+d",
			defaultDate : new Date(),
			onSelect:function(){
				
			},
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
                {'showAnim':'','dateFormat':'dd/mm/yy','changeMonth':'true','changeYear':'true',
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
	var nowDate = new Date();
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
		
        
        timeStr = goTo((nowDate.getMonth()+1))+'/'+ goTo( nowDate.getDate())+ '/' +nowDate.getFullYear();
        nowDate.setDate(nowDate.getDate()+parseInt(-1));
        var endDateStr = goTo((nowDate.getMonth()+1))+ '/'+ goTo(nowDate.getDate()) + '/' + nowDate.getFullYear();
		$(".ui-datepicker-time").attr("value",endDateStr +"-"+ timeStr)
		$("#startDate").attr("value",endDateStr)
		$("#endDate").attr("value",timeStr)
    });


    function timeConfig(time){
		//快捷菜单的控制
        var nowDate = new Date();
        timeStr = '-' + goTo((nowDate.getMonth()+1)) + '/' +  goTo(nowDate.getDate()) + '/' + nowDate.getFullYear();
        nowDate.setDate(nowDate.getDate()+parseInt(time));
        var endDateStr = goTo((nowDate.getMonth()+1))+ '/'+goTo(nowDate.getDate())+ '/'+nowDate.getFullYear();
        if(time == -1){
            endDateStr += '-' + endDateStr;
        }else{
            endDateStr += timeStr;
        }
        return endDateStr;
    }
	//对过去几个月的写法
	function mouth( num ){
		
		var oDateMup = new Date();
		oDateMup.setMonth(oDateMup.getMonth()-num);	
		var iYearMup = oDateMup.getFullYear();
		var iMonthMup = oDateMup.getMonth()+1;
		var iDayMup = oDateMup.getDate();
		var monStr = "";
		
		if(num==0){
			monStr =goTo(iMonthMup)+ '/' + '01' + '/'+ iYearMup  +' - '  + goTo(iMonthMup)+ '/' + goTo(iDayMup)+ '/'+ iYear2;
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
		
		//var curDate = new Date();
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
	getCountDays()      // 返回31
    function datePickers(){
		//自定义菜单
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
		
        var dateList = startDate +'-'+ endDate;
        $(".ui-datepicker-time").val(dateList);
        $(".ui-datepicker-css").css("display","none");
//        getAppCondtion()
    }