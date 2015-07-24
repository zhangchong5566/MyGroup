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
			<div class="row-fluid">
			    <div class="span8">
					<form id="searchForm" method="post" > 
					       <div>
					       		<div style="padding:5px;float:left;">
									<span>
									<a class="btn blue thickbox" id="add" href="toEditJob.do?TB_iframe=true&height=500&width=700" title="Add Job"><i class="icon-edit"></i> Add Job</a>
									</span>
								</div>
					        </div>
					</form>
				</div>
			</div>
			<form action="" id="mainForm" method="post" >
				<table class="table table-striped table-hover" id="myTable">
					<thead>
						<tr>
							<th>Job name</th>
							<th>Class</th>
							<th>Description</th>
							<th>Parameters</th>
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
         "sAjaxSource": "${cxp}/manage/quartz/listJob.do",      //mvc后台ajax调用接口。
         "sServerMethod": "POST",
         "ordering" : false,
         'bPaginate': true,                      //是否分页。
         "bProcessing": true,                    //当datatable获取数据时候是否显示正在处理提示信息。
         'bFilter': false,                       //是否使用内置的过滤功能。
         'bLengthChange': true,                  //是否允许用户自定义每页显示条数。
         'sPaginationType': 'full_numbers',      //分页样式
         "sScrollX": "98%",
         "lengthMenu": [10,25, 50,100,200],
         "sDom": '<"top"f>rt<"bottom"flip><"clear">',
         "fnDrawCallback": function (oSettings, json) { 
        	 tb_init('a.edit');
         },
         "sAjaxDataProp":"jobList",
         "aoColumns": [
				{ "mData": "name","bSortable": false},
				{"mData" : "className"},
				{"mData" : "description","mRender":strDefRender},
				{"mData" : "parameters","mRender":strDefRender},
				{
					"mData" : "id","mRender" : function(data, type, full) {
						return "<a href='toEditJob.do?id="+data+"&TB_iframe=true&height=500&width=700'  class='thickbox edit' title='Edit Job'>Edit</a>";	
					}
				}
             ]
     });
	
	
	function numDefRender(data,type,full){
		if(data==null){
			return "0";
		}
		return data;
	}
	
	function strDefRender(data,type,full){
		if(data==null){
			return "&nbsp;";
		}
		return data;
	}
	
	function datetimeRender(data,type,full){
		if(data==null){
			return "&nbsp;";
		}
		return data.replace("T"," ");
	}
	
	 
	 function refresh(){
		 myTable.fnDraw();
		 tb_remove();
	}
	 
	 
	
	
</script>
<%@ include file="/include/foot.jsp"%>