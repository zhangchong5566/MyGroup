<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Account</title>
<%@ include file="/include/taglibs.jsp"%>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.thickbox.js"></script>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.edit.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<style type="text/css">
div.ss div {
	margin: 5px auto;
}

table tr {
	height: 48px
}

td span a {
	margin: 5px;
	width: 50px;
}

textarea {
	width: 98%;
	height: 200px;
}
</style>
</head>
<body style="padding: 20px;">
	<input type="hidden" name="account.id" value="${account.id}">
	<div id="cont" style="padding: 10px;">
		<div id="add" class="ss">
			<div style="margin: 20px;">
				<table style="width: 90%" id="table_a">
					<tr>
						<td width="130px">游戏：</td>
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
						<td>${account.userTag}</td>
					</tr>
					<tr>
						<td>备注：</td>
						<td>${account.remark}</td>
					</tr>
					<tr>
						<td colspan="2" style="font-size: 16px; font-weight: bold;">
							以下数字每一个代表一个账号文件，可以点击编辑其内容
						</td>
					</tr>
					<c:set var="maxNum" value="${fn:length(detailList)}"></c:set>
					<tr>
						<td colspan="2"><span id="details">
								<c:forEach items="${detailList}" var="detail" varStatus="vs">
									<span> <a class="btn green thickbox"
										id="add_${vs.index}"
										href="toEditAccountDetail.do?accountId=${account.id}&adindex=${detail.adindex}&TB_iframe=true&height=550&width=750"
										title="${detail.adindex}"><c:out
												value="${detail.adindex}"></c:out></a>
									</span>
								</c:forEach>
						</span> <span><a class="btn red" href="javascript:void(0)"
								onclick="addDetail()">添 加</a></span></td>
					</tr>
				</table>
				<input type="hidden" name="maxNum" id="maxNum" value="${maxNum}" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function addDetail() {
			num = $("#maxNum").val();
			n = parseInt(num) + 1;
			$.blockUI({message:"<img src='${cxp}/img/loading.gif'>"});
			$.ajax({  
				      url:'${cxp}/manage/game/saveAccountDetail.do',// 跳转到 action  
				      data:{  
				    	  accountId:"${account.id}",
				    	  adindex:n
				      },  
				     type:'post',  
				     cache:false,  
				     dataType:'json',  
				     success:function(data) {  
				         if(data.result=="SUCCESS" ){  
				        	 $("#maxNum").val(n);
				 			var tr = "<span> <a class=\"btn green thickbox\" id='btn_"
				 					+ n
				 					+ "' href=\"toEditAccountDetail.do?TB_iframe=true&height=550&width=700&accountId=${account.id}&adindex="
				 					+ n + "\" title='" + n + "'>" + n + "</a></span>";
				 			$("#details").append(tr);
				 			tb_init("#btn_"+n);
				 			$.unblockUI();
				         }
				      },  
				      error : function() {  
				           alert("异常！");  
				      }  
				 });
				 
			
			
		}
	</script>

</body>
</html>