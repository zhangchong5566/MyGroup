<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recommend user</title>
<%@ include file="/include/jquery.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<style type="text/css">
div.ss div{
	margin:5px auto;
}
table{
	width:100%;
}
table tr td{
	padding:5px;
}
</style>
</head>
<body style="padding:20px;">
	<form id="form1" action="saveMember.do" method="post">
	<input type="hidden" name="member.id" value="${member.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<table>
						<tr>
							<td style="width:120px;">登录名：</td>
							<td style="width:300px;">
								<input type="text" name="member.loginName" id="loginName" class="m-wrap small {required:true,url:false,messages:{required:'登录不能为空'}}" value="${member.loginName}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
							<td style="width:120px;">姓名：</td>
							<td>
								<input type="text" name="member.trueName" id="trueName" class="m-wrap small" value="${member.trueName}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>性别：</td>
							<td>
								<select name="member.sex">
									<option value="">请选择</option>
									<option value="男" ${member.sex=='男'?'selected':''}>男</option>
									<option value="女" ${member.sex=='女'?'selected':''}>女</option>
								</select>
							</td>
							<td>Email：</td>
							<td>
								<input type="text" name="member.email" id="email" class="m-wrap small" value="${member.email}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td>
								<input type="text" name="member.telephone" id="telephone" class="m-wrap small" value="${member.telephone}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
					</table>
				</div>
			</div>
	</div>
	<div style="height: 50px;"></div>
	<div class="form-actions navbar-fixed-bottom">
		<button type="submit" id="submit" class="btn blue">
			<i class="icon-ok"></i> Save
		</button>
		<span id="submitloading" style="display: none;"><img
			src='${cxp}/img/loading.gif' /></span>
		<button type="button" class="btn" id="cancel">Cancel</button>
	</div>
	</form>
	<script type="text/javascript">
		$('#form1').ajaxForm({
			beforeSubmit : validateForm,
			clearForm : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				window.parent.refresh();
			},
			error : function(response) {
			}
		});
		function validateForm(){
			var isSuccess=$("#form1").validate({
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