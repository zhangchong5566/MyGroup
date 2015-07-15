<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/tags.jsp"%>
<%@ include file="/include/jquery.jsp"%>
<link rel="stylesheet" href="${cxp}/js/data-tables/DT_bootstrap.css" />
<script src="${cxp}/js/jquery/DataTables/js/jquery.dataTables.min.js"></script>
<div class="row-fluid">
	<div class="span8">
	</div>
</div>

<form action="" id="mainForm2" method="post">
	<table class="table table-striped table-hover" id="myTable2">
		<thead>
			<tr>
				<th>Send date</th>
				<th>Subject</th>
				<th>Receiver</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</form>
<script type="text/javascript">
	
	myTable2 = $('#myTable2').dataTable({
		"bServerSide" : true,
		"sAjaxSource" : "mailList.do", //mvc后台ajax调用接口。
		"sServerMethod": "POST",
        'bPaginate': true,                      //是否分页。
        "bProcessing": true,                    //当datatable获取数据时候是否显示正在处理提示信息。
        'bFilter': false,                       //是否使用内置的过滤功能。
        'bLengthChange': true,                  //是否允许用户自定义每页显示条数。
        'sPaginationType': 'full_numbers',      //分页样式
        "sScrollX": "100%",
        "lengthMenu": [5,10,25, 50],
        "sDom": '<"top"f>rt<"bottom"flip><"clear">',
        "fnDrawCallback": function (oSettings, json) { 
       	 tb_init('a.thickbox, area.thickbox, input.thickbox');
        },
		"sAjaxDataProp" : "sendList",
		"aoColumns" : [
			{
			"mData" : "sendDate",
			"mRender" : datetimeRender
			},
			{
			"mData" : "subject"
			},
			{
			"mData" : "receiverMails",
			"mRender" : mailRender
			}
		]
	});
	
	function numDefRender(data, type, full) {
		if (data == null) {
			return "0";
		}
		return data;
	}
	
	function datetimeRender(data, type, full) {
		if (data == null) {
			return "NULL";
		}
		return data.replace("T", " ");
	}
	function mailRender(data,type,full){
		var str = "";
		var arr = data.split(",");
		for(i=1;i<=arr.length;i++){
			if(i%2==0){
				str+="<span style='color:blue'>"+arr[i-1]+"</span>";
			}else{
				str+="<span>"+arr[i-1]+"</span>";
			}
			str+="&nbsp;&nbsp;";
		}
		return str;
	}
</script>
