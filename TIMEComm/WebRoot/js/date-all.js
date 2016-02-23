var oDm = document.getElementById('demospan');
var oLi = document.getElementById("uldiv").getElementsByTagName('li');
var oDate2 = new Date(year_now,month_now,day_now);
oDate2.setDate( oDate2.getDate()-1);
var iYear2 = oDate2.getFullYear();
var iMonth2 = oDate2.getMonth()+1;
var iDay2 = oDate2.getDate();

function oDay(numD,numH){
	
if(numH){
	
	var oDateW = new Date(year_now,month_now,day_now);
	oDateW.setDate(oDateW.getDate()-numD);
	oDateW.setDate(oDateW.getDate()-numH);
	var iYearH = oDateW.getFullYear();
    var iMonthH = oDateW.getMonth()+1;
    var iDayH = oDateW.getDate();
	oDm.value = goTo(iMonthH)+ '/' + goTo(iDayH) + '/'+ iYearH  + ' - ' +  goTo(iMonth2)+ '/' +  goTo(iDay2)+ '/'+ iYear2;
}else{
	var oDate = new Date(year_now,month_now,day_now);

oDate.setDate(oDate.getDate()-numD);
	var iYear = oDate.getFullYear();
	var iMonth = oDate.getMonth()+1;
	var iDay = oDate.getDate();

	oDm.value =goTo(iMonth)+ '/' + goTo(iDay)+ '/' + iYear  +' - ' +  goTo(iMonth2)+ '/' +  goTo(iDay2) + '/'+ iYear2;
}
}
function mouth( num ){
	var oDateMup = new Date(year_now,month_now,day_now);
	oDateMup.setMonth(oDateMup.getMonth()-num);	
	var iYearMup = oDateMup.getFullYear();
	var iMonthMup = oDateMup.getMonth()+1;
	var iDayMup = oDateMup.getDate();
	if(num==0){
		oDm.value =goTo(iMonthMup)+ '/' + '01' + '/'+ iYearMup  +' - '  + goTo(iMonthMup)+ '/' + goTo(iDayMup)+ '/'+ iYear2;
	}else{
		oDm.value =goTo(iMonthMup)+ '/' + '01'+ '/' + iYearMup +' - '  + goTo(iMonthMup)+ '/' + getCountDays(oDateMup ) + '/'+ iYearMup;
	}
}
function wDay(to){
	var oDateW = new Date(year_now,month_now,day_now);
	var iYearH = oDateW.getFullYear();
   	var iMonthH = oDateW.getMonth()+1;
   	var iDayH = oDateW.getDate();
	oDm.value = goTo(iMonthH)+ '/' + goTo(iDayH)+ '/' + iYearH +' - '+ goTo(iMonthH)+ '/' + goTo(iDayH)+ '/'+ iYearH;
	}
function goTo(n){
		if(n<10){
			return '0'+n;
		}else{
			return n;	
		}
	}
oLi[4].onclick = function(){ mouth(0) }
oLi[5].onclick = function (){ mouth(1) }
oLi[6].onclick = function (){ mouth(2) }
oLi[7].onclick =  function(){mouth(3)}
oLi[0].onclick =  function(){wDay(0)}
oLi[1].onclick =  function(){oDay(1)}
oLi[2].onclick =  function(){oDay(7)}
oLi[3].onclick =  function(){oDay(30)}
var thism = document.getElementById('thisM');
var lastm = document.getElementById('lastM');
var lastTm = document.getElementById('lastTM');
var timez=new Date(year_now,month_now,day_now);
var curYear = timez.getFullYear();
var curMonth = timez.getMonth()+1;
	if(curMonth ==1){
		chgYear = curYear-1;
		curMonth = 12;
		thism.innerHTML =curMonth +"/"+chgYear;
		lastm.innerHTML = (curMonth-1)+"/"+chgYear;
		lastTm.innerHTML = (curMonth-2)+"/"+chgYear;
		
	}else if(curMonth == 2){
		chgYear = curYear-1;
		curMonth = 12;
		lastTm.innerHTML = "1"+"/"+curYear;
		thism.innerHTML = (curMonth-1)+"/"+ chgYear;
		lastm.innerHTML = curMonth+"/" +chgYear;
		
	}else if(curMonth == 3){
		chgYear = curYear-1;
		curMonths = 12;
		lastTm.innerHTML =(curMonth-1) +"/"+curYear;
		lastm.innerHTML =(curMonth-2) +"/" +curYear;
		thism.innerHTML =curMonths +"/"+chgYear;
	
	}else{
		lastTm.innerHTML =(curMonth-1) +"/"+curYear;
		lastm.innerHTML =(curMonth-2)+"/"+curYear ;
		thism.innerHTML =(curMonth-3) +"/"+curYear;	
	}


function getCountDays( oDateM ) {
        //var curDate = new Date(year_now,month_now,day_now);
        /* 获取当前 */
        var curMonth = oDateM .getMonth();
       /*  生成实际的: 由于curMonth会比实际小1, 故需加1 */
       oDateM .setMonth(curMonth + 1);
       /* 将日期设置为0, 这里为什么要这样设置, 我不知道原因, 这是从网上学来的 */
       oDateM .setDate(0);
       /* 返回当月的天数 */
       return oDateM .getDate();
}
//例如,  获取当前(现在是3月)的总天数: 
     // 返回31
