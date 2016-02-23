PLAN.tier={
	 num:0,//当前显示个数
	 new_number:1,//当前页数，
	 del_id:{},//删除的id
	 load:function(){
		 var self=this;
		 var c=self.new_number;
		   if(c=='add'){
			    var data={
			    pageNum:1,
			    pageSize:self.num
			 };
			    $.ajax({
			    data:data,
			    dataType:'json',
			    url:'/manager/tierbasepolicylist',
			    async:true,
			    type:'post',
			    success:function(obj){
			    	self.new_number=Math.ceil(obj.total/self.num);
			    	self.load();
			    }
			  });
			   }else{
			    var data={
			    pageNum:c,
			    pageSize:self.num
			     };
			    $.ajax({
			    data:data,
			    dataType:'json',
			    url:'/manager/tierbasepolicylist',
			    async:true,
			    type:'post',
			    success:function(obj){
			        console.log(obj);
			        if(Math.ceil(obj.total/self.num)<c){
			        	self.new_number=Math.ceil(obj.total/self.num);
			        	self.load();
			        }else{
			         self.pro_data(obj.tierPolicys,c);
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
		  console.log(a,b);
		   var arr_b=['policyname','policyid']; 
		   var arr_c=['download','upload'];	
		   var tr='<tr><th>item ID</th><th>Activation</th><th>Policy ID</th><th>Policy Name</th><th>Uload Speed</th>+<th>Download Speed</th><th>Operation</th></tr>';
		    for(var i=0;i<a.length;i++){
		      Filter_Data(a[i],arr_b,arr_c);
		      if(Mana_da){
		       if(MANA_ID==a[i].policyid){
		         MANA_img='<img class="MANA MANA-active" val="1" src="/img/blue_d.png" onclick="MANA_(this)">';
		     }else{
		        MANA_img='<img val="0" class="MANA" src="/img/blue_z.png" onclick="MANA_(this)">';
		     }
		      }
		       var num=(b-1)*self.num+i+1;
		         tr+='<tr>'+
		         '<td>'+num+'</td>'+
		         '<td>'+MANA_img+'</td>'+
		         '<td>'+a[i].policyid+'</td>'+
		         '<td>'+a[i].policyname+'</td>'+
		         '<td><img alt="" src="/img/img-upload.png"><span>'+a[i].download+'Mbps</span></td>'+
		         '<td><img alt="" src="/img/img-download.png"><span>'+a[i].upload+'Mbps</span></td>'+
		         '<td><img class="tier_edit" src="/img/_edit.png"><img policyid='+a[i].policyid+' src="/img/_dele.png" class="tier_del"></td>'+
		         '</tr>';
		    };
		    $("#base").html(tr);
		    $('.tier_edit').on('click',function(){
		    	self.edit(this)
		    });
		    $('.tier_del').on('click',function(){
		    	self.del(this)
		    }); 
	 },
	 edit:function(a){
	        var inp=$('.bigChartDiv input');
	        var td=$(a).parent().siblings();
	            inp.eq(0).val(td.eq(2).html());
	            inp.eq(1).val(td.eq(3).html());
	            inp.eq(2).val(parseFloat(td.eq(4).find("span").html()));
	            inp.eq(3).val(parseFloat(td.eq(5).find("span").html()));
	     		$("#modal-backdrop").fadeIn("slow",function(){
	     			$("#add_proj").addClass('a-fadeinT');
	     		}	     		
	     		);
				
	 },
	 del:function(a){
	     this.del_id={policyId:$(a).attr("policyid")};    
	     $("#modal-backdrop").fadeIn('slow',function(){
	    	 $("#add_proj1").addClass('a-fadeinT');
	     });
	 }
}