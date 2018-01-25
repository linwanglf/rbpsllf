layui.use([ 'form', 'element', 'laytpl' ], function() {
	var laytpl = layui.laytpl, 
		form = layui.form, 
		element = layui.element,
		path = window.location.pathname, 
		search = window.location.search;
		redirect = urlHash.get('redirect') || '';
	var init = {
		//根据id获取详情信息
		form: function(){
			ajax.req(path + '/detail' + search, data, 'get', 'json', function(data){
				config.demoRender.call(this, data)
			})
		},
		//tab初始化
		tabInit: function(){
			element.tabChange('tabDemo', urlHash.get('tab'));
		},
		//tab跳转参数初始化
		tabJump: function(){
			element.on('tab(tabDemo)', function(obj){
				var id = this.getAttribute('lay-id')
				if(path.indexOf('/' + id) > -1) return
				var url = path.replace(/\/form/g, '');
				window.location.href = url
			});
		}
	}
	
	var config = {
		//layui绑定数据
		demoRender: function(data){
			var getTpl = formDemo.innerHTML, view = document.getElementById('formView');
			laytpl(getTpl).render(data, function(html) {
				view.innerHTML = html;
			});
			form.render();
		}
	}
	
	$.each(init, function(n, o){
		o()
	})
	
	form.verify({
	    selected: function(value){
			if(value.length < 1){
				return '必选项';
			}
		}
	});
	
	form.on('switch(isBan)', function(data){
		$('#formView input[name="isBan"]').val(data.elem.checked ? 'y' : 'n')
	});
	
	form.on('submit(formSubmit)', function(data) {
		var url = path.replace(/form/g, '') + (data.field.id ? 'update' : 'add');
		
		var success = function(data){
			if(data.key == 1){
				layer.msg('保存信息成功', {icon: 1, shade: 0.1});
				setTimeout(function(){
					window.location.href = redirect ? redirect.replace(/\[and\]/g, '&') : path.replace(/\/form/g, '');
				}, 700);
			}
			else{
				layer.msg(data.msg!=null?data.msg:'保存信息失败', {icon: 2, shade: 0.1});
			}
		}
		$.ajax({url: url, data: data.field, type: 'post', dataType: 'json', success: success})
		return false;
	});
})