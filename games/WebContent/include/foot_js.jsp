<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	//$(".sub").show();
	$(".has-sub>a").each(function() {
		// $(this).css("background-color", "#313131");

	});
	var buttons = $.extend([], $.fn.datebox.defaults.buttons);
	buttons.splice(1, 0, {
        text: 'Clear',
        handler: function(target){
        	$('#'+target.id).combo("clear");
        	$('#'+target.id).combo("hidePanel");
        	
        	//$('#'+target.id).datebox("setValue", "");
        }
    });
	
	</script>
