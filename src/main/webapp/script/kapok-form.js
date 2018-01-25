var regObj = {
	username: {reg: /[A-Za-z0-9_]{4,18}$/, msg: '帐号由大小写字母或下划线组成,长度4-18'}, //
	password: "",
	purl: {reg: /^(\/{1}([A-Za-z0-9_-]+|\*+))+$/, msg: '格式为/xx/xx或/xx/**'},
	permission: {reg: /^([A-Za-z0-9]+:)+([A-Za-z0-9]+|\*)$/, msg: '格式为xx:xx或xx:*'}
	//icon_required: {reg: /[\S]+/, msg: '请选择图标'}
}

;!function(e){
	"use strict";
	var f = function(){}
	f.prototype.render = function(){
		layui.use(['form'], function(){
			var form = layui.form(), $ = layui.jquery, layer = layui.layer;
			form.verify({
			    username: function(value, e){
					if(value.length < 1){
						return '帐号不能为空';
					}
					if(!regObj.username.reg.test(value)){
						return regObj.username.msg;
					}
					if($.ajax({
						url: "/verify/username",
						data: {username: value, id: $('#id').val()},
						type: 'post',
						async: false,
						dataType: 'json'
					}).responseJSON){return '帐号已存在'};
				},
				password: function(value){
					if(value.length < 1){
						return '密码不能为空';
					}
				},
				select: function(value){
					if(value.length < 1){
						return '必选项不能为空';
					}
				},
				purl: [regObj.purl.reg, regObj.purl.msg],
				permission: [regObj.permission.reg, regObj.permission.msg]
				//icon_required: {}
			});
			form.on('submit(formSubmit)', function(data){
				var _data = data.field, url = $('#form').attr('action'), index = parent.layer.getFrameIndex(window.name);
				$.ajax({
    				url: url,
					data: _data,
					type: 'post',
					dataType: 'json',
					success: function(res){
						if(res.key == 1){
							layer.msg('保存信息成功', {icon: 1, shade: 0.1});
							setTimeout('parent.layer.close('+index+');parent.location.reload();', 1000);
						}
						else{
							layer.msg(res.msg!=null?res.msg:'保存信息失败', {icon: 2, shade: 0.1});
						}
					},
					error: function(xhr, stat){
						if(stat == 'parsererror' && xhr.status == '200'){
							layer.open({
								title: '<i class="fa fa-warning"></i> 温馨提示：权限受限',
								content: '访问受限，请联系管理员'
							});  
						}
						if(stat == 'error' && xhr.status == '404'){
							layer.open({
								title: '<i class="fa fa-warning"></i> 温馨提示：404 ️',
								content: '访问不存在，请联系管理员'
							});  
						}
						if(xhr.status == '500'){
							layer.open({
								title: '<i class="fa fa-warning"></i> 温馨提示：500',
								content: '访问出错，请联系管理员'
							});  
						}
					},
					complete: function(xhr, stat) {}
				});
			});

		});
	}
	e.formSubmit = new f;
	e.formSubmit.render();


}(window);