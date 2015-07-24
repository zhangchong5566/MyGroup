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
			    <div class="span8">
			       <div>
			        	<div style="padding:5px;">
							<input id="name" name="name" type="text" value="" class="m-wrap small" placeholder="Role name"></input>
			           		<span>
			                <button type="button" class="btn" onclick="search()"><i class="icon-search"></i> Search </button>
			                <a class="btn blue thickbox" title='Add role' href="${cxp}/sys/toEditRole.do?TB_iframe=true&height=350&width=500"><i class="icon-plus icon-white"></i> Add</a>
							</span>
			            </div>
			        </div>
				</div>
			</div>
			<form action="" id="mainForm" method="post">
				<table class="table table-striped table-hover" id="myTable" >
					<thead>
						<tr>
							<th>Role name</th>
							<th>Describe</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</form>
			<script type="text/javascript">
	//$("#navigation").hide();
	
	myTable = $('#myTable').dataTable({
         "bServerSide": true,
         "sAjaxSource": "${cxp}/sys/listRole.do",   //mvc后台ajax调用接口。
         "sServerMethod": "POST",
         "ordering" : false,
         'bPaginate': true,                      //是否分页。
         "bProcessing": true,                    //当datatable获取数据时候是否显示正在处理提示信息。
         'bFilter': false,                       //是否使用内置的过滤功能。
         'bLengthChange': true,                  //是否允许用户自定义每页显示条数。
         'sPaginationType': 'full_numbers',      //分页样式
         "sDom": '<"top"f>rt<"bottom"flip><"clear">',
         "fnDrawCallback": function (oSettings, json) {
        	 tb_init('a.edit');
         },
         "sAjaxDataProp":"list",
         "aoColumns": [
				{ "mData": "name","bSortable": false},
				{ "mData": "description","bSortable": false},
				{ "mData": "id","bSortable": false,
					"mRender":function(data,type,full){
	              		return "<a href='${cxp}/sys/toRoleMenu.do?roleId="+data+"&TB_iframe=true&height=450&width=500'  class='btn mini green thickbox edit' title='Setting menu'><i class='icon-list'></i> Menu</a>" 
	              				+"&nbsp;&nbsp;<a href='${cxp}/sys/toRolePopedom.do?roleId="+data+"&TB_iframe=true&height=450&width=500'  class='btn mini blue thickbox edit' title='Setting popedom'><i class='icon-edit'></i> Popedom</a>"
	              		+"&nbsp;&nbsp;<a href='${cxp}/sys/toEditRole.do?id="+data+"&TB_iframe=true&height=250&width=500' class='btn mini purple thickbox edit' title='Edit popedom'><i class='icon-edit'></i> Edit</a>"
	              		+"&nbsp;&nbsp;<a class='btn mini red' href='javascript:void(0)' onclick='delRole("+data+")'><i class='icon-remove'></i> Delete</a>";
	              	}
				}
             ]
     });
	
	function search(){
		var oSettings = myTable.fnSettings();
	    oSettings.aoServerParams.push({
	        "fn": function (aoData) {
	            aoData.push(
	            		{"name":"role.name","value":$('#name').val()}

	            );
	        }
	    });
	    myTable.fnDraw();
	    
	}
		
	function refresh(){
		 myTable.fnDraw();
		 tb_remove();
	}
	
	function delRole(id){
		
		if(confirm("Confirm delete?")){
			$.ajax({
	             type: "POST",
	             url: "deleteRole.do",
	             data: {id:id},
	             dataType: "json",
	             success: function(data){
	                  alert(data.message);
	                  if(data.message.indexOf("Success")>-1){
	                	  myTable.fnDraw();
	                  }
	             }
	         });
		}
		
	}

</script>
		
			
				<!-- END PAGE CONTENT-->
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	<!-- END PAGE -->
</div>
<!-- END CONTAINER -->
<%@ include file="/include/foot.jsp"%>