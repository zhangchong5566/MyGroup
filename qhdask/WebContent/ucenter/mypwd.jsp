<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝-会员中心</title>
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
<script type="text/javascript"
	src="${cxp}/js/My97DatePicker/WdatePicker.js"></script>
<style>
table{
	width:90%;
}
table tr td{
	padding:10px 20px;
	font-size: 14px;
}
</style>
</head>

<body>
	<div class="fahter">
		<div class="header"></div>

		<div class="conmain">
			<div class="conleft">
				<%@ include file="/ucenter/left.jsp"%>
			</div>

			<div class="conright">
				<div class="clear"></div>
				<form id="userform" class="sign-in__form user-form__form"
					action="savePassword.do" method="post">
				<input type="hidden" name="mid" value="${member_session.id}"/>
				<div style="width:70%;margin:0px auto;">
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">旧密码 *</label><input
							class="sign-in__field user-form__field {required:true}"
							autocorrect="off" autocapitalize="off" type="password"
							name="oldPassword" />
						<span style='color:red' class='msg'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">新密码 *</label><input
							class="sign-in__field user-form__field {required:true,minlength:3,maxlength:50}"
							autocorrect="off" autocapitalize="off" type="password"
							name="newPassword" id="newPassword" value="" />
						<span style='color:red' class='msg'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">确认新密码 *</label><input
							class="sign-in__field user-form__field {required:true,minlength:3,maxlength:50,equalTo:'#newPassword',messages:{equalTo:'两次密码输入不一致'}}"
							autocorrect="off" autocapitalize="off" type="password"
							name="confirmPassword" />
						<span style='color:red' class='msg'></span>
					</fieldset>
					<div style="margin:auto 0px; text-align:center;">
					<button name="button" type="submit"
						class="user-form__button">确认修改</button>
					</div>
				</div>
				</form>
			</div>
		</div>
		<div class="clear"></div>
		<%@ include file="/foot.jsp"%>
		<script type="text/javascript">
		$('#userform').ajaxForm({
			beforeSubmit : validateForm,
			clearForm : false,
			dataType : 'json',
			success : function(data) {
				if(data.message == "Success"){
					alert("修改成功！");
					location.reload(true);
				}else{
					alert(data.message);
					$.unblockUI();
				}
			},
			error : function(response) {
			}
		});
		function validateForm(){
			var isSuccess=$("#userform").validate({
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
	</div>
</body>
</html>
