<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("member_session") != null) {
		response.sendRedirect(request.getContextPath() + "/home.do");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝-登录</title>
<%@ include file="/include/tags.jsp"%>
<link href="${cxp}/css/style_web.css" rel="stylesheet" type="text/css" />
<script src="${cxp}/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.tabspanel.js"></script>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.message_cn.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>

</head>

<body>
	<div class="fahter">
		<div class="header"></div>
		<div class="logincon">
			<div class="loginleft">
				<div class="menu">
					<ul>
						<li><a href="#" class="act">用户登录</a></li>
						<li><a href="${cxp}/register.jsp">快速注册</a></li>
					</ul>
				</div>
				<div id="menu_con">
					<form id="signform" action="${cxp}/login.do"
					accept-charset="UTF-8" method="post" onsubmit="return validateForm();">
					<div class="login">
						<c:if test="${param.error=='2'}">
							<div class="errorbox">用户名或密码错误！</div>
						</c:if>
						<div class="slbox">
							<span>用户名：</span>
							<div class="wbkbox">
								<input placeholder="" class="textwidth {required:true}"
							autocorrect="off" autocapitalize="off" type="text"
							name="loginName" id="session_login" />
						<span style='color:red' class='msg pwd'></span>
							</div>
						</div>
						<div class="promptbox" style="display: none;">4-20个字符(字母、数字、下划线不区分大小写)</div>
						<div class="slbox">
							<span>密 码：</span>
							<div class="wbkbox">
								<input
							placeholder="" class="textwidth  {required:true}"
							type="password" name="password" id="session_password" />
						<span style='color:red' class='msg pwd'></span>
							</div>
						</div>
						<div class="slbox">
							<span>验证码：</span>
							<div class="wbkbox">
								<input
							type="text" name="randomCode"
							class="textwidth"
							style="width: 100px; float: left;" /> <em
									style="float: left; margin-left: 10px;"><img
							src="${cxp}/servlet/randomCodeImage?height=36&width=100&fontSize=20"
							onclick="javascript:this.src=this.src+'&'+Math.random();"
							title="看不清楚？点击图片切换"/></em>
							</div>
							
						</div>
						<c:if test="${param.error=='1'}">
								<div class="errorbox">验证码错误！</div>
							</c:if>
						<div class="loginbtn">
							<input type="submit" class="dlbtn" value="立即登录" align="absmiddle" />
							&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="alert('请联系网站管理员重置密码！');" class="link_yellow">忘记密码？</a>
						</div>
					</div>
					</form>
					
				</div>
			</div>

			<div class="loginright">
				<div class="righttext">
					没有帐号？<a href="${cxp}/register.jsp" class="link_yellowblock">立即注册</a>
				</div>
			</div>
		</div>
		<%@ include file="/foot.jsp"%>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$.tabspanel({
				tabobj : '.menu',
				panelobj : '#menu_con'
			});

		});
		function validateForm() {
			var isSuccess = $("#signform").validate({
				errorPlacement : function(error, element) {
					error.appendTo(element.parent().find(".msg"));
				}
			}).form(); //返回是否验证成功

			return isSuccess;
		}
	</script>
</body>
</html>
