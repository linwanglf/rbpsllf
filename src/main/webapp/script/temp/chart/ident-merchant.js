layui.use(['layer'], function(){
	var code = urlSearch.get('code')
	var init = {
		line: function(){
			var success = function(data){
				//console.log(data)
				data = data.list.concat(data.redis)
				var mainchart = echarts.init(document.getElementById('mainchart')),
					pieOfRspCount = echarts.init(document.getElementById('pieOfRspCount')),
					chadCount = [], 
					allCount = [], 
					sucCount = [], 
					chadRate = [], 
					sucRate = [],
					failCount = [],
					category= [],
					seconds1 = 0,
					seconds2 = 0,
					seconds3 = 0,
					secondsn = 0;
				$.each(data, function(n, o){
					chadCount.push(o.chadCount)
					allCount.push(o.allCount)
					sucCount.push(o.sucCount)
					chadRate.push(o.chadRate.toFixed(2))
					sucRate.push(o.sucRate.toFixed(2))
					failCount.push(o.allCount - o.sucCount)
					category.push(o.startDate)
					seconds1 += o.seconds1 ? o.seconds1 : 0
					seconds2 += o.seconds2 ? o.seconds2 : 0
					seconds3 += o.seconds3 ? o.seconds3 : 0
					secondsn += o.secondsn ? o.secondsn : 0
				})
				var dataZoomStart = category.length > 30 ? 100 - (30 / category.length * 100).toFixed(2) : 0
				var option = {
				    title: {
				        text: urlHash.get('code'),
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
				    	data:['总笔数', '成功笔数', '失败笔数', '查得笔数', '成功率', '查得率'],
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
				            name : '成功率(%)',
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
				            name : '查得率',
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
				            name : '查得笔数/总笔数',
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
				            name:'成功率',
				            type:'line',
				            symbolSize: 8,
				            hoverAnimation: false,
				            data:sucRate
				        },
				        {
					        name: '总笔数',
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
					    	name:'查得率',
				            type:'line',
				            symbolSize: 8,
				            xAxisIndex: 2,
				            yAxisIndex: 2,
				            hoverAnimation: false,
				            data:chadRate
					    },
					    {
					        name: '总笔数',
					        type: 'bar',
					        barWidth: 7,
					        xAxisIndex: 3,
				            yAxisIndex: 3,
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
					        name: '查得笔数',
					        type: 'bar',
					        barWidth: 7,
					        xAxisIndex: 3,
				            yAxisIndex: 3,
					        itemStyle: {
					            normal: {
					                color: new echarts.graphic.LinearGradient(
					                    0, 0, 0, 1,
					                    [
					                        {offset: 0, color: '#d4cd02'},
					                        {offset: 1, color: '#fdf746'}
					                    ]
					                )
					            }
					        },
					        data: chadCount
					    }
				    ]
				};
				var pieOption = {
					    title : {
					        text: '响应笔数占比',
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
					         data: ['1s内响应笔数', '2s内响应笔数', '3s内响应笔数', '3s以上响应笔数'],
					         textStyle: {
						        color: '#ccc'
						     }
					    },
					    series : [
					        {
					            name: '响应笔数占比',
					            type: 'pie',
					            radius : '50%',
					            center: ['60%', '50%'],
					            data: [{
					            	name: '1s内响应笔数',
					            	value: seconds1
					            }, {
					            	name: '2s内响应笔数',
					            	value: seconds2
					            }, {
					            	name: '3s内响应笔数',
					            	value: seconds3
					            }, {
					            	name: '3s以上响应笔数',
					            	value: secondsn
					            }],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            },
					            labelLine: {
					                normal: {
					                    lineStyle: {
					                        color: 'rgba(255, 255, 255, 0.3)'
					                    },
					                    smooth: 0.2,
					                    length: 10,
					                    length2: 20
					                }
					            },
					            label: {
					                normal: {
					                    textStyle: {
					                        color: 'rgba(255, 255, 255, 0.3)'
					                    }
					                }
					            },
					        }
					    ]
					};
				mainchart.setOption(option)
				pieOfRspCount.setOption(pieOption)
				return 
				setInterval(function() {
					$.ajax({ url: '/kapok/ident/chart/merchant/new', data:{type: 'idmerchant', code: code, startTime: category.length > 0 ? category[category.length-1] : ''}, type: 'post', dataType: 'json', success: function(data){
						console.log(data)
						$.each(data.redis, function(n, o){
							chadCount.push(o.chadCount)
							allCount.push(o.allCount)
							sucCount.push(o.sucCount)
							chadRate.push(o.chadRate.toFixed(2))
							sucRate.push(o.sucRate.toFixed(2))
							failCount.push(o.allCount - o.sucCount)
							category.push(o.startDate)
						})
						mainchart.setOption({
							//dataZoom: [{start: dataZoomStart}, {start: dataZoomStart}],
							xAxis: [{data: category}, {data: category}, {data: category}, {data: category}],
							series: [{data: sucRate}, {data: allCount}, {data: failCount}, {data: sucCount}, {data: chadRate}, {data: allCount}, {data: chadCount}]
						})
					} })
				}, 2000)
			}
			$.ajax({ url: '/kapok/ident/chart/merchant', data:{type: 'idmerchant', code: code}, type: 'post', dataType: 'json', success: success })
		},
		pieOfRsp: function(){
			var success = function(data){
				return
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
			$.ajax({ url: '/kapok/ident/chart/merchant/rsp', data: {code: code}, type: 'post', dataType: 'json', success: success })
//			setInterval(function() {
//				$.ajax({ url: '/kapok/chart/rsp', data: {code: code}, type: 'post', dataType: 'json', success: success })
//			}, 60000)
		}

	}
	
	code && $.each(init, function(e, i) {
		i()
	})
});
