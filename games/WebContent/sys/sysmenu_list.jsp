<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<%@ include file="/include/head.jsp"%>
<!-- BEGIN CONTAINER -->
<div class="page-container row-fluid">
	<%@ include file="/include/menu.jsp"%>
	<!-- BEGIN PAGE -->
<div class="page-content">
		<!-- BEGIN PAGE CONTAINER-->
		<div class="container-fluid">
			<%@ include file="/include/page_header.jsp"%>
			<!-- BEGIN PAGE CONTENT-->
			
			<form action="" id="mainForm" method="post">
				<table class="table table-striped table-hover" id="myTable">
					<tbody>
						<c:forEach items="${mlist}" var="menu">
							<tr>
								<td>
								<c:forEach begin="2" end="${menu.level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
								<span style="color:blue;">${menu.orderBy}</span> - ${menu.name}</td>
								<td>${menu.link}</td>
								<td>
									<c:forEach begin="2" end="${menu.level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
			            			<a class='btn mini blue thickbox edit' title='Add menu' href="${cxp}/sys/toEditMenu.do?parentId=${menu.id}&TB_iframe=true&height=300&width=500"><i class='icon-plus icon-white'></i> Add child</a>
			            			&nbsp;
									<a  class='btn mini purple thickbox edit' title='Edit menu' href="${cxp}/sys/toEditMenu.do?mid=${menu.id}&TB_iframe=true&height=300&width=500"><i class='icon-edit'></i> Edit</a>
									&nbsp;
									<a class='btn mini red' href="javascript:void(0)" onclick="delMenu(${menu.id})"><i class='icon-remove'></i> Delete</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		
				
			
				<!-- END PAGE CONTENT-->
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	<!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<script type="text/javascript">
	//$("#navigation").hide();
	function delMenu(mid){
		
		if(confirm("Confirm delete?")){
			$.ajax({
	             type: "POST",
	             url: "deleteMenu.do",
	             data: {mid:mid},
	             dataType: "json",
	             success: function(data){
	                  alert(data.message);
	                  if(data.message.indexOf("Success")>-1){
	                	 window.location.reload();
	                  }
	             }
	         });
		}
		
	}
</script>
<%@ include file="/include/foot.jsp"%>