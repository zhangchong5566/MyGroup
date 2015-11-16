<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>后台登录 - 问答宝</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<link href="${cxp}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${cxp}/css/metro.css" rel="stylesheet" />
<link href="${cxp}/css/font-awesome-4.1.0/css/font-awesome.css"
	rel="stylesheet" />
<link href="${cxp}/css/style.css" rel="stylesheet" />
<link href="${cxp}/css/style_responsive.css" rel="stylesheet" />
<link href="${cxp}/css/style_default.css" rel="stylesheet"
	id="style_color" />
<link rel="stylesheet" type="text/css"
	href="${cxp}/js/uniform/css/uniform.default.css" />
<link rel="stylesheet" type="text/css" href="${cxp}/css/admin.main.css" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img
			src="${cxp}/img/logo.png"
			alt="问答宝后台管理" />
	</div>
	<!-- END LOGO -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form action="${cxp}/manage/login.do" method="post">
			<h3 class="form-title">请输入你的帐号和密码</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>Enter any username and passowrd.</span>
			</div>
			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Username</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i> <input class="m-wrap placeholder-no-fix"
							data-val="true" data-val-required="用户名不能为空" id="loginName"
							name="loginName" placeholder="用户名" type="text" value="" style="height:35px;"/>
					</div>
					<span class="help-block"><span
						class="field-validation-valid" data-valmsg-for="loginName"
						data-valmsg-replace="true"></span></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">Password</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i> <input class="m-wrap placeholder-no-fix"
							data-val="true" data-val-required="密码不能为空" id="password"
							name="password" placeholder="密码" type="password" value="" style="height:35px;"/>
					</div>
					<span class="help-block"><span
						class="field-validation-valid" data-valmsg-for="password"
						data-valmsg-replace="true"></span></span>
				</div>
			</div>
			<div class="form-actions">
				<!-- <input id="verifycode" name="verifycode" placeholder="验证码"
					style="width: 80px;height:35px;" type="text" value="" /> <img
					style="cursor: pointer; vertical-align: middle"
					src="" id="VerifyImage"
					title="看不清？点击换一个"
					onclick="javascript:this.src='?r='+Math.random();"> -->
				<button type="submit" class="btn green pull-right">
					登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
				<div>
					<span class="field-validation-${empty message?"valid":"error"}" data-valmsg-for="error"
						data-valmsg-replace="true">${message}</span>
				</div>
			</div>
			<div class="forget-password">
				<h4>忘记密码 ?</h4>
				<p>
					请发送邮件 <a href="javascript:;" class="" id="forget-password">admin@me.com</a>
					提出申请重设密码.
				</p>
			</div>
		</form>
		<!-- END LOGIN FORM -->
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">2015 &copy; ZHC</div>
	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS -->
	<script src="${cxp}/js/jquery/jquery-1.8.3.min.js"></script>
	<script src="${cxp}/js/bootstrap/bootstrap.min.js"></script>
	<script src="${cxp}/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${cxp}/js/app.js"></script>
	<script type="text/javascript"
		src="${cxp}/js/jquery/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="${cxp}/js/jquery/jquery.validate.unobtrusive.min.js"></script>
	
	<script>
		jQuery(document).ready(function() {
			App.initLogin();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
