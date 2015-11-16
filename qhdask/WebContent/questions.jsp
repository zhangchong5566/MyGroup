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

				<div class="search">
					<div class="stubiao">问题搜索</div>
					<div class="searchbox">
						<form action="questions.do" method="post">
							<select name="sf.status" class="sbox" style="width:80px;height:36px">
								<option value="0">全部</option>
								<option value="1" ${sf.status==1?'selected':''}>待解决</option>
								<option value="2" ${sf.status==2?'selected':''}>已解决</option>
							</select>
							<input name="sf.key" type="text" class="sbox"
								placeholder="请入问题关键字" align="absmiddle" value="<c:out value='${sf.key}'/>"/><input name=""
								type="submit" class="sbtn" value="" />
						</form>
					</div>
				</div>
				<div class="list3">
					<div class="clear"></div>
					<div id="menu_con" class="qacon" style="height: auto">
						<div style="display: block">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<c:choose>
									<c:when test="${empty qlist}">
										<tr>
											<td colspan="4" align="center" style="padding: 10px;">没有记录！</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr class="tabletit">
											<td>问题标题</td>
											<td>分类</td>
											<td>提问人</td>
											<td height="30">人气</td>
											<td>回答数</td>
											<td>最后回复</td>
										</tr>
										<c:forEach items="${qlist}" var="q">
											<tr>
												<td class="listbiaoti"><a href="${cxp}/sq.do?qid=${q.id}" title="<c:out
															value="${q.title}"/>"><c:out
															value="${q.title}"/></a></td>
												<td><c:out
															value="${q.classify.parentObj.name}"></c:out>
															&gt;
													<c:out
															value="${q.classify.name}"></c:out></td>
												<td height="30">${q.member.loginName}</td>
												<td height="30">${q.viewCount}</td>
												<td>
												<c:if test="${q.status==1}">
												<span class="bluebox">${q.replayCount}个回答</span>
												</c:if>
												<c:if test="${q.status==2}">
												<span class="yellowbox">已解决</span>
												</c:if>
												</td>
												<td><fmt:formatDate value="${q.lastReplayDate}"
														pattern="yyyy-MM-dd HH:mm" /></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</div>
					<c:if test="${not empty qlist}">
						<pages:pageForm name="pages" action="questions.do" method="post">
							<input type="hidden" name="sf.clsid" value="${sf.clsid}" />
							<input type="hidden" name="sf.key" value="${sf.key}" />
							<input type="hidden" name="sf.status" value="${sf.status}" />
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
