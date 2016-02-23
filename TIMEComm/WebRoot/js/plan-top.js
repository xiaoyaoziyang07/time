PLAN.top={
		 num:0,//当前显示个数
		 new_number:1,//当前页数，
		 del_id:{},//删除的id	
		 load:function(){
			 var c=this.new_number;
			 var self=this;
			  if(c=='add'){
				var data={
				    pageNum:1,
				    pageSize:self.num
				 };
				   $.ajax({
				    data:data,
				    dataType:'json',
				    url:'/manager/topupvolumnlist',
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
				    url:'/manager/topupvolumnlist',
				    async:true,
				    type:'post',
				    success:function(obj){
				        console.log(obj);
				        if(Math.ceil(obj.total/self.num)<c){
				        	self.new_number=Math.ceil(obj.total/self.num);
				        	self.load();
				        }else{
				         self.pro_data(obj.topUpvolumns,c);
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
			  var arr_c=['traffic'];
			   var tr='<tr><th>item ID</th><th>Activation</th><th>Policy ID</th><th>Policy Name</th><th>Top-up(MB)</th><th>Operation</th></tr>';
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
			         '<td><img src="/img/img-sc.png">Quoat=<span>'+a[i].traffic+'MB</span></td>'+
			         '<td><img class="top_edit" src="/img/_edit.png"><img policyid='+a[i].policyid+' src="/img/_dele.png" class="top_del"></td>'+
			         '</tr>';
			    };
			    $("#top").html(tr);
			    $('.top_edit').on('click',function(){
			    	self.edit(this)
			    });
			    $('.top_del').on('click',function(){
			    	self.del(this)
			    });
		 },
		 edit:function(a){
		        var g=$(a).parent().siblings();
		        $("#PID").val(g.eq(2).html());
		        $("#PNAME").val(g.eq(3).html());
		        $("#TPP").val(parseFloat(g.eq(4).find("span").html()));
		     	$("#modal-backdrop").fadeIn("slow",function(){
		     		$("#add_proj").addClass('a-fadeinT')
		     	});			
		 },
		 del:function(a){
		     this.del_id={policyId:$(a).attr("policyid")};		     
		     $("#modal-backdrop").fadeIn('slow',function(){
		    	 $("#add_proj1").addClass('a-fadeinT')
		    	 });
		 }
}