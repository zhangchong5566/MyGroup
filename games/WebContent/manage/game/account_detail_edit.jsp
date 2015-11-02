<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Account</title>
<%@ include file="/include/taglibs.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
</head>
<body style="padding: 20px;">
	<form id="form1" action="saveAccountDetail.do" method="post">
		<input type="hidden" name="detailId" value="${accountDetail.id}">
		<input type="hidden" name="accountId" value="${param.accountId}">
		<input type="hidden" name="adindex" value="${param.adindex}">
		<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin: 5px;">
					(账号密码使用;符号分割，多账号之间使用|符号分割)
					<textarea style="width:98%;height:420px;" name="content"><c:out value="${accountDetail.content}"></c:out></textarea>
				</div>
			</div>
		</div>
		<div class="form-actions navbar-fixed-bottom" >
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
				alert("保存成功！");
				window.parent.tb_remove();
			},
			error : function(response) {
			}
		});
		function validateForm() {
			var isSuccess = $("#form1").validate({
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