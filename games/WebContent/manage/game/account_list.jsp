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
									<a class="btn blue" id="add" href="toEditAccount.do" title="增加账号"  target='_blank'><i class="icon-edit"></i> 增加账号</a>
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
							<th>游戏</th>
							<th>账号标记</th>
							<th>当前账号序号</th>
							<th>备注</th>
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
         "sAjaxSource": "${cxp}/manage/game/listAccount.do",      //mvc后台ajax调用接口。
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
         "sAjaxDataProp":"accountList",
         "aoColumns": [
				{"mData" : "game.gname","mRender":strDefRender},
				{ "mData": "userTag","bSortable": true},
				{"mData" : "currIndex"},
				{"mData" : "remark","mRender":strDefRender},
				{
					"mData" : "id","mRender" : function(data, type, full) {
						return "<a href='toEditAccount.do?id="+data+"' title='修改' target='_blank'>Edit</a>";	
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
	}
	 
	 
	
	
</script>
<%@ include file="/include/foot.jsp"%>