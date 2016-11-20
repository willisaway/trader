$(window).load(function(){
	$.ajaxSettings.async = false;
	$("#downloadKLineD").click(function(){
		$.getJSON(unieap_main.WEB_APP_NAME+"/fsc/rule/taskPoolAction!takeTasks.action", function(data){
			
		});
	});
});