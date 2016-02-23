<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>Dynamic Policy Management System</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="chrome=1"> 
	<!-- basic styles -->
	<link href="/css/bootstrap.min.css" rel="stylesheet" />
	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="/css/font-awesome.min.css" />
	<link rel="stylesheet" href="/css/ace.min.css" />
	<link rel="stylesheet" href="/css/ace-responsive.min.css" />

	<link rel="shortcut icon" href="/img/favicon.png" />
	<script type="text/javascript" src="/js/public/Validation.js"></script>
</head>
<body class="login-layout">
	<div class="container-fluid" id="main-container">
		<div id="main-content">
			<div class="row-fluid">
				<div class="span12">
					<div class="login-container">
						<div class="row-fluid">
							<div class="center" style='margin-bottom: 32px;margin-top: 28px; margin-left: -30px'>
								<h1>
									<img src="/img/loginlo.png"/>
								</h1>
								<h4 class="blue" style="font-size:14px; font-family:Arial, Helvetica, sans-serif;">
								
								</h4>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="row-fluid">
							<div class="position-relative">
								<div id="login-box" class="visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header lighter bigger">
												
												<img src="/img/user_def.png" />
												<%-- <s:text name="ui.login.title" /> --%>
											</h4>
											<!-- <div class="space-6"></div> -->
											<form  method="post" onsubmit="return check()">
												<fieldset>
													<span class="block input-icon input-icon-left span8" style="margin-bottom:15px;margin-left:55px;"> 
													<input type="text" class="" placeholder="<s:text name="ui.login.username" />" id="account" name="account" />
														<i class="icon-userM"></i>
														<!-- <a style="position: absolute; border:1px solid #ccc;border-width:0 1px 1px 0; -webkit-transform:rotate(75deg) translate(-50%, 0); width:10px; height: 10px; left:85px;"></a> -->
													</span> 
													<span class="block input-icon input-icon-left span8 "style="margin-left:55px;"> 
													<input type="password" class="" placeholder="<s:text name="ui.login.password"/>" id="passwd" name="passwd" />
													<i class="icon-lockM"></i>
													</span>
													<span class="span8" id="sp_msg"  style="margin-left: 54px;">
														${message}
													</span>

													<span class="span8">
														<div class="row-fluid">
															<span class="span4" style="margin-left: 47px; margin-top: 10px;width:113.914894%">
																<%-- <label class="span6">
																	<input type="checkbox" class="ace-checkbox-1">
																	<span class="lbl" style="font-size:12px">Remember Me</span>
																</label> --%>
																<button class="btn-login btn-small btn-grad pull-left" >
																<!-- <i class="icon-key"></i> --><s:text name="ui.login.loginbutton"/>
																</button>
																<span class="posSpan">
																	<a href="javascript:;" class="btn-small btn-grad  btn-gradsmall pull-left"></a>
																	<span>Yet opened</span>
																</span>
																
															</span>

														</div>
													</span>
													
												</fieldset>
											</form>
										</div>
										<div class="row-fluid login_way">
											<div class="login_header">
												<span class="loBorder" style="left:6px"></span>
												<span class="loWords">Other ways of landing</span>
												<span class="loBorder" style="right:6px"></span>
											</div>
											
											<div class="login_icon">
												<a class="twitter" href="javascript:;"></a>
												<a class="fbook" href="javascript:;"></a>
											</div>
										</div>
										<!--/widget-main-->
										
									</div>
									<!--/widget-body-->
									<div class="toolbar clearfix">
										<div class="logbar">
											Not a registered user yet?  <a href='javascript:;'>Sign up now!</a>
										</div>
									</div>
								</div>
								<!--/login-box-->
							</div>
							<!--/position-relative-->
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		
	<!-- <div class="arrowTop"></div>
	<div class="arrowDown"></div> -->
	</div>
		
	<!--/.fluid-container-->
	<!-- basic scripts -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='/js/jquery-1.9.1.min.js'>\x3C/script>");
	</script>
	<script type="text/javascript">
		function show_box(id) {
			$('.widget-box.visible').removeClass('visible');
			$('#' + id).addClass('visible');
		}
		function check() {
			var account = $('#account').val();
			var passwd = $('#passwd').val();
			var errMsg = null;
			if (isNull(account)) {
				errMsg = "<s:text name="ui.login.error.usernameisnull"/>";
				}
			else if (isNull(passwd)) {
				errMsg = "<s:text name="ui.login.error.passwordisnull"/>";
				}
			if (errMsg != null) {
				$('#sp_msg').html(errMsg);
					return false;
				}
		}
		$(function(){
			$('.btn-gradsmall').hover(function(){
				$(this).next('span').show();
			},function(){
				$(this).next('span').hide();
			});
		});
	</script>
</body>
</html>
