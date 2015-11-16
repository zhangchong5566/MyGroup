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
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:12px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width:100%;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body style="padding:20px;">
	<div id="cont" class="portlet-body form-horizontal form-bordered form-row-stripped" style="padding:10px;">
			<div id="add" class="ss">
				<div style="margin-left:2px;">
					<table class="gridtable">
						<tr>
							<td width="130px">问题标题：</td>
							<td>
								<c:out value="${qbean.title}"></c:out>
							</td>
						</tr>
						<tr>
							<td>分类:</td>
							<td>
								<c:out value="${qbean.classify.parentObj.name}"></c:out>
								&gt;
								<c:out value="${qbean.classify.name}"></c:out>
							</td>
						</tr>
						<tr>
							<td>提问人:</td>
							<td>
								<c:out value="${qbean.member.loginName}"></c:out>
								<c:out value="${qbean.member.trueName}"></c:out>
							</td>
						</tr>
						<tr>
							<td>标签：</td>
							<td>
								<c:out value="${qbean.askTag}"></c:out>
							</td>
						</tr>
						<tr>
							<td>提问时间：</td>
							<td>
								<fmt:formatDate value="${qbean.createDate}" pattern="yyyy-MM-dd HH:mm" />
							</td>
						</tr>
						<tr>
							<td valign="top">问题描述：</td>
							<td>
								${qbean.description}
							</td>
						</tr>
						<tr>
							<th colspan="2">共${fn:length(answers)}条回答</th>
						</tr>
						<c:forEach items="${answers}" var="ans">
							<tr>
								<td  valign="top">
									<label>${ans.member.loginName} <br/>${ans.member.trueName}</label>
									<label><fmt:formatDate value="${ans.createDate}" pattern="yyyy-MM-dd HH:mm" /></label>
									<br/>
									<a class='btn mini red' href="javascript:void(0)" onclick="delAnswer(${ans.id})"><i class='icon-remove'></i>删除</a>
								</td>
								<td>
									<c:if test="${ans.status==2}"><span style="background:#66cc52;color:#fff;margin-left:20px;padding:5px;">已采纳</span><br/></c:if>
									${ans.answerContent}
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
	</div>
	<div style="height: 150px;"></div>
	<div class="form-actions navbar-fixed-bottom">
		<span id="submitloading" style="display: none;"><img
			src='${cxp}/img/loading.gif' /></span>
		<button type="button" class="btn" id="cancel">关闭</button>
	</div>
	<script type="text/javascript">
		function delAnswer(ansid){
			if(confirm("确认删除此回答吗？")){
				$.ajax({
		             type: "POST",
		             url: "delAnswer.do",
		             data: {"ansid":ansid},
		             dataType: "json",
		             success: function(data){
		                 location.reload(true);
		             }
		         });
			}
		}
		
	</script>
</body>
</html>