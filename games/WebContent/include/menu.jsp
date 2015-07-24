<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.zhc.sys.entity.SysMenu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar nav-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->        	
	<ul>
		<li>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			<div class="sidebar-toggler hidden-phone"><h3 style="margin-left:-160px; line-height:23px;  color:#666; ">Menu</h3></div>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		</li>
		<li>
			<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
			<form class="sidebar-search dn">
				<div class="input-box">
					<a href="javascript:;" class="remove"></a>
					<input type="text" placeholder="Search..." />				
					<input type="button" class="submit" value=" " />
				</div>
			</form>
			<!-- END RESPONSIVE QUICK SEARCH FORM -->
		</li>
		
		<%
		List<SysMenu> menuList = (List<SysMenu>)session.getAttribute("sysmenu_session");
		
		Iterator<SysMenu> iter = menuList.iterator();
		List<Long> mids = new ArrayList<Long>();
		
		SysMenu m = null;
		while(iter.hasNext()){
			m = iter.next();
			
			if(mids.contains(m.getId())){
				continue;
			}
			
			mids.add(m.getId());
			%>
			<li class="has-sub">
			    <a href="<%=StringUtils.isNotBlank(m.getLink())?(request.getContextPath()+m.getLink()):"javascript:;" %>" title="">
			    <i class="icon-file-alt"></i> 
			    <span class="title"><%=m.getName() %></span>
                    <span class='arrow'></span>
			    </a>
			<%
				if(m.getChilds()!=null && m.getChilds().size() > 0){
			%>
                    <ul class="sub">
             <%for(SysMenu sub : m.getChilds()){
            	 if(!menuList.contains(sub)){
            		 continue;
            	 }
             	mids.add(sub.getId());
             %>
				        <li><a href="${cxp}<%=sub.getLink() %>" title="<%=sub.getDescription()%>"><%=sub.getName() %></a></li>
             <%}%>
               		 </ul>
                    
            <%} %>
		    </li>
			<%
			
		}
		
		%>
	</ul>
	<!-- END SIDEBAR MENU -->
</div>
<script type="text/javascript">
<!--
	var url=window.location.href;
	if(url.indexOf('listSysUser.do')>-1){
		$("#sysuser").addClass("active");
	}
//-->
</script>
		<!-- END SIDEBAR -->