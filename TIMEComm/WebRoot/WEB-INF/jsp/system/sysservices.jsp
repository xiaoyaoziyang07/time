<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<div>
 <div class="ami_table_header">
			<span class="ami_table_header_title">业务组列表2</span>
			<span class="ami_table_header_btn">
				<button class="btn btn-mini btn-info " data-rel="tooltip" data-placement="left"   data-original-title="新增业务组" id="add"><i class="icon-plus"></i></button>
			</span>
		</div>
 
			<table>
				<tr id="colTotal">
				        <td style="cursor: pointer;">序号</td>
						<td style="cursor: pointer;">服务名</td>

						<td style="cursor: pointer;">内容</td>
                        <td style="cursor: pointer;">类型</td>
						<td style="cursor: pointer;">创建时间</td>
						<td style="cursor: pointer;">服务标志</td>

						<td style="cursor: pointer;">时间</td>

					</tr>
					 <s:iterator value="pageBean"  id="one"> 
					  <tr>
					  <td width="100px"><s:property value="Id"/></td>
					  <td width="100px"><s:property value="name"/></td>
					  <td width="600px"><s:property value="content"/></td>
					  <td width="100px"><s:property value="type"/></td>
					  <td width="200px"> <s:property value="createtime"/></td>
					  <td width="200px"> <s:property value="serversign"/></td>
					  <td width="200px"> <s:property value="time_stamp"/></td>
					  <td width="100px">
						<div class='btn-group'>							
							<button class='btn btn-mini btn-info' onclick="openNewWinow('/servicesgroup/add?method=toEditProtocolGroup&id=${id }&iscancel=1','业务组编辑');"><i class='icon-edit'></i></button>
							<button class='btn btn-mini btn-danger' onclick="doDelete(${id})"><i class='icon-trash'></i></button>						
						</div>
					</td>
					  </tr>
					 </s:iterator>
			</table>
</div>
<div id="ami_newwindow" style="display: none;">

</div>
<div id="ami_add" style="display: none;">
    <form method="post" action="/servicesgroup/del">
			<table style="width:400px;height:200px;margin:0px auto">
				<tr>
					<td>客户编码:</td>
					<td><input type="text" name="id" style="width:200px" />
					</td>
				</tr>
				<tr>
					<td>客户编码:</td>
					<td><input type="text" name="id" style="width:200px" />
					</td>
				</tr>
				<tr>
					<td>客户编码:</td>
					<td><input type="text" name="id" style="width:200px" />
					</td>
				</tr>
			</table>
		</form>
</div>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script> 
<script type="text/javascript" src="js/share7.js"></script>
<script type="text/javascript">
$('.ami_Mask').hide();
    function doDelete(id)
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
		var url =geturl('/servicesgroup/add');
		openNewWinow(url,'新增业务组');
		
	});
	
	/**
 * 在本页面打开新的窗口 支持回退
 */
function openNewWinow(url,title)
{
	var url =geturl(url);
	//主窗口隐错
	$('#ami_main').hide();
	$('#ami_newwindow').show();
	//找到新窗口
	$.get(url,function(data){$('#ami_newwindow').html(data)});
	$('#page_title').html(title);
	$('#page_title_nav').html(title);
}
/**
 * 回退
 */
function goback()
{
	//主窗口隐错
	$('#ami_main').show();
	$('#ami_newwindow').html('');
	$('#ami_newwindow').hide();
}
</script>