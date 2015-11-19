<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<%@ include file="/include/head.jsp"%>
<style>
<!--
.title{
    display: inline-block;
    min-width: 120px;
    max-width: 200px;
    white-space: nowrap;
    word-break: keep-all;
    overflow: hidden;
    text-overflow: ellipsis;
}
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
			       <div>
			        	<div style="padding:5px;">
			           		<span>
			                <a class='btn blue thickbox' href='toEditArticle.do?TB_iframe=true&height=550&width=900' title='添加文章'><i class="icon-plus icon-white"></i> 添加文章 </a>
							</span>
			            </div>
			        </div>
				</div>
			</div>
			<form action="" id="mainForm" method="post">
				<table class="table table-striped table-hover" id="myTable" >
					<thead>
						<tr>
							<th>标题</th>
							<th>作者</th>
							<th>来源</th>
							<th>发布时间</th>
							<th>操作</th>
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
         "sAjaxSource": "${cxp}/manage/article/listArticle.do",   //mvc后台ajax调用接口。
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
         "sAjaxDataProp":"artList",
         "aoColumns": [
				{ "mData": "atitle","bSortable": false,"mRender":strDefRender},
				{ "mData": "aauthor","bSortable": false,"mRender":strDefRender},
				{ "mData": "asource","bSortable": false,"mRender":strDefRender},
				{ "mData": "aputouttime","bSortable": false,"mRender":datetimeRender},
				{ "mData": "id","bSortable": false,
					"mRender":function(data,type,full){
						var s = "<a class='btn mini green edit' href='toEditArticle.do?id="+data+"&TB_iframe=true&height=550&width=800' title='编辑'><i class='icon-edit'></i> 编辑</a>";
						s += "&nbsp;&nbsp;<a class='btn mini red' href='javascript:void(0)' onclick='delArticle("+data+")'><i class='icon-remove'></i> 删除</a>"
	              		return s;
	              	}
				}
             ]
     });
	
	
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
	
	function delArticle(id){
		
		if(confirm("确认删除这个文章吗?")){
			$.ajax({
	             type: "POST",
	             url: "delArticle.do",
	             data: {"id":id},
	             dataType: "json",
	             success: function(data){
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