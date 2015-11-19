<%@page import="java.util.Date"%>
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
<style>
.hot_title{
    max-width: 300px;
    white-space: nowrap;
    word-break: keep-all;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>
<script>
	$(document).ready(function() {
		$.tabspanel({
			tabobj : '#nav',
			panelobj : '#menu_con'
		});
		$.imgscreenmove({
			parentobj : '#s1',
			snum : 7,
			autotime : 5
		});//自动滚动 autotime 5为速度
		$.imgscreenmove({
			parentobj : '#s2',
			snum : 7
		});//点击滚动 snum  为一页7张

	});
</script>
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
				<%
				pageContext.setAttribute("now", new Date());
				%>
				<div class="list2">
					<div class="title">
						<em>热门回答</em> <i>&nbsp;</i> <span style="padding-left:20px;"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></span>
					</div>
					<div class="clear"></div>
					<div class="rank">
						<div class="pic">
							<a href="#"><img src="images/wdb_20.jpg" width="311"
								height="175" /></a>
						</div>

						<ul class="ranktext">
							<c:forEach items="${hotList}" var="q">
							<li><span class="hot_title"><a href="${cxp}/sq.do?qid=${q.id}"><c:out value="${q.title}"/></a> [<a href="${cxp}/questions.do?sf.clsid=${subcls.id}"
								class="link_hui">${q.clsName}</a>]</span></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="clear"></div>

				<div class="search">
					<div class="stubiao">问题搜索</div>
					<div class="searchbox">
						<form action="${cxp}/questions.do" method="post">
							<input name="sf.key" type="text" class="sbox"
								placeholder="请入问题关键字" align="absmiddle" value="<c:out value='${sf.key}'/>"/><input name=""
								type="submit" class="sbtn" value="" />
						</form>
					</div>
				</div>

				<div class="list3">

					<ul id="nav" class="tabmenu">
						<li style="border-left: #ddd solid 1px;"><a href="javascript:void(0)"
							class="act">待解决问题</a></li>
						<li><a href="javascript:void(0)">已解决问题</a></li>
					</ul>
					<div class="clear"></div>
					<div id="menu_con" class="qacon">
						<div style="display: block">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr class="tabletit">
									<td>问题标题</td>
									<td>分类</td>
									<td>提问人</td>
									<td height="30">人气</td>
									<td>回答数</td>
									<td>最后回复</td>
								</tr>
								<c:forEach items="${unResolvedList}" var="q">
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
							</table>

							<div class="ckmore">
								<a href="${cxp}/questions.do">查看更多问题>></a>
							</div>
						</div>
						<div>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr class="tabletit">
									<td>问题标题</td>
									<td>分类</td>
									<td>提问人</td>
									<td height="30">人气</td>
									<td>回答数</td>
									<td>最后回复</td>
								</tr>
								<c:forEach items="${resolvedList}" var="q">
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
							</table>

							<div class="ckmore">
								<a href="${cxp}/questions.do">查看更多问题>></a>
							</div>
						</div>

					</div>


				</div>
			</div>
		</div>

		<div class="clear"></div>


		<div class="expert">
			<div class="extit">
				<h1>推荐专家</h1>
				<h2>
					<a href="${cxp}/experts.do" class="link_more">更多专家</a>&nbsp;&nbsp;&nbsp; <a
						href="${cxp}/register_expert.jsp" class="sqzj">申请成为专家</a>
				</h2>
			</div>
			
			<div id="s1" class="scroll">
				<a class="prev"></a>
				<div class="box">
					<div class="scroll_list">
						<ul>
							<c:forEach items="${expertList}" var="exp">
							<li><h4>
									<a href="${cxp}/viewExpert.do?id=${exp.id}"><img src="${cxp}/affix/showImage.do?id=${exp.affixId}" width="125"
										height="128" /></a>
								</h4>
								<h5 class="infor">
									<span class="blue"><a href="${cxp}/viewExpert.do?id=${exp.id}">${exp.trueName}</a></span><br />专长：<a href="${cxp}/viewExpert.do?id=${exp.id}"><c:out value="${exp.personProfile}"/>
									</a>
								</h5></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<a class="next"></a>
			</div>
		</div>

<%@ include file="/foot.jsp"%>
	


	</div>
	<c:if test="${not empty param.errno}">
	<script type="text/javascript">
		if("${param.errno}"=="101"){
			alert("对不起，您的账号未审核或审核未通过，不允许提问，请等待管理员审核或检查注册信息。");
		}
	</script>
	</c:if>
</body>
</html>
