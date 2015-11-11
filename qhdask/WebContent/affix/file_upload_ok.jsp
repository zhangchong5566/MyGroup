<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
 
<script>
<c:if test="${empty affix}">
	alert("上传失败！");
</c:if>
<c:if test="${not empty affix}">
window.parent.location.reload();
</c:if>

</script>