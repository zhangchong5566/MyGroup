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
					       			序列号：<input id="serialNumber" name="serialNumber" type="text" value="" class="m-wrap Medium" ></input>
					       			<button type="button" class="btn" onclick="search()">查询 <i class="icon-search"></i></button>
					       			&nbsp;&nbsp;
									<a class="btn blue thickbox" id="add" href="toEditSN.do?TB_iframe=true&height=450&width=700" title="添加序列号"><i class="icon-edit"></i> 添加序列号</a>
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
							<th>ID</th>
							<th>游戏</th>
							<th>手机序列号</th>
							<th>有效期开始</th>
							<th>有效期结束</th>
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
         "sAjaxSource": "${cxp}/manage/game/listSerialNumber.do",      //mvc后台ajax调用接口。
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
         "sAjaxDataProp":"snList",
         "aoColumns": [
				{ "mData": "id","bSortable": true},
				{ "mData": "game.gname","bSortable": false},
				{ "mData": "serialNumber","bSortable": false},
				{"mData" : "beginDate","mRender":datetimeRender},
				{"mData" : "endDate","mRender":datetimeRender},
				{
					"mData" : "id","mRender" : function(data, type, full) {
						return "<a href='toEditSN.do?id="+data+"&TB_iframe=true&height=450&width=700'  class='thickbox edit' title='修改'>修改</a>";	
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
	function dateRender(data,type,full){
		if(data==null){
			return "&nbsp;";
		}
		return data.substring(0,10);
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
	 
	 function search(){
			var oSettings = myTable.fnSettings();
		    oSettings.aoServerParams.push({
		        "fn": function (aoData) {
		            aoData.push(
		            	{"name":"form.serialNumber","value":$('#serialNumber').val()}

		            );
		        }
		    });
		    myTable.fnDraw();
		    
		}
	
	
</script>
<%@ include file="/include/foot.jsp"%>