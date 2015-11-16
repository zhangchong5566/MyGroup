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
					action="saveAccount.do" method="post">
				<input type="hidden" name="member.id" value="${member.id}"/>
				<c:if test="${member.role != 2}">
				<div style="width:70%;margin:0px auto;">
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">用户名 *</label><input
							class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50,messages:{required:'用户名不能为空。'}}"
							autocorrect="off" autocapitalize="off" type="text"
							name="member.loginName" value="${member.loginName}" id="session_login" />
						<span style='color:red' class='msg'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">真实姓名</label><input
							class="sign-in__field user-form__field {maxlength:50}"
							autocorrect="off" autocapitalize="off" type="text"
							name="member.trueName"  value="${member.trueName}" id="trueName" />
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">性别</label> <select
							name="member.sex" class="sign-in__field user-form__field">
							<option value="男" ${member.sex=="男"?"selected":""}>男</option>
							<option value="女" ${member.sex=="女"?"selected":""}>女</option>
						</select>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">电子邮箱</label><input
							class="sign-in__field user-form__field {email:true,maxlength:200}"
							autocorrect="off" autocapitalize="off" type="text" name="member.email"
							id="email" value="${member.email}"/>
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">联系电话</label><input
							class="sign-in__field user-form__field {maxlength:100}"
							autocorrect="off" autocapitalize="off" type="text"
							name="member.telephone" id="telephone" value="${member.telephone}"/>
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="session_login">QQ、微信</label><input
							class="sign-in__field user-form__field {maxlength:100}"
							autocorrect="off" autocapitalize="off" type="text"
							name="member.qqWeChart" id="qqWeChart"  value="${member.qqWeChart}"/>
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<div style="margin:auto 0px; text-align:center;">
					<button name="button" type="submit"
						class="user-form__button">保存</button>
					</div>
				</div>
				</c:if>
				<c:if test="${member.role == 2}">
					<table>
						<tr>
							<td style="width:100px;">用户名*：</td>
							<td style="width:220px;">
								<input class="sign-in__field user-form__field {required:true,url:false,minlength:3,maxlength:50}"
										autocorrect="off" autocapitalize="off" type="text"
										name="member.loginName" value="${member.loginName}"  id="session_login" />
								<span style='color:red' class='msg'></span>
							</td>
							<td style="width:80px;">真实姓名*：</td>
							<td>
								<input type="text" name="member.trueName" id="trueName"  value="${member.trueName}" class="sign-in__field user-form__field {required:true,url:false,minlength:1,maxlength:50}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>性别：</td>
							<td>
								<select name="member.sex" class="sign-in__field user-form__field">
									<option value="男" ${member.sex=="男"?"selected":""}>男</option>
									<option value="女" ${member.sex=="女"?"selected":""}>女</option>
								</select>
							</td>
							<td>出生年月</td>
							<td>
								<input name="member.birthdate" id="birthdate" class="Wdate sign-in__field user-form__field" type="text"
								value="<fmt:formatDate value="${member.birthdate}" pattern="yyyy-MM-dd"/>"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>工作单位：</td>
							<td colspan="3">
								<input type="text" name="member.company" id="company" class="sign-in__field user-form__field {maxlength:200}" value="${member.company}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>毕业院校：</td>
							<td>
								<input type="text" name="member.school" id="school" class="sign-in__field user-form__field {maxlength:200}" value="${member.school}"/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>学历：</td>
							<td>
								<input type="text" name="member.education" id="education" class="sign-in__field user-form__field {maxlength:200}" value="${member.education}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>职称：</td>
							<td>
								<input type="text" name="member.jobTitle" id="jobTitle" class="sign-in__field user-form__field {maxlength:200}" value="${member.jobTitle}"/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>专业：</td>
							<td>
								<input type="text" name="member.professional" id="professional" class="sign-in__field user-form__field {maxlength:200}" value="${member.professional}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>现从事专业：</td>
							<td>
								<input type="text" name="member.currProfessional" id="currProfessional" class="sign-in__field user-form__field {maxlength:200}" value="${member.currProfessional}"/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>从事年限：</td>
							<td>
								<input type="text" name="member.currYears" id="currYears" class="sign-in__field user-form__field {maxlength:200}" value="${member.currYears}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>通讯地址：</td>
							<td>
								<input type="text" name="member.address" id="address" class="sign-in__field user-form__field {maxlength:200}" value="${member.address}"/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>Email：</td>
							<td>
								<input type="text" name="member.email" id="email" class="sign-in__field user-form__field {email:true,maxlength:200}" value="${member.email}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td>
								<input type="text" name="member.telephone" id="telephone" class="sign-in__field user-form__field {maxlength:50}" value="${member.telephone}"/>
								<span style='color:red' class='msg'></span>
							</td>
							<td>QQ、微信：</td>
							<td>
								<input type="text" name="member.qqWeChart" id="qqWeChart" class="sign-in__field user-form__field {maxlength:200}" value="${member.qqWeChart}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>专家称号(荣誉）：</td>
							<td colspan="3">
								<input type="text" name="member.expretTitle" id="expretTitle" class="sign-in__field user-form__field {maxlength:200}" value="${member.expretTitle}"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>个人简介：</td>
							<td colspan="3">
								<textarea name="member.personProfile" class="sign-in__field user-form__field {maxlength:2000}" style="height:100px;"><c:out value="${member.personProfile}"/></textarea>
							</td>
						</tr>
						<tr>
							<td>擅长专业领域：</td>
							<td colspan="3">
								<textarea name="member.expertiseArea" class="sign-in__field user-form__field {maxlength:2000}" style="height:100px;"><c:out value="${member.expertiseArea}"/></textarea>
							</td>
						</tr>
						<tr>
							<td>服务情况：</td>
							<td colspan="3">
								<textarea name="member.servicefall" class="sign-in__field user-form__field {maxlength:2000}" style="height:100px;"><c:out value="${member.servicefall}"/></textarea>
							</td>
						</tr>
						<tr>
							<td>
								照片
							</td>
							<td colspan="3">
								<input type="hidden" name="objectId" value="${member.id}"/>
								<iframe src="${cxp }/affix/list.do?objectType=1&objectId=${member.id}&action=update&num=1&img=1" width="100%" height="50px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="auto" ></iframe>
							</td>
						</tr>
					</table>
					<button name="button" type="submit"
						class=" user-form__button">保存</button>
				</c:if>
				
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
