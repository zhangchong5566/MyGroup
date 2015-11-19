<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝</title>
<%@ include file="/include/tags.jsp"%>
<link href="${cxp}/css/style_web.css" rel="stylesheet" type="text/css" />
<script src="${cxp}/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${cxp}/js/jquery/jquery.tabspanel.js"></script>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:12px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
	width:100%;
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

		<div class="conmain">
			<div class="conleft">
				<%@ include file="/include/classify_left.jsp"%>
			</div>

			<div class="conright">
				<%@ include file="/include/top.jsp"%>

				<div class="clear"></div>
				<div>
					<ul id="breadcrumbs-one">
						<li><a href="${cxp}/">首页</a></li>
						<li><a href="" class="current">专家列表</a></li>
					</ul>
				</div>
				<div class="search">
					<div class="stubiao">专家搜索</div>
					<div class="searchbox">
						<form action="experts.do" method="post">
							<input name="sf.key" type="text" class="sbox"
								placeholder="请输入专家姓名" align="absmiddle" value="<c:out value='${sf.key}'/>"/><input name=""
								type="submit" class="sbtn" value=" "/>
						</form>
					</div>
				</div>
				<div class="list3">
					<div class="clear"></div>
					<div id="menu_con" style="height: auto">
						<div style="display: block">
							<table class="gridtable">
								<c:choose>
									<c:when test="${empty expertList}">
										<tr>
											<td colspan="4" align="center" style="padding: 10px;">没有记录！</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${expertList}" var="exp">
											<tr>
												<td rowspan="2" style="width:135px;padding:5px;">
													<a href="${cxp}/viewExpert.do?id=${exp.id}"><img src="${cxp}/affix/showImage.do?id=${exp.affixId}" width="125"
														height="128" border="0"/></a>
												</td>
												<td height="30px" style="width:50px">姓名：</td>
												<td><a href="${cxp}/viewExpert.do?id=${exp.id}"><c:out value="${exp.trueName}"></c:out></a></td>
												<td  style="width:50px">性别：</td>
												<td><c:out value="${exp.sex}"></c:out></td>
												<td  style="width:50px">学历：</td>
												<td><c:out value="${exp.education}"></c:out></td>
											</tr>
											<tr>
												<td valign="top">专长：</td>
												<td colspan="5" valign="top"><c:out value="${exp.personProfile}"/></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
					<c:if test="${not empty expertList}">
						<pages:pageForm name="pages" action="experts.do" method="post">
							<input type="hidden" name="sf.key" value="${sf.key}" />
						</pages:pageForm>
					</c:if>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<%@ include file="/foot.jsp"%>
	</div>
</body>
</html>
