<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>

<div class="userbox">
	<c:choose>
		<c:when test="${not empty member_session}">
			<div class="user2">
				欢迎您，<a href="${cxp}/ucenter/"><span class="yellow">${empty member_session.trueName?(member_session.loginName):(member_session.trueName)}</span></a>
				<span style="margin-left: 10px;"><a href="${cxp}/loginOut.do">退出</a></span>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				您尚未登录，请先<a href="${cxp}/login.jsp" class="link_yellow"><u>登录</u></a>
				或 <a href="${cxp}/register.jsp" class="link_yellow"><u>注册</u></a>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<div class="qaclass">
	<h1>问答分类</h1>
	<div class="qaclasscon">
		<div class="ztrw">
			已解决问题：${not empty resolvedCount?resolvedCount:0}<br /> <span class="red">待解决问题：${not empty unResolvedCount?unResolvedCount:0} </span>
		</div>

		<ul class="listclass">
			<c:forEach items="${context_askclassify}" var="cls" varStatus="stat">
				<c:if test="${cls.level==2}">
					<li><dt>
							<a><c:out value="${cls.name}" /></a>
						</dt>
						<dd style="display:${stat.index>3?'none':''}">
							<ul>
								<c:forEach items="${cls.childs}" var="subcls">
									<li><a href="${cxp}/questions.do?sf.clsid=${subcls.id}"><c:out value="${subcls.name}" /></a></li>
								</c:forEach>
							</ul>
						</dd></li>
				</c:if>
			</c:forEach>
		</ul>
		<div class="clear"></div>
		<div class="qahelp">
			<h2>问答帮助中心</h2>
			<ul class="helplist">
				<li><a href="${cxp}/article.do?id=1">怎么使用平台问答宝？</a></li>
				<li><a href="${cxp}/article.do?id=2">使用问答宝的好片？</a></li>
				<li><a href="${cxp}/article.do?id=3"> 科技创新服务中心简介</a></li>
			</ul>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$(".listclass dt").click(function() {
				$(".listclass dd").css("display", "none");
				$(this).next("dd").css("display", "block");
			});
		});
	</script>
</div>