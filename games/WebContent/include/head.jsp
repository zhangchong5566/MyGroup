<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>脚本授权系统</title>
	<script>
	var cxp = "${cxp}";
</script>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<link href="${cxp}/css/style_default.css" rel="stylesheet" id="style_color" />
	<%@ include file="/include/taglibs.jsp"%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">

	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
	<div class="container-fluid">
		<!-- BEGIN LOGO -->
		<a class="brand" href="index.html">
		<img src="${cxp}/img/logo.png" alt="" style="height:32px"/>
		</a>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
		<img src="${cxp}/img/menu-toggler.png" alt="" />
		</a>          
		<!-- END RESPONSIVE MENU TOGGLER -->				
		<!-- BEGIN TOP NAVIGATION MENU -->					
		<ul class="nav pull-right">
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
				<img alt="" src="${cxp}/img/avatar.gif" style="height:29px;" />
				<span class="username">${sysuser_session.trueName}</span>
				<i class="icon-angle-down"></i>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="icon-user"></i> 我的信息</a></li>
					<li class="divider"></li>
                    <li><a href="${cxp}/sys/toChangePwd.do" title="修改密码"><i class="icon-key"></i> 修改密码</a></li>
                    <li><a href="${cxp}/loginOut.do"><i class="icon-signout"></i> 退出系统</a></li>
				</ul>
			</li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->	
	</div>
</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->