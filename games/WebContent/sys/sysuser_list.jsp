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
			<div class="row-fluid">
			    <div class="span4">
			        <div>
			            <a class="btn red dn" id="delete" href="javascript:;"><i class="icon-trash icon-white"></i> Delete</a>
			            <a class="btn blue thickbox" title='Add user' href="toEditUser.do?TB_iframe=true&height=400&width=500"><i class="icon-plus icon-white"></i> Add</a>
			        </div>
			    </div>
			    <div class="span8">
					<form id="search" method="get" > 
					       <div class="dataTables_filter">
					            <label>
					                <button type="button" class="btn" onclick="search()">Search <i class="icon-search"></i></button>
					             </label>
					            <label>
					                <span>User Name：</span>
					                <input class="m-wrap small" id="trueName" name="sysUser.trueName" type="text" value="${sysUser.trueName}" />
					            </label>
					            <label>
					                <span>Login Name：</span>
					                <input class="m-wrap small" id="loginName" name="sysUser.loginName" type="text" value="${sysUser.loginName}" />
					            </label>
					        </div>
					</form>
				</div>
			</div>
			
			<form action="/Account/User/Delete" id="mainForm" method="post">
				<table class="table table-striped table-hover" id="myTable">
					<thead>
						<tr>
							<th>Login name</th>
							<th>User name</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Activity</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
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
	
	myTable = $('#myTable').dataTable({
         "bServerSide": true,
         "sAjaxSource": "${cxp}/sys/listSysUserJsonData.do",      //mvc后台ajax调用接口。
         "sServerMethod": "POST",
         "ordering" : false,
         'bPaginate': true,                      //是否分页。
         "bProcessing": true,                    //当datatable获取数据时候是否显示正在处理提示信息。
         'bFilter': false,                       //是否使用内置的过滤功能。
         'bLengthChange': true,                  //是否允许用户自定义每页显示条数。
         'sPaginationType': 'full_numbers',      //分页样式
         "sAjaxDataProp":"list",
         "sDom": '<"top"f>rt<"bottom"flip><"clear">',
         "fnDrawCallback" : function(oSettings, json) {
				tb_init('a.edit');
			},
         "aoColumns": [
                 { "mData": "loginName","mRender":strDefRender},
                 { "mData": "trueName" ,"mRender":strDefRender},
                 { "mData": "email" ,"mRender":strDefRender},
                 { "mData": "phone" ,"mRender":strDefRender},
                 { "mData": "status" , "mRender": function(data, type, full) {
                	 if(data!="0"){
                		 return "<span class='label label-warning'>Freeze</span>";
                	 }else{
                     	return "<span class='label label-success'>Normal</span>";
                	 }
                 	}
                 },
                 { "mData": "id" ,"bSortable": false, "mRender": function(data, type, full) {
                	 str =  "<a href='toEditUser.do?id="+data+"&TB_iframe=true&height=400&width=500' class='btn mini purple edit' title='Edit user'><i class='icon-edit'></i> Edit</a>";
                	 str+="&nbsp;&nbsp;<a href='toUserRoles.do?id="+data+"&TB_iframe=true&height=400&width=500' class='btn mini blue edit' title='Edit user roles'><i class='icon-edit'></i> Roles</a>";
                	 str+="&nbsp;&nbsp;<a href='toResetPassword.do?id="+data+"&TB_iframe=true&height=200&width=500' class='btn mini red edit' title='Reset password'><i class='icon-key'></i> Reset password</a>";
                	 return str;
                 	}
                 }
             ]
     });
	
	function numDefRender(data, type, full) {
		if (data == null) {
			return "0";
		}
		return data;
	}

	function strDefRender(data, type, full) {
		if (data == null) {
			return "&nbsp;";
		}
		return data;
	}

	function datetimeRender(data, type, full) {
		if (data == null) {
			return "NULL";
		}
		return data.replace("T", " ");
	}
	
	function refresh(){
		 myTable.fnDraw();
		 tb_remove();
	}
	function search(){
		var oSettings = myTable.fnSettings();
	    oSettings.aoServerParams.push({
	        "fn": function (aoData) {
	            aoData.push(
	            		{"name":"sysUser.trueName","value":$('#trueName').val()},
	            		{"name":"sysUser.loginName","value":$('#loginName').val()}
	            );
	        }
	    });
	    myTable.fnDraw();
		
	}
	
</script>
<%@ include file="/include/foot.jsp"%>