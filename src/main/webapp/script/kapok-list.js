;!function(e){
	"use strict";
	var kl = function(){}
	
	kl.prototype.render = function(){
		var n = {
			//合并表格单元格
			table_colspan: function(){
				var empty = $('.layui-table').find('.table-empty');
				empty.length > 0 && empty.attr('colspan', $('.layui-table').find('th').length);
			},
			//分页跳转
			page: function(){
				var init = function(){
					$(document).on('click', '#page a', function(){
						$('#pageIndex').val($(this).data('page'));
						$('#form').submit();
					})
					$(document).on('click', '.layui-laypage-btn', function(){
						$('#pageIndex').val($(this).prev().val());
						$('#form').submit();
					});
				}
				$('#page').length > 0 && init();
			},
			//列表页工具栏
			list_tools: function(){
				var url = window.location.pathname,
				openDialog = function(c, t){
					layui.use(['layer'], function() {
						var i = layer.open({
							  type: t,
							  content: c,
							  area: ['990px', '400px'],
							  maxmin: true
							});
						layer.full(i);
					})
				},
				error = function(xhr, stat){
					if(stat == 'parsererror' && xhr.status == '200'){
						layer.open({
							title: '<i class="fa fa-warning"></i> 温馨提示：权限受限',
							content: '访问受限，请联系管理员'
						});  
					}
					if(stat == 'error' && xhr.status == '404'){
						layer.open({
							title: '<i class="fa fa-warning"></i> 温馨提示：️404',
							content: '访问不存在，请联系管理员'
						});  
					}
					if(xhr.status == '500'){
						layer.open({
							title: '<i class="fa fa-warning"></i> 温馨提示：500 ️',
							content: '访问出错，请联系管理员'
						});  
					}
				},
				t = {
					//最大化cookies状态
					cookies_stat: function(){
						var stat = cookies.getCookie("expand" + url) ? true : false,
						init = function() {
							var html = $('.table-panel').html();
							html && (html = html.replace(/class="hide"/g, "").replace(/expand/g, 'compress').replace(/最大化/g, "最小化")), openDialog(html, 1);
						}
						stat && init();
					},
					//工具栏点击事件
					tools_event: function(){
						var reg = /(list|form|recycle)$/g;
						$(document).on('click', 'a[data-action="expand"]', function(){
							var html = $('.table-panel').html();
							html && (html = html.replace(/class="hide"/g, "").replace(/expand/g, 'compress').replace(/最大化/g, "最小化")), openDialog(html, 1), cookies.setCookie("expand" + url, true, 7);;
						});
						$(document).on('click', 'a[data-action="compress"]', function(){
							$('.layui-layer-close').click();
						});
						$(document).on('click', '.layui-layer-close', function(){
							cookies.delCookie("expand" + url);
						});
						$(document).on('click', 'a[data-action="edit"],a[data-action="add"],a[data-action="recycle"]', function(){
							var link = $(this).data('link');
							link && openDialog(link, 2);
						});
						$(document).on('click', '[data-action="stat"]', function(){
							var _class = $(this).find('i').attr('class'), id = $(this).parent().data('id'), msg = _class.indexOf('ban') != -1?'禁用':'启用';
							var success = function(data){
								data.key == 1 ? 
								(layer.msg(msg+'数据成功~', {icon: 1, shade: 0.1}), setTimeout("window.location.reload();", 700)) : 
								layer.msg(msg+'数据失败~', {icon: 2, shade: 0.1});
							}
							ajax.req(url+"/stat", {id: id}, 'post', 'json', success, error, null);
						})
						//删除
						$(document).on('click', '[data-action="delete"]', function(){
							var ids = kapok.getCheckBoxValue(),
							init = function() {
								var success = function(data){
									data.key == 1 ?
									(layer.msg('移动所选数据成功', {icon: 1}), setTimeout("window.location.reload();", 700)) :
									layer.msg('移动所选数据失败', {icon: 2});
								}
								layer.confirm('是否将所选数据移至回收站？', {
									icon: 0, btn: ['确定','取消']
								}, function(){
									ajax.req(url+"/delete", {ids: ids}, 'post', 'json', success, error, null);
								})
							}
							ids.length > 0 ? init() : layer.msg('请勾选要移动的数据', {icon: 2});
						})
						//恢复
						$(document).on('click', '[data-action="recover"]', function(){
							var ids = kapok.getCheckBoxValue(),
							init = function() {
								var index = parent.layer.getFrameIndex(window.name),
								success = function(data){
									data.key == 1 ?
									(layer.msg('恢复所选数据成功', {icon: 1}), setTimeout("parent.layer.close("+index+");parent.window.location.reload();", 700)) :
									layer.msg('恢复所选数据失败', {icon: 2});
								}
								layer.confirm('是否恢复所选数据？', {
									icon: 0, btn: ['确定','取消']
								}, function(){
									ajax.req(url+"/recover", {ids: ids}, 'post', 'json', success, error, null);
								})
							}
							ids.length > 0 ? init() : layer.msg('请勾选要恢复的数据', {icon: 2});
						})
						//彻底删除
						$(document).on('click', '[data-action="clean"]', function(){
							var ids = kapok.getCheckBoxValue(),
							init = function() {
								var success = function(data){
									data.key == 1 ?
									(layer.msg('删除所选数据成功', {icon: 1}), setTimeout("window.location.reload();", 700)) :
									layer.msg('删除所选数据失败', {icon: 2});
								}
								layer.confirm('是否删除所选数据？', {
									icon: 0, btn: ['确定','取消']
								}, function(){
									ajax.req(url+"/delete", {ids: ids}, 'post', 'json', success, error, null);
								})
							}
							ids.length > 0 ? init() : layer.msg('请勾选要删除的数据', {icon: 2});
						})
						//导出
						$(document).on('click', '[data-action="export"]', function(){
							var data = $('#form').serialize();
							url += "/export";
							var i = layer.confirm('是否把该搜索结果导出Excel表格？', {
								btn: ['确定','取消']
							}, function(){
								window.location.href = url+'?'+data;
								layer.close(i);
							});
						})
						//回收页面导出
						$(document).on('click', '[data-action="recycle_export"]', function(){
							var data = $('#form').serialize() + "&isDelete=y";
							url = url.replace(reg, "") + "export";
							var i = layer.confirm('是否把该搜索结果导出Excel表格？', {
								btn: ['确定','取消']
							}, function(){
								window.location.href = url+'?'+data;
								layer.close(i);
							});
						})
					}
				}
				$.each(t, function(i, o){
					o();
				})
			}
		}
		$.each(n, function(i, o){
			o();
		})
	}
	e.kapokList = new kl;
	e.kapokList.render();
}(window);

