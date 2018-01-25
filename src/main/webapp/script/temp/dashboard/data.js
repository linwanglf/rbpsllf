layui.use(['table'], function(){
	
	var config = {
		hasEvents: function(name){
			var arr = $._data(document, 'events'), flag = false;
			arr.click && $.each(arr.click, function(n, o){
				o.selector == name && (flag = true)
			})
			return flag
		},
		option: function(limit){
			return '<a class="layui-btn layui-btn-normal layui-btn-mini load" data-act="slide"><i class="fa fa-angle-double-'+(limit>7?'up':'down')+'"></i>'+(limit>7?'收起':'展开')+'</a>'
		},
		dateFormat: function(date){
			
			return new Date(date.replace(/-/g, '/')).format("hh:mm:ss")
		}
	}
	
	var init = {
		bank: function(){
			var url = '/kapok/dashboard/data', limit = urlHash.get('bankLimit') || 7, elem = '#bankTable a[data-act="slide"]';
			var success = function(data){
				console.log(data)
				var html = [];
				$.each(data.type, function(n, o){
					var temp = ['\
					            <div class="table-panel col-7">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td">' + o.typeName + '</div>\
										</div>\
										<div class="table-thead">\
											<div class="table-td col-3">时间</div>\
											<div class="table-td col-3">笔数</div>\
											<div class="table-td col-3">成功率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].sucRateState+'">'+ p[o.typeCode].sucRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#bankTable').html(html.slice(0, limit).join('') + config.option(limit) + '<a href="/kapokbam/dashboard/bank" class="layui-btn layui-btn-mini more">更多详情 <i class="fa fa-angle-double-right"></i></a>')
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 7 : html.length;
					urlHash.set('bankLimit', limit)
					$('#bankTable').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({ url: url, data:{type: 'bank'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'bank'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		proc: function(){
			var url = '/kapok/dashboard/data', limit = urlHash.get('procLimit') || 7, elem = '#procTable a[data-act="slide"]';
			var success = function(data){
				var html = []
				$.each(data.type, function(n, o){
					var temp = ['\
							       <div class="table-panel col-7">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td"><a href="/kapok/chart/proc?code='+ o.typeCode +'">' + o.typeCode + '</a></div>\
										</div>\
										<div class="table-thead">\
											<div class="table-td col-3">时间</div>\
											<div class="table-td col-3">笔数</div>\
											<div class="table-td col-3">成功率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].sucRateState+'">'+ p[o.typeCode].sucRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#procTable').html(html.slice(0, limit).join('') +  config.option(limit) + '<a href="/kapokbam/dashboard/proc" class="layui-btn layui-btn-mini more">更多详情 <i class="fa fa-angle-double-right"></i></a>')
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 7 : html.length;
					urlHash.set('procLimit', limit)
					$('#procTable').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({ url: url, data:{type: 'proc'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'proc'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		merchant: function(){
			var url = '/kapok/dashboard/data', limit = urlHash.get('merchantLimit') || 7, elem = '#merchantTable a[data-act="slide"]';
			var success = function(data){
				var html = []
				$.each(data.type, function(n, o){
					var temp = ['\
							       <div class="table-panel col-7">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td"><a href="/kapok/chart/merchant#code='+ o.typeCode +'&name='+o.typeName+'">' + o.typeName.replace(/(\(机构)\)|(平台)|(有限公司)|(_)/g, '') + '</a></div>\
										</div>\
										<div class="table-thead">\
											<div class="table-td col-3">时间</div>\
											<div class="table-td col-3">笔数</div>\
											<div class="table-td col-3">成功率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].sucRateState+'">'+ p[o.typeCode].sucRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#merchantTable').html(html.slice(0, limit).join('') +  config.option(limit)+ '<a href="/kapokbam/dashboard/merchant" class="layui-btn layui-btn-mini more">更多详情 <i class="fa fa-angle-double-right"></i></a>')
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 7 : html.length;
					urlHash.set('merchantLimit', limit)
					$('#merchantTable').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({ url: url, data:{type: 'merchant'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'merchant'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		business: function(){
			var url = '/kapok/dashboard/data', limit = urlHash.get('businessLimit') || 7, elem = '#merchantTable a[data-act="slide"]';
			var success = function(data){
				var html = []
				var length = data.type.length;
				$.each(data.type, function(n, o){
					var temp = ['\
						       <div class="table-panel col-'+length+'">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td">' + o.typeName + '</div>\
										</div>\
										<div class="v-table-tbody">\
											<div class="v-table-tr col-'+(limit+1)+'">\
												<div class="v-table-td">时间</div>\
												<div class="v-table-td">请求量</div>\
												<div class="v-table-td">成功率</div>\
											</div>']
					$.each(data.list, function(m, p){
						m < limit && p[o.typeCode] && temp.push('\
									<div class="v-table-tr col-'+(limit+1)+'">\
										<div class="v-table-td">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
										<div class="v-table-td"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
										<div class="v-table-td"><div class="color'+ p[o.typeCode].sucRateState+'">'+ p[o.typeCode].sucRate.toFixed(2) +'</div></div>\
									</div>\
								')
					})
					temp.push('</div></div></div>')
					html.push(temp.join(""));
				})
				$('#businessTable').html(html.join(''))
			}
			$.ajax({url: url, data:{type: 'buss'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({url: url, data:{type: 'buss'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		module: function(){
			var success = function(data){
				var html = [], time = data.time.slice(0, 5)
				$.each(time, function(n, o){
					var temp = ['<div class="table-panel col-'+time.length+'">\
								<div class="table">\
								<div class="table-thead">\
									<div class="table-td">' + config.dateFormat(o) + '</div>\
								</div>\
								<div class="table-thead">\
									<div class="table-td col-wx-6">系统名</div>\
									<div class="table-td col-wx-2">并发量</div>\
									<div class="table-td col-wx-2">错误数</div>\
								</div>']
					for(var i in data.list[n]){
						var obj = data.list[n];
						temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-wx-6">'+ obj[i].moduleId +'</div>\
								<div class="table-td col-wx-2"><div class="color'+ obj[i].allCountState+'">'+ obj[i].allCount +'</div></div>\
								<div class="table-td col-wx-2"><div class="color'+ obj[i].sucRateState+'">'+ obj[i].allErrors +'</div></div>\
							</div>\
						')
					}
					temp.push('</div></div>')
					html.push(temp.join(''))
				})
				$('#moduleTable').html(html.join(''))
			}
			$.ajax({ url: '/kapok/dashboard/data', data:{type: 'module'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: '/kapok/dashboard/data', data:{type: 'module'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		}
	}
	
	$.each(init, function(e, i) {
		i()
	})
});