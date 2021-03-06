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
	<form id="form1" action="saveJob.do" method="post">
	<input type="hidden" name="job.id" value="${job.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<div>
						<input type="text" name="job.name" id="name" class="m-wrap small {required:true,url:false,messages:{required:'Job name is required'}}" value="${Job.name}" placeholder="Job name" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.className" id="className" class="m-wrap small {required:true,url:false,messages:{required:'Class name is required'}}" value="${job.className}" placeholder="Class name" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.description" id="description" class="m-wrap small" value="${job.description}" placeholder="Description" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.parameters" id="parameters" class="m-wrap small" value="${job.parameters}" placeholder="Parameters" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf1" id="extendf1" class="m-wrap small" value="${job.extendf1}" placeholder="Extendf_1 (Long)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf2" id="extendf2" class="m-wrap small" value="${job.extendf2}" placeholder="Extendf_2 (Long)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf3" id="extendf3" class="m-wrap small" value="${job.extendf3}" placeholder="Extendf_3 (Integer)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf4" id="extendf4" class="m-wrap small" value="${job.extendf4}" placeholder="Extendf_4 (Integer)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf5" id="extendf5" class="m-wrap small" value="${job.extendf5}" placeholder="Extendf_5 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf6" id="extendf6" class="m-wrap small" value="${job.extendf6}" placeholder="Extendf_6 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf7" id="extendf7" class="m-wrap small" value="${job.extendf7}" placeholder="Extendf_7 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="job.extendf8" id="extendf8" class="m-wrap small" value="${job.extendf8}" placeholder="Extendf_8 (String)" style="width:70% !important;"/>
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
				alert(data.result);
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