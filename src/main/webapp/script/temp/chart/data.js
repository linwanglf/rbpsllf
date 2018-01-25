layui.use(['layer'], function(){
	var code = urlHash.get('code')
	var init = {
		line: function(){
			var success = function(data){
				var mainchart = echarts.init(document.getElementById('mainchart'));
				var allCount = [], 
					sucCount = [], 
					sucRate = [], 
					category = [], 
					avgTime = [], 
					failCount = [], 
					procCount = [],
					todayAllCount = 0,
					//todayProcCount = 0,
					todaySucCount = 0,
					todayFailCount = 0;
				$.each(data.list, function(n, o){
					category.push(o.startDate)
					allCount.push(o.allCount)
					sucCount.push(o.sucCount)
					sucRate.push(o.sucRate.toFixed(2))
					avgTime.push(o.avgRspTime)
					procCount.push(o.processingCount)
					failCount.push(o.allCount - o.sucCount)
					todayAllCount += o.allCount
					//todayProcCount += o.processingCount
					todaySucCount += o.sucCount
					todayFailCount += o.allCount - o.sucCount - o.userCount
				})
				$.each(data.redis, function(n, o){
					category.push(o.startDate)
					allCount.push(o.allCount)
					sucCount.push(o.sucCount)
					sucRate.push(o.sucRate.toFixed(2))
					avgTime.push(o.avgRspTime)
					procCount.push(o.processingCount)
					failCount.push(o.allCount - o.sucCount)
					todayAllCount += o.allCount
					//todayProcCount += o.processingCount
					todaySucCount += o.sucCount
					todayFailCount += o.allCount - o.sucCount - o.userCount
				})
				$('#allCount').text(todayAllCount), $('#failCount').text(todayFailCount), $('#succCount').text(todaySucCount)//, $('#procCount').text(todayProcCount)
				var dataZoomStart = category.length > 30 ? 100 - (30 / category.length * 100).toFixed(2) : 0
				var option = {
				    title: {
				        text: urlHash.get('name'),
				        x: 'center',
				        textStyle: {
							 color: '#fb9104',
							 fontSize: '27',
						 }
				    },
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'shadow',
				            label: {
				                show: true,
				                backgroundColor: '#333'
				            }
				        },
				        formatter: function(params){
                            var result = [params[0].name + '<br>'];
                            for(var i = 0; i < params.length; i++){
                                    var marker = typeof(params[i].color) == 'object' ? params[i].marker.replace(/\[object Object\]/gi, params[i].color.colorStops[0].color) : params[i].marker
                                    result.push(marker + params[i].seriesName + ': ' + params[i].data + '<br>')
                            }
                            return result.join('');
				        }
				    },
				    legend: {
				        orient: 'horizontal',
				    	top: '40px',
				    	data:['系统成功率', '交易笔数', '失败笔数', '成功笔数', '处理中笔数'],
				        textStyle: {
				            color: '#ccc'
				        }
				    },
				    axisPointer: {
				        link: {xAxisIndex: 'all'}
				    },
				    dataZoom: [
				        {
				            show: true,
				            realtime: true,
				            start: dataZoomStart,
				            end: 100,
				            xAxisIndex: [0, 1, 2, 3],
				            textStyle: {
				            	color: '#ccc'
				            }
				        },
				        {
				            type: 'inside',
				            realtime: true,
				            start: dataZoomStart,
				            end: 100,
				            xAxisIndex: [0, 1, 2, 3]
				        }
				    ],
				    grid: [{
				    	left: 50,
				        right: 40,
				        top: '20%',
				        height: '31%',
				        width: '45%'
				    }, {
				        right: 30,
				        top: '20%',
				        height: '31%',
				        width: '45%'
				    }, {
				        left: 50,
				        right: 40,
				        top: '60%',
				        height: '31%',
				        width: '45%'
				    }, {
				        right: 30,
				        top: '60%',
				        height: '31%',
				        width: '45%'
				    }],
				    xAxis : [
				        {
				            type : 'category',
//				            boundaryGap : false,
				            axisLine: {
				            	onZero: true,
				            	lineStyle: {
				            		color: '#ccc'
				            	}
				            },
				            data: category,
				            axisLabel:{
				            	formatter: function(v, i){
				            		return new Date(v.replace(/-/g, '/')).format('hh:mm:ss');
				            	}
				            }
				        },
				        {
				            gridIndex: 1,
				            type : 'category',
				            axisLine: {
				            	onZero: true,
				            	lineStyle: {
				            		color: '#ccc'
				            	}
				            },
				            data: category,
				            axisLabel:{
				            	formatter: function(v, i){
				            		return new Date(v.replace(/-/g, '/')).format('hh:mm:ss');
				            	}
				            }
				        },
				        {
				            gridIndex: 2,
				            type : 'category',
				            axisLine: {
				            	onZero: true,
				            	lineStyle: {
				            		color: '#ccc'
				            	}
				            },
				            data: category,
				            axisLabel:{
				            	formatter: function(v, i){
				            		return new Date(v.replace(/-/g, '/')).format('hh:mm:ss');
				            	}
				            }
				        },
				        {
				            gridIndex: 3,
				            type : 'category',
				            axisLine: {
				            	onZero: true,
				            	lineStyle: {
				            		color: '#ccc'
				            	}
				            },
				            data: category,
				            axisLabel:{
				            	formatter: function(v, i){
				            		return new Date(v.replace(/-/g, '/')).format('hh:mm:ss');
				            	}
				            }
				        }
				    ],
				    yAxis : [
				        {
				            name : '系统成功率(%)',
				            type : 'value',
				            axisLine: {
					    		lineStyle: {
					    			color: '#ccc'
					    		}
					    	},
					    	splitLine: {show: false},
				            max : 100
				        },
				        {
				            gridIndex: 1,
				            name : '总/成功/失败笔数(笔)',
				            type : 'value',
				            axisLine: {
					    		lineStyle: {
					    			color: '#ccc'
					    		}
					    	},
					    	splitLine: {show: false}
				            //inverse: true
				        },
				        {
				            gridIndex: 2,
				            name : '今日处理中笔数(笔)',
				            type : 'value',
				            axisLine: {
					    		lineStyle: {
					    			color: '#ccc'
					    		}
					    	},
					    	splitLine: {show: false}
				            //inverse: true
				        },
				        {
				            gridIndex: 3,
				            name : '平均响应时间(s)',
				            type : 'value',
				            axisLine: {
					    		lineStyle: {
					    			color: '#ccc'
					    		}
					    	},
					    	splitLine: {show: false}
				            //inverse: true
				        }
				    ],
				    series : [
				        {
				            name:'系统成功率',
				            type:'line',
				            symbolSize: 8,
				            hoverAnimation: false,
				            data:sucRate
				        },
				        {
					        name: '交易笔数',
					        type: 'bar',
					        barWidth: 7,
					        xAxisIndex: 1,
				            yAxisIndex: 1,
					        itemStyle: {
					            normal: {
					                color: new echarts.graphic.LinearGradient(
					                    0, 0, 0, 1,
					                    [
					                        {offset: 0, color: '#007ccb'},
					                        {offset: 1, color: '#4eabe6'}
					                    ]
					                )
					            }
					        },
					        data: allCount
					    },
					    {
					        name: '失败笔数',
					        type: 'bar',
					        barWidth: 7,
					        xAxisIndex: 1,
				            yAxisIndex: 1,
					        stack: '交易笔数',
					        itemStyle: {
					            normal: {
					                color: new echarts.graphic.LinearGradient(
					                    0, 0, 0, 1,
					                    [
					                        {offset: 0, color: '#ea0000'},
					                        {offset: 1, color: '#ee6464'}
					                    ]
					                )
					            }
					        },
					        data: failCount
					    },
					    {
					        name: '成功笔数',
					        type: 'bar',
					        barWidth: 7,
					        xAxisIndex: 1,
				            yAxisIndex: 1,
					        stack: '交易笔数',
					        itemStyle: {
					            normal: {
					                color: new echarts.graphic.LinearGradient(
					                    0, 0, 0, 1,
					                    [
					                        {offset: 0, color: '#00ba83'},
					                        {offset: 1, color: '#45d8ad'}
					                    ]
					                )
					            }
					        },
					        data: sucCount
					    },
					    {
					        name: '处理中笔数',
					        type: 'bar',
					        barWidth: 10,
					        xAxisIndex: 2,
				            yAxisIndex: 2,
					        itemStyle: {
					            normal: {
					                barBorderRadius: 5,
					                color: new echarts.graphic.LinearGradient(
					                    0, 0, 0, 1,
					                    [
					                        {offset: 0, color: '#14c8d4'},
					                        {offset: 1, color: '#43eec6'}
					                    ]
					                )
					            }
					        },
					        data: procCount
					    },
				        {
				            name:'平均响应时间',
				            type:'line',
				            xAxisIndex: 3,
				            yAxisIndex: 3,
				            symbolSize: 8,
				            hoverAnimation: false,
				            lineStyle:{
				            	normal: {
				            		color: '#ffd025'
				            	}
				            },
				            data: avgTime
				        }
				    ]
				};
				mainchart.setOption(option)
				category[category.length-1] && 
				setInterval(function() {
					$.ajax({ url: '/kapok/chart/merchant/new', data:{type: 'merchant', code: code, startTime: category[category.length-1]}, type: 'post', dataType: 'json', success: function(data){
						$.each(data.redis, function(n, o){
							category.push(o.startDate)
							allCount.push(o.allCount)
							sucCount.push(o.sucCount)
							sucRate.push(o.sucRate.toFixed(2))
							avgTime.push(o.avgRspTime)
							procCount.push(o.processingCount)
							failCount.push(o.allCount - o.sucCount)
							todayAllCount += o.allCount
							//todayProcCount += o.processingCount
							todaySucCount += o.sucCount
							todayFailCount += o.allCount - o.sucCount - o.userCount
						})
						$('#allCount').text(todayAllCount), $('#failCount').text(todayFailCount), $('#succCount').text(todaySucCount)//, $('#procCount').text(todayProcCount)
						//dataZoomStart = category.length > 30 ? 100 - (30 / category.length * 100).toFixed(2) : 0
						mainchart.setOption({
							//dataZoom: [{start: dataZoomStart}, {start: dataZoomStart}],
							xAxis: [{data: category}, {data: category}, {data: category}, {data: category}],
							series: [{data: sucRate}, {data: allCount}, {data: failCount}, {data: sucCount}, {data: procCount}, {data: avgTime}]
						})
					} })
				}, 60000)
			}
			$.ajax({ url: '/kapok/chart/merchant', data:{type: 'merchant', code: code}, type: 'post', dataType: 'json', success: success })

		},
		pieOfBank: function(){
			var success = function(data){
				var obj = [].concat.apply([], data.list),  //转为一维数组
					bank = {}, merchant = []
				$.each(obj, function(n, o){
					o.merchantCode == code && (merchant.push(o), !bank[o.bankCode] && (bank[o.bankCode] = {code: o.bankCode, name: o.bankName, count: 0}))
				})
				$.each(merchant, function(n, o){
					bank[o.bankCode].count += o.allCount; 
				})
				var optData = {series: [], legend: []}
				for(var i in bank){
					optData.legend.push(bank[i].name)
					optData.series.push({value: bank[i].count, name: bank[i].name})
				}
				var chart = echarts.init(document.getElementById('pieOfBank'));
				var option = {
					    title : {
					        text: '各银行交易笔数占比',
					        x:'center',
					        textStyle: {
					        	color: '#ccc'
							}
					    },
					    //backgroundColor: 'rgba(15, 55, 95, 0.43)',
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					    	 orient: 'vertical',
					    	 top: '10%',
					         left: '3%',
					         data: optData.legend,
					         textStyle: {
						        color: '#ccc'
						     }
					    },
					    series : [
					        {
					            name: '交易笔数占比',
					            type: 'pie',
					            radius : '50%',
					            center: ['60%', '50%'],
					            data: optData.series,
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
				chart.setOption(option);
			}
			$.ajax({ url: '/kapok/chart/bank', data: {code: code}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: '/kapok/chart/bank', data: {code: code}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		pieOfRsp: function(){
			var success = function(data){
				
				var obj = [].concat.apply([], data.list),  //转为一维数组
				rsp = {}, merchant = []
			$.each(obj, function(n, o){
				o.merchantCode == code && (merchant.push(o), !rsp[o.rspCode] && (rsp[o.rspCode] = {code: o.rspCode, name: o.rspMsg, count: 0}))
			})
			$.each(merchant, function(n, o){
				rsp[o.rspCode].count += o.allCount; 
			})
			var optData = {series: [], legend: []}
			for(var i in rsp){
				optData.legend.push(rsp[i].name.replace(/\[([\s\S]*)\]/g,''))
				optData.series.push({value: rsp[i].count, name: rsp[i].name.replace(/\[([\s\S]*)\]/g,'')})
			}
			var chart = echarts.init(document.getElementById('pieOfRsp'));
			var option = {
				    title : {
				        text: '各响应码占比',
				        x:'center',
				        textStyle: {
				        	color: '#ccc'
						}
				    },
				    //backgroundColor: 'rgba(15, 55, 95, 0.43)',
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	 orient: 'vertical',
				    	 top: '10%',
				         left: '3%',
				         data: optData.legend,
				         textStyle: {
					        color: '#ccc'
					     }
				    },
				    series : [
				        {
				            name: '交易笔数占比',
				            type: 'pie',
				            radius : '50%',
				            center: ['60%', '50%'],
				            data: optData.series,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
				chart.setOption(option);
//				chart.on('click', function (params) {
//				    console.log(params);
//				});
			}
			$.ajax({ url: '/kapok/chart/rsp', data: {code: code}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: '/kapok/chart/rsp', data: {code: code}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		}

	}
	
	code && $.each(init, function(e, i) {
		i()
	})
});
