<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<%@ include file="/include/head.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<!-- BEGIN CONTAINER -->
<div class="page-container row-fluid">
	<%@ include file="/include/menu.jsp"%> 
	<!-- BEGIN PAGE -->
	<div class="page-content">
		<!-- BEGIN PAGE CONTAINER-->
		<div class="container-fluid">
			<%@ include file="/include/page_header.jsp"%>
			<!-- BEGIN PAGE CONTENT-->

				<form id="form1" action="changePwd.do" method="post">
					<div id="cont"
						style="padding: 10px;">
						<div id="add" class="ss">
							<div style="margin-left: 2px;">
								<div>
									<input type="password" name="oldPwd" id="oldPwd"
										class="m-wrap small {required:true,messages:{required:'Old password is required'}}"
										value="" placeholder="Old password"
										style="width: 90% !important;" AUTOCOMPLETE="off"/> <span style='color: red'
										class='msg'></span>
								</div>
								<div>
									<input type="password" name="newPwd" id="newPwd"
										class="m-wrap small {required:true,messages:{required:'New password is required'}}"
										value="" placeholder="New password"
										style="width: 90% !important;" AUTOCOMPLETE="off"/> <span style='color: red'
										class='msg'></span>
								</div>
								<div>
									<input type="password" name="retypePwd" id="retypePwd"
										class="m-wrap small {equalTo:'#newPwd'}" value=""
										placeholder="Retype new password"
										style="width: 90% !important;" AUTOCOMPLETE="off"/> <span style='color: red'
										class='msg'></span>
								</div>
							</div>
						</div>
					</div>
					<div style="height: 50px;"></div>
					<div>
						<button type="submit" id="submit" class="btn blue">
							<i class="icon-ok"></i> Save
						</button>
						<span id="submitloading" style="display: none;"><img
							src='${cxp}/img/loading.gif' /></span>
					</div>
				</form>
				<!-- END PAGE CONTENT-->
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
	<script type="text/javascript">
		$('#form1').ajaxForm({
			beforeSubmit : validateForm,
			clearForm : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				$.unblockUI();
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
<%@ include file="/include/foot.jsp"%>