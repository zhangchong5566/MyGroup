<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<link href="${cxp}/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
	<link href="${cxp}/css/metro.css" rel="stylesheet" />
	<link href="${cxp}/js/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
	<link href="${cxp}/css/style.css" rel="stylesheet" />
	<link href="${cxp}/css/style_responsive.css" rel="stylesheet" />
	<link href="${cxp}/js/jquery/jquery.fancybox.css" rel="stylesheet" />
	<!--<link rel="stylesheet" type="text/css" href="${cxp}/js/uniform/css/uniform.default.css" />  -->
	<link rel="stylesheet" type="text/css" href="${cxp}/css/chosen/chosen.css" />
	<link rel="stylesheet" href="${cxp}/js/data-tables/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${cxp}/css/admin.main.css" />
    <link rel="stylesheet" type="text/css" href="${cxp}/js/jquery/jquery.thickbox.css" />
    <link rel="stylesheet" type="text/css" href="${cxp}/js/jquery/DataTables/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="${cxp}/js/jquery/easyui-1.4/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="${cxp}/js/jquery/easyui-1.4/themes/icon.css">
    <script src="${cxp}/js/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${cxp}/js/jquery/easyui-1.4/jquery.easyui.min.js"></script>
    <script src="${cxp}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${cxp}/js/jquery/DataTables/js/jquery.dataTables.min.js"></script>
     <script src="${cxp}/js/layer/layer.js"></script>
    <script type="text/javascript">
$(document).ready(function(){
	$("#checkall").click(function () {
	    var ischecked = this.checked;
	    $("input:checkbox[name='ids']").each(function () {
	        this.checked = ischecked;
	    });

	    //$.uniform.update(':checkbox');
	});

});
</script>
