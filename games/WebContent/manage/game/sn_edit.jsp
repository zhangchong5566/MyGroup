<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit</title>
<%@ include file="/include/jquery.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="${cxp}/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
div.ss div {
	margin: 5px auto;
}

table td {
	padding: 3px 5px
}
</style>
</head>
<body style="padding: 20px;">
	<form id="form1" action="saveSN.do" method="post">
		<input type="hidden" name="sn.id" value="${sn.id}">
		<div id="cont"
			class="portlet-body form-horizontal form-bordered form-row-stripped"
			style="padding: 10px;">
			<div id="add" class="ss">
				<div style="margin-left: 2px;">
					<table>
						<tr>
							<td>游戏：</td>
							<td><c:if test="${not empty sn.game}">
									<input type="hidden" name="gameId" value="${sn.game.id}">
								${sn.game.gname}
							</c:if> <c:if test="${empty sn.game}">
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
							<td>手机序列号：</td>
							<td><input type="text" name="sn.serialNumber"
								id="serialNumber"
								class="m-wrap small {required:true,url:false,messages:{required:'手机序列号不能为空！'}}"
								value="${sn.serialNumber}" style="width: 200px !important;" /> <span
								style='color: red' class='msg'></span></td>
						</tr>
						<tr>
							<td>有效期：</td>
							<td><input name="beginDate" id="beginDate" class="Wdate"
								style="width: 150px; height: 25px;" type="text"
								value="<fmt:formatDate value="${sn.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
								至 <input name="endDate" id="endDate" class="Wdate"
								style="width: 150px; height: 25px;" type="text"
								value="<fmt:formatDate value="${sn.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />

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