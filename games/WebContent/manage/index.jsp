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
			<div class="portlet box red m10">
				<div class="portlet-title">
					<h4>
						<i class="icon-reorder"></i>Menu
					</h4>
					<div class="tools">
						<a href="javascript:;" class="collapse"></a>
					</div>
				</div>
				<div class="portlet-body">
				<ul>
				<%
		menuList = (List<SysMenu>)session.getAttribute("sysmenu_session");
		iter = menuList.iterator();
		mids = new ArrayList<Long>();
		
		while(iter.hasNext()){
			m = iter.next();
			
			if(mids.contains(m.getId())){
				continue;
			}
			
			mids.add(m.getId());
			
			if(StringUtils.isNotBlank(m.getLink())){
			%>
			
			<li><h4><a href="${cxp}<%=m.getLink() %>" class="red-stripe"><%=m.getName() %></a></h4></li>
			<%}else{ %>
			<li><h4><%=m.getName() %></h4></li>
			<%} %>
			<ul class="dl-horizontal unstyled">
			<%
				if(m.getChilds()!=null && m.getChilds().size() > 0){
					for(SysMenu sub : m.getChilds()){
						if(!menuList.contains(sub)){
		            		 continue;
		            	 }
		             	mids.add(sub.getId());
		             	%>
		             	<li class="m10 floatleft"><a
					href="${cxp}<%=sub.getLink() %>"
					class="btn red-stripe"><%=sub.getName() %><span class="muted"></span></a></li>
		             	<%
					}
				}
			%>
			</ul>
			<%}%>
					</ul>
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	<!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<script type="text/javascript">
	//$("#navigation").hide();
</script>
<%@ include file="/include/foot.jsp"%>