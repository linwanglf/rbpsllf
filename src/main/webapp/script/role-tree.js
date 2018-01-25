;!function(e){
	"use strict";
	var kt = function(){}
	kt.prototype.render = function(){
		layui.use(['tree', 'layer'], function() {
			$(document).on('click', '[data-action="permission"]', function(){
				var h = $(window).height() - 100, 
					roleId = $(this).parent().data('id'),
					i,
					err = function(xhr, stat) {
						layer.close(i);
						stat == 'parsererror' && xhr.status == '200' && layer.open({ 
							title: '<i class="fa fa-warning"></i> 温馨提示：权限受限',
							content: '访问受限，请联系管理员'
						});
						stat == 'error' && xhr.status == '404' && layer.open({
							title: '<i class="fa fa-warning"></i> 温馨提示：404 ️',
							content: '访问不存在，请联系管理员'
						}); 
						xhr.status == '500' && layer.open({
							title: '<i class="fa fa-warning"></i> 温馨提示：500 ️',
							content: '访问出错，请联系管理员'
						});  
					};
				i = layer.load(2);
				$.ajax({
					url: '/dict/role/resource',
					data: {roleId: roleId},
					type: 'post',
					dataType: 'json',
					async: false,
					success: function(res, stat, xhr){
						var str = JSON.stringify(res);
						str = str.replace(/("[a-zA-Z]*"(:null|:[0-9]|:"0"),|"parentId":"[0-9]",)/g, '').replace(/childList/g, 'children'); //格式化数据
						var obj = JSON.parse(str);
						layer.close(i);
						var j = layer.confirm('<ul id="demo1" style="padding: 5px 10px"></ul>', {
							btn: ['修改权限', '取消'],
							type: 1,
							area: ['400px', h+'px'],
							maxmin: true
						}, function(){
							var ids = kt.prototype.value();
							$.ajax({
								url: '/admin/role/resource/update',
								data: {roleId: roleId, ids: ids},
								type: 'post',
								dataType: 'json',
								traditional: true,
								success: function(res, stat, xhr){
									res.key == 1 ? (layer.close(j),layer.msg('修改权限成功~', {icon: 1, shade: 0.3})) : layer.msg('修改失败，请联系管理员', {icon: 2, shade: 0.3});
								},
								error: err
							})
						});
						layui.tree({
						    elem: '#demo1',
						    target: '_blank',
						    click: function(item){},
						    type: 'checkbox',
						    nodes: obj
						});
					},
					error: err
				})
			})
		})
		kt.prototype.value = function(){
			var dm = $('#demo1').children(), ids = [];
			var fv = function(e){
				e.each(function(n, o){
					var c = $(o).children('.tree-checkbox');
					c.attr('class').indexOf('tree-checkbox-checked') > 0 ? ids.push(c.data('id')) : fv(c.next().children());
				})
			}
			fv(dm);
			return ids;
		}
	}
	e.kTree = new kt;
	e.kTree.render();
}(window);