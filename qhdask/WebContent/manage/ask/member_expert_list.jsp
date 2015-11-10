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
							<input id="name" name="name" type="text" value="" class="m-wrap small" placeholder="登录名/姓名/Email" style="width:300px !important;"></input>
			           		<span>
			                <button type="button" class="btn" onclick="search()"><i class="icon-search"></i> 查询 </button>
							</span>
			            </div>
			        </div>
				</div>
			</div>
			<form action="" id="mainForm" method="post">
				<table class="table table-striped table-hover" id="myTable" >
					<thead>
						<tr>
							<th>登录名</th>
							<th>姓名</th>
							<th>性别</th>
							<th>从事专业</th>
							<th>Email</th>
							<th>联系电话</th>
							<th>注册时间</th>
							<th>最后登录时间</th>
							<th>状态</th>
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
         "sAjaxSource": "${cxp}/manage/ask/listMember.do?member.role=2",   //mvc后台ajax调用接口。
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
         "sAjaxDataProp":"mlist",
         "aoColumns": [
				{ "mData": "loginName","bSortable": false},
				{ "mData": "trueName","bSortable": false,"mRender":strDefRender},
				{ "mData": "sex","bSortable": false,"mRender":strDefRender},
				{ "mData": "currProfessional","bSortable": false,"mRender":strDefRender},
				{ "mData": "email","bSortable": false,"mRender":strDefRender},
				{ "mData": "telephone","bSortable": false,"mRender":strDefRender},
				{ "mData": "joinDate","bSortable": false,"mRender":datetimeRender},
				{ "mData": "lastLoginDate","bSortable": false,"mRender":datetimeRender},
				{ "mData": "status","bSortable": false,
					"mRender":function(data,type,full){
	              		if(data=="1"){
	              			return "待审核";
	              		}else if(data == "2"){
	              			return "已审核";
	              		}
	              	}},
				{ "mData": "id","bSortable": false,
					"mRender":function(data,type,full){
						var s = "<a href='toEditExpretMember.do?id="+data+"&TB_iframe=true&height=550&width=800'  class='btn mini edit' title='专家信息'><i class='icon-list'></i> 查看</a>";
						if(full["status"]=="1"){
							s += "&nbsp;&nbsp;<a class='btn mini green' href='javascript:void(0)' onclick='changeStatus("+data+",2)'> 审核通过</a>";
						}else if(full["status"]=="2"){
							s += "&nbsp;&nbsp;<a class='btn mini red' href='javascript:void(0)' onclick='changeStatus("+data+",1)'> 取消审核</a>";
						}
	              		return s;
	              	}
				}
             ]
     });
	
	function search(){
		var oSettings = myTable.fnSettings();
	    oSettings.aoServerParams.push({
	        "fn": function (aoData) {
	            aoData.push(
	            		{"name":"member.loginName","value":$('#name').val()}

	            );
	        }
	    });
	    myTable.fnDraw();
	    
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
	
	function changeStatus(id,status){
		s = status==2?"通过审核":"取消审核";
		if(confirm("确认"+s+"吗?")){
			$.ajax({
	             type: "POST",
	             url: "changeStatus.do",
	             data: {"id":id,"status":status},
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