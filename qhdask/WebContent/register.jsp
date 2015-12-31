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
						<li><a href="${cxp}/login.jsp">用户登录</a></li>
						<li><a href="#" class="act">快速注册</a></li>
					</ul>
				</div>
				<form id="regform" class="sign-in__form user-form__form"
					action="${cxp}/register.do" method="post">
					<div id="menu_con">
						<div class="login">

							<div class="slbox">
								<span>用户名：</span>
								<div class="wbkbox">
									<input
										class="textwidth {required:true,url:false,minlength:3,maxlength:50,messages:{required:'用户名不能为空。'}}"
										autocorrect="off" autocapitalize="off" type="text"
										name="m.loginName" id="session_login" /> <span
										style='color: red' class='msg'></span>
								</div>
							</div>
							<div class="slbox">
								<span>登录密码 ：</span>
								<div class="wbkbox">
									<input
										class="textwidth {required:true,url:false,minlength:3,maxlength:50}"
										type="password" name="m.password" id="session_password" />
								</div>
							</div>
							<div class="slbox">
								<span>再次输入密码 ：</span>
								<div class="wbkbox">
									<input
										class="textwidth {required:true,url:false,minlength:3,maxlength:50,equalTo:'#session_password',messages:{required:'密码不能为空。',equalTo:'两次密码不一致'}}"
										type="password" name="confirm_password" id="session_password2" />
								</div>
							</div>
							<div class="slbox">
								<span>真实姓名 ：</span>
								<div class="wbkbox">
									<input class="textwidth {maxlength:50}" autocorrect="off"
										autocapitalize="off" type="text" name="m.trueName"
										id="trueName" />
								</div>
							</div>
							<div class="slbox">
								<span>性别 ：</span>
								<div class="wbkbox">
									<input type="radio" name="m.sex" value="男" id="sex1"
										style="border: none; float: left;" /> <em
										style="line-height: 35px; float: left; margin-left: 7px; font-size: 14px;">男</em>
									<input type="radio" name="m.sex" value="女" id="sex2"
										style="border: none; margin-left: 15px; float: left;"
										align="absmiddle" /> <em
										style="line-height: 35px; float: left; margin-left: 7px; font-size: 14px;">女</em>

								</div>
							</div>
							<div class="promptbox">输入您常用的E-mail,以便忘记密码时好找回</div>
							<div class="slbox">
								<span>电子邮箱 ：</span>
								<div class="wbkbox">
									<input class="textwidth {email:true,maxlength:200}"
										autocorrect="off" autocapitalize="off" type="text"
										name="m.email" id="email" />
								</div>
							</div>
							<div class="slbox">
								<span>联系电话 ：</span>
								<div class="wbkbox">
									<input class="textwidth {maxlength:100}" autocorrect="off"
										autocapitalize="off" type="text" name="m.telephone"
										id="telephone" />
								</div>
							</div>
							<div class="slbox">
								<span>QQ、微信：</span>
								<div class="wbkbox">
									<input class="textwidth {maxlength:100}" autocorrect="off"
										autocapitalize="off" type="text" name="m.qqWeChart"
										id="qqWeChart" />
								</div>
							</div>
							<div class="slbox">
								<span>验证码：</span>
								<div class="wbkbox">
									<input type="text" name="randomCode" class="textwidth"
										style="width: 100px; float: left;" /> <em
										style="float: left; margin-left: 10px;"><img
										id="radomCode"
										src="${cxp}/servlet/randomCodeImage?height=36&width=100&fontSize=20"
										onclick="javascript:this.src=this.src+'&'+Math.random();"
										title="看不清楚？点击图片切换" /></em>
								</div>
							</div>

							<div class="loginbtn">
								<input type="submit" class="dlbtn" value="立即注册"
									align="absmiddle" />
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="loginright">
				<div class="righttext">
					已注册帐号？<a href="${cxp}/login.jsp" class="link_yellowblock">立即登录</a>
				</div>
			</div>
		</div>
		<%@ include file="/foot.jsp"%>
	</div>
	<script type="text/javascript">
		$('#regform').ajaxForm(
				{
					beforeSubmit : validateForm,
					clearForm : false,
					dataType : 'json',
					success : function(data) {
						if (data.message == "Success") {
							alert("恭喜你，注册成功！");
							location.href = "${cxp}/login.jsp";
						} else {
							alert(data.message);
							$.unblockUI();
							$("#radomCode").attr(
									"src",
									$("#radomCode").attr("src") + "&"
											+ Math.random());
						}
					},
					error : function(response) {
					}
				});
		function validateForm() {
			var isSuccess = $("#regform").validate({
				errorPlacement : function(error, element) {
					error.appendTo(element.parent().find(".msg"));
				}
			}).form(); //返回是否验证成功

			if (isSuccess) {
				$.blockUI({
					message : "<img src='${cxp}/img/loading.gif'>"
				});
			}
			return isSuccess;
		}
	</script>
</body>
</html>
