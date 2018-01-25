;!function(e){
	"use strict";
	var kt = function(){}
	kt.prototype.render = function(){
		layui.use(['tree', 'layer'], function() {
			$(document).on('click', '#dept_btn', function(){
				var h = $(window).height() - 100, 
					roleId = $(this).parent().data('id'),
					i;
				i = layer.load(2);
				$.ajax({
					url: '/dict/sort/dept',
					type: 'post',
					dataType: 'json',
					success: function(res, stat, xhr){
						var str = JSON.stringify(res);
						str = '[{"name": "部门节点","id": 0, "parentIds": 0, "spread": true, "children": ' + str.replace(/childList/g, 'children') +'}]';
						var obj = JSON.parse(str);
						layer.close(i);
						var j = layer.open({
							btn: ['取消'],
							type: 1,
							area: ['400px', h+'px'],
							maxmin: true,
							content: '<ul id="demo1" style="padding: 5px 10px"></ul>'
						});
						layui.tree({
						    elem: '#demo1',
						    target: '_blank',
						    click: function(item){
						    	$('#pname').val(item.name)
						    	$('#parentId').val(item.id)
						    	$('#parentIds').val((item.id == 0?0:item.parentIds+','+item.id))
						    	layer.close(j)
						    },
						    type: 'normal',
						    nodes: obj
						});
					},
					error: function(xhr, stat){}
				})
			})
		})
	}
	e.sTree = new kt;
	e.sTree.render();
}(window);