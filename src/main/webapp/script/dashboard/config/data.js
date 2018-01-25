layui.use([ 'form' ], function() {
	var form = layui.form, url = window.location.pathname; 
	var init = {
		data: function(){
			$.ajax({ url: url + '/data', type: 'get', dataType: 'json', success: function(data){
					var html = [];
				    $.each(data.type, function(n, o){
				    	html.push('<div class="layui-form-item"><label class="layui-form-label">'+o.typeName+'</label><div class="layui-input-block">');
				    	$.each(data.list, function(m, p){
				    		o.type == p.type && html.push('<input type="checkbox" name="ids" title="'+ p.name+'" value="'+ p.id +'" '+(p.codeId && 'checked')+'>')
				    	})
				    	html.push('</div></div>')
				    })
				    html.push('<div class="layui-form-item">\
							<label class="layui-form-label"></label>\
							<div class="layui-input-block">\
								<button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>\
							</div>\
						</div>')
					$('#form').html(html.join(""))
					form.render();
				}
			});
		}
	}
	
	$.each(init, function(n, o){
		o();
	})
	
	form.on('submit(demo1)', function(data) {
		var data = $('#form').serializeArray();
		$.ajax({ url: url + '/save', data: data, type: 'post', dataType: 'json', success: function(res){
				res.code == 0 ? layer.msg('修改成功') : (layer.msg('修改失败'), console.log('res.msg'))
			}
		});
		return false
	});
})