<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问答宝-提问问题</title>
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
<script type="text/javascript" 
	src="${cxp}/js/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<link href="${cxp}/js/umeditor1_2_2/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" charset="utf-8" src="${cxp}/js/umeditor1_2_2/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${cxp}/js/umeditor1_2_2/umeditor.min.js"></script>
<script type="text/javascript" src="${cxp}/js/umeditor1_2_2/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
.ask-title {
    display: inline-block;
    font-size: 18px;
    font-weight: 400;
    line-height: 32px;
    overflow: hidden;
    text-align:left;
}
.ask-content{
	font-size: 14px;
    word-wrap: break-word;
    word-break: break-all;
    font: 14px/24px arial,"\5b8b\4f53",sans-serif,tahoma,"Microsoft YaHei";
}
a.btn{
    color: #003399;
    border: 1px #003399 solid;
    color: #A0522D;
    border-bottom: #93bee2 1px solid;
    border-left: #93bee2 1px solid;
    border-right: #93bee2 1px solid;
    border-top: #93bee2 1px solid;
    background-color: #97FFFF;
    cursor: hand;
    font-style: normal;
	padding:2px;   
}
</style>
</head>

<body>
	<div class="fahter">
		<div class="header"></div>
		<div class="sign-in" style="margin:0px;">
			<div class="user-form"  style="max-width:100%;text-align:left;">
					<fieldset class="user-form__fieldset">
						<div class="ask-title"><c:out value="${qbean.title}"></c:out></div>
						<div>
							${qbean.member.loginName} | 浏览${qbean.viewCount}次 | <fmt:formatDate value="${qbean.createDate}" pattern="yyyy-MM-dd HH:mm" />
							<c:if test="${qbean.status==1 && qbean.member.id==member_session.id}">
								| <a href="${cxp}/answer/toask.do?id=${qbean.id}" class="btn">编辑问题</a>
							</c:if>
						</div>
						<c:if test="${not empty qbean.askTag}">
						<div style="padding:10px 0px;">标签：<c:out value="${qbean.askTag}"></c:out></div>
						</c:if>
					</fieldset>
					
					<c:if test="${not empty qbean.askTag}">
					<fieldset class="user-form__fieldset">
						<div class="ask-content">${qbean.description}</div>
					</fieldset>
					</c:if>
				<c:choose>
				<c:when test="${qbean.status==1 && member_session.role==2 && member_session.status==2 && member_session.id!=qbean.member.id}">
					<form id="aform" class="sign-in__form user-form__form"
						action="${cxp}/answer/saveAnswer.do" method="post">
						<fieldset class="user-form__fieldset" style="text-align:left;">
						<input type="hidden" name="qid" value="${qbean.id}"/>
						<div style="margin-bottom: 5px;font-size: 13px;color: #1e1e1e;">我的回答</div>
						<script type="text/plain" id="uEditor" style="width:100% !important;height:200px;"></script>
						</fieldset>
						<fieldset class="user-form__fieldset">
							<label class="user-form__label" for="session_login">验证码</label> <input
								type="text" name="randomCode"
								class="sign-in__field user-form__field"
								style="width: 100px; float: left;" /> <label style="float: left;"><img id="radomCode"
								src="${cxp}/servlet/randomCodeImage?height=36&width=100&fontSize=20"
								onclick="javascript:this.src=this.src+'&'+Math.random();"
								title="看不清楚？点击图片切换"/></label>
						</fieldset>
						<fieldset class="user-form__fieldset">
							<div style="text-align:left;">
							<button name="button" type="submit"
								class="sign-in__button user-form__button">提交回答</button>
							</div>
						</fieldset>
					</form>
					<script type="text/javascript">
						toolbar={toolbar:[
			      			'image'
			             ]};
			      		var um = UM.getEditor('uEditor',toolbar);
						$('#aform').ajaxForm({
							beforeSubmit : validateForm,
							clearForm : false,
							dataType : 'json',
							success : function(data) {
								if(data.message == "Success"){
									alert("提交成功！");
									location.reload(true);
								}else{
									alert(data.message);
									$.unblockUI();
									$("#radomCode").attr("src",$("#radomCode").attr("src")+"&"+Math.random());
								}
							},
							error : function(response) {
							}
						});
						function validateForm(){							
							if(um.hasContents()){
								$.blockUI({message:"<img src='${cxp}/img/loading.gif'>"});
								 return true;
							}else{
								alert("请输入内容后提交。");
							}
						   return false;
						}
						
					</script>
				</c:when>
				<c:when test="${empty member_session && qbean.status==1}">
					<fieldset class="user-form__fieldset">
					<div style="margin-bottom: 5px;font-size: 13px;color: #1e1e1e;">
					<a href="${cxp}/login.jsp" style="color:#2d64b3">我要回答</a>
					</div>
					</fieldset>
				</c:when>
				</c:choose>
				<hr style="height:3px;border:none;border-top:3px groove skyblue;" />
				<fieldset class="user-form__fieldset">
					<div style="font-size: 16px;font-weight: 400;color: #1e1e1e;">共${fn:length(answers)}条回答</div>
				</fieldset>
				<c:forEach items="${answers}" var="ans" varStatus="stat">
				<fieldset class="user-form__fieldset">
					<c:if test="${stat.index>0}">
					<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
					</c:if>
					<div style="padding:10px 0px;">
						<label style="float:left;">${ans.member.loginName}<c:if test="${ans.status==2}"><span style="background:#66cc52;color:#fff;margin-left:20px;padding:5px;">已采纳</span></c:if></label>
						<label style="float:right;"><fmt:formatDate value="${ans.createDate}" pattern="yyyy-MM-dd HH:mm" /></label>
					</div>
					<br/>
					<div class="ask-content" style="padding:10px;">
						<label>${ans.answerContent}</label>
					</div>
					<c:if test="${qbean.status==1 && qbean.member.id==member_session.id}">
					<br/>
					<a href="javascript:caina(${ans.id})"><span style="background:#66cc52;color:#fff;padding:5px;">采纳这个回答</span></a>
					</c:if>
				</fieldset>
				</c:forEach>
			</div>
		</div>
		<%@ include file="/foot.jsp"%>
	</div>
	<script type="text/javascript">
		function caina(ansid){
			
			if(confirm("确认采纳这个回答吗？")){
				$.ajax({
		          type: "post",//使用get方法访问后台
		          dataType: "json",//返回json格式的数据
		          url: "${cxp}/answer/caina.do",//要访问的后台地址
		          data: "ansid="+ansid,//要发送的数据
		          success: function(data){//data为返回的数据，在这里做数据绑定 
		        	  if(data.message == "Success"){
							alert("提交成功！");
							location.reload(true);
		        	  }else{
		        		  alert("出现错误。");
		        	  }
		        	}
		    	});
			}
			
		}
	</script>
</body>
</html>
