<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/pages.tld" prefix="pages"%>
<%
	String cxp = request.getContextPath();
	pageContext.setAttribute("cxp", cxp);
%>
<script>
	var cxp = "${cxp}";
	var web_url="${web_url}";
	var image_website="${web_image_website}";
</script>
