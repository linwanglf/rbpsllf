;!function(e){
	"use strict";
//	var layui_icon = [{key: '&#xe652;'}, {key: '&#xe651;'}, {key: '&#xe6fc;'}, {key: '&#xe6ed;'}, 
//	                  {key: '&#xe688;'}, {key: '&#xe645;'}, {key: '&#xe611;'}, {key: '&#xe614;'}, 
//	                  {key: '&#xe60f;'}, {key: '&#xe615;'}, {key: '&#xe641;'}, {key: '&#xe620;'}, 
//	                  {key: '&#xe628;'}, {key: '&#x1006;'}, {key: '&#x1007;'}, {key: '&#xe629;'},
//	                  {key: '&#xe600;'}, {key: '&#xe617;'}, {key: '&#xe606;'}, {key: '&#xe609;'},
//	                  {key: '&#xe60a;'}, {key: '&#xe62c;'}, {key: '&#x1005;'}, {key: '&#xe61b;'},
//	                  {key: '&#xe610;'}, {key: '&#xe602;'}, {key: '&#xe603;'}, {key: '&#xe62d;'},
//	                  {key: '&#xe62e;'}, {key: '&#xe62f;'}, {key: '&#xe61f;'}, {key: '&#xe601;'},
//	                  {key: '&#xe630;'}, {key: '&#xe631;'}, {key: '&#xe654;'}, {key: '&#xe642;'},
//	                  {key: '&#xe640;'}, {key: '&#xe61a;'}, {key: '&#xe621;'}, {key: '&#xe632;'},
//	                  {key: '&#xe618;'}, {key: '&#xe608;'}, {key: '&#xe633;'}, {key: '&#xe61c;'},
//	                  {key: '&#xe634;'}, {key: '&#xe607;'}, {key: '&#xe635;'}, {key: '&#xe636;'},
//	                  {key: '&#xe60b;'}, {key: '&#xe619;'}, {key: '&#xe637;'}, {key: '&#xe61d;'},
//	                  {key: '&#xe612;'}, {key: '&#xe605;'}, {key: '&#xe638;'}, {key: '&#xe60c;'},
//	                  {key: '&#xe616;'}, {key: '&#xe613;'}, {key: '&#xe61e;'}, {key: '&#xe60d;'}]
	var kt = function(){}
	kt.prototype.render = function(){
		layui.use(['tree', 'layer'], function() {
			$(document).on('click', '#res_btn', function(){
				var h = $(window).height() - 100, 
					roleId = $(this).parent().data('id'),
					i;
				i = layer.load(2);
				$.ajax({
					url: '/dict/role/resource',
					type: 'post',
					dataType: 'json',
					success: function(res, stat, xhr){
						var str = JSON.stringify(res);
						str = '[{"name": "菜单节点","id": 0, "parentIds": 0, "spread": true, "children": ' + str.replace(/childList/g, 'children') +'}]';
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
			$('#icon_btn').on('click', function(){
				var h = $(window).height() - 100, w = $(window).width() - 100;
				
				var i = layer.open({
					btn: ['确定', '取消'],
					type: 1,
					area: [w+'px', h+'px'],
					maxmin: true,
					content: icon_content(),
					yes: function(index, layero){
						var e = $(layero).find('.icon-content li.selected i').attr('class');
						$('.icon-item i').attr('class', e), $('.icon-item input').val(e), layer.close(index)
					}
				});
			})
			var icon_content = function(){
				var arr = ['<ul class="icon-content">'];
				$.ajaxSettings.async = false;
				$.getJSON('/script/json/font-awesome.json', function(data){
					$.each(data.icon, function(n, o) {
						arr.push('<li><i class="'+o.key+'"></i></li>')
					})
				}), arr.push('</ul>'), console.log(arr.join(""))
				return arr.join("")
			}
			$(document).on('click', '.icon-content li', function(){
				$(this).addClass('selected').siblings().removeClass('selected')
			})
		})
	}
	e.sTree = new kt;
	e.sTree.render();
}(window);