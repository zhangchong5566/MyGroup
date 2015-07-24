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
<script language="javascript" type="text/javascript" src="${cxp}/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
div.ss div{
	margin:5px auto;
}
</style>
</head>
<body style="padding:20px;">
	<form id="form1" action="saveTrigger.do" method="post">
	<input type="hidden" name="trigger.id" value="${trigger.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<div>
						<input type="text" name="trigger.name" id="name" class="m-wrap small {required:true,url:false,messages:{required:'trigger name is required'}}" value="${trigger.name}" placeholder="Trigger name" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<select name="qzJobDetailId" id="qzJobDetailId" class="m-wrap small {required:true,url:false,messages:{required:'trigger name is required'}}">
							<option value="">Select Job</option>
							<c:forEach items="${jobList}" var="job">
								<option value="${job.id}" ${job.id==trigger.qzJobDetail.id?"selected":""}>${job.name}</option>
							</c:forEach>
						</select>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.description" id="description" class="m-wrap small" value="${trigger.description}" placeholder="Description" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<select name="trigger.type">
							<option value="0">循环执行</option>
							<option value="1" ${trigger.type==1?"selected":""}>精确定时</option>
						</select>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.startDelay" id="startDelay" class="m-wrap small" value="${trigger.startDelay}" placeholder="Start Delay" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.repeatCount" id="repeatCount" class="m-wrap small" value="${trigger.repeatCount}" placeholder="Repeat Count" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.repeatInterval" id="repeatInterval" class="m-wrap small" value="${trigger.repeatInterval}" placeholder="Repeat Interval" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.cronExpression" id="cronExpression" class="m-wrap small" value="${trigger.cronExpression}" placeholder="CronExpression" style="width:90% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="startDate" id="startDate" class="m-wrap small" value="<fmt:formatDate value="${trigger.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="Start Date"  style="width:150px !important;"/>
						<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,el:'startDate'})" src="${cxp}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="endDate" id="endDate" class="m-wrap small" value="<fmt:formatDate value="${trigger.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="End Date" style="width:150px !important;"/>
						<img onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,el:'endDate'})" src="${cxp}/js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<select name="trigger.status">
							<option value="0">Disable</option>
							<option value="1" ${trigger.status==1?"selected":""}>Enable</option>
						</select>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf1" id="extendf1" class="m-wrap small" value="${trigger.extendf1}" placeholder="Extendf_1 (Long)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf2" id="extendf2" class="m-wrap small" value="${trigger.extendf2}" placeholder="Extendf_2 (Long)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf3" id="extendf3" class="m-wrap small" value="${trigger.extendf3}" placeholder="Extendf_3 (Integer)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf4" id="extendf4" class="m-wrap small" value="${trigger.extendf4}" placeholder="Extendf_4 (Integer)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf5" id="extendf5" class="m-wrap small" value="${trigger.extendf5}" placeholder="Extendf_5 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf6" id="extendf6" class="m-wrap small" value="${trigger.extendf6}" placeholder="Extendf_6 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf7" id="extendf7" class="m-wrap small" value="${trigger.extendf7}" placeholder="Extendf_7 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf8" id="extendf8" class="m-wrap small" value="${trigger.extendf8}" placeholder="Extendf_8 (String)" style="width:70% !important;"/>
						<span style='color:red' class='msg'></span>
					</div>
					<div>
						<input type="text" name="trigger.extendf9" id="extendf9" class="m-wrap small" value="${trigger.extendf9}" placeholder="Extendf_9 (String)" style="width:70% !important;"/>
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