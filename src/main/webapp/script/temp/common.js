layui.use(['form'], function(){
	var form = layui.form,
		url = window.location.pathname;
	
	var init = {
		
	}
	
	form.on('select(component)', function(data) {
		data.value != url && (window.location.href = data.value);
	}); 
})