<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝-${m.trueName}</title>
<%@ include file="/include/tags.jsp"%>
<link href="${cxp}/css/style_web.css" rel="stylesheet" type="text/css" />

<script src="${cxp}/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.tabspanel.js"></script>
<script src="${cxp}/js/jquery/jquery.form.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.validate.message_cn.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="${cxp}/js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 12px;
	color: #333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
	width: 100%;
	margin:10px 0px;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
	background-color: #ffffff;
}
</style>
</head>

<body>
	<div class="fahter">
		<div class="header"></div>
		<div>
			<div>
				<ul id="breadcrumbs-one">
					<li><a href="${cxp}/">首页</a></li>
					<li><a href="${cxp}/experts.do" >专家列表</a></li>
					<li><a href="" class="current">${m.trueName}</a></li>
				</ul>
			</div>
			<table class="gridtable">
				<tr><td colspan="8" align="center">
				<h1 class="sign-in__title user-form__title nolinks">专家介绍</h1>
				</td></tr>
				<tr>
					<td style="width: 120px;">姓名：</td>
					<td>${m.trueName}</td>
					<td style="width: 120px;">性别：</td>
					<td width="100px">${m.sex}</td>
					<td style="width: 120px;">出生年月：</td>
					<td><fmt:formatDate value="${m.birthdate}"
							pattern="yyyy-MM-dd" /></td>
					<td rowspan="4" width="130px"><img
						src="${cxp}/affix/showImage.do?id=${affix.id}" width="125"
						height="128" /></td>
				</tr>
				<tr>
					<td>工作单位：</td>
					<td colspan="5">${m.company}</td>
				</tr>
				<tr>
					<td>毕业院校：</td>
					<td colspan="2">${m.school}</td>
					<td>学历：</td>
					<td colspan="2">${m.education}</td>
				</tr>
				<tr>
					<td>职称：</td>
					<td colspan="2">${m.jobTitle}</td>
					<td>专业：</td>
					<td colspan="2">${m.professional}</td>
				</tr>
				<tr>
					<td>现从事专业：</td>
					<td colspan="2">${m.currProfessional}</td>
					<td>从事年限：</td>
					<td colspan="3">${m.currYears}</td>
				</tr>
				<tr>
					<td>通讯地址：</td>
					<td colspan="2">${m.address}</td>
					<td>Email：</td>
					<td colspan="3">${m.email}</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td colspan="3">${m.telephone}</td>
					<td>QQ、微信：</td>
					<td colspan="3">${m.qqWeChart}</td>
				</tr>
				<tr>
					<td>专家称号(荣誉）：</td>
					<td colspan="6">${m.expretTitle}</td>
				</tr>
				<tr>
					<td>个人简介：</td>
					<td colspan="6"><c:out value="${m.personProfile}"></c:out></td>
				</tr>
				<tr>
					<td>擅长专业领域：</td>
					<td colspan="6"><c:out value="${m.expertiseArea}"></c:out></td>
				</tr>
				<tr>
					<td>服务情况：</td>
					<td colspan="6"><c:out value="${m.servicefall}"></c:out></td>
				</tr>
			</table>
		</div>
		<%@ include file="/foot.jsp"%>
	</div>

</body>
</html>
