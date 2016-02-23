<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form class="form-horizontal"  id="form" onsubmit="return false;">
	  <div class="alert alert-error" id="tip" style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<i class="icon-remove"></i>
										</button>
										<strong>
											<i class="icon-remove"></i>
											操作失败!
										</strong>
										原始密码不正确。
										<br>
									</div>
	  <div class="control-group">
		<label class="control-label" for="form-field-password">原始密码</label>		
		<div class="controls">
		<input class="tooltip-error " type="password"  name="passwd"  id="oldpwd" data-rel="tooltip" onblur="" title="原始密码不正确" data-trigger="focus" id="oldpwd" >
		 <i style="color:#FFFFFF" class=" icon-star" ></i>
		  <span class="help-inline" id="errorOldPwd" style="display:none"></span>
		</div>
	  </div>
	  
	  <div class="control-group">
		<label class="control-label" for="form-field-password">密码</label>		
		<div class="controls">
		  <input type="password" name="newPasswd" id="form-field-password" >
		   <i style="color:#FFFFFF" class=" icon-star" id="errorPasswordStar"></i>
		   <span class="help-inline" id="errorPassword" style="display:none">不得少于6位字符</span>
		</div>
	  </div>
	  <div class="control-group">
		<label class="control-label" for="form-field-renewpassword">重复密码</label>
		<div class="controls">
		  <input type="password" id="form-field-renewpassword" >
		  <i style="color:#FFFFFF" class=" icon-star" ></i>
		  <span class="help-inline" id="errorRenewPassword" style="display:none">你输入的和密码不一样,请重新输入</span>
		</div>
	  </div>
	  <div class="form-actions">
			<button class="btn btn-small btn-info"  onclick="check()"><i class="icon-ok"></i>提交</button>	
	  </div>
 </form>
<script>
$(document).ready(function () {
           /*  $.post("user/updatePwd",function (){
            }); */
});
function check()
{
var passwd =$("[name=passwd]").val();
var newpasswd =$("[name=newPasswd]").val();
if(passwd==''){
alert("旧密码不能为空!");
return false;
}
if(newpasswd==''){
alert("新密码不能为空!");
return false;
}
if(newpasswd!=$("#form-field-renewpassword").val()){
	alert("两次密码不一致!");
return false;
}
var parms ={'passwd':passwd,'newpasswd':newpasswd};
$.post("user/updatePwd",parms,function(result){
if(result.message=='null'||result.message==null){
	alert("修改成功!");
	$("[name=passwd]").val('');
	$("[name=newPasswd]").val('');
	$("#form-field-renewpassword").val('');
}else if(result.message=='修改成功'){
	alert("修改成功!");
	$("[name=passwd]").val('');
	$("[name=newPasswd]").val('');
	$("#form-field-renewpassword").val('');
}else{
	alert(result.message);
}
});
return false;
}

</script>
