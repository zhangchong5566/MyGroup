<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ include file="/include/tags.jsp"%>
		
<html>
<head></head>
<%@ include file="/include/jquery.jsp"%>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.blockUI.js"></script>
<body>
<c:forEach var="affix" items="${beans}">
<div>
<c:out value="${affix.name}"/>：<a href="${cxp}/affix/download.do?id=${affix.id }">下载</a>
<c:if test="${param.action=='update'}">
<a href="javascript:delAffix(${affix.id })" style="margin-left:6px">删除</a>
</c:if>
</div>	 
  </c:forEach>
 
 <c:if test="${param.action=='update'}">
 
 <c:if test="${empty param.num || param.num>affixSize}">
<iframe src="" id="uploadFrame" name="uploadFrame"  height="0" width="0" style="border:0px;" ></iframe>
 <form name="form" id="photoForm" target="uploadFrame" action="${cxp}/affix/uploadFile.do" method="post" enctype="multipart/form-data"  onsubmit="return upload(this)">
 <input type="hidden" name="objectType" value="${param.objectType}">
 <input type="hidden" name="objectId" value="${param.objectId}">
 <input type="file" name="file"   >
 <input type="submit"  value="上传" />
</form>
</c:if>
</c:if>
 

 


<script>
function upload(form){
	 var value = form.file.value;
	if(value !=""){
		var filetype = value.substring(value.lastIndexOf(".")+1);
		 
 
		$("#photoForm").block({
				message : "正在上传，请稍后",
				css : {
					width : '70%'
				}
			});

		 
		 return true;
 
	} 
	return false;
}
function delAffix(value){
if(confirm("确认删除吗？")){
	$.ajax({
		type : "POST",
		url : "${cxp}/affix/affixAction.do?method=delete",
		data : {id:value},
		dataType : "json",
		success : function(data){
		   window.location.reload();
		
		},
		error:function(response){
			alert("删除失败");
			alert(response.responseText);
		}	 

	});
}
}

$(document).ready(function(){
 
if(window.parent.resizeFrame){
 window.parent.resizeFrame();
 
 }
});


function iframeAutoFit() {
  try {
    if(window != parent) {
      var a = parent.document.getElementsByTagName("IFRAME");
      for(var i = 0; i < a.length; i++) {
        if(a[i].contentWindow == window) {
          var h1 = 0, h2 = 0, d = document, dd = d.documentElement;
          a[i].parentNode.style.height = a[i].offsetHeight +"px";
          a[i].style.height = "10px";
          if(dd && dd.scrollHeight) {
            h1=dd.scrollHeight;
          }
          if(d.body) {
            h2 = d.body.scrollHeight;
          }
          var h = Math.max(h1, h2);
          if(document.all) {
            h += 4;
          }
          if(window.opera) {
            h += 1;
          }
          if(h<10){
          	<c:out value="h=${affixSize*25}+25"/>
          }
          a[i].style.height = a[i].parentNode.style.height = h +"px";
        }
      }
    }
  } catch(ex) {}
}

if(window.attachEvent) {
  window.attachEvent("onload", iframeAutoFit);
} else if(window.addEventListener) {
  window.addEventListener("load", iframeAutoFit, false);
}

</script>
</body>
</html>