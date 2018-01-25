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
		idproc: function(){
			var url = '/kapok/authentication/dashboard/data', limit = urlHash.get('idprocLimit') || 7, elem = '#idproc a[data-act="slide"]';
			var success = function(data){
				//console.log(data)
				var html = []
				$.each(data.type, function(n, o){
					var temp = ['\
							       <div class="table-panel col-7">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td">' + o.typeCode + '</div>\
										</div>\
										<div class="table-thead">\
											<div class="table-td col-3">时间</div>\
											<div class="table-td col-3">笔数</div>\
											<div class="table-td col-3">查得率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].chadRateState+'">'+ p[o.typeCode].chadRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#idproc').html(html.slice(0, limit).join('') +  config.option(limit))
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 7 : html.length;
					urlHash.set('idprocLimit', limit)
					$('#idproc').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({ url: url, data:{type: 'idproc'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'idproc'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		idbuss: function(){
			var url = '/kapok/authentication/dashboard/data', limit = urlHash.get('idbussLimit') || 7, elem = '#idbuss a[data-act="slide"]';
			var success = function(data){
				var html = []
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
											<div class="table-td col-3">查得率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-3"><div class="color'+ p[o.typeCode].chadRateState+'">'+ p[o.typeCode].chadRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#idbuss').html(html.slice(0, limit).join('') +  config.option(limit))
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 7 : html.length;
					urlHash.set('idbussLimit', limit)
					$('#idbuss').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({url: url, data:{type: 'idbuss'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({url: url, data:{type: 'idbuss'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		idproduct: function(){
			var url = '/kapok/authentication/dashboard/data', limit = urlHash.get('idproductLimit') || 6, elem = '#idproduct a[data-act="slide"]';
			var success = function(data){
				console.log(data)
				var html = []
				$.each(data.type, function(n, o){
					var temp = ['\
							       <div class="table-panel col-6">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td">' + o.typeName + '</div>\
										</div>\
										<div class="table-thead">\
											<div class="table-td col-3">时间</div>\
											<div class="table-td col-6">笔数</div>\
											<div class="table-td col-4">查得率</div>\
											<div class="table-td col-4">成功率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-6"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-4"><div class="color'+ p[o.typeCode].chadRateState+'">'+ p[o.typeCode].chadRate.toFixed(2) +'</div></div>\
								<div class="table-td col-4"><div class="color'+ p[o.typeCode].sucRateState+'">'+ p[o.typeCode].sucRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#idproduct').html(html.slice(0, limit).join('') +  config.option(limit))
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 6 : html.length;
					urlHash.set('idproductLimit', limit)
					$('#idproduct').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({ url: url, data:{type: 'idproduct'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'idproduct'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		idmerchant: function(){
			var url = '/kapok/authentication/dashboard/data', limit = urlHash.get('idmerchantLimit') || 6, elem = '#idmerchant a[data-act="slide"]';
			var success = function(data){
				console.log(data)
				var html = []
				$.each(data.type, function(n, o){
					var temp = ['\
							       <div class="table-panel col-6">\
									<div class="table">\
										<div class="table-thead">\
											<div class="table-td"><a href="/kapok/ident/chart/merchant?code='+ o.typeCode +'">' + o.typeCode + '</a></div>\
										</div>\
										<div class="table-thead">\
											<div class="table-td col-3">时间</div>\
											<div class="table-td col-6">笔数</div>\
											<div class="table-td col-4">查得率</div>\
											<div class="table-td col-4">成功率</div>\
										</div>']
					$.each(data.list, function(m, p){
						temp.length < 6 && p[o.typeCode] && temp.push('\
								<div class="table-tbody">\
								<div class="table-td col-3">'+ config.dateFormat(p[o.typeCode].startDate) +'</div>\
								<div class="table-td col-6"><div class="color'+ p[o.typeCode].allCountState+'">'+ p[o.typeCode].allCount +'</div></div>\
								<div class="table-td col-4"><div class="color'+ p[o.typeCode].chadRateState+'">'+ p[o.typeCode].chadRate.toFixed(2) +'</div></div>\
								<div class="table-td col-4"><div class="color'+ p[o.typeCode].sucRateState+'">'+ p[o.typeCode].sucRate.toFixed(2) +'</div></div>\
							</div>\
						')
					})
					temp.push('</div></div>')
					html.push(temp.join(""));
				})
				$('#idmerchant').html(html.slice(0, limit).join('') +  config.option(limit))
				!config.hasEvents(elem) && $(document).on('click', elem, function(){
					limit = limit == html.length ? 6 : html.length;
					urlHash.set('idmerchantLimit', limit)
					$('#idmerchant').html(html.slice(0, limit).join('') + config.option(limit))
				})
			}
			$.ajax({ url: url, data:{type: 'idmerchant'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'idmerchant'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		today: function(){
			var url = '/kapok/authentication/dashboard/data/today'; 
				
			var success = function(data){
				var allCount = 0,
					sucCount = 0,
					chadCount = 0;
				$.each(data.list, function(n, o){
					for(var e in o){
						allCount += o[e].allCount;
						sucCount += o[e].sucCount;
						chadCount += o[e].chadCount;
					}
				})
				$('#allCount').text(allCount), $('#sucRate').text((sucCount/allCount*100).toFixed(2)+'%'), $('#chadRate').text((chadCount/allCount*100).toFixed(2)+'%')
			}
			$.ajax({ url: url, data:{type: 'idproduct'}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: url, data:{type: 'idproduct'}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		}
	}
	
	$.each(init, function(e, i) {
		i()
	})
});