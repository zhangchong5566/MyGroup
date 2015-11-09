<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reset password</title>
<%@ include file="/include/jquery.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<style type="text/css">
div.ss div{
	margin:5px auto;
}
</style>
</head>
<body style="padding:20px;">
	<form id="form1" action="resetPassword.do" method="post">
	<input type="hidden" name="id" value="${param.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<div>
						<input type="password" name="password" id="password" class="m-wrap small {required:true,url:false,messages:{required:'Password is required'}}" value="" placeholder="New password" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
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
			window.parent.location.reload();
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