<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章管理</title>
<%@ include file="/include/jquery.jsp"%>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="${cxp}/js/My97DatePicker/WdatePicker.js"></script>
<link href="${cxp}/js/umeditor1_2_2/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript" charset="utf-8"
	src="${cxp}/js/umeditor1_2_2/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${cxp}/js/umeditor1_2_2/umeditor.min.js"></script>
<script type="text/javascript"
	src="${cxp}/js/umeditor1_2_2/lang/zh-cn/zh-cn.js"></script>

<style type="text/css">
div.ss div{
	margin:5px auto;
}
table{
	width:100%;
}
table tr td{
	padding:5px;
}
</style>
</head>
<body style="padding:20px;">
	<form id="form1" action="saveArticle.do" method="post">
	<input type="hidden" name="abean.id" value="${abean.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<table>
						<tr>
							<td style="width:120px;">标题：</td>
							<td>
								<input type="text" name="abean.atitle" id="title" class="m-wrap small {required:true,url:false,messages:{required:'标题不能为空'}}" value="${abean.atitle}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td style="width:120px;">副标题：</td>
							<td>
								<input type="text" name="abean.asubtitle" id="asubtitle" class="m-wrap small" value="${abean.asubtitle}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td style="width:120px;">作者：</td>
							<td>
								<input type="text" name="abean.aauthor" id="aauthor" class="m-wrap small" value="${abean.aauthor}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td style="width:120px;">来源：</td>
							<td>
								<input type="text" name="abean.asource" id="asource" class="m-wrap small" value="${abean.asource}" style="width:90% !important;"/>
								<span style='color:red' class='msg'></span>
							</td>
						</tr>
						<tr>
							<td>内容：</td>
							<td colspan="3">
								<script type="text/plain" id="uEditor" style="width:700px;height:500px;"></script>
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
	<div id="content" style="display:none">${abean.acontent}</div>
	<script type="text/javascript">
	if (typeof(toolbar) == "undefined") { 
		var toolbar = new Array('source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
	            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
	            '| justifyleft justifycenter justifyright justifyjustify |',
	            'link unlink | emotion image video ',
	            '| horizontal print preview fullscreen', 'drafts', 'formula');
	}
	
	
		var um = UM.getEditor('uEditor', toolbar);
		um.setContent($("#content").html());
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
			$("#form1").attr("enctype","application/x-www-form-urlencoded");
			$("#form1").attr("encoding","application/x-www-form-urlencoded");
			
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