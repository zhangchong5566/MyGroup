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
</style>
</head>
<body style="padding:2px;">
	<form id="form1" action="saveRoleMenu.do" method="post">
	<input type="hidden" name="roleId" value="${role.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" >
		<table class="table table-striped table-hover" id="myTable">
				<tbody>
					<c:forEach items="${mlist}" var="menu">
						<tr>
							<td>
							<c:forEach begin="2" end="${menu.level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
							<span>
								<c:set var="mid" value=",${menu.id},"></c:set>
								<input type="checkbox" name="mid" value="${menu.id}" ${fn:indexOf(menuIds,mid)>-1?"checked":""}/>
							</span>
							${menu.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
			
			$.blockUI({message:"<img src='${cxp}/img/loading.gif'>"});
		    return true;
		}
	</script>
</body>
</html>