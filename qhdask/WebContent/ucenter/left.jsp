<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<style>
<!--
.active{
	font-weight:bold !important;
	color:#1f5fa6 !important;
}
-->
</style>
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
	<h1>会员中心</h1>
	<div class="qaclasscon">
		<ul class="listclass">
			<li>
				<a id="m1" href="${cxp}/ucenter/myask.do">我的提问</a>
			</li>
			<li>
				<a id="m2" href="${cxp}/ucenter/myanswer.do">我的回答</a>
			</li>
			<li>
				<a id="m3" href="${cxp}/ucenter/myaccount.do">账号信息</a>
			</li>
			<li>
				<a id="m4" href="${cxp}/ucenter/mypwd.do">修改密码</a>
			</li>
		</ul>
		
		<div class="clear"></div>
	</div>
<script type="text/javascript">
url=location.href;
if(url.indexOf("myask")>-1){
	$("#m1").addClass("active");
}else if(url.indexOf("myanswer")>-1){
	$("#m2").addClass("active");
}else if(url.indexOf("myaccount")>-1){
	$("#m3").addClass("active");
}else if(url.indexOf("mypwd")>-1){
	$("#m4").addClass("active");
}
</script>
</div>