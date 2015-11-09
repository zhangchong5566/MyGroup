<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit user roles</title>
<%@ include file="/include/jquery.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.main.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" href="${cxp}/js/jquery/DataTables/css/jquery.dataTables.min.css">
<script src="${cxp}/js/bootstrap/bootstrap.min.js"></script>
<script src="${cxp}/js/jquery/DataTables/js/jquery.dataTables.min.js"></script>
</head>
<body style="padding: 20px;">
	<form action="saveRoleToPopedom.do" id="form1" method="post">
		<input type="hidden" name="role.id" value="${role.id}">
		<div id="cont"
			class="portlet-body form-horizontal form-bordered form-row-stripped"
			style="padding: 10px;">
			<div id="add">
				<table class="table table-striped table-hover" id="myTable">
					<thead>
						<tr>
							<th style="width: 8px;"><input type="checkbox" id="checkall"
								class="group-checkable" /></th>
							<th>Describe</th>
							<th>Popedom code</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<div style="height: 50px;"></div>
		<div class="form-actions navbar-fixed-bottom">
			<button type="button" id="submit" class="btn blue" onclick="submitForm()">
				<i class="icon-ok"></i> Save
			</button>
			<span id="submitloading" style="display: none;"><img
				src='${cxp}/img/loading.gif' /></span>
			<button type="button" class="btn" id="cancel">Cancel</button>
		</div>
	</form>
	<script type="text/javascript">
		//$("#navigation").hide();
		var pids = ",";
		<c:forEach items="${popedomSet}" var="bean">
		pids += "${bean.id},";
		</c:forEach>

		myTable = $('#myTable')
				.dataTable(
						{
							"bServerSide" : true,
							"sAjaxSource" : "${cxp}/sys/listPopedom.do", //mvc后台ajax调用接口。
							"sServerMethod" : "POST",
							"ordering" : false,
							'bPaginate' : true, //是否分页。
							"bProcessing" : true, //当datatable获取数据时候是否显示正在处理提示信息。
							'bFilter' : false, //是否使用内置的过滤功能。
							'bLengthChange' : true, //是否允许用户自定义每页显示条数。
							"lengthMenu" : [ 5, 10, 25, 50 ],
							'sPaginationType' : 'full_numbers', //分页样式
							"sDom" : '<"top"f>rt<"bottom"flip><"clear">',
							"fnDrawCallback" : function(oSettings, json) {
							},
							"sAjaxDataProp" : "list",
							"aoColumns" : [
									{
										"mData" : "id",
										"bSortable" : false,
										"mRender" : function(data, type, full) {
											var chk = "";
											if (pids.indexOf("," + data + ",") > -1) {
												chk = "checked";
											}
											return "<input type='checkbox' class='checkboxes' name='ids' value='"+data+"' "+chk+"/>";
										}
									}, {
										"mData" : "description",
										"bSortable" : false
									}, {
										"mData" : "code",
										"bSortable" : false
									} ]
						});
		
		function submitForm(){
			$('#form1').ajaxSubmit({
				clearForm : false,
				dataType : 'json',
				success : function(data) {
					alert(data.message);
					window.parent.location.reload();
				},
				error : function(response) {
				}
			});
		}
		$("#checkall").click(function () {
		    var ischecked = this.checked;
		    $("input:checkbox[name='ids']").each(function () {
		        this.checked = ischecked;
		    });

		});
	</script>
</body>
</html>