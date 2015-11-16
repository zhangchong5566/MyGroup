<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝-专家注册</title>
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
.user-form table{
	width:80%;
}
.user-form table tr td{
	padding:10px;
	font-size: 14px;
}
</style>
</head>

<body>
	<div class="fahter">
		<div class="header"></div>
		<div class="sign-in">
			<div class="user-form" style="max-width:100%;">
				<h1 class="sign-in__title user-form__title nolinks">专家注册</h1>
				<div class="sign-in__sign-up-info user-form__info">
					已有账号，点击这里 <a class="sign-in__sign-up-link user-form__info-link"
						href="${cxp}/login.jsp">登录</a>.
				</div>
				<form id="regform" class="sign-in__form user-form__form"
					action="${cxp}/register_expert.do" method="post">
					<table>
						<tr>
							<td style="width:120px;">用户名*：</td>
							<td style="width:250px;">
								<input class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50}"
										autocorrect="off" autocapitalize="off" type="text"
										name="m.loginName" id="session_login" />
								<span style='color:red' class='msg'></span>
							</td>
							<td style="width:120px;">真实姓名*：</td>
							<td>
								<input type="text" name="m.trueName" id="trueName" class="sign-in__field user-form__field {required:true,url:false,minlength:1,maxlength:50}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>登录密码*：</td>
							<td>
								<input class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50,messages:{required:'密码不能为空。'}}"
									type="password" name="m.password" id="session_password" />
								<span style='color:red' class='msg'></span>
							</td>
							<td>再次输入密码*：</td>
							<td>
								<input class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50,equalTo:'#session_password',messages:{required:'密码不能为空。',equalTo:'两次密码不一致'}}"
							type="password" name="confirm_password" id="session_password2" />
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>性别：</td>
							<td>
								<select name="m.sex" class="sign-in__field user-form__field">
									<option value="男">男</option>
									<option value="女">女</option>
								</select>
							</td>
							<td>出生年月</td>
							<td>
								<input name="m.birthdate" id="birthdate" class="Wdate sign-in__field user-form__field" type="text"
								value=""
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>工作单位：</td>
							<td colspan="3">
								<input type="text" name="m.company" id="company" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>毕业院校：</td>
							<td>
								<input type="text" name="m.school" id="school" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>学历：</td>
							<td>
								<input type="text" name="m.education" id="education" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>职称：</td>
							<td>
								<input type="text" name="m.jobTitle" id="jobTitle" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>专业：</td>
							<td>
								<input type="text" name="m.professional" id="professional" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>现从事专业：</td>
							<td>
								<input type="text" name="m.currProfessional" id="currProfessional" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>从事年限：</td>
							<td>
								<input type="text" name="m.currYears" id="currYears" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>通讯地址：</td>
							<td>
								<input type="text" name="m.address" id="address" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>Email：</td>
							<td>
								<input type="text" name="m.email" id="email" class="sign-in__field user-form__field {email:true,maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td>
								<input type="text" name="m.telephone" id="telephone" class="sign-in__field user-form__field {maxlength:50}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>QQ、微信：</td>
							<td>
								<input type="text" name="m.qqWeChart" id="qqWeChart" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>专家称号(荣誉）：</td>
							<td colspan="3">
								<input type="text" name="m.expretTitle" id="expretTitle" class="sign-in__field user-form__field {maxlength:200}" value=""/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>个人简介：</td>
							<td colspan="3">
								<textarea name="m.personProfile" class="sign-in__field user-form__field {maxlength:2000}" style="height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td>擅长专业领域：</td>
							<td colspan="3">
								<textarea name="m.expertiseArea" class="sign-in__field user-form__field {maxlength:2000}" style="height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td>服务情况：</td>
							<td colspan="3">
								<textarea name="m.servicefall" class="sign-in__field user-form__field {maxlength:2000}" style="height:100px;"></textarea>
							</td>
						</tr>
						<tr>
							<td>
								照片
							</td>
							<td colspan="3">
								<c:set var="uuid" value="<%=UUID.randomUUID().toString()%>"/>
								<input type="hidden" name="objectId" value="${uuid}">
								<iframe src="${cxp }/affix/list.do?objectType=1&objectId=${uuid}&action=update&num=1&img=1" width="100%" height="50px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="auto" ></iframe>
							</td>
						</tr>
						<tr>
							<td>验证码</td>
							<td>
								 <input
									type="text" name="randomCode"
									class="sign-in__field user-form__field"
									style="width: 100px; float: left;" /> <label style="float: left;"><img id="radomCode"
									src="${cxp}/servlet/randomCodeImage?height=36&width=100&fontSize=20"
									onclick="javascript:this.src=this.src+'&'+Math.random();"
									title="看不清楚？点击图片切换"></img></label>
							</td>
						</tr>
					</table>
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
