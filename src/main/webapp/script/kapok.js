/**
 * cookies 操作
 */
;!function(e){
	"use strict";
	var c = function(){}
	c.prototype.getCookie = function(c_name){
		if (document.cookie.length > 0) {
			var c_start = document.cookie.indexOf(c_name + "=")
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1
				var c_end = document.cookie.indexOf(";", c_start)
				if (c_end == -1)
					c_end = document.cookie.length
				return unescape(document.cookie.substring(c_start, c_end))
			}
		}
		return ""
	}
	c.prototype.setCookie = function(c_name, value, expiredays){
		var exdate = new Date()
		exdate.setDate(exdate.getDate() + expiredays)
		document.cookie = c_name + "=" + escape(value)
				+ ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
	}
	c.prototype.delCookie = function(name){
		var exdate = new Date()
		exdate.setTime(exdate.getTime() - 1);
		var cval = e.cookies.getCookie(name);
		if (cval != null)
			document.cookie = name + "=" + cval + ";expires=" + exdate.toGMTString();
	}
	e.cookies = new c;
}(window);

;!function(e){
	"use strict";
	var hash = function(){}
	var regExp = function(name){
		return new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i")
	}
	hash.prototype.get = function(name){
		var reg = regExp(name); 
		var r = window.location.hash.substr(1).match(reg); 
		if (r != null) return decodeURI(r[2]); return null; 
	}
	hash.prototype.set = function(name, value){
		var reg = regExp(name), str = window.location.hash;
		var r = str.substr(1).match(reg); 
		return !r ? window.location.hash = (str ? str + '&' : '') + name + '=' + encodeURI(value) : window.location.hash = str.substr(1).replace(reg, '$1' + name + '=' + encodeURI(value) + '$3')
	}
	hash.prototype.setAll = function(obj){
		for(var o in obj){
			obj[o] ? urlHash.set(o, obj[o]) : urlHash.remove(o)
		}
	}
	hash.prototype.remove = function(name){
		var reg = regExp(name); 
		window.location.hash.substr(1).replace(reg, function(a, b, c, d, f, g){
			window.location.hash = g.replace(a, d).replace(/^&/g, '')
		})
	}
	hash.prototype.removeAll = function(){
		window.location.hash = ''
	}
	hash.prototype.toJson = function(){
		var array = window.location.hash.substr(1).split("&"),
			json = {};
		for(var a in array){
			var e = array[a];
			e && e.split('=').length > 0 && (json[e.split('=')[0]] = decodeURI(e.split('=')[1]));
		}
		return json;
	}
	e.urlHash = new hash;
}(window);

;!function(e){
	"use strict";
	var search = function(){}
	var regExp = function(name){
		return new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i")
	}
	search.prototype.get = function(name){
		var reg = regExp(name); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return decodeURI(r[2]); return null; 
	}
	search.prototype.set = function(name, value){
		var reg = regExp(name), str = window.location.search;
		var r = str.substr(1).match(reg); 
		return !r ? window.location.search = (str ? str + '&' : '') + name + '=' + value : window.location.search = str.substr(1).replace(reg, '$1' + name + '=' + value + '$3')
	}
//	search.prototype.setAll = function(obj){
//		//console.log(obj)
//		var str = window.location.search, fin = '';
//		for(var o in obj){
//			var reg = regExp(o);
//			//console.log(reg)
//			var r = str.substr(1).match(reg);
//			//console.log(r)
//			!r ? fin += '&' + o + '=' + obj[o] : str = window.location.search.substr(1).replace(reg, '$1' + o + '=' + obj[o] + '$3')
//			console.log(str)
//		}
//		
//		window.location.search = str + fin;
//	}
	search.prototype.remove = function(name){
		var reg = regExp(name); 
		window.location.search = window.location.search.substr(1).replace(reg, '')
	}
	search.prototype.removeAll = function(){
		window.location.search = ''
	}
	search.prototype.toJson = function(){
		var array = window.location.search.substr(1).split("&"),
			json = {};
		for(var a in array){
			var e = array[a];
			e && e.split('=').length > 0 && (json[e.split('=')[0]] = decodeURI(e.split('=')[1]));
		}
		return json;
	}
	e.urlSearch = new search;
}(window);

/**
 * ajax
 * @param e
 */
!function(e){
	"use strict";
	var ajax = function(){}
	var f = function(url, data, type, dataType, success, error, complete){
		$.ajax({
			url: url,
			data: data,
			//timeout : 10000,
			traditional: true,
			type: type,
			dataType: dataType,
			success: success,
			error: error ? error : defaultError,
			complete: complete ? complete : defaultComplete
		})
	},
	defaultError = function(xhr, stat){
		console.log('Ajax request error, stat: ' + stat)
	},
	defaultComplete = function(xhr, status){
		//console.log(layui.form)
		if(status == 'timeout'){
			console.log("超时");
		}
	}
	ajax.prototype.req = function(url, data, type, dataType, success, error, complete){
		f(url, data, type, dataType, success, error, complete)
	}
	e.ajax = new ajax;
}(window);

/**
 * localStorage
 * @param e
 */
!function(e){
	
	var s = function(){ }
	var ls = window.localStorage;
	s.prototype.set = function(key, value){
		var obj = {};
		obj[key] = value;
		ls.setItem(key, JSON.stringify(obj))
	}
	s.prototype.get = function(key){
		var value = ls.getItem(key);
		return value && JSON.parse(value)[key];
	}
	s.prototype.remove = function(key){
		return ls.removeItem(key);
	}
	s.prototype.clear = function(){
		
		ls.clear();
	}
	e.storage = new s;
}(window);

/**
 * dateFormat function
 */
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} ;

	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	
	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
};