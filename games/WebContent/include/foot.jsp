<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- BEGIN FOOTER -->
<div class="footer">
	2015 &copy; ZHC
	<div class="span pull-right">
		<span class="go-top"><i class="icon-angle-up"></i></span>
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<script type="text/javascript">
	var staticFileRoot = "${cxp}/";
</script>
<!-- Load javascripts at bottom, this will reduce page load time -->

<script src="${cxp}/js/breakpoints/breakpoints.js"></script>
<script src="${cxp}/js/jquery.blockUI.js"></script>
<script src="${cxp}/js/jquery/jquery.cookie.js"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
	<script src="${cxp}/js/excanvas.js"></script>
	<script src="${cxp}/js/respond.js"></script>
	<![endif]-->
<%-- <script type="text/javascript"
	src="${cxp}/js/uniform/jquery.uniform.min.js"></script> --%>
<script type="text/javascript"
	src="${cxp}/js/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${cxp}/js/data-tables/DT_bootstrap.js"></script>

<script type="text/javascript" src="${cxp}/js/app.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.numeric.js"></script>
<script type="text/javascript" src="${cxp}/js/admin.main.js"></script>
<script type="text/javascript" src="${cxp}/js/jquery/jquery.thickbox.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		// initiate layout and plugins
		App.init();
	});
</script>


<%@ include file="/include/foot_js.jsp"%>
</body>
<!-- END BODY -->
</html>
