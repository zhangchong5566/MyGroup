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
			        		分类：<select name="parentClsId" id="parentClsId" style="width:auto;" onchange="selCls(this.value)">
								<option value="">-全部-</option>
								<c:forEach items="${context_askclassify}" var="cls" varStatus="stat">
									<c:if test="${cls.level==2}">
										<option value="${cls.id}"><c:out value="${cls.name}"/></option>
									</c:if>
								</c:forEach>
							</select>
							<select name="clsId" id="clsId" style="width:auto;" >
								<option value="">-全部-</option>
							</select>
							<input id="key" name="key" type="text" value="" class="m-wrap small" placeholder="关键字" style="width:300px !important;"></input>
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
							<th>问题标题</th>
							<th>分类</th>
							<th>提问人</th>
							<th>是否公开</th>
							<th>人气</th>
							<th>回答数</th>
							<th>提问时间</th>
							<th>最后回复</th>
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
         "sAjaxSource": "${cxp}/manage/ask/listQuestions.do",   //mvc后台ajax调用接口。
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
         "sAjaxDataProp":"qlist",
         "aoColumns": [
				{ "mData": "title","bSortable": false,"mRender":function(data,type,full){
              		return "<span class='title'><a class='edit' href='showQuestion.do?id="+full["id"]+"&TB_iframe=true&height=550&width=800' title='"+data+"'>"+data+"</a></span>";
              	}},
				{ "mData": "classify.name","bSortable": false,"mRender":strDefRender},
				{ "mData": "member.loginName","bSortable": false,
					"mRender":function(data,type,full){
						if(full["member"].trueName!=null){
							return full["member"].trueName;
						}
	              		return data;
	              	}
				},
				{ "mData": "isopen","bSortable": false,
					"mRender":function(data,type,full){
	              		if(data=="1"){
	              			return "公开";
	              		}else if(data == "2"){
	              			return "不公开";
	              		}
	              	}},
				{ "mData": "viewCount","bSortable": false,"mRender":strDefRender},
				{ "mData": "replayCount","bSortable": false,"mRender":strDefRender},
				{ "mData": "createDate","bSortable": false,"mRender":datetimeRender},
				{ "mData": "lastReplayDate","bSortable": false,"mRender":datetimeRender},
				{ "mData": "status","bSortable": false,
					"mRender":function(data,type,full){
	              		if(data=="1"){
	              			return "未解决";
	              		}else if(data == "2"){
	              			return "已解决";
	              		}
	              	}},
				{ "mData": "id","bSortable": false,
					"mRender":function(data,type,full){
						var s = "<a class='btn mini red' href='javascript:void(0)' onclick='delQuestion("+data+")'><i class='icon-remove'></i> 删除</a>";
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
	            		{"name":"sf.clsid","value":$('#clsId').val()},
	            		{"name":"sf.key","value":$('#key').val()}

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
	
	function delQuestion(id){
		
		if(confirm("确认删除这个问题吗?")){
			$.ajax({
	             type: "POST",
	             url: "delQuestion.do",
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
	
	function selCls(pid){
		
		$.ajax({
	          type: "post",//使用get方法访问后台
	          dataType: "json",//返回json格式的数据
	          url: "${cxp}/subClassify.do",//要访问的后台地址
	          data: "pid="+pid,//要发送的数据
	          success: function(data){//data为返回的数据，在这里做数据绑定 
	        	  $("#clsId").empty();
	        	  $("#clsId")[0].options.add(new Option("-请选择-",""));
		          	$.each(data.clsList, function(i,val){
						var name=val.name;
					    var opt = new Option(name,val.id);						    
					    $("#clsId")[0].options.add(opt);
				   
			    	}); 
	        	 }
	    	 });
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