<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<%@ include file="/include/head.jsp"%>
<style>
<!--

-->
</style>
<!-- BEGIN CONTAINER -->
<div class="page-container row-fluid">
	<%@ include file="/include/menu.jsp"%>
	<!-- BEGIN PAGE -->
<div class="page-content">
		<!-- BEGIN PAGE CONTAINER-->
		<div class="container-fluid">
			<%@ include file="/include/page_header.jsp"%>
			<!-- BEGIN PAGE CONTENT-->
					<div id="tabs" class="easyui-tabs" style="width: 100%; height: auto;">
						<div title="Send mail" id="tabs-1" style="padding: 10px;min-height:780ox;"
							href="${cxp}/manage/mail/mail_send.jsp"></div>
						<div title="Send record" id="tabs-2" style="padding: 10px;" 
							href="${cxp}/manage/mail/mail_list.jsp">
						</div>
					</div>
				<!-- END PAGE CONTENT-->
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	<!-- END PAGE -->
</div>
<%@ include file="/include/foot.jsp"%>