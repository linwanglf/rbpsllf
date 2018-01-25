layui.use(['layer'], function(){
	var code = urlSearch.get('code');
	var init = {
		//折线图,柱状图组合图
		line: function(){
			var success = function(data){
				var mainchart = echarts.init(document.getElementById('mainchart'));
				var allCount = [], 
					sucCount = [], 
					sucRate = [], 
					category = [], 
					avgTime = [], 
					failCount = [], 
					codeAvgTime = [],
					codeCount = [];
//					todayAllCount = 0,
//					todayProcCount = 0,
//					todaySucCount = 0,
//					todayFailCount = 0;
				$.each(data.list, function(n, o){
					category.push(o.startDate)
					allCount.push(o.allCount)
					sucCount.push(o.sucCount)
					sucRate.push(o.sucRate.toFixed(2))
					avgTime.push(o.avgRspTime)
					codeAvgTime.push(o.codeAvgTime)
					failCount.push(o.allCount - o.sucCount)
					codeCount.push(o.codeCount)
					//todayAllCount += o.allCount
					//todayProcCount += o.processingCount
					//todaySucCount += o.sucCount
					//todayFailCount += o.allCount - o.sucCount - o.userCount
				})
				$.each(data.redis, function(n, o){
					category.push(o.startDate)
					allCount.push(o.allCount)
					sucCount.push(o.sucCount)
					sucRate.push(o.sucRate.toFixed(2))
					avgTime.push(o.avgRspTime)
					codeAvgTime.push(o.codeAvgTime)
					failCount.push(o.allCount - o.sucCount)
					codeCount.push(o.codeCount)
//					todayAllCount += o.allCount
//					todayProcCount += o.processingCount
//					todaySucCount += o.sucCount
//					todayFailCount += o.allCount - o.sucCount - o.userCount
				})
				//$('#allCount').text(todayAllCount), $('#failCount').text(todayFailCount), $('#succCount').text(todaySucCount), $('#procCount').text(todayProcCount)
				var dataZoomStart = category.length > 30 ? 100 - (30 / category.length * 100).toFixed(2) : 0
				var option = {
				    title: {
				        text: urlSearch.get('code'),
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
				    	data:['系统成功率', '交易笔数', '失败笔数', '成功笔数', '0040平均未回调时间', '0040未回调笔数'],
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
				            name : '0040平均回调时间(s)/0040回调笔数(笔)',
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
					        name: '0040平均未回调时间',
					        type: 'line',
					        xAxisIndex: 2,
				            yAxisIndex: 2,
				            symbolSize: 8,
				            hoverAnimation: false,
				            lineStyle:{
				            	normal: {
				            		color: '#7c96e0'
				            	}
				            },
					        data: codeAvgTime
					    },
					    {
					        name: '0040未回调笔数',
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
					        data: codeCount
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
					$.ajax({ url: '/kapok/chart/proc/new', data:{type: 'proc', code: code, startTime: category[category.length-1]}, type: 'post', dataType: 'json', success: function(data){
						$.each(data.redis, function(n, o){
							category.push(o.startDate)
							allCount.push(o.allCount)
							sucCount.push(o.sucCount)
							sucRate.push(o.sucRate.toFixed(2))
							avgTime.push(o.avgRspTime)
							codeAvgTime.push(o.codeAvgTime)
							failCount.push(o.allCount - o.sucCount)
							codeCount.push(o.codeCount)
//							todayAllCount += o.allCount
//							todayProcCount += o.processingCount
//							todaySucCount += o.sucCount
//							todayFailCount += o.allCount - o.sucCount - o.userCount
						})
						mainchart.setOption({
							xAxis: [{data: category}, {data: category}, {data: category}, {data: category}],
							series: [{data: sucRate}, {data: allCount}, {data: failCount}, {data: sucCount}, {data: codeAvgTime}, {data: codeCount}, {data: avgTime}]
						})
					} })
				}, 60000)
			}
			$.ajax({ url: '/kapok/chart/proc', data:{type: "proc", code: code}, type: 'post', dataType: 'json', success: success })
		},
		pieOfBank: function(){
			var success = function(data){
				var obj = [].concat.apply([], data.list),  //转为一维数组
					bank = {}, 
					proc = []
			$.each(obj, function(n, o){
				o.procCode == code && (proc.push(o), !bank[o.bankCode] && (bank[o.bankCode] = {code: o.bankCode, name: o.bankName, count: 0}))
			})
			$.each(proc, function(n, o){
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
				chart.setOption(option);
			}
			$.ajax({ url: '/kapok/chart/proc/bank', data: {code: code}, type: 'post', dataType: 'json', success: success })
			setInterval(function() {
				$.ajax({ url: '/kapok/chart/proc/bank', data: {code: code}, type: 'post', dataType: 'json', success: success })
			}, 60000)
		},
		//各响应码占比
		pieOfRsp: function(){
			var success = function(data){
				//console.log(data)
				var obj = data.list.concat([].concat.apply([], data.redis)),  //转为一维数组并合并数组
				rsp = {}, proc = []
				var rspType = [{name: '系统原因', value: 0, codes: ',0000,0030,0050,6517,6536,6542,6543,6544,6547,6553,6573,6590,6601,6602,6603,6604,6605,6606,6607,6608,6609,6701,6702,6703,6704,6705,6706,6707,6708,6709,'},
				               {name: '渠道原因', value: 0, codes: ',0040,0060,6533,6535,6556,6557,6561,6563,6564,6580,6581,6588,'},
				               {name: '用户原因', value: 0, codes: ',6500,6501,6502,6503,6504,6505,6506,6507,6508,6509,6510,6511,6512,6513,6514,6515,6516,6517,6518,6519,6520,6521,6522,6523,6524,6525,6526,6527,6528,6529,6530,6531,6532,6533,6534,6535,6536,6537,6538,6539,6540,6541,6542,6543,6544,6545,6546,6547,6548,6549,6550,6551,6552,6560,6554,6555,6562,6565,6558,6559,6566,6567,6568,6569,6570,6571,6572,6578,6574,6575,6576,6577,6579,6582,6583,6584,6585,6586,6587,6589,'}]
				
			$.each(obj, function(n, o){
				!rsp[o.rspCode] ? rsp[o.rspCode] = {code: o.rspCode, name: o.rspCode, count: o.allCount} : rsp[o.rspCode].allCount += o.allCount;
				//o.procCode == code && (proc.push(o), !rsp[o.rspCode] && (rsp[o.rspCode] = {code: o.rspCode, name: o.rspCode, count: 0}))
			})
//			$.each(proc, function(n, o){
//				rsp[o.rspCode].count += o.allCount; 
//			})
			var optData = {series: [], legend: [], type:[]}
			
			$.each(rspType, function(n, o){
				for(var i in rsp){
					o.codes.indexOf(',' + i + ',') > -1 && (o.value += rsp[i].count, optData.series.push({value: rsp[i].count, name: rsp[i].name}))
				}
				o.value > 0 && optData.type.push(o)
			})
				
			var chart = echarts.init(document.getElementById('pieOfRsp'));
			var option = {
				    title : {
				        text: '各响应码占比',
				        x:'center',
				        textStyle: {
				        	color: '#ccc'
						}
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				    	 orient: 'vertical',
				    	 top: '10%',
				         left: '3%',
				         data: ['系统原因', '渠道原因', '用户原因'],
				         textStyle: {
					        color: '#ccc'
					     }
				    },
				    series : [{
				    	name: '占比类型',
				        type: 'pie',
				        radius: [0, '30%'],
				        center: ['60%', '50%'],
				        label: {
				            normal: {
				                position: 'inner'
				            }
				        },
				        labelLine: {
				            normal: {
				                show: false
				            }
				        },
				        data: optData.type
				    },
				        {
				            name: '交易笔数占比',
				            type: 'pie',
				            radius : ['35%', '55%'],
				            center: ['60%', '50%'],
				            data: optData.series,
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
				chart.setOption(option);
				setInterval(function() {
					$.ajax({ url: '/kapok/chart/proc/rsp/new', data: {code: code, startTime: obj.length > 0 ? obj[length-1].startDate : ''}, type: 'post', dataType: 'json', success: function(data){
						var redis = [].concat.apply([], data.redis),
							series = [],
							temple = [];
						obj = obj.concat(redis)
						$.each(redis, function(n, o){
							!rsp[o.rspCode] ? rsp[o.rspCode] = {code: o.rspCode, name: o.rspCode, count: o.allCount} : rsp[o.rspCode].allCount += o.allCount;
						})
						$.each(rspType, function(n, o){
							o.value = 0;			//覆盖掉原有的值，初始化为0
							for(var i in rsp){
								o.codes.indexOf(',' + i + ',') > -1 && (o.value += rsp[i].count, series.push({value: rsp[i].count, name: rsp[i].name}))
							}
							o.value != 0 && temple.push(o)
						})
						chart.setOption({
							series:[{data: temple}, {data: series}]
						})
					} })
				}, 60000)
			}
			$.ajax({ url: '/kapok/chart/proc/rsp', data: {code: code}, type: 'post', dataType: 'json', success: success })
//			setInterval(function() {
//				$.ajax({ url: '/kapok/chart/proc/rsp', data: {code: code}, type: 'post', dataType: 'json', success: success })
//			}, 60000)
		},
	}
	
	$.each(init, function(n, o){
		o();
	})
})