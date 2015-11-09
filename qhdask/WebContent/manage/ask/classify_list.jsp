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
						<c:forEach items="${clist}" var="classify">
							<tr>
								<td>
								<c:forEach begin="2" end="${classify.level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
								<span style="color:blue;">${classify.orderBy}</span> - ${classify.name}</td>
								<td>${classify.link}</td>
								<td>
									<c:forEach begin="2" end="${classify.level}">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
			            			<a class='btn mini blue thickbox edit' title='添加分类' href="${cxp}/manage/ask/toEditClassify.do?parentId=${classify.id}&TB_iframe=true&height=300&width=500"><i class='icon-plus icon-white'></i> 添加子分类</a>
			            			&nbsp;
									<a  class='btn mini purple thickbox edit' title='编辑分类' href="${cxp}/manage/ask/toEditClassify.do?cid=${classify.id}&TB_iframe=true&height=300&width=500"><i class='icon-edit'></i> 编辑</a>
									&nbsp;
									<a class='btn mini red' href="javascript:void(0)" onclick="delClassify(${classify.id})"><i class='icon-remove'></i> 删除</a>
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
	function delClassify(cid){
		
		if(confirm("确认删除这个分类吗?")){
			$.ajax({
	             type: "POST",
	             url: "deleteClassify.do",
	             data: {cid:cid},
	             dataType: "json",
	             success: function(data){
	                alert(data.message);
	                window.location.reload();
	             }
	         });
		}
		
	}
</script>
<%@ include file="/include/foot.jsp"%>