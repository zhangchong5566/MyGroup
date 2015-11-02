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
<style type="text/css">
div.ss div {
	margin: 5px auto;
}

table tr {
	height: 48px
}
td span a {
	margin: 5px;width:50px;
}

textarea {
	width: 98%;
	height: 200px;
}
</style>
</head>
<body style="padding: 20px;">
	<form id="form1" action="saveAccount.do" method="post">
		<input type="hidden" name="account.id" value="${account.id}">
		<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin: 20px;">
					<table style="width: 80%" id="table_a">
						<tr>
							<td width="200px">游戏：</td>
							<td><c:if test="${not empty account.game}">
									<input type="hidden" name="gameId" value="${account.game.id}">
								${account.game.gname}
							</c:if> <c:if test="${empty account.game}">
									<select name="gameId"
										class="m-wrap small {required:true,url:false,messages:{required:'Game is required'}}">
										<option value="">--选择--</option>
										<c:forEach items="${gameList}" var="game">
											<option value="${game.id}">${game.gname}</option>
										</c:forEach>
									</select>
									<span style='color: red' class='msg'></span>
								</c:if></td>
						</tr>
						<tr>
							<td>用户标识：</td>
							<td><input type="text" name="account.userTag" id="userTag"
								class="m-wrap small {required:true,url:false,messages:{required:'用户标识不能为空！'}}"
								value="${account.userTag}" style="width: 200px !important;" />
								<span style='color: red' class='msg'></span></td>
						</tr>
						<tr>
							<td>当前账号序号：</td>
							<td><input type="text" name="account.currIndex" id="currIndex"
								class="m-wrap small"
								value="${account.currIndex}" style="width: 200px !important;" />
								<span style='color: red' class='msg'></span></td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><input type="text" name="account.remark" id="remark"
								class="m-wrap small {required:false,url:false}"
								value="${account.remark}" style="width: 97% !important;" /> <span
								style='color: red' class='msg'></span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div style="height: 20px;"></div>
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
				alert(data.result);
				window.parent.refresh();
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