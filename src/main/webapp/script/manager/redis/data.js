layui.use(['layer', 'form'], function(){
	var path = window.location.pathname,
		form = layui.form,
		elem = $('textarea[name="redisValue"]');
	form.on('submit(redisSearch)', function(data) {
		var url = path + '/search',
			type = data.field.type;
		var success = function(data){
			type == 'get' && elem.val(data.key);
			type == 'getSyncData' && elem.val(JSON.stringify(data.key));
		}
		$.ajax({url: url, data: data.field, type: 'post', dataType: 'json', success: success})
		return false;
	});
	
	form.on('submit(redisUpdate)', function(data) {
		var url = path + '/save'; data.field.redisKey = $('input[name="redisKey"]').val();
		if(elem.is(':disabled')){
			return false
		}
		var success = function(data){
			if(data.code == 1){
				layer.msg('保存信息成功', {icon: 1, shade: 0.1});
			}
			else{
				layer.msg(data.msg!=null?data.msg:'保存信息失败', {icon: 2, shade: 0.1});
			}
		}
		$.ajax({url: url, data: data.field, type: 'post', dataType: 'json', success: success})
		return false;
	});
	
	form.on('select(type)', function(data){
		data.value == 'get' && elem.removeAttr('disabled');
		data.value == 'getSyncData' && elem.attr('disabled', 'disabled');
	});      
});
