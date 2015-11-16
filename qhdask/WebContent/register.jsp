<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝-注册</title>
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
				<h1 class="sign-in__title user-form__title nolinks">会员注册</h1>
				<div class="sign-in__sign-up-info user-form__info">
					已有账号，点击这里 <a class="sign-in__sign-up-link user-form__info-link"
						href="${cxp}/login.jsp">登录</a>.
				</div>
				<form id="regform" class="sign-in__form user-form__form"
					action="${cxp}/register.do" method="post">
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">用户名 *</label><input
							class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50,messages:{required:'用户名不能为空。'}}"
							autocorrect="off" autocapitalize="off" type="text"
							name="m.loginName" id="session_login" />
						<span style='color:red' class='msg'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_password">登录密码
							*</label><input class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50}"
							type="password" name="m.password" id="session_password" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_password">再次输入密码
							*</label><input class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50,equalTo:'#session_password',messages:{required:'密码不能为空。',equalTo:'两次密码不一致'}}"
							type="password" name="confirm_password" id="session_password2" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">真实姓名</label><input
							class="sign-in__field user-form__field {maxlength:50}"
							autocorrect="off" autocapitalize="off" type="text"
							name="m.trueName" id="trueName" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">性别</label> <select
							name="m.sex" class="sign-in__field user-form__field">
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">电子邮箱</label><input
							class="sign-in__field user-form__field {email:true,maxlength:200}"
							autocorrect="off" autocapitalize="off" type="text" name="m.email"
							id="email" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">联系电话</label><input
							class="sign-in__field user-form__field {maxlength:100}"
							autocorrect="off" autocapitalize="off" type="text"
							name="m.telephone" id="telephone" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">QQ、微信</label><input
							class="sign-in__field user-form__field {maxlength:100}"
							autocorrect="off" autocapitalize="off" type="text"
							name="m.qqWeChart" id="qqWeChart" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">验证码</label> <input
							type="text" name="randomCode"
							class="sign-in__field user-form__field"
							style="width: 100px; float: left;" /> <label style="float: left;"><img id="radomCode"
							src="${cxp}/servlet/randomCodeImage?height=36&width=100&fontSize=20"
							onclick="javascript:this.src=this.src+'&'+Math.random();"
							title="看不清楚？点击图片切换"/></label>
					</fieldset>
					<button name="button" type="submit"
						class="sign-in__button user-form__button">注 册</button>
				</form>
			</div>
		</div>
		<%@ include file="/foot.jsp"%>
	</div>
	<script type="text/javascript">
		$('#regform').ajaxForm({
			beforeSubmit : validateForm,
			clearForm : false,
			dataType : 'json',
			success : function(data) {
				if(data.message == "Success"){
					alert("恭喜你，注册成功！");
					location.href="${cxp}/login.jsp";
				}else{
					alert(data.message);
					$.unblockUI();
					$("#radomCode").attr("src",$("#radomCode").attr("src")+"&"+Math.random());
				}
			},
			error : function(response) {
			}
		});
		function validateForm(){
			var isSuccess=$("#regform").validate({
				errorPlacement: function(error, element) {
				    error.appendTo(element.parent().find(".msg"));
				}
			}).form(); //返回是否验证成功
			
			if(isSuccess){
				$.blockUI({message:"<img src='${cxp}/img/loading.gif'>"});
			}
		    return isSuccess;
		}
	</script>
</body>
</html>
