<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit user</title>
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
</style>
</head>
<body style="padding:20px;">
	<form id="form1" action="saveSysUser.do" method="post">
	<input type="hidden" name="sysUser.id" value="${sysUser.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<div>
						<input type="text" name="sysUser.loginName" id="loginName" class="m-wrap small {required:true,url:false,messages:{required:'Login name is required'}}" value="${sysUser.loginName}" placeholder="Login name" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<c:if test="${empty sysUser}">
					<div>
						<input type="password" name="sysUser.password" id="password" class="m-wrap small {required:true,url:false,messages:{required:'Password is required'}}" value="" placeholder="Password" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					</c:if>
					<div>
						<input type="text" name="sysUser.trueName" id="trueName" class="m-wrap small" value="${sysUser.trueName}" placeholder="True name" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="sysUser.email" id="email" class="m-wrap small" value="${sysUser.email}" placeholder="Email" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="sysUser.phone" id="phone" class="m-wrap small" value="${sysUser.phone}" placeholder="Phone" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<select name="sysUser.status" id="status">
							<option value="0" ${sysUser.status==0?"selected":""}>Normal</option>
							<option value="1" ${sysUser.status==1?"selected":""}>Freeze</option>
						</select>
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