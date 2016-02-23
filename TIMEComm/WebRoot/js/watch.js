//时刻表的//时刻表的方法
function drawClock(id,width,height,oneday,a,b) {
	var s=a*30;
	var e=b*30;
    var canvas = document.getElementById(id);
    var cxt = canvas.getContext("2d");
        var now = new Date();
        var sec = now.getSeconds();
        var min = now.getMinutes();
        var hour = now.getHours();
        hour > 12 ? hour - 12 : hour;
        hour += (min / 60);
        //先清空画布
        cxt.clearRect(0, 0, canvas.width, canvas.height);
        //画表盘大圆 圆心：x=250 y=250
        var x=width/2;
        var y=height/2;
        var ban1=y-y/6;
        var ban2=y-y/5;
        var ban3=y-y/1.6;
        var ban4=ban2;
        var ban5=y-y/2.2;
        cxt.strokeStyle = "#aeaeae";
        cxt.lineWidth =2;
        cxt.beginPath();
        cxt.arc(x,y,ban1, 0, 360);
        cxt.fillStyle = "#fff";
        cxt.fill();
        cxt.stroke();
        cxt.beginPath();
        cxt.strokeStyle = "#d6d6d6";
        var star_j=((s-90)/180);
        var end_j=((e-90)/180);
        cxt.arc(x, y,ban2,star_j*Math.PI,end_j*Math.PI);
        cxt.save();
        cxt.translate(x,y);
        cxt.lineTo(0,0);           
        cxt.fillStyle = "#d6d6d6";
        cxt.fill(); 
        cxt.stroke();
        cxt.closePath();
        cxt.restore();   
        cxt.globalAlpha=1;
        cxt.beginPath();
        cxt.strokeStyle = "#aeaeae";
        cxt.arc(x, y, ban3, 0, 360);
        cxt.fillStyle = "#aeaeae";
        cxt.fill();
        cxt.stroke();
        var a=[12,3,6,9];
            cxt.save();
            cxt.translate(x,y);
            cxt.beginPath();
            cxt.lineWidth = 0;
            cxt.font ="9px Arial";
            cxt.fillStyle = "#ff0d09";
            cxt.textAlign="center";
            cxt.textBaseline="middle";
            cxt.fillText(a[0],0,-ban5);   
            cxt.fillText(a[1],ban5,0);   
            cxt.fillText(a[2],0,ban5);   
            cxt.fillText(a[3],-ban5,0);   
            cxt.stroke();
            cxt.restore();
        for (var i = 0; i < 12; i++) {
            cxt.save();//保存当前状态
            cxt.lineWidth =1;
            cxt.strokeStyle = "#ff0d09";
            cxt.translate(x, y);
            cxt.rotate(30 * i * Math.PI / 180);//弧度   角度*Math.PI/180
            cxt.beginPath();
            cxt.moveTo(0, -ban4);
            cxt.lineTo(0, -(ban4-ban4/8));
            cxt.stroke();
            cxt.restore();//把原来状态恢复回来
        }
        cxt.save();
        cxt.font = y/3.5+"px 微软雅黑";
        cxt.fillStyle = "#fff";
        cxt.lineWidth = 2;
        cxt.textAlign="center";
        cxt.textBaseline="middle";
        cxt.fillText(oneday,x,y);   
    }
   
    (function($){
       $.fn.watches= function(id,width,height,oneday,a,b) {
    	a=parseFloat(a);
    	b=parseFloat(b);
       	var canv='<canvas id="'+id+'" width="'+width+'" height="'+height+'"></canvas>';
       	$(this).prepend(canv);
         return this.each(function(){         	
         	drawClock(id,width,height,oneday,a,b);
         })
         
}
})(jQuery);