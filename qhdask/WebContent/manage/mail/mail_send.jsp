<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<%@ include file="/include/jquery.jsp"%>
<script type="text/javascript">
<!--
window.UMEDITOR_HOME_URL = cxp+"/js/umeditor1_2_2/";
//-->
</script>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<link rel="stylesheet" href="${cxp}/js/data-tables/DT_bootstrap.css" />
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<link href="${cxp}/js/umeditor1_2_2/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${cxp}/js/umeditor1_2_2/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${cxp}/js/umeditor1_2_2/umeditor.min.js"></script>
<script type="text/javascript" src="${cxp}/js/umeditor1_2_2/lang/en/en.js"></script>
<div class="row-fluid">
	<div class="span8">
		<form id="mailForm" method="post" action="sendMail.do">
			<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
				<div id="add" class="ss">
					<div style="margin-left:2px;">
						<div style="margin:5px;">
							<textarea name="receivers" id="receivers" class="m-wrap small {required:true,url:false,messages:{required:'receiver is required'}}" placeholder="Receiver email" style="width:90% !important;height:60px;"></textarea>
							<a class="thickbox" title='Choose user' href="${cxp}/manage/webuser/toChooseUser.do?userRole=0&TB_iframe=true&height=500&width=650"><i class="icon-tag"></i> Choose user</a>
							<span>(use[,]separated)</span>
							<span style='color:red' class='msg'></span>
						</div>
						<div style="margin:5px;">
							<input type="text" name="subject" id="subject" class="m-wrap small {required:true,url:false,messages:{required:'Subject is required'}}" placeholder="Subject" style="width:90% !important;"></input>
							<span style='color:red' class='msg'></span>
						</div>
						<div style="margin:5px;">
							<script type="text/plain" id="myEditor" style="width:70% !important;height:270px;"></script>
							<span style='color:red' class='msg'></span>
						</div>
						<div>
							<button type="submit" class="btn green">Send <i class="icon-ok"></i></button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<script type="text/javascript">
		 tb_init('.thickbox');
		var um = UM.getEditor('myEditor');
		 
		 $(".edui-container").css("z-index","0");
         
		$('#mailForm').ajaxForm({
			beforeSubmit : validateForm,
			clearForm : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				$.unblockUI();
				um.setContent('');
				$("#mailForm")[0].reset();
			},
			error : function(response) {
			}
		});
		function validateForm(){
			var isSuccess=$("#mailForm").validate({
				errorPlacement: function(error, element) {
				    error.appendTo(element.parent().find(".msg"));
				}
			}).form(); //返回是否验证成功
			var content = um.getContent();
			
			if(content == ""){
				$("#myEditor").parent().find(".msg").html("Content is required");
				isSuccess = false;
			}
			
			if(isSuccess){
				$.blockUI({message:"<img src='${cxp}/img/loading.gif'>"});
			}
		    return isSuccess;
		}
		
		function selectUser(users){
			var arr = users.split(",");
			for(var i=0;i<arr.length;i++){
				user=arr[i];
				user_arr = user.split("=");
				uid=user_arr[0];
				username = user_arr[1];
				email=user_arr[2];
				
				if($("#receivers").val().indexOf(","+email+",")>-1){
					continue;
				}
				
				$("#receivers").val($("#receivers").val()+email+" , ");
			}
			
			$("#receivers").parent().find(".msg").text("");
		}
		</script>
	</div>
</div>

