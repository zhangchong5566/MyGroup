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
</head>

<body>
	<div class="fahter">
		<div class="header"></div>
		<div class="sign-in" style="margin:0px;">
			<div class="user-form"  style="max-width:100%;text-align:left;">
				<form id="regform" class="sign-in__form user-form__form"
					action="${cxp}/answer/saveAsk.do" method="post">
					<input type="hidden" name="id" value="${qbean.id}"/>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="clsid">问题分类*</label>
						<div style="text-align:left;">
							<select name="parentClsId" id="parentClsId" class="user-form__field" style="width:auto;display:initial;" onchange="selCls(this.value)">
								<option value="">-请选择-</option>
								<c:forEach items="${context_askclassify}" var="cls" varStatus="stat">
									<c:if test="${cls.level==2}">
										<option value="${cls.id}" ${qbean.classify.parentObj.id==cls.id?"selected":""}><c:out value="${cls.name}"/></option>
									</c:if>
								</c:forEach>
							</select>
							<select name="clsId" id="clsId" class="user-form__field {required:true}" style="width:auto;display:initial;">
								<option value="">-请选择-</option>
							</select>
							<span style='color:red;display:block' class='msg'></span>
						</div>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="title">问题标题*
							</label><input class="sign-in__field user-form__field {required:true,minlength:3,maxlength:200}"
							type="text" name="title" id="title" value="${qbean.title}"/>
						<span style='color:red' class='msg pwd'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="content">详细描述
							</label>
						<script type="text/plain" id="uEditor" style="width:100% !important;height:300px;"></script>
						<span style='color:red' class='msg'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="askTag">标签（问题的关键字，空格分割，方便于别人搜索你的问题）
							</label><input class="sign-in__field user-form__field {required:true,minlength:3,maxlength:200}"
							type="text" name="askTag" id="askTag" value="${qbean.askTag}"/>
						<span style='color:red' class='msg'></span>
					</fieldset>
					<fieldset class="user-form__fieldset">
						<label class="user-form__label" for="askTag">是否公开（选择不公开，只有专家可以看到你的问题）</label>
						<div style="text-align:left;">
							<select name="isopen" id="isopen" class="user-form__field" style="width:auto;display:initial;">
								<option value="1" ${qbean.isopen==1?"checked":""}>公开</option>
								<option value="2" ${qbean.isopen==2?"checked":""}>不公开</option>
							</select>
							<span style='color:red' class='msg'></span>
						</div>
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
					<div style="text-align:right;margin-right:80px;">
					<button name="button" type="submit"
						class="sign-in__button user-form__button">提交问题</button>
					</div>
				</form>
			</div>
		</div>
		<div id="description" style="display:none;">
			${qbean.description}
		</div>
		<%@ include file="/foot.jsp"%>
	</div>
	<script type="text/javascript">
		toolbar={toolbar:[
   			'image'
          ]};
   		var um = UM.getEditor('uEditor',toolbar);
   		um.setContent($("#description").html());
   		
		function selCls(pid){
			
			$.ajax({
		          type: "post",//使用get方法访问后台
		          dataType: "json",//返回json格式的数据
		          url: "${cxp}/subClassify.do",//要访问的后台地址
		          data: "pid="+pid,//要发送的数据
		          success: function(data){//data为返回的数据，在这里做数据绑定 
		        	  $("#clsId").empty();
		        	  $("#clsId")[0].options.add(new Option("-请选择-",""));
			          	$.each(data.clsList, function(i,val){
							var name=val.name;
						    var opt = new Option(name,val.id);	
						    if(val.id=="${qbean.classify.id}"){
						    	opt.selected=true;
						    }
						    $("#clsId")[0].options.add(opt);
					   
				    	}); 
		        	 }
		    	 });
		}
		<c:if test="${not empty qbean.classify}">
		selCls("${qbean.classify.parentObj.id}");
		</c:if>
		$('#regform').ajaxForm({
			beforeSubmit : validateForm,
			clearForm : false,
			dataType : 'json',
			success : function(data) {
				if(data.message == "Success"){
					alert("提交问题成功！");
					location.href="${cxp}/home.do";
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
			var isSuccess=$("#regform").validate({
				errorPlacement: function(error, element) {
				    error.appendTo(element.parent().find(".msg"));
				}
			}).form(); //返回是否验证成功
			
			if(isSuccess){
				$.blockUI({message:"<img src='${cxp}/img/loading.gif'>"});
			}
		    return isSuccess;
		}
		
	</script>
</body>
</html>
