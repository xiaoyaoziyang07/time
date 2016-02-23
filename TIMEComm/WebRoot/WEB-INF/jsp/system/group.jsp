<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<div>
 <div class="ami_table_header">
			<span class="ami_table_header_title">业务组列表1</span>
			<span class="ami_table_header_btn">
				<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left"   data-original-title="新增业务组" id="add"><i class="icon-plus"></i></button>
			</span>
		</div>
 
			<table>
				<tr id="colTotal">
				        <td style="cursor: pointer;">序号</td>
						<td style="cursor: pointer;">组名</td>

						<td style="cursor: pointer;">内容</td>

						<td style="cursor: pointer;">时间</td>

						<td style="cursor: pointer;">类型</td>
						<td style="cursor: pointer;">操作</td>

					</tr>
					 <s:iterator value="#list"  id="one"> 
					  <tr>
					  <td width="100px"><s:property value="groupId"/></td>
					  <td width="100px"><s:property value="groupname"/></td>
					  <td width="600px"><s:property value="content"/></td>
					  <td width="200px"> <s:property value="createtime"/></td>
					  <td width="100px"><s:property value="type"/></td>
					  <td><a href="/servicesgroup/del?id=${id}">删除</a></td>
					  <td>
						<div class='btn-group'>	
											
							<button class='btn btn-mini btn-info' onclick="update(${id})" ><i class='icon-edit'></i></button>
							<button class='btn btn-mini btn-danger' onclick="doDelete(${id})"><i class='icon-trash'></i></button>
													
						</div>
					</td>
					  </tr>
					 </s:iterator>
			</table>
</div>
<div id="add_proj" class="modal" style="display:none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body" style="max-height:311px;">
  	  <form method="post" action="/servicesgroup/add">
			<table  id="reportTab"  style="width:300px;height:200px">
				<tr>
					<td width="100">组名:</td>
					<td><input type="text" name="groupname" style="width:250px" />
					</td>
				</tr>
				<tr>
					<td width="100">内容:</td>
					<td><input type="text" name="content" style="width:250px" />
					</td>
				</tr>
				<tr>
					<td width="100">时间:</td>
					<td><input type="text"  name="createtime"   style="width:250px">
					</td>
				</tr>
				<tr>
					<td width="100">类型:</td>
					<td><input type="text" name="type" style="width:250px" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
					<input type="submit"  value="确定"  class="btn btn-info cancel" data-dismiss='modal' />
					<input type="button"  value="取消"  class="btn btn-info cancel" data-dismiss='modal' />
					</td>
				</tr>
			</table>
		</form>
  </div>
</div>
<div class="modal-backdrop fade in" id="modal-backdrop" style="display:none"></div>
<!--添加 Modal 弹出框内容  end -->

<div id="update_proj" class="modal" style="display:none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-body" style="max-height:311px;">
  	  <form method="post" action="/servicesgroup/update">
			<table  id="reportTab"  style="width:300px;height:200px">
			       <tr>
                        <td><input type="hidden" name="id"   value="${id}"/></td>
                    </tr>
                    <tr>
                        <td width="100">组名:</td>
                        <td><input type="text" name="groupname"  value="${groupname }"/></td>
                    </tr>
                    <tr>
                        <td width="100">内容:</td>
                        <td><input type="text" name="content"   value="${content }"/></td>
                    </tr>
                    <tr>
                        <td width="100">时间:</td>
                        <td><input type="text" name="createtime"   value="${createtime }" /></td>
                    </tr>
                     <tr>
                        <td width="100">类型:</td>
                        <td><input type="text" name="type"  value="${type}"/></td>
                    </tr>
                    
				<tr>
					<td></td>
					<td>
					<input type="submit"  value="确定"  class="btn btn-info cancel" data-dismiss='modal' />
					<input type="button"  value="取消"  class="btn btn-info cancel" data-dismiss='modal' />
					</td>
				</tr>
			</table>
		</form>
  	
  </div>
</div>
<div class="modal-backdrop fade in" id="modal-backdrop" style="display:none"></div>
<!--修改Modal 弹出框内容  end -->

<script type="text/javascript" src="js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="js/share7.js"></script>
<script type="text/javascript">
$('.ami_Mask').hide();
    function Delete(id)
{
	
	bootbox.confirm("你确定删除吗?", function(result) {
		if(result) {
			
			$('.ami_Mask').show();
			
			$.get('/servicesgroup/del='+id,function (data){
				
				$('.ami_Mask').hide();
				$('#tr_'+id).remove();
				
				bootbox.alert("	删除成功！！");
			});
		}
	});

}
    
    $('#add').click(function(){
    $('.ami_Mask').show();
		$('#add_proj').show();
	});
	function update(id){
	$('.ami_Mask').show();
	$('#update_proj').show();
	
	}

</script>