layui.use([ 'table', 'form', 'element', 'laytpl', 'laydate' ], function() {
	"use strict";
	var table = layui.table, 
		laytpl = layui.laytpl, 
		form = layui.form, 
		element = layui.element, 
		laydate = layui.laydate,
		path = window.location.pathname,
		formData = urlHash.toJson();
	
	var init = {
		//表格初始化
		table: function(){
			var t = table.render({
				id: 'listTable',
				elem : '#listTable',
				cellMinWidth: 100,
				url : path + '/data',
				request : {
					pageName : 'pageIndex', // 页码的参数名称，默认：page
					limitName : 'pageSize' // 每页数据量的参数名，默认：limit
				},
				where: formData,
				height : 348,
				cols : [ initData.colsArr ],
				skin : 'row', // 表格风格
				even : true,
				page : true, // 是否显示分页
				limits : [ 10, 20, 30, 40, 50, 60, 70 ],
				limit : 10  //每页默认显示的数量
			});
		},
		//tab初始化
		tabInit: function(){
			//var tab = $('.layui-tab[lay-filter="tabDemo"] ul')
			
			//element.tabChange('tabDemo', urlHash.get('tab'));
		},
		//tab跳转参数初始化
		tabJump: function(){
			element.on('tab(tabDemo)', function(obj){
				var id = this.getAttribute('lay-id')
				if((path+'/list').indexOf('/' + id) > -1) return
//				//urlHash.set('tab', id), urlHash.set('redirect', path)
				var url = path + '/'+ id + '#redirect=' + path + window.location.search.replace(/&/g, '[and]');
				//console.log(url)
				window.location.href = url
			});
		},
		//条件查询表达初始化
		demoRender: function(){
			initData.dictsArr && initData.dictsArr.length > 0 ? ajax.req( path + '/dict', {dict: initData.dictsArr}, 'post', 'json', function(data){
				data['model'] = formData, option.formTpl(data)
			}): option.formTpl({model: formData});
		},
		//时间控件初始化
		laydate: function(){
			laydate.render({
			    elem: '#startTime',
			    type: 'datetime'
			});
			laydate.render({
			    elem: '#endTime',
			    type: 'datetime'
			});
		},
		highLightNav: function(){
			$('.layui-nav-child').find('a[href="'+path+'"]').parent().addClass('layui-this')
		}
	}
	
	var option = {
		//删除操作
		del: function(obj){
			layer.confirm('是否删除？', {
				icon: 0, btn: ['确定','取消']
			}, function(){
				$.ajax({ url: path + '/del', data: {id: obj.data.id}, type: 'post', dataType: 'json', success: function(res){
					    res.key == 1 ? (layer.msg('删除成功'), obj.del()) : (layer.msg('删除失败'), console.log(res.msg));
					},
					error: option['error'],
					complete: function(xhr, stat) {}
				});
			})
		},
		//编辑操作
		edit: function(obj){
			window.location.href = path + '/form?id=' + obj.data.id + '#redirect=' + path + window.location.search.replace(/&/g, '[and]');
		},
		isBan: function(obj){
			$.ajax({ url: path + '/ban', data: {id: obj.value}, type: 'post', dataType: 'json', success: function(res){
				    res.key == 1 ? layer.msg('已'+obj.othis[0].innerText): (layer.msg(obj.othis[0].innerText + '失败'), console.log(res.msg));
				},
				error: option['error'],
				complete: function(xhr, stat) {}
			});
		},
		error: function(xhr, stat){
			stat == 'parsererror' && xhr.status == '200' && layer.open({
					title: '<i class="fa fa-warning"></i> 温馨提示：权限受限',
					content: '访问受限，请联系管理员'
				}); 
			stat == 'error' && xhr.status == '404' && layer.open({
					title: '<i class="fa fa-warning"></i> 温馨提示：404 ️',
					content: '访问不存在，请联系管理员'
				});  
			xhr.status == '500' && layer.open({
					title: '<i class="fa fa-warning"></i> 温馨提示：500',
					content: '访问出错，请联系管理员'
				}); 
		},
		formTpl: function(data){
			console.log(data)
			var getTpl = formDemo.innerHTML, 
				view = document.getElementById('formView');
			laytpl(getTpl).render(data, function(html) {
				view.innerHTML = html;
			});
			form.render();
		}
	}
	
	$.each(init, function(n, o){
		o();
	})

	form.on('submit(listSubmit)', function(data){
		urlHash.setAll(data.field)
		table.reload('listTable', {
			where: data.field
		});
		return false;
	})
	
	table.on('edit(listTable)', function(obj){
	    var value = obj.value, //得到修改后的值
	    field = obj.field, //得到字段
	    data = {};
	    data[field] = value;
	    data['id'] = obj.data.id;   //obj.data得到所在行所有键值
	    
	    $.ajax({ url: path + '/update', data: data, type: 'post', dataType: 'json', success: function(res){
			    res.key == 1 ? layer.msg('修改成功') : (layer.msg('修改失败'), console.log(res.msg));
			},
			error: option['error'],
			complete: function(xhr, stat) {}
		});
	});
	
	table.on('tool(listTable)', function(obj){
		var event = obj.event;
		option[event].call(this, obj);
	});
	
	form.on('switch(banDemo)', function(obj){
	    option['isBan'].call(this, obj);
	});

	$(document).on('click', '[data-action="export"]', function(){
							var data = $('#formView').serialize();
							path += "/export";
							var i = layer.confirm('是否把该搜索结果导出Excel表格？', {
								btn: ['确定','取消']
							}, function(){
								window.location.href = path+'?'+data;
								layer.close(i);
							});
						})
	
//	var element = layui.element;
//	  
//	  //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
//	  var layid = location.hash.replace(/^#test1=/, '');
//	  
//	  element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
//	  
//	  //监听Tab切换，以改变地址hash值
//	  element.on('tab(test1)', function(){
//	    location.hash = 'test1='+ this.getAttribute('lay-id');
//	  });
})