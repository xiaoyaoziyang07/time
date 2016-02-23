PLAN.plan={
	 num:0,//当前显示个数
	 new_number:1,//当前页数，
	 del_id:{},//删除的id
	 edit_:false,//是否编辑
	 index:false,//第几行
	 dom:false,//保存按钮的位置
	 data:[],//数据提交时的比较
	 load:function(){
		this.index=false;
		this.dom=false;
		this.edit_=false
		 var self=this;
		 var c=self.new_number;
		   if(c=='add'){
			 var data={
				  'queryInfo.currentPage':1,
				  'queryInfo.pageSize':self.num
			 };
			    $.ajax({
			    data:data,
			    dataType:'json',
			    url:'/manager/planbuilderlist',
			    async:true,
			    type:'post',
			    success:function(data){
			     var obj=data.pageBean;
			     self.new_number=Math.ceil(obj.totalRecord/self.num);
			     self.load();
			    }
			  });
			   }else{
			    var data={
			    'queryInfo.currentPage':c,
			    'queryInfo.pageSize':self.num
			     };
			    $.ajax({
			    data:data,
			    dataType:'json',
			    url:'/manager/planbuilderlist',
			    async:true,
			    type:'post',
			    success:function(data){
			        var obj=data.pageBean;
			          console.log(obj);
			        if(Math.ceil(obj.totalRecord/self.num)<c){
			        self.new_number=Math.ceil(obj.totalRecord/self.num);
			          self.load();
			        }else{
			        self.pro_data(obj.list,c);
			        $("#page_id_").pagination({
			        items:obj.totalRecord,
			        itemsOnPage:self.num,
			        cssStyle: 'light-theme',
			        currentPage:c,
			        onPageClick: function(pageNumber,event) {
			        	self.new_number=pageNumber;
				        self.load(pageNumber);
					}
			    });
			        }
			    }
			  });
			   }
	 },
	 pro_data:function(a,b){
		 var self=this;
		   MANA_DATA_obj=[];
		   self.data=[];
		   console.log(a,b);
		    var brbp='block';
		    var tm_bo='block';
		    var q_top='block';
		    var tm_bo_arr,q_top_arr,tm_bo_str,q_top_str;
		    var arr_b=['packageid','policyname','planid','plantype','source','isactived','packageid','timepolicyid','topuppolicyid','qfuppolicyid',
		               'freeboostid',
		 			  'paidboost',     
		 			  'tierpolicyid',
		 			  'planname'];
		    var arr_c=['paymenttype','boostDL','boostUL','tierbaseDL','tierbaseUL','timebaseDL','plantype','source','ruleType'];
		    var tr='<tr><th>Item ID</th><th>Plannum</th><th>Plan ID</th><th>Plan Name</th><th>Payment Type</th><th>Plan Type</th><th>Package ID</th><th>TrBp</th><th>TmBP/Boost</th><th>Qfup/Top-up</th><th>Source</th><th>Operation</th></tr>';
		     for(var i=0;i<a.length;i++){
			        tm_bo_arr=[];  
			        q_top_arr=[];
			        Filter_Data(a[i],arr_b,arr_c);
		      var obj={};
		      var obj1={}
		          for(var p=0;p<MANA_DATA_arr.length;p++){
		            var qz='planBuilder.'+MANA_DATA_arr[p];
		            obj[qz]=a[i][MANA_DATA_arr[p]];
		            obj1[qz]=a[i][MANA_DATA_arr[p]];
		          }     
		          MANA_DATA_obj.push(obj);
		          self.data.push(obj1);
		       var o0= '<option value="0" '
		       var o0_='>Please select a</option>';
		       var o1= '<option value="1" ';
		       var o1_='>Base Plan</option>';
		       var o2= '<option value="2" ';
		       var o2_='>Free Boost</option>';
		       var o3= '<option value="3" ';
		       var o3_='>Paid Boost</option>';
		       var o4= '<option value="4" ';
		       var o4_='>Top up Volume</option>';
		       var o5= '<option value="5" ';
		       var o5_='>Top up Time</option>';
		       var zhong='selected="selected"';
		       var opo='';
		        switch(a[i].plantype){
		          case 0:
		              brbp='block';
		 	         tm_bo='block';
		 	         q_top='block';
		 	         tm_bo_arr=['timepolicyid','timebaseUL','timebaseDL'];
		 	         q_top_arr=['qfupQuote','qfuppolicyid'];
		 	         opo=o0+o0_+o1+o1_+o2+o2_+o3+o3_+o4+o4_+o5+o5_;
		 	         break;
		          case 1:
		 	         brbp='none';
		 	         tm_bo='none';
		 	         q_top='none';
		 	         tm_bo_arr=['timepolicyid','timebaseUL','timebaseDL'];
		 	         q_top_arr=['qfupQuote','qfuppolicyid'];
		 	         opo=o0+o0_+o1+zhong+o1_+o2+o2_+o3+o3_+o4+o4_+o5+o5_;
		 	         break;
		          case 2:
		          	 brbp='block';
		 	         tm_bo='none';
		 	         q_top='block';
		 	         tm_bo_arr=['freeboostid','boostUL','boostDL'];
		 	         q_top_arr=['qfupQuote','qfuppolicyid'];
		 	         opo=o0+o0_+o1+o1_+o2+zhong+o2_+o3+o3_+o4+o4_+o5+o5_;
		 	         break;
		 	     case 3:
		 	     	 brbp='block';
		 	         tm_bo='none';
		 	         q_top='block';
		 	         q_top_arr=['qfupQuote','qfuppolicyid'];
		 	         tm_bo_arr=['paidboost','boostUL','boostDL'];
		 	         opo=o0+o0_+o1+o1_+o2+o2_+o3+zhong+o3_+o4+o4_+o5+o5_;
		 	         break;
		 	     case 4:
		 	         brbp='block';
		 	         tm_bo='blcok';
		 	         q_top='none';
		 	         tm_bo_arr=['timepolicyid','timebaseUL','timebaseDL'];
		 	         q_top_arr=['topupTraffic','topuppolicyid'];
		 	         opo=o0+o0_+o1+o1_+o2+o2_+o3+o3_+o4+zhong+o4_+o5+o5_;
		 	         break;
		 	     case 5:
		 	         brbp='block';
		 	         tm_bo='none';
		 	         q_top='block';
		 	         q_top_arr=['qfupQuote','qfuppolicyid'];
		 	         tm_bo_arr=['timepolicyid','timebaseUL','timebaseDL'];
		 	         opo=o0+o0_+o1+o1_+o2+o2_+o3+o3_+o4+o4_+o5+zhong+o5_;
		 	         break;
		        }
		        if(a[i].source==1){
		           var oop='<option value="0">Please select a</option><option value="1" selected="selected">DPMS</option><option value="2">API</option>';
		        }else if(a[i].source==2){
		           var oop='<option value="0">Please select a</option><option value="1">DPMS</option><option value="2" selected="selected">API</option>';
		        }else{
		           var oop='<option value="0" selected="selected">Please select a</option><option value="1">DPMS</option><option value="2">API</option>';
		        }
		        if(a[i].paymenttype==0){
		        var ttt='<option value="0" selected="selected">Please select a</option><option value="1">Post-Paid</option><option value="2">Pre-Paid</option>';
		        }else if(a[i].paymenttype==1){
		        var ttt='<option value="0">Please select a</option><option  selected="selected"  value="1">Post-Paid</option><option value="2">Pre-Paid</option>';
		        }else{
		        	 var ttt='<option value="0">Please select a</option><option value="1">Post-Paid</option><option selected="selected" value="2">Pre-Paid</option>';	
		        }
		        var num=(b-1)*self.num+i+1;
		        tr+='<tr>'+
		          '<td>'+num+'</td>'+
		          '<td>'+a[i].plannum+'</td>'+
		          '<td class="conten">'+a[i].planid+'</td>'+
		          '<td class="conten">'+a[i].planname+'</td>'+
		          '<td><select>'+ttt+'</select></td>'+
		          '<td><select class="plan-s">'+opo+'</select></td>'+
		          '<td class="conten">'+a[i].packageid+'</td>'+
		          '<td><div style="display:'+brbp+'" class="gaic"></div><div zid="'+a[i].tierpolicyid+'" id="'+a[i].planid+'_1" class="plan_trbp"  tierbaseDL="'+a[i].tierbaseDL+'" tierbaseUL="'+a[i].tierbaseUL+'" tierpolicyid="'+a[i].tierpolicyid+'"><div><img src="/img/img-upload.png">UL:<span>'+a[i].tierbaseUL+'Mbps</span></div>'+		    
		          '<div><img src="/img/img-download.png">DL:<span>'+a[i].tierbaseDL+'Mbps</span></div></div></td>';

		        var im=' ';
		        if(a[i].ruleType==0){
		        	im='<img class="img-img" src="/img/img-red-time.png" style="display:none">';
		        }else if(a[i].ruleType==1){
		        	im='<img class="img-img" src="/img/img-red-time.png">';
		        }
		        else if(a[i].ruleType==2){
		        	im='<img class="img-img" src="/img/img-red-week.png">';
		        }
		        else if(a[i].ruleType==3){
		        	im='<img class="img-img" src="/img/img-red-date.png">';
		        }
		        tr+='<td>'+
		        '<div style="display:'+tm_bo+'" class="gaic"></div>'+
		        '<div ruleType="'+a[i].ruleType+'" zid="'+a[i][tm_bo_arr[0]]+'" id="'+a[i].planid+'_2" class="plan_bpto" paidboost="'+a[i].paidboost+'" freeboostid="'+a[i].freeboostid+'" boostUL="'+a[i].boostUL+'" boostDL="'+a[i].boostDL+'" timepolicyid="'+a[i].timepolicyid+'" timebaseUL="'+a[i].timebaseUL+'" timebaseDL="'+a[i].timebaseDL+'"><div>'+im+'<div>UL:<span>'+a[i][tm_bo_arr[1]]+'Mbps</span></div><div>DL:<span>'+a[i][tm_bo_arr[2]]+'Mbps</span></div></div></div></td>';		  
		        
		        
		        
		        tr+='<td><div style="display:'+q_top+'" class="gaic"></div><div zid="'+a[i][q_top_arr[1]]+'" id="'+a[i].planid+'_3" class="plan_qfup" qfuppolicyid="'+a[i].qfuppolicyid+'" qfupQuote="'+a[i].qfupQuote+'" topuppolicyid="'+a[i].topuppolicyid+'" topupTraffic="'+a[i].topupTraffic+'" ><img src="/img/img-sc.png"><p>Quoat=<span>'+a[i][q_top_arr[0]]+'MB</span></p></div></td>'+
		          '<td><select>'+oop+'</select></td>'+
		          '<td><img title="save" class="ami-save plan_save" style="display:none" src="/img/_save.png"><img title="delet" class="ami-del plan_del" plannum='+a[i].plannum+' src="/img/_dele.png"></td>'+
		          '</tr>';
		     };
		     $("#plan").html(tr);
		     $('.plan_trbp').on('click',function(){
		    	 self.trbp(this);
		     });
		     $('.plan_bpto').on('click',function(){
		    	 self.bpto(this);
		     });
		     $('.plan_qfup').on('click',function(){
		    	 self.qfup(this);
		     });
		     $('.plan_del').on('click',function (){
		    	 self.del(this);		    	 
		     })
		     $('.plan_save').on('click',function(){
		    	 self.save($(this));
		     });
		     $('.plan-s').change(function(){
		    	 self.change(this)
		     });
		     $('#plan .conten').click(function(){
		    	 self.edit(this)
		     });
		     $('#plan select').mousedown(function(e){
		    	 $('.ami-save').hide();
		    	 $('.ami-del').show();
		    	 var index=$(this).parent().index();
				 if(!self.index){
					 
				 }else{
					 if(self.index!=$(this).parent().parent().index()){
						 if(self.to_com()){
							 self.pop('plan_',function(){
								 $("#plan_").remove();
								 self.save(self.dom);
							 },function(){
								 $("#plan_").remove();
								 $("#modal-backdrop").fadeOut("slow");
								 self.load();
							 });
						 }else{
							 self.dom=$(this).parent().parent().children().find('.ami-save');
							 $(this).parent().parent().children().find('.ami-save').show();
							 $(this).parent().parent().children().find('.ami-del').hide();
							 var td=$(this).parent().parent().children();
						     self.index=$(this).parent().parent().index();
						     MANA_index=$(this).parent().parent().index()-1;
						     td.eq(2).attr('contenteditable','true');
						     td.eq(3).attr('contenteditable','true');
						     td.eq(6).attr('contenteditable','true');
						     $('.planactive').removeClass('planactive');
						     td.last().addClass('planactive');
						     $(this).focus();
						     $(this).click();
						     return;
						 }
				    	 var th = window.event || arguments.callee.caller.arguments[0];
				    	 if (th) {
				    	 th.stopPropagation();
				    	 } else {
				    	 th.cancelBubble = true;
				    	 }
				    	 return false;
					 }
				 }
				 self.dom=$(this).parent().parent().children().find('.ami-save');
				 $(this).parent().parent().children().find('.ami-save').show();
				 $(this).parent().parent().children().find('.ami-del').hide();
				 var td=$(this).parent().parent().children();
			     self.index=$(this).parent().parent().index();
			     MANA_index=$(this).parent().parent().index()-1;
			     td.eq(2).attr('contenteditable','true');
			     td.eq(3).attr('contenteditable','true');
			     td.eq(6).attr('contenteditable','true');
			     $('.planactive').removeClass('planactive');
			     td.last().addClass('planactive');
			     $(this).focus();
			     $(this).click()
		     });
	 },
	 save:function(a){
		 var self=this;
		 var td=a.parent().siblings();
		 var ft;
	     MANA_DATA_obj[MANA_index]['planBuilder.plannum']=removeHTMLTag(td.eq(1).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.planid']=removeHTMLTag(td.eq(2).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.planname']=removeHTMLTag(td.eq(3).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.paymenttype']=td.eq(4).find('select').val();
	     MANA_DATA_obj[MANA_index]['planBuilder.plantype']=td.eq(5).find('select').val();
	     MANA_DATA_obj[MANA_index]['planBuilder.packageid']=removeHTMLTag(td.eq(6).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.source']=td.eq(10).find('select').val();
	     console.log( MANA_DATA_obj[MANA_index],self.data[MANA_index]);
	     for(var y=0;y<MANA_DATA_arr.length;y++){
	    	 var t='planBuilder.'+MANA_DATA_arr[y];
	    	 if(MANA_DATA_obj[MANA_index][t]!=self.data[MANA_index][t]){
	    		 ft=true;
	    		 break;
	    	 }else{
	    		 ft=false;
	    	 }
	     }
	     if(!ft){
	    	 return;
	     }		 
		     self.pop_('_plan_');
			 $("#_plan_").addClass('a-fadeinT');
	            $.ajax({
	      			data:MANA_DATA_obj[MANA_index],
				    dataType:'json',
				    url:'/manager/planbuilderupdate',
				    async:true,
				    type:'post',
				    success:function(obj){				    	
				    	$("#_plan_ .plan-plan").html('save success!');
				    	setTimeout(function(){
				    		$('#_plan_').remove();
				    		$("#modal-backdrop").fadeOut("slow");
				    		self.load();
						     $('#PAGECONTENT').show();
						     $('#PAGECONTENT1').hide();
				    	},1000)
				    	
				    }
	   });
	 },//保存按钮
	 edit:function(a,b){
    	 $('.ami-save').hide();
    	 $('.ami-del').show();
		 var self=this;
		 var td=$(a).parent().children();
		 console.log(this.index,$(a).parent().index())
		 if(!this.index){
			 
		 }else{
			 if(this.index!=$(a).parent().index()){
				 if(self.to_com()){
					 this.pop('plan_',function(){
						 $("#plan_").remove();
						 self.save(self.dom);
					 },function(){
						 $("#plan_").remove();
						 $("#modal-backdrop").fadeOut("slow");
						 self.load();
					 });
				 }else{
				 	 this.edit_=true;
				     this.index=$(a).parent().index();
				     MANA_index=$(a).parent().index()-1;
					 this.dom=$(a).parent().children().find('.ami-save');
					 $(a).parent().children().find('.ami-save').show();
					 $(a).parent().children().find('.ami-del').hide();
				     td.eq(2).attr('contenteditable','true');
				     td.eq(3).attr('contenteditable','true');
				     td.eq(6).attr('contenteditable','true');
				     $('.planactive').removeClass('planactive');
				     td.last().addClass('planactive');
				     $('#PAGECONTENT').show();
				     $('#PAGECONTENT1').hide();
				 }
				 return;
			 }
		 }
		 	 this.edit_=true;
		     this.index=$(a).parent().index();
		     MANA_index=$(a).parent().index()-1;
			 this.dom=$(a).parent().children().find('.ami-save');
			 $(a).parent().children().find('.ami-save').show();
			 $(a).parent().children().find('.ami-del').hide();
		     td.eq(2).attr('contenteditable','true');
		     td.eq(3).attr('contenteditable','true');
		     td.eq(6).attr('contenteditable','true');
		     $('.planactive').removeClass('planactive');
		     td.last().addClass('planactive');
		     $(a).focus()
	 },//需改按钮执行方法
	 cal:function(){
		 $("#modal-backdrop").fadeOut("slow");
		 $(a).parent().parent().fadeOut("slow");
	 },//取消按钮执行方法
	 del:function(a){
		 var self=this;
		 self.del_id={plannum:$(a).attr("plannum")};
	     $("#add_proj_").show();
	     $("#modal-backdrop").show();
	 },//点击删除按钮执行的方法
	 con_ok:function(){
		 var self=this;
            $.ajax({
      			data:self.del_id,
			    dataType:'json',
			    url:'/manager/planbuilderdelete',
			    async:true,
			    type:'post',
			    success:function(obj){
			      self.load();
			     $("#add_proj1").hide();
			     $("#modal-backdrop").hide();
			    }
   });
	 },//确定删除按钮执行方法
	 qfup:function(a){
    	 $('.ami-save').hide();
    	 $('.ami-del').show();
		 var self=this;
		 if(!this.index){
			 
		 }else{
			 if(this.index!=$(a).parent().parent().index()){
				 if(self.to_com()){
					 this.pop('plan_',function(){
						 $("#plan_").remove();
						 self.save(self.dom);
					 },function(){
						 $("#plan_").remove();
						 $("#modal-backdrop").fadeOut("slow");
						 self.load();
					 });
				 }else{
					  var td=$(a).parent().parent().children();
					  $('.planactive').removeClass('planactive');
				      td.last().addClass('planactive');
				      td.children().addClass('plan-fous');
					  this.dom=$(a).parent().parent().children().find('.ami-save');
					  $(a).parent().parent().children().find('.ami-save').show();
					  $(a).parent().parent().children().find('.ami-del').hide();
					  this.index=$(a).parent().parent().index();
					  var index=$(a).parent().parent().index();
					  MANA_this=$(a);
					  MANA_index=$(a).parent().parent().index()-1;
					  Mana_da=true;
					  MANA_ID=$(a).attr('zid');
					  var val=$(a).parent().siblings().eq(5).find('select').val();
					  var url;
					  var title;
					  if(val==1){
					      url='/manager/qfupplan';
					      title= 'QFUP Plan';  		
					   }else{
					      url='/manager/topupvolumn';
					      title= 'Top Up Volumn';      
					   }
					     nav(url,title,'','post','#PAGECONTENT1');
					     $('#PAGECONTENT1').show();
					     $('#PAGECONTENT').hide();
				 }
				 return;
			 }
		 }
		  var td=$(a).parent().parent().children();
		  $('.planactive').removeClass('planactive');
	      td.last().addClass('planactive');
	      td.children().addClass('plan-fous');
		  this.dom=$(a).parent().parent().children().find('.ami-save');
		  $(a).parent().parent().children().find('.ami-save').show();
		  $(a).parent().parent().children().find('.ami-del').hide();
		  this.index=$(a).parent().parent().index();
		  var index=$(a).parent().parent().index();
		  MANA_this=$(a);
		  MANA_index=$(a).parent().parent().index()-1;
		  Mana_da=true;
		  MANA_ID=$(a).attr('zid');
		  var val=$(a).parent().siblings().eq(5).find('select').val();
		  var url;
		  var title;
		  if(val==1){
		      url='/manager/qfupplan';
		      title= 'QFUP Plan';  		
		   }else{
		      url='/manager/topupvolumn';
		      title= 'Top Up Volumn';      
		   }
		     nav(url,title,'','post','#PAGECONTENT1');
		     $('#PAGECONTENT1').show();
		     $('#PAGECONTENT').hide();
	 },//第九个td 点击执行的方法
	 bpto:function(a){
    	 $('.ami-save').hide();
    	 $('.ami-del').show();
		 var self=this;
		 if(this.index==false){
			 
		 }else{
			 if(this.index!=$(a).parent().parent().index()){
				 if(self.to_com()){
					 this.pop('plan_',function(){
						 $("#plan_").remove();
						 self.save(self.dom);
					 },function(){
						 $("#plan_").remove();
						 $("#modal-backdrop").fadeOut("slow");
						 self.load();
					 });
				 }else{
					  var td=$(a).parent().parent().children();
					  $('.planactive').removeClass('planactive');
				      td.last().addClass('planactive');
				      td.children().addClass('plan-fous');
					  this.dom=$(a).parent().parent().children().find('.ami-save');
					  $(a).parent().parent().children().find('.ami-save').show();
					  $(a).parent().parent().children().find('.ami-del').hide();
					  this.index=$(a).parent().parent().index();
					  var index=$(a).parent().parent().index();
					  MANA_this=$(a);
					  MANA_ID=$(a).attr('zid');
					  MANA_index=$(a).parent().parent().index()-1;
					  Mana_da=true;
					  var val=$(a).parent().siblings().eq(5).find('select').val();
					  var url;
					  var title;
					  if (val==='1' || val==='5'){
					      	url='/manager/timebaseplan';
					  	    title='Time Base Plan';
					  }else if(val==='2'){
					      	url='/manager/freeboost';
					  	    title='Free Boost';
					  }else{
					      	url='/manager/paidboost';
					  	    title='Paid Boost';
					  }
					      console.log(url,title,val);
					      nav(url,title,'','post','#PAGECONTENT1');
					      $('#PAGECONTENT1').show();
						     $('#PAGECONTENT').hide();
				 }
				 return;
			 }
		 }
		  var td=$(a).parent().parent().children();
	      td.last().addClass('planactive');
	      td.children().addClass('plan-fous');
		  this.dom=$(a).parent().parent().children().find('.ami-save');
		  $(a).parent().parent().children().find('.ami-save').show();
		  $(a).parent().parent().children().find('.ami-del').hide();
		  this.index=$(a).parent().parent().index();
		  var index=$(a).parent().parent().index();
		  MANA_this=$(a);
		  MANA_ID=$(a).attr('zid');
		  MANA_index=$(a).parent().parent().index()-1;
		  Mana_da=true;
		  var val=$(a).parent().siblings().eq(5).find('select').val();
		  var url;
		  var title;
		  if (val==='1' || val==='5'){
		      	url='/manager/timebaseplan';
		  	    title='Time Base Plan';
		  }else if(val==='2'){
		      	url='/manager/freeboost';
		  	    title='Free Boost';
		  }else{
		      	url='/manager/paidboost';
		  	    title='Paid Boost';
		  }
		      console.log(url,title,val);
		      nav(url,title,'','post','#PAGECONTENT1');
		      $('#PAGECONTENT1').show();
			     $('#PAGECONTENT').hide();
	 },//第八个td点击执行的方法
	 trbp:function(a){
    	 $('.ami-save').hide();
    	 $('.ami-del').show();
		 var self=this;
		 console.log(this.index,$(a).parent().parent().index())
		 if(!this.index){
			 
		 }else{
			 if(this.index!=$(a).parent().parent().index()){
				 if(self.to_com()){
					 this.pop('plan_',function(){
						 $("#plan_").remove();
						 self.save(self.dom);
					 },function(){
						 $("#plan_").remove();
						 $("#modal-backdrop").fadeOut("slow");
						 self.load();
					 });
				 }else{
					  var td=$(a).parent().parent().children();
					 $('.planactive').removeClass('planactive');
				      td.last().addClass('planactive');
				      td.children().addClass('plan-fous');
					  this.dom=$(a).parent().parent().children().find('.ami-save');
					  $(a).parent().parent().children().find('.ami-save').show();
					  $(a).parent().parent().children().find('.ami-del').hide();
					  this.index=$(a).parent().parent().index();
					  var index=$(a).parent().parent().index();
					  MANA_this=$(a);
					  Mana_da=true;
					  MANA_ID=$(a).attr('zid');
					  MANA_index=$(a).parent().parent().index()-1;
					  nav('/manager/tierbasepolicy','Tier Base Policy','','post','#PAGECONTENT1');
					  $('#PAGECONTENT1').show();
					  $('#PAGECONTENT').hide();
				 }
				 
				 return;
			 }
		 }
		  var td=$(a).parent().parent().children();
	      td.last().addClass('planactive');
	      td.children().addClass('plan-fous');
		 this.dom=$(a).parent().parent().children().find('.ami-save');
		  $(a).parent().parent().children().find('.ami-save').show();
		  $(a).parent().parent().children().find('.ami-del').hide();
		 this.index=$(a).parent().parent().index();
		  var index=$(a).parent().parent().index();
		  MANA_this=$(a);
		  Mana_da=true;
		  MANA_ID=$(a).attr('zid');
		  MANA_index=$(a).parent().parent().index()-1;
		  nav('/manager/tierbasepolicy','Tier Base Policy','','post','#PAGECONTENT1');
		  $('#PAGECONTENT1').show();
		  $('#PAGECONTENT').hide();
	 },//第七个td点击执行的方法
	 change:function(a){
		 var td=$(a).parent().siblings();
		 var id=a.value;
		 console.log(td,id);
		 switch (id){
		 case '0':
			 td.eq(6).find('.gaic').show();
			 td.eq(7).find('.gaic').show();
			 td.eq(8).find('.gaic').show();
			 break;
		 case '1':
			 td.eq(6).find('.gaic').hide();
			 td.eq(7).find('.gaic').hide();
			 td.eq(8).find('.gaic').hide();
			 break;
		 case '2':
			 td.eq(6).find('.gaic').show();
			 td.eq(7).find('.gaic').hide();
			 td.eq(7).find('.img-img').hide();
			 td.eq(7).find('span').html('0Mbps');
			 td.eq(8).find('.gaic').show();
			 break;
		 case '3':
			 td.eq(6).find('.gaic').show();
			 td.eq(7).find('.gaic').hide();
			 td.eq(7).find('span').html('0Mbps')
			 td.eq(8).find('.gaic').show();
			 break;
		 case '4':
			 td.eq(6).find('.gaic').show();
			 td.eq(7).find('.gaic').show();
			 td.eq(8).find('.gaic').hide();
			 break;
		 case '5':
			 td.eq(6).find('.gaic').show();
			 td.eq(7).find('.gaic').hide();
			 td.eq(8).find('.gaic').show();
			 break;
		 }//下拉框执行的方法
	 },
	 pop:function(a,b,c){
		 var div='<div id="'+a+'" class="modal " style="">'+
				 '<p>Prompt</p>'+
				 '<hr style="color:#bfbfbf" />'+
				 '<div class="plan-plan">'+
				 'Save before quit?'+'</div><center>'+
				 '<a class="ami_ok" href="#">Save</a>'+
	             '<a class="ami_ca" href="#">Cancel</a></center>'+
	             '</div>';
		 $('body').append(div);
		 $('#'+a).find('a').eq(0).click(b);
		 $('#'+a).find('a').eq(1).click(c);
	     $("#modal-backdrop").fadeIn("slow");
		 $("#"+a).addClass('a-fadeinT');
	 },
	 pop_:function(a){
		 var div='<div id="'+a+'" class="modal" style="margin-top:-150px">'+
		 '<p>Prompt</p>'+
		 '<hr style="color:#bfbfbf" />'+
		 '<div class="plan-plan">'+
		 'Is to save.....</div></div>';
		$('body').append(div);
	 },
	 to_com:function(){
		 var td=this.dom.parent().siblings();
		 var ft;
		 var self=this;
	     MANA_DATA_obj[MANA_index]['planBuilder.plannum']=removeHTMLTag(td.eq(1).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.planid']=removeHTMLTag(td.eq(2).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.planname']=removeHTMLTag(td.eq(3).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.paymenttype']=td.eq(4).find('select').val();
	     MANA_DATA_obj[MANA_index]['planBuilder.plantype']=td.eq(5).find('select').val();
	     MANA_DATA_obj[MANA_index]['planBuilder.packageid']=removeHTMLTag(td.eq(6).html());
	     MANA_DATA_obj[MANA_index]['planBuilder.source']=td.eq(10).find('select').val();
	     console.log( MANA_DATA_obj[MANA_index],self.data[MANA_index],2);
	     for(var y=0;y<MANA_DATA_arr.length;y++){
	    	 var t='planBuilder.'+MANA_DATA_arr[y];
	    	 if(MANA_DATA_obj[MANA_index][t]!=self.data[MANA_index][t]){
	    		 ft=true;
	    		 break;
	    	 }else{
	    		 ft=false;
	    	 }
	     }
	         console.log(ft,12)
	    	 return ft;
	 }
};