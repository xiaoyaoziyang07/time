PLAN.time={
		 num:'',//当前显示个数
		 new_number:'1',//当前页数，
		 del_id:{},//删除的id
		 load:function(){
			 var self=this;
			 var c=self.new_number;
			   if(c=='add'){
				    var data={
				    'pageNum':1,
				    'pageSize':self.num
				 };
				    $.ajax({
				    data:data,
				    dataType:'json',
				    url:'/manager/timebaseplanlist',
				    async:true,
				    type:'post',
				    success:function(obj){
				    	self.new_number=Math.ceil(obj.total/self.num);
				    	self.load();
				    }
				  });
				   }else{
				    var data={
				    'pageNum':c,
				    'pageSize':self.num
				     };
				    $.ajax({
				    data:data,
				    dataType:'json',
				    url:'/manager/timebaseplanlist',
				    async:true,
				    type:'post',
				    success:function(obj){
				        console.log(obj);
				        if(Math.ceil(obj.total/self.num)<c){
				        	self.new_number=Math.ceil(obj.total/self.num);
				        	self.load();
				        }else{
				         self.pro_data(obj.timePolicys,c);
				        $("#page_id").pagination({
				        items:obj.total,
				        itemsOnPage:self.num,
				        cssStyle: 'light-theme',
				        currentPage:c,
				        onPageClick: function(pageNumber,event) {
					       self.new_number=pageNumber;
					       self.load();
						}
				    });
				        }
				    }
				  });
				   }
		 },
		 pro_data:function(a,b){
			 var self=this;
			    var th='<tr><th>item ID</th><th>Activation</th><th>Policy ID</th><th>Policy Name</th><th>Time1 of day</th><th>Time2 of day</th><th style="min-width:200px;width:200px">Day of week</th><th>Specific Period</th><th>Operation</th></tr>';
			    $("#ibod").html(th);
			    var td2='<td><div class="gaic"></div>'+		        
						'<span class="week week1">Mon.</span>'+
						'<span class="week week2">Tues.</span>'+
						'<span class="week week3">Wed.</span>'+
						'<span class="week week4">Thur.</span>'+
						'<span class="week week5">Fri.</span>'+
						'<span class="week week6">Sat.</span>'+
						'<span class="week week7">Sun.</span>'+
						'</td>';   	
			    var td3='<td><div class="gaic"></div>'+
					    '<p><span>Start time</span><input readonly="readonly" class="input2" type="text" value="" /></p><p><span>End time</span><input class="input2" type="text" value="" readonly="readonly"/></p>'+
					    '</td>';	
				var tr_1='';	   
				 var arr_b=['policyname','policyid']; 		
				 var arr_c=['upload1','download1','download','upload'];
			    for(var i=0;i<a.length;i++){
			      Filter_Data(a[i],arr_b,arr_c);
			      if(Mana_da){
			       if(MANA_ID==a[i].policyid){
			         MANA_img='<img class="MANA MANA-active" val="1" src="/img/blue_d.png" onclick="MANA_(this)">';
			     }else{
			        MANA_img='<img val="0" class="MANA" src="/img/blue_z.png" onclick="MANA_(this)">';
			     }
			      }
			       tr_1='<tr>';
			       var num=(b-1)*self.num+i+1;
			       var arc=false;
			       var uull,ddll;
			       if(a[i].ruletype==0){
			    	   uull='0Mbps';ddll='0Mbps';
			       }
			       else if(a[i].ruletype==1){
			    	   uull=a[i].upload1+'Mbps';ddll=a[i].download1+'Mbps';
			       }else if(a[i].ruletype==2){
			    	   uull=a[i].upload+'Mbps';ddll=a[i].download+'Mbps';
			       }else if(a[i].ruletype==3){
			    	   uull=a[i].upload+'Mbps';ddll=a[i].download+'Mbps';
			       }
			         tr_1+='<td>'+num+'</td>'+
			         '<td>'+MANA_img+'</td>'+
			         '<td ruletype="'+a[i].ruletype+'" ul="'+uull+'" dl="'+ddll+'">'+a[i].policyid+'</td>'+
			         '<td>'+a[i].policyname+'</td>';			    
			        if(a[i].ruletype==0){
			         var td1='<td data-down="'+a[i].download1+'" data-up="'+a[i].upload1+'" id="'+a[i].policyid+'" data-star-time="'+a[i].starttime1+'" data-end-time="'+a[i].endtime1+'"><div class="gaic"></div></td><td data-down="'+a[i].download2+'" data-up="'+a[i].upload2+'" data-end-time="'+a[i].endtime2+'" data-star-time="'+a[i].starttime2+'" id="'+a[i].policyid+'-"><div class="gaic"></div></td>';
					  	tr_1+=td1+td2+td3;
					  	}else if(a[i].ruletype==1){
					  	 var td1='<td data-down="'+a[i].download1+'" data-up="'+a[i].upload1+'" id="'+a[i].policyid+'" data-star-time="'+a[i].starttime1+'" data-end-time="'+a[i].endtime1+'"><div style="clear:both"></div><hr /><div class="up_down"><img src="/img/img-upload.png">UL:'+a[i].upload1+'Mbps</div><div class="up_down"><img src="/img/img-download.png">DL:'+a[i].download1+'Mbps</div></td><td data-down="'+a[i].download2+'" data-up="'+a[i].upload2+'" data-end-time="'+a[i].endtime2+'" data-star-time="'+a[i].starttime2+'" id="'+a[i].policyid+'-"><div style="clear:both"></div><hr /><div class="up_down"><img src="/img/img-upload.png">UL:'+a[i].upload2+'Mbps</div><div class="up_down"><img src="/img/img-download.png">DL:'+a[i].download2+'Mbps</div></td>';
					  	     var s1=(a[i].starttime1!="")?a[i].starttime1:"00:00";
					  	     var s2=(a[i].starttime2!="")?a[i].starttime2:"00:00";
					  	     var e1=(a[i].endtime1!="")?a[i].endtime1:"00:00";
					  	     var e2=(a[i].endtime2!="")?a[i].endtime2:"00:00";
						  	 var star1=s1.split(':');
						 	 var star2=s2.split(':');
						 	 var end1=e1.split(':');
						 	 var end2=e2.split(':');
						 	    console.log(star1,star2,end1,end2);
				 		 	     arc=true;
							     tr_1+=td1+td2+td3;
					  	}else if(a[i].ruletype==2){
					  	     var td1='<td data-down="'+a[i].download1+'" data-up="'+a[i].upload1+'" id="'+a[i].policyid+'" data-star-time="'+a[i].starttime1+'" data-end-time="'+a[i].endtime1+'"><div class="gaic"></div></td><td data-down="'+a[i].download2+'" data-up="'+a[i].upload2+'" data-end-time="'+a[i].endtime2+'" data-star-time="'+a[i].starttime2+'" id="'+a[i].policyid+'-"><div class="gaic"></div></td>';
					  	 	var week=[a[i].mon,a[i].tues,a[i].wed,a[i].thur,a[i].fri,a[i].sat,a[i].sun];
			 			     for(var k in week){
			 			         if(week[k]){
			 			           week[k]="active";
			 			         }else{
			 			           week[k]="";
			 			         }
			 			     }
					  		 tr_1+=td1;
					         tr_1+='<td data-week="'+week+'" data-down="'+a[i].download+'" data-up="'+a[i].upload+'">'+		        
							 '<span class="week week1 '+week[0]+'">Mon.</span>'+
						     '<span class="week week2 '+week[1]+'">Tues.</span>'+
						     '<span class="week week3 '+week[2]+'">Wed.</span>'+
						     '<span class="week week4 '+week[3]+'">Thur.</span>'+
						     '<span class="week week5 '+week[4]+'">Fri.</span>'+
						     '<span class="week week6 '+week[5]+'">Sat.</span>'+
						     '<span class="week week7 '+week[6]+'">Sun.</span>'+
					         '<div style="clear:both"></div><hr /><div class="up_down"><img src="/img/img-upload.png">UL:'+a[i].upload+'Mbps</div><div class="up_down"><img src="/img/img-download.png">DL:'+a[i].download+'Mbps</div></td>';		         
					         tr_1+=td3;
					  	
					  	}else if(a[i].ruletype==3){
					  	     var td1='<td data-down="'+a[i].download1+'" data-up="'+a[i].upload1+'" id="'+a[i].policyid+'" data-star-time="'+a[i].starttime1+'" data-end-time="'+a[i].endtime1+'"><div class="gaic"></div></td><td data-down="'+a[i].download2+'" data-up="'+a[i].upload2+'" data-end-time="'+a[i].endtime2+'" data-star-time="'+a[i].starttime2+'" id="'+a[i].policyid+'-"><div class="gaic"></div></td>';
					  	    tr_1+=td1+td2;
					        tr_1+='<td data-end-time="'+a[i].endtime+'" data-star-time="'+a[i].starttime+'"  data-down="'+a[i].download+'" data-up="'+a[i].upload+'">'+
					        '<p><span>Start time</span><input class="input2" type="text" value="'+a[i].starttime+'" /></p><p><span>End time</span><input class="input2" type="text" value="'+a[i].endtime+'" /></p>'+
					        '<div style="clear:both"></div><hr /><div class="up_down"><img src="/img/img-upload.png">UL:'+a[i].upload+'Mbps</div><div class="up_down"><img src="/img/img-download.png">DL:'+a[i].download+'Mbps</div></td>';
					  	}
			         tr_1+='<td><img class="time_edit" ruleType="'+a[i].ruletype+'" src="/img/_edit.png"><img class="time_del" policyid='+a[i].policyid+' src="/img/_dele.png"></td>'+
			         '</tr>';
			         $("#ibod").append(tr_1);
			         $('.time_edit').on('click',function(){
			        	 self.edit(this)
			         });
			         $('.time_del').on('click',function(){
			        	 self.del(this)
			         })
			         if(arc){
			 			if(star1[0]>12){
			 			    $('#'+a[i].policyid).watches(a[i].policyid+'2',80,80,'PM',star1[0],end1[0]);
					 	    $('#'+a[i].policyid).watches(a[i].policyid+'1',80,80,'AM',0,0);	 	      
					 	}else{
					 		if(end1[0]<=12){
			      	   		$('#'+a[i].policyid).watches(a[i].policyid+'2',80,80,'PM',0,0);		 		
					 		$('#'+a[i].policyid).watches(a[i].policyid+'1',80,80,'AM',star1[0],end1[0]);		 		 	    
					 	    }else{
			      	   		$('#'+a[i].policyid).watches(a[i].policyid+'2',80,80,'PM',12,end1[0]);		 	    
					 	    $('#'+a[i].policyid).watches(a[i].policyid+'1',80,80,'AM',star1[0],12);	
					 	    }
					 	 }
					 	 if(star2[0]>12){
					 	    $('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'1',80,80,'PM',star2[0],end2[0]);
					 	 	$('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'2',80,80,'AM',0,0);	     	   		 	      
					 	}else{
					 		if(end2[0]<=12){
			      	   		$('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'2',80,80,'PM',0,0);	
					 	    $('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'1',80,80,'AM',star2[0],end2[0]); 	 		 	    
					 	    }else{
			      	   		$('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'2',80,80,'PM',12,end2[0]);	
					 	    $('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'1',80,80,'AM',star2[0],12);
					 	    }
					 	 }

			         }else{
			      	   		$('#'+a[i].policyid).watches(a[i].policyid+'2',80,80,'PM',0,0);	         
			           		$('#'+a[i].policyid).watches(a[i].policyid+'1',80,80,'AM',0,0); 
			      	   		$('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'2',80,80,'PM',0,0);	
					 	    $('#'+a[i].policyid+'-').watches(a[i].policyid+'-'+'1',80,80,'AM',0,0);
			         }
			    };
			    

		 },
		 edit:function(a){
		     	$("#modal-backdrop").fadeIn("slow",function(){
		     		$("#add_proj").addClass('a-fadeinT');		     		
		     	});				
				$("#add_proj input").val("");
				$('#add_proj .week').removeClass('active');
				$('#add_proj .week').attr('value','0');
				$("#add_proj select").val("Mps");
				var ruletype=$(a).attr('ruletype')-1;
				var dom=$(a).parent().siblings();
				var ip=$('.bigChartDiv input');
				console.log(dom,ip);
				ip.eq(0).val(dom.eq(2).html());
				ip.eq(1).val(dom.eq(3).html());
				act(ruletype);
				if(ruletype==0){
				 var time_day= dom.eq(4);
				 var time_day_= dom.eq(5);
				     $('#start_time').val(time_day.attr("data-star-time"));
				     $('#end_time').val(time_day.attr("data-end-time"));
				     $('#start_time_1').val(time_day_.attr("data-star-time"));
				     $('#end_time_1').val(time_day_.attr("data-end-time"));
				     $('.Time_of_day .free_number').eq(0).find('input').val(time_day.attr('data-up'));
				     $('.Time_of_day .free_number').eq(1).find('input').val(time_day.attr('data-down'));
				     $('.Time_of_day .free_number').eq(2).find('input').val(time_day_.attr('data-up'));
				     $('.Time_of_day .free_number').eq(3).find('input').val(time_day_.attr('data-down'));
				}else if(ruletype==1){
				   	 var time_day= dom.eq(6);
				   	 var we=time_day.attr('data-week').split(',');
				   	 var wee=$('#bigChartDiv .week');
				   	 for(var t=0;t<we.length;t++){
				   	     wee.eq(t).addClass(we[t]);
				   	     var y=(we[t]=='active')?1:0;
				   	     wee.eq(t).attr('value',y);
				   	 }
				   	 $('.Day_of_week .free_number input').eq(0).val(time_day.attr('data-up'));
				   	 $('.Day_of_week .free_number input').eq(1).val(time_day.attr('data-down'));
				}else{
				     var time_day= dom.eq(7);
				     $('.specific_Period input').eq(0).val(time_day.attr('data-star-time'));
				     $('.specific_Period input').eq(1).val(time_day.attr('data-end-time'));
				     $('.specific_Period input').eq(2).val(time_day.attr('data-up'));
				     $('.specific_Period input').eq(3).val(time_day.attr('data-down'));
				}			 
		 },
		 del:function(a){
		     this.del_id={policyId:$(a).attr("policyid")};		     
		     $("#modal-backdrop").fadeIn('slow',function(){
		    	 $("#add_proj1").addClass('a-fadeinT');		    	 
		     });			 
		 }
}