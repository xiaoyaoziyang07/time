PLAN.qufp={
		 num:0,//当前显示个数
		 new_number:1,//当前页数，
		 del_id:{},//删除的id
		 load:function(){
			 var c=this.new_number;
			 var self=this;
			   if(c=='add'){
				    var data={
				    'queryInfo.currentPage':1,
				    'queryInfo.pageSize':self.num
				 };
				    $.ajax({
				    data:data,
				    dataType:'json',
				    url:'/manager/qfupplanlist',
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
				    url:'/manager/qfupplanlist',
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
				        $("#page_id").pagination({
				        items:obj.totalRecord,
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
			   var arr_b=['policyname','policyid'];
			   var arr_c=['fup1_dl','fup1_ul','fup2_ul','fup2_dl'];
			   var tr='<tr><th>item ID</th><th>Activation</th><th>Policy ID</th><th>Policy Name</th><th>Quota</th><th colspan="2">FUP1</th><th colspan="2">FUP2</th><th>Operation</th></tr>';
			    for(var i=0;i<a.length;i++){
			      if(Mana_da){
			       if(MANA_ID==a[i].policyid){
			         MANA_img='<img class="MANA" val="1" src="/img/blue_d.png" onclick="MANA_(this)">';
			     }else{
			        MANA_img='<img val="0" class="MANA" src="/img/blue_z.png" onclick="MANA_(this)">';
			     }
			      }
			    Filter_Data(a[i],arr_b,arr_c);
			       var num=(b-1)*self.num+i+1;
			       var quota=(a[i].quota<0)?'unlimited':a[i].quota+'G';
			         tr+='<tr>'+
			         '<td>'+num+'</td>'+
			         '<td>'+MANA_img+'</td>'+
			         '<td title="'+a[i].policyid+'">'+a[i].policyid+'</td>'+
			         '<td title="'+a[i].policyname+'">'+a[i].policyname+'</td>'+
			         '<td title="'+a[i].quota+'"><img src="/img/img-sc.png" alt=""><span>'+quota+'</span></td>'+
			         '<td title="'+a[i].fup1_percent+'" valign="top"><span>Percent</span><p>'+a[i].fup1_percent+'%</p><div><div class="ani-data" data-width="'+a[i].fup1_percent+'%"></div></div></td>'+
			         '<td data-ul="'+a[i].fup1_ul+'" data-dl="'+a[i].fup1_dl+'" valign="top"><span>Action</span>'+
			         	'<div><img alt="" src="/img/img-sms-s.png" /><img alt="" src="/img/img-window-s.png" /></div>'+     
			         	'<div><img src="/img/img-upload.png"><span>'+a[i].fup1_ul+'Mbps</span></div>'+     
			         	'<div><img src="/img/img-download.png"><span>'+a[i].fup1_dl+'Mbps</span></div> </td>'+
			         '<td title="'+a[i].fup2_percent+'" valign="top"><span>Percent</span><p>'+a[i].fup2_percent+'%</p><div><div class="ani-data" data-width="'+a[i].fup2_percent+'%"></div></div></td>'+
			         '<td data-ul="'+a[i].fup1_ul+'" data-dl="'+a[i].fup1_dl+'" valign="top"><span>Action</span>'+
			         	'<div><img alt="" src="/img/img-sms-s.png" /><img alt="" src="/img/img-window-s.png" /></div>'+     
			         	'<div><img src="/img/img-upload.png"><span>'+a[i].fup2_ul+'Mbps</span></div>'+     
			         	'<div><img src="/img/img-download.png"><span>'+a[i].fup2_dl+'Mbps</span></div> </td>'+
			         '<td><img class="qfup_edit" src="/img/_edit.png"><img class="qfup_del" policyid='+a[i].policyid+' src="/img/_dele.png"></td>'+
			         '</tr>';
			    };
			    $("#qfup").html(tr); 
			    setTimeout(function(){
				    var did=$('.ani-data');
				    for(var p=0;p<did.length;p++){
				    	console.log(did.eq(p),p);
				    	var width=did.eq(p).attr("data-width");
				    		did.eq(p).animate({'width':width},'3000');
				    };
			    },'800')
			    $('.qfup_edit').on('click',function(){
			    	self.edit(this);
			    });
			    $('.qfup_del').on('click',function(){
			    	self.del(this);
			    });
		 },
		 edit:function(a){
				var sub=$(a).parent().siblings();
				var dv=$(".bigChartDiv tr"); 
		        $("#modal-backdrop").fadeIn('fast',function(){
		        	$("#add_proj").addClass('a-fadeinT');
		        });		
		        var quo=sub.eq(4).attr("title");
		        if(quo<0){
		        	dv.eq(2).find("input").val('');
		        	$('.free_number select').val(-1);
		        	dv.eq(2).find("input").attr('readonly','readonly');
		        }else{
		    		dv.eq(2).find("input").val(sub.eq(4).attr("title"));
		    		$('.free_number select').val(0);
		    		dv.eq(2).find("input").removeAttr('readonly');
		        }
				dv.eq(0).find("input").val(sub.eq(2).attr("title"));
				dv.eq(1).find("input").val(sub.eq(3).attr("title"));
			    dv.eq(4).find("input").val(sub.eq(6).attr("data-ul"));	
			    dv.eq(5).find("input").val(sub.eq(6).attr("data-dl"));			
			    dv.eq(7).find("input").val(sub.eq(8).attr("data-ul"));		
			    dv.eq(8).find("input").val(sub.eq(8).attr("data-dl"));	
			 	var s1=sub.eq(5).attr("title"),s2=sub.eq(7).attr("title");
			    mySlider.slider('setValue',parseInt(s1));
			    mySlider2.slider('setValue',parseInt(s2)); 
		 },
		 del:function(a){
		     this.del_id={policyid:$(a).attr("policyid")};		     
		     $("#modal-backdrop").fadeIn('fast',function(){
		    	 $("#add_proj1").addClass('a-fadeinT');
		     });
		 }
}