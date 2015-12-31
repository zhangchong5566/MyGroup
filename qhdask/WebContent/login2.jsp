<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if(session.getAttribute("member_session")!=null){
	response.sendRedirect(request.getContextPath()+"/home.do");
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
<script type="text/javascript" 
	src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>

</head>

<body>
	<div class="fahter">
		<div class="header"></div>
		<div class="sign-in">
			<div class="user-form">
				<h1 class="sign-in__title user-form__title nolinks">用户登录</h1>
				<div class="sign-in__sign-up-info user-form__info">
					如果还没有账号，点击这里 <a class="sign-in__sign-up-link user-form__info-link"
						href="${cxp}/register.jsp">注册</a>.
				</div>
				<form id="signform" class="sign-in__form user-form__form" action="${cxp}/login.do"
					accept-charset="UTF-8" method="post" onsubmit="return validateForm();">
					<c:if test="${param.error=='1'}">
						<div style="color: red;font-size:14px;">验证码错误！</div>
					</c:if>
					<c:if test="${param.error=='2'}">
						<div style="color: red;font-size:14px;">用户名或密码错误！</div>
					</c:if>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">用户名</label><input
							placeholder="" class="sign-in__field user-form__field {required:true}"
							autocorrect="off" autocapitalize="off" type="text"
							name="loginName" id="session_login" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_password">密码</label><input
							placeholder="" class="sign-in__field user-form__field  {required:true}"
							type="password" name="password" id="session_password" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">验证码</label> <input
							type="text" name="randomCode"
							class="sign-in__field user-form__field"
							style="width: 100px; float: left;" /> <label style="float: left;"><img
							src="${cxp}/servlet/randomCodeImage?height=36&width=100&fontSize=20"
							onclick="javascript:this.src=this.src+'&'+Math.random();"
							title="看不清楚？点击图片切换"/></label>
					</fieldset>
					<button name="button" type="submit"
						class="sign-in__button user-form__button">登 录 →</button>
				</form>
				<a class="sign-in__forgot-password-link user-form__info-link"
					href="https://tutsplus.com/password_resets/new">忘记密码?</a>
			</div>
		</div>

		<%@ include file="/foot.jsp"%>
	</div>
	<script type="text/javascript">
	function validateForm(){
		var isSuccess=$("#signform").validate({
			errorPlacement: function(error, element) {
			    error.appendTo(element.parent().find(".msg"));
			}
		}).form(); //返回是否验证成功
		
	    return isSuccess;
	}
	</script>
</body>
</html>
