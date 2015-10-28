<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Account</title>
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
table tr {
height:48px
}
textarea{
width:98%;height:200px;
}
</style>
</head>
<body style="padding:20px;">
	<form id="form1" action="saveAccount.do" method="post">
	<input type="hidden" name="account.id" value="${account.id}">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin:20px;">
					<table style="width:950px" id="table_a">
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
							<td><input type="text" name="account.userTag"
								id="userTag"
								class="m-wrap small {required:true,url:false,messages:{required:'用户标识不能为空！'}}"
								value="${account.userTag}" style="width: 200px !important;" /> <span
								style='color: red' class='msg'></span></td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><input type="text" name="account.remark"
								id="remark"
								class="m-wrap small {required:false,url:false}"
								value="${account.remark}" style="width:97% !important;"/> <span
								style='color: red' class='msg'></span></td>
						</tr>
						<tr>
							<td colspan="2" style="font-size:16px;font-weight:bold;">
							账号内容设置，前面的数字代表第几台手机<br/>
							(账号密码使用;符号分割，多账号之间使用|符号分割)
							</td>
						</tr>
						<c:set var="maxNum" value="10"></c:set>
						<c:if test="${empty detailList}">
							<c:forEach var="n" varStatus="index" begin="1" end="10" > 
								<tr>
									<td>
										<c:out value="${n}"></c:out>
									</td>
									<td>
										<textarea name="content_${n}"></textarea>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:forEach items="${detailList}" var="detail">
							<tr>
								<td>
									<c:out value="${detail.adindex}"></c:out>
								</td>
								<td>
									<textarea name="content_${detail.adindex}">${detail.content}</textarea>
								</td>
							</tr>
							<c:set var="maxNum" value="${detail.adindex}"></c:set>
						</c:forEach>
					</table>
					<input type="button" value="添 加" onclick="addDetail()">
					<input type="hidden" name="maxNum" id="maxNum" value="${maxNum}"/>
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
				window.opener.refresh();
				window.close();
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
		function addDetail(){
			num = $("#maxNum").val();
			n=parseInt(num)+1;
			$("#maxNum").val(n);
			var tr="<tr><td>"+n+"</td><td><textarea name='content_"+n+"'></textarea></td></tr>";
			$("#table_a").append(tr);
			document.documentElement.scrollTop=document.body.clientHeight+99999; 
	        document.body.scrollTop=document.body.clientHeight+99999; 
		}
	</script>
</body>
</html>