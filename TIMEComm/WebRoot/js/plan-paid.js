PLAN.paid={
		num:0,//当前显示个数
		new_number:1,//当前页数，
		del_id:{},//删除的id	
		load:function(){
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
				    url:'/manager/boostlist?boosttype=PAID',
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
				    url:'/manager/boostlist?boosttype=PAID',
				    async:true,
				    type:'post',
				    success:function(data){
				        var obj=data.pageBean;
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
					       self.load()
						}
				    });
				        }
				    }
				  });
				   }
		},
		pro_data:function(a,b){
			var self=this;
			  console.log(a,b);
			  var arr_b=['policyname','policyid'];
			  var arr_c=['download','upload'];
			   var tr='<tr><th>item ID</th><th>Activation</th><th>Policy ID</th><th>Policy Name</th><th>Upload Speed</th><th>Download Speed</th><th style="width:155px">Duration</th><th>Claim Time</th><th>Validation</th><th>Operation</th></tr>';
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
			         tr+='<tr>'+
			         '<td>'+num+'</td>'+
			         '<td>'+MANA_img+'</td>'+
			         '<td>'+a[i].policyid+'</td>'+
			         '<td>'+a[i].policyname+'</td>'+
			         '<td><img alt="" src="/img/img-upload.png"><span>'+a[i].download+'Mbps</span></td>'+
			         '<td><img alt="" src="/img/img-download.png"><span>'+a[i].upload+'Mbps</span></td>'+
			         '<td>'+a[i].duration+'</td>'+
			         '<td>'+a[i].claimtime+'</td>'+
			         '<td>'+a[i].validation+'</td>'+
			         '<td><img class="paid_edit" src="/img/_edit.png"><img class="paid_del" policyid='+a[i].policyid+' src="/img/_dele.png"></td>'+
			         '</tr>';
			    };
			    $("#free").html(tr);
			    $('.paid_edit').on('click',function(){
			    	self.edit(this)
			    });
			    $('.paid_del').on('click',function(){
			    	self.del(this)
			    });
		},
		edit:function(a){
	        var inp=$('.bigChartDiv input');
	        var td=$(a).parent().siblings();
	            inp.eq(0).val(td.eq(2).html());
	            inp.eq(1).val(td.eq(3).html());
	            if(parseFloat(td.eq(4).find("span").html())==0.5){
	             	var t1=512;
	             	inp.eq(2).siblings().val("Kbps");
	             	inp.eq(2).attr('readonly','readonly');
	            }else{
	            	var t1=parseInt(td.eq(4).find("span").html());
	            }
	            if(parseFloat(td.eq(5).find("span").html())==0.5){
	           		var t2=512;
	           		inp.eq(3).siblings().val("Kbps");
	           		inp.eq(3).attr('readonly','readonly');
	            }else{
	            	var t2=parseInt(td.eq(5).find("span").html());
	            }                       
	            inp.eq(2).val(t1);
	            inp.eq(3).val(t2);
	            inp.eq(4).val(parseFloat(td.eq(6).html()));
	            inp.eq(5).val(parseFloat(td.eq(7).html()));
	            inp.eq(6).val(parseFloat(td.eq(8).html()));
	     		$("#modal-backdrop").fadeIn("fast",function(){
	     			$("#add_proj").addClass('a-fadeinT');
	     		});				
		},
		del:function(a){
		     del_id={policyid:$(a).attr("policyid")};		     
		     $("#modal-backdrop").fadeIn('slow',function(){
		    	 $("#add_proj1").addClass('a-fadeinT');		    	 
		     });
		}
}