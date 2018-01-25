$(function() {
	var lineOption = {
		tooltip : {
			show : false
		},

		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			top : '9%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			axisTick : {
				show : false
			},
			boundaryGap : false,
			axisLabel : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			data : [ '13:00', '13:05', '13:10', '13:15', '13:20', '13:25',
					'13:30', '13:35', '13:40', '13:45', '13:50', '13:55' ]
		} ],
		yAxis : [ {
			type : 'value',
			axisTick : {
				show : false
			},
			axisLine : {
				lineStyle : {
					color : '#57617B'
				}
			},
			axisLabel : {
				show : false,
				margin : 10,
				textStyle : {
					fontSize : 14
				}
			},
			splitLine : {
				show : false,
				lineStyle : {
					color : '#57617B'
				}
			}
		} ],
		series : [
				{
					name : 'data1',
					type : 'line',
					smooth : true,
					symbol : 'circle',
					symbolSize : 5,
					showSymbol : false,
					lineStyle : {
						normal : {
							width : 1
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(137, 189, 27, 0.3)'
									}, {
										offset : 0.8,
										color : 'rgba(137, 189, 27, 0)'
									} ], false),
							shadowColor : 'rgba(0, 0, 0, 0.1)',
							shadowBlur : 10
						}
					},
					itemStyle : {
						normal : {
							color : 'rgb(137,189,27)',
							borderColor : 'rgba(137,189,2,0.27)',
							borderWidth : 12

						}
					},
					data : [ 220, 182, 191, 134, 150, 120, 110, 125, 145, 122,
							165, 122 ]
				},
				{
					name : 'data2',
					type : 'line',
					smooth : true,
					symbol : 'circle',
					symbolSize : 5,
					showSymbol : false,
					lineStyle : {
						normal : {
							width : 1
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(0, 136, 212, 0.3)'
									}, {
										offset : 0.8,
										color : 'rgba(0, 136, 212, 0)'
									} ], false),
							shadowColor : 'rgba(0, 0, 0, 0.1)',
							shadowBlur : 10
						}
					},
					itemStyle : {
						normal : {
							color : 'rgb(0,136,212)',
							borderColor : 'rgba(0,136,212,0.2)',
							borderWidth : 12

						}
					},
					data : [ 120, 110, 125, 145, 122, 165, 122, 220, 182, 191,
							134, 150 ]
				},
				{
					name : 'data3',
					type : 'line',
					smooth : true,
					symbol : 'circle',
					symbolSize : 5,
					showSymbol : false,
					lineStyle : {
						normal : {
							width : 1
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgba(219, 50, 51, 0.3)'
									}, {
										offset : 0.8,
										color : 'rgba(219, 50, 51, 0)'
									} ], false),
							shadowColor : 'rgba(0, 0, 0, 0.1)',
							shadowBlur : 10
						}
					},
					itemStyle : {
						normal : {

							color : 'rgb(219,50,51)',
							borderColor : 'rgba(219,50,51,0.2)',
							borderWidth : 12
						}
					},
					data : [ 220, 182, 125, 145, 122, 191, 134, 150, 120, 110,
							165, 122 ]
				}, ]
	};

	var linechart = echarts.init(document.getElementById("linechart"));
	linechart.setOption(lineOption);

	var pieOption = {
		tooltip : {
			show : false
		},
		series : [ {
			type : 'pie',
			radius : [ '60%', '70%' ],
			center : [ '20%', '50%' ],
			data : [ {
				value : 25,
				label : {
					normal : {
						formatter : '{d}%',
						position : 'center',
						textStyle : {
							fontSize : '25',
							color : '#a6f08f'
						}
					}
				},
				itemStyle : {
					normal : {
						color : '#a6f08f',
						shadowColor : '#a6f08f',
					}
				}
			}, {
				value : 75,
				itemStyle : {
					normal : {
						color : 'rgba(44,59,70,1)',
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					},
					emphasis : {
						color : 'rgba(44,59,70,1)', // 未完成的圆环的颜色
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					}
				},
			} ]
		}, {
			type : 'pie',
			radius : [ '60%', '70%' ],
			center : [ '45%', '50%' ],
			data : [ {
				value : 45,
				label : {
					normal : {
						formatter : '{d}%',
						position : 'center',
						textStyle : {
							fontSize : '25',
							color : '#a6f08f'
						}
					}
				},
				itemStyle : {
					normal : {
						color : '#a6f08f',
						shadowColor : '#a6f08f',
					}
				}
			}, {
				value : 55,
				itemStyle : {
					normal : {
						color : 'rgba(44,59,70,1)',
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					},
					emphasis : {
						color : 'rgba(44,59,70,1)', // 未完成的圆环的颜色
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					}
				},
			} ]
		}, {
			type : 'pie',
			radius : [ '60%', '70%' ],
			center : [ '70%', '50%' ],
			data : [ {
				value : 85,
				label : {
					normal : {
						formatter : '{d}%',
						position : 'center',
						textStyle : {
							fontSize : '25',
							color : '#a6f08f'
						}
					}
				},
				itemStyle : {
					normal : {
						color : '#a6f08f',
						shadowColor : '#a6f08f',
					}
				}
			}, {
				value : 15,
				itemStyle : {
					normal : {
						color : 'rgba(44,59,70,1)',
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					},
					emphasis : {
						color : 'rgba(44,59,70,1)', // 未完成的圆环的颜色
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					}
				},
			} ]
		} ]
	}

	var piechart = echarts.init(document.getElementById("piechart"));
	piechart.setOption(pieOption);

	var columnOption = {
		color : [ '#3398DB' ],
		tooltip : {
			show : false
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			top : '9%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			axisLabel : {
				show : false
			},
			axisTick : {
				show : false
			},
			data : [ '13:00', '13:05', '13:10', '13:15', '13:20', '13:25',
					'13:30', '13:35', '13:40', '13:45', '13:50', '13:55' ],
		} ],
		yAxis : [ {
			axisLabel : {
				show : false
			},
			axisTick : {
				show : false
			},
		} ],
		series : [ {
			name : '直接访问',
			type : 'bar',
			barWidth : '40%',
			data : [ 1, 3, 2, 3, 4, 2, 1, 3, 3, 2, 3, 2 ]
		}],

		itemStyle : {
			normal : {

				color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
					offset : 0,
					color : 'rgba(17, 168,171, 1)'
				}, {
					offset : 1,
					color : 'rgba(17, 168,171, 0.1)'
				} ]),
				shadowColor : 'rgba(0, 0, 0, 0.1)',
				shadowBlur : 10
			}
		}
	}

	var columnchart = echarts.init(document.getElementById("columnchart"));
	columnchart.setOption(columnOption);
})