/**
 * Created by XiaoJunfeng on 14/11/10.
 *
 * Utility packages global tools, such as ajax get factory, post factory, error catch and others
 */

;define(function(require, exports, module) {
    /**
     * @class Utility
     * @constructor
     */
    function Utility(baseuri) {
        //this.baseUri = (window.location.protocol||"http:") + "//";
        //use uri route from root, so the baseUri is empty
        this.baseUri = '/api/';
        if(baseuri) this.baseUri = baseuri;

        //把资源文件放到cdn时使用
        this.cdnUri = '';

        //广告来源处理
        var source = this.getURLParameter('source');
        if(source) {
            this.setLocalStorage('Channel-Source', source);
            if(source.toUpperCase()=='YIRUITE'){
                //易瑞特
                var tid = this.getURLParameter('tid');
                if(tid) this.setLocalStorage('Channel-Source-ExtInfo', tid);
            }
            if(source.toUpperCase()=='YIGOU'){
                //易购
                var uid = this.getURLParameter('uid');
                if(uid) this.setLocalStorage('Channel-Source-ExtInfo', uid);
            }
        }

        console.log('Utility model init...');
    }

    console.log('Utility model reference...');

    module.exports = Utility;

    /**
     * a common error handle for all xhr
     *
     * @method errorHandle
     * @param {Object} jqXHR http://api.jquery.com/Types/#jqXHR
     * @param {String} textStatus "null", "timeout", "error", "abort", and "parsererror"
     * @param {String} errorThrown textual portion of the HTTP status, such as "Not Found" or "Internal Server Error."
     */
    Utility.prototype.errorHandle = function (jqXHR, textStatus, errorThrown) {
        alert([textStatus, errorThrown]);
    }

    /**
     * Convert ajax send data format
     *
     * @method convertSendData
     * @param {Object} json A json format data
     * @param {String} [type='json'] "json" or "query", which decides the send data is json string or query string
     * @return {String} Returns the formatted data string
     */
    Utility.prototype.convertSendData = function(json, type){
        if(typeof(json)=="undefined" || json==null) return null;
        if(typeof(json)=="string") return json;
        if(type && type=="json")
           return JSON.stringify(json);
        var arr = [];
        for(var n in json){
            arr.push(n + "=" + (json[n]||""));
        }
        return arr.join("&");
    }


    /**
     * merge 2 json, mergeData => json
     *
     * @method mergeJson
     * @param {Object} json
     * @param {Object} mergeData
     * @returns {*}
     */
    Utility.prototype.mergeJson = function(json, mergeData){

        if(!json || typeof(json)!=="object")
            return json;

        for(var n in mergeData){
            json[n] = mergeData[n];
        }

        return json;
    }

    /**
     * Get native XMLHttpRequest object
     *
     * @method xmlHttpRequest
     * @returns {Object} Returns XMLHttpRequest
     */
    Utility.prototype.xmlHttpRequest = function(){
        if(typeof(XMLHttpRequest)!="undefined"){
            return new XMLHttpRequest();
        }
        return new ActiveXObject("Microsoft.XMLHTTP");
    }

    /**
     * Override ajax method
     *
     * @method ajax
     * @param {String} url REST uri
     * @param {String} ajaxType GET,PUT,POST,DELETE
     * @param {Object} headers A json object for request header
     * @param {Object} data A json object or string for post/get/put, because the rest use uri route, this will be json
     * @param {String} dataType html,xml,json,script,text,jsonp
     * @param {Function} callback A function which will be run after success
     * @param {Function} errorCallback A function which will be run when error
     */
    Utility.prototype.ajax = function(url, ajaxType, headers, data, dataType, callback, errorCallback){
        var self = this;
        headers = headers||{};
        if(typeof(jQuery)!="undefined"){
            console.log("Using jQuery XHR..");
            //JSON parse has an issue with json type, such as {firstname:"Jeff",lastname:"Xiao"} will cause a parsererror
            //please return quote with json data name
            $.ajax({
                async: true,
                cache: false,
                contentType: headers["Content-Type"]||"application/x-www-form-urlencoded",
                timeout:10000,
                type: ajaxType || "GET",
                url: url,
                data: data, //self.convertSendData(data, "query"),
                headers: headers,
                dataType: dataType || "html",
                success: function (data, textStatus, jqXHR) {
                    console.log("XHR returns 200 ok.");
                    if (typeof (callback)=="function") {
                        console.log(["callback call", callback]);
                        callback(data, textStatus, jqXHR);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("XHR returns error " + textStatus, jqXHR);
                    //处理session timeout
                    var sessionStatus = jqXHR.getResponseHeader("sessionStatus");
                    if(sessionStatus && sessionStatus=="timeout"){
                        //如果超时就处理 ，重定向到login页面
                        top.location.replace("/login");
                        return;
                    }
                    if(typeof(errorCallback)=="function")
                        errorCallback(jqXHR, textStatus, errorThrown);
                    else
                        self.errorHandle(jqXHR, textStatus, errorThrown);
                }
            });
            return;
        }

        //if there is no jQuery, use normal xhr
        var xhr = this.xmlHttpRequest();
        console.log("Using native XHR..");
        if(xhr==null) {
            if(typeof(errorCallback)=="function")
                errorCallback(null, "error", "Your browser does not support XMLHTTP.");
            else
                this.errorHandle(null, "error", "Your browser does not support XMLHTTP.");
            return
        }
        xhr.onreadystatechange = stateChange;
        //always use async, note : ios webkit rejects async=false
        xhr.open(ajaxType||"GET", url, true);

        if(ajaxType && ajaxType.toUpperCase() =="POST")
            xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

        //add headers
        if(headers && typeof(headers)=="object") {
            for (var n in headers) {
                if(!n) continue;
                xhr.setRequestHeader(n, headers[n]||"");
            }
        }

        if(headers && header["Content-Type"] && header["Content-Type"] == "application/json")
            xhr.send(this.convertSendData(data, "json"));
        else
            xhr.send(this.convertSendData(data, "query"));

        function stateChange(){
            // 4 = "Loaded"
            if (xhr.readyState==4){
                // 200 = OK
                if (xhr.status==200){
                    console.log("XHR returns 200 ok.");
                    var dataText = xhr.responseText;
                    //just use json,text,html,xml  ignore others
                    var result = dataText;
                    var dt = (dataType||"html").toLowerCase();
                    try {
                        switch (dt) {
                            case "xml":
                                result = xhr.responseXML;
                                break;
                            case "json":
                                result = eval("(" + dataText + ")");
                                break;
                            case "html":
                            case "text":
                            default :
                                result = dataText;
                                break;
                        }

                        //callback here
                        if (typeof (callback)=="function")
                            callback(result);
                    }
                    catch(x){
                        if(typeof(errorCallback)=="function")
                            errorCallback(null, "error", x.message);
                        else
                            self.errorHandle(null, "error", x.message);
                    }
                }
                else{
                    console.log("XHR returns error " + xhr.status);
                    if(typeof(errorCallback)=="function")
                        errorCallback(null, "http error " + xhr.status, "Problem retrieving XML data");
                    else
                        self.errorHandle(null, "http error " + xhr.status, "Problem retrieving XML data");
                }
            }

        }

    }

    /**
     * a get method ajax returns json data
     *
     * @method getJSON
     * @param {String} api API path
     * @param {Object} headers headers A json object for request header
     * @param {Object} data dataType html,xml,json,script,text,jsonp
     * @param {Function} callback callback A function which will be run after success
     * @param {Function} errorCallback errorCallback A function which will be run when error
     */
    Utility.prototype.getJSON = function(api, headers, data, callback, errorCallback){
        this.ajax(this.baseUri + api, "GET", headers, data, "json", callback, errorCallback);
    }

    /**
     * a get method ajax returns html/text
     *
     * @method get
     * @param {String} api API path
     * @param {Object} headers headers A json object for request header
     * @param {Object} data dataType html,xml,json,script,text,jsonp
     * @param {Function} callback callback A function which will be run after success
     * @param {Function} errorCallback errorCallback A function which will be run when error
     */
    Utility.prototype.get = function(api, headers, data, callback, errorCallback){
        this.ajax(this.baseUri + api, "GET", headers, data, "html", callback, errorCallback);
    }

    /**
     * a post method ajax that just send json data(Content-Type is application/json), returns json data
     *
     * @method sendJSON
     * @param {String} api API path
     * @param {Object} headers headers A json object for request header
     * @param {Object} data dataType html,xml,json,script,text,jsonp
     * @param {Function} callback callback A function which will be run after success
     * @param {Function} errorCallback errorCallback A function which will be run when error
     */
    Utility.prototype.sendJSON = function(api, headers, data, callback, errorCallback){
        headers = headers || {};
        headers["Content-Type"] = "application/json";
        this.ajax(this.baseUri + api, "POST", headers, data, "json", callback, errorCallback);
    }

    /**
     * a post method ajax returns json data
     *
     * @method postJSON
     * @param {String} api API path
     * @param {Object} headers headers A json object for request header
     * @param {Object} data dataType html,xml,json,script,text,jsonp
     * @param {Function} callback callback A function which will be run after success
     * @param {Function} errorCallback errorCallback A function which will be run when error
     */
    Utility.prototype.postJSON = function(api, headers, data, callback, errorCallback){
        this.ajax(this.baseUri + api, "POST", headers, data, "json", callback, errorCallback);
    }

    /**
     * a post method ajax returns html/text
     *
     * @method post
     * @param {String} api API path
     * @param {Object} headers headers A json object for request header
     * @param {Object} data dataType html,xml,json,script,text,jsonp
     * @param {Function} callback callback A function which will be run after success
     * @param {Function} errorCallback errorCallback A function which will be run when error
     */
    Utility.prototype.post = function(api, headers, data, callback, errorCallback){
        this.ajax(this.baseUri + api, "POST", headers, data, "html", callback, errorCallback);
    }

    /**
     * set cookie, non-comment use
     *
     * @method setCookie, non-comment use, please use setLocalStorage instead
     * @param {String} name
     * @param {String} value
     * @param {Int} day
     */
    Utility.prototype.setCookie = function(name, value, day) {
        var Days = day || 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    }

    /**
     * get cookie, non-comment use
     *
     * @method getCookie, non-comment use, please use getLocalStorage instead
     * @param {String} name
     * @returns {String}
     */
    Utility.prototype.getCookie = function(name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null)
            return unescape(arr[2]);
        return null;
    }

    /**
     * check if the browser support local storage
     *
     * @returns {boolean}
     */
    Utility.prototype.isLocalStorageSupported = function() {
        if (typeof (window.localStorage) == "undefined") return false;
        var testKey = 'test', storage = window.sessionStorage;
        try{
            storage.setItem(testKey, '1');
            storage.removeItem(testKey);
            return true;
        }
        catch (error) {
            return false;
        }
    }

    /**
     * get local storage, which use html5 local storage first; if down-level browser, it will use cookie
     *
     * @method getLocalStorage
     * @param {String} name
     * @returns {*}
     */
    Utility.prototype.getLocalStorage = function(name) {
        if (!this.isLocalStorageSupported())
            return this.getCookie(name);
        return window.localStorage.getItem(name);
    }

    /**
     * set local storage, which use html5 local storage first; if down-level browser, it will use cookie
     *
     * @method setLocalStorage
     * @param {String} name
     * @param {Object} value
     */
    Utility.prototype.setLocalStorage = function(name, value) {
        if (!this.isLocalStorageSupported())
            this.setCookie(name, value);
        else
            window.localStorage.setItem(name, value);
    }

    /**
     * 获取Url的参数值
     *
     * @method getURLParameter
     * @param {String} name 参数名
     * @param {String} win window对象
     */
    Utility.prototype.getURLParameter = function(name, win) {
        if(!win)
            return decodeURI(
                (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || ['', ''])[1]
            );
        return decodeURI(
            (RegExp(name + '=' + '(.+?)(&|$)').exec(win.location.search) || ['', ''])[1]
        );
    }

    /**
     * 设置Url的参数值
     *
     * @method setURLParameter
     * @param {String} url 要设置的Url
     * @param {String} name 参数名
     * @param {String} v 参数值
     */
    Utility.prototype.setURLParameter = function(url, name, value){
        if (!url) return "";
        if (url.indexOf("?") == -1) return url += "?" + name + "=" + encodeURI(value);
        var absurl = url.split("?")[0];
        var query = url.split("?")[1];
        var vars = query.split("&");
        var isfound = false;
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == name) {
                vars[i] = pair[0] + "=" + encodeURI(value);
                isfound = true;
            }
        }
        if (!isfound)
            url += "&" + name + "=" + encodeURI(value);
        else
            url = absurl + "?" + vars.join("&");
        return url;
    }

    /**
     * realize a simple html template logic,
     * TODO: the logic needs to define
     *
     * @method bindDataTemplate
     * @param {String} html
     * @param {Object} data
     */
    Utility.prototype.bindDataTemplate = function(html, data){
        //some tips
        //check attributes,  data-text is use to innerHTML or value

        var div = document.createElement("div");

        div.innerHTML = html;

        function rescueBind(p){
            if(!p.tagName)
                return;

            if(p.attributes.length>0) {
                var isLeaf = false;
                var attrs = p.attributes;

                for(var j=0; j<attrs.length; j++){
                    var name = attrs[j].name;
                    var val = attrs[j].value;

                    if(!val) continue;

                    //just a simple validation here, if there is one field, it passes
                    //multiple fields need to make sure the template code is standard
                    //such as data-role="'abc'+{id}+':'+{days}"
                    //if there is complex calculate, it must calc first!
                    var ix1 = val.indexOf('{');
                    var ix2 = val.indexOf('}');
                    if(ix1<0 || ix2<=0)
                        continue;
                    var field = val.substr(ix1+1, ix2-ix1-1);
                    if(!field) continue;

                    //DO NOT Try Catch, because this will cost much time
                    var trans = eval(val.replace(/{/g, 'ensureData(data.').replace(/}/g,')'));
                    if(name == 'data-text'){
                        //data-text just support for innerHTML, because input/textArea can use value
                        p.innerHTML = trans;
                    }else if(name == 'data-style'){
                    	var olds=p.style.cssText;
                    	p.style.cssText=olds+';'+trans;
                    }else if(name == 'data-class'){
                    	var olds = p.className;
                    	p.className=olds+' '+trans;
                    }else{
                        p.setAttribute(name, trans);
                    }
                }
            }

            var cld = p.childNodes;
            if(cld.length==0)
                return;
            for(var i=0;i<cld.length;i++){
                rescueBind(cld[i]);
            }
        }

        //mare true 0 return 0, not ''
        function ensureData(val){
            if(typeof(val) === 'undefined' || val == null)
                return '';
            if(typeof(val) === 'number' || typeof(val) === 'string')
                return val;
            return val.toString();
        }

        rescueBind(div);

        // if {**}, then use data bind
        // if **{**}**, replace
        return div.innerHTML;
    }

    /**
     * format money with ,
     *
     * @method formatMoney
     * @param {Float|Integer} num
     * @returns {string}
     */
    Utility.prototype.formatMoney = function(num){
        if(typeof(num) === 'undefined' || num == null)
            return '';
        if(typeof(num) != 'string')
            num = num.toString();
        num = num.replace(/,/g,'');
        var spec1 = num.indexOf('-');
        num = num.replace(/-/,'');
        var arr1 = num.split('.');
        var arr2 = [];
        var temp = arr1[0];
        while(temp.length>3){
            arr2.push(temp.substr(temp.length - 3, 3));
            temp = temp.substr(0, temp.length - 3);
        }
        if(temp)
            arr2.push(temp);
        arr2.reverse();
        var str = arr2.join(',');
        if(spec1===0) str = '-' + str;
        if(arr1.length > 1) str = str + '.' + arr1[1];

        return str;
    }

    /**
     * format loan duration
     *
     * @method formatDuration
     * @param {Integer} y
     * @param {Integer} m
     * @param {Integer} d
     * @returns {string}
     */
    Utility.prototype.formatDuration = function(y, m, d){
        var str = "";
        if(y>0) str += y + "年";
        if(m>0) {
            if(str) str+= "零";
            str += m + "个月";
        }
        if(d>0) {
            if(str) str+= "零";
            str += d + "天";
        }
        return str;
    }
    
    /**
     * 格式化时间戳
     * 
     * @author MengHuiGao
     * @method formatDate
     * @param {Number} msecond
     * @param {Boolean} isH
     * @returns {string}
     */
    Utility.prototype.formatDate = function(msecond,isH){    	
    	var d = new Date(msecond),strD=['年','月','日',':'];
    	function padLeft(str, len, char){
            if(typeof(str)!='string'){
                str = str.toString();
            }
            var rawLen = str.length;
            for(var i=rawLen; i<len;i++){
                str = char+str;
            }
            return str;
        }
    	var str=d.getFullYear()+strD[0]+padLeft(d.getMonth()+1, 2, '0')+strD[1]+padLeft(d.getDate(),2,'0')+strD[2]
    	if(isH==true)  		
    		return str+' '+padLeft(d.getHours(),2,'0') + strD[3] + padLeft(d.getMinutes(),2,'0') + strD[3] + padLeft(d.getSeconds(),2,'0');    	
    	else
			return str; 
    	
    	
    }
    
    /**
     * tab方法
     * 
     * @author MengHuiGao
     * @menthod tab
     * @param {jqeuryObject} headli
     * @param {jqeuryObject} con
     * @returns {object}
     */
    Utility.prototype.tab = function(headli,con){
    	$(headli).eq(0).addClass("cur");
		$(con).eq(0).show();			
		function fn(){
			$(this).addClass("cur").siblings().removeClass("cur");
			var m=$(headli).index(this);
			$(con).eq(m).show().siblings().hide();
		}	
		$(headli).on("click",fn);
    }
    
    /**
     * 格式化银行卡号
     * 
     * @author MengHuiGao
     * @menthod formatBankcardNo
     * @param {String} bankcard     
     * @returns {String}     
     */
    Utility.prototype.formatBankcardNo=function(bankcard){
    	var bcA=bankcard.split(''),bc='',j;    	
    	for (var i=0; i<bcA.length;i++){
    		j=i+1;
    		if(j%4==0)
    			bc+=bcA[i]+' '
    		else
    			bc+=bcA[i]; 	
    	}
    	return bc    	
    }
    /**
     * 中文金额提示
     * 
     * @author MengHuiGao
     * @menthod convertCurrency
     * @param {String} currencyDigits  输入金额
     * @param {jQuery object}  输入框元素  
     * @param {jQuery object}  提示信息框
     * @returns {String}     
     */
    Utility.prototype.convertCurrency = function(currencyDigits,ele,tipWarp){    	
    	var MAXIMUM_NUMBER = 10000000;  //最大值
    	
        // 定义转移字符
        var CN_ZERO = "零";
        var CN_ONE = "壹";
        var CN_TWO = "贰";
        var CN_THREE = "叁";
        var CN_FOUR = "肆";
        var CN_FIVE = "伍";
        var CN_SIX = "陆";
        var CN_SEVEN = "柒";
        var CN_EIGHT = "捌";
        var CN_NINE = "玖";
        var CN_TEN = "拾";
        var CN_HUNDRED = "佰";
        var CN_THOUSAND = "仟";
        var CN_TEN_THOUSAND = "万";
        var CN_HUNDRED_MILLION = "亿";
        var CN_DOLLAR = "元";
        var CN_TEN_CENT = "角";
        var CN_CENT = "分";
        var CN_INTEGER = "整";
     
        // 初始化验证:
        var integral, decimal, outputCharacters, parts;
        var digits, radices, bigRadices, decimals;
        var zeroCount;
        var i, p, d;
        var quotient, modulus;
     
        // 验证输入字符串是否合法
        if (currencyDigits.toString() == "") {
        	tipWarp.html("还没有输入数字");
        	ele.focus();
            return;
        }
        if (currencyDigits.match(/[^,.\d]/)) {
        	tipWarp.html("请输入有效数字");
        	ele.focus();
            return;
        }
     
        //判断是否输入有效的数字格式
        var reg = /^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/;
        if (!reg.test(currencyDigits)) {
        	tipWarp.html("请输入有效格式数字");
        	ele.focus();
            return;
     
        }
     
        currencyDigits = currencyDigits.replace(/,/g, ""); 
        currencyDigits = currencyDigits.replace(/^0+/, ""); 
        //判断输入的数字是否大于定义的数值
        if (Number(currencyDigits) > MAXIMUM_NUMBER) {
        	tipWarp.html("您输入的数字太大了");
            $("#withdrawValue").focus();
            return;
        }
         
        parts = currencyDigits.split(".");
        if (parts.length > 1) {
            integral = parts[0];
            decimal = parts[1];
            decimal = decimal.substr(0, 2);
        }
        else {
            integral = parts[0];
            decimal = "";
        }
        // 实例化字符大写人民币汉字对应的数字
        digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
        radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
        bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
        decimals = new Array(CN_TEN_CENT, CN_CENT);
        
        outputCharacters = "";
        //大于零处理逻辑
        if (Number(integral) > 0) {
            zeroCount = 0;
            for (i = 0; i < integral.length; i++) {
                p = integral.length - i - 1;
                d = integral.substr(i, 1);
                quotient = p / 4;
                modulus = p % 4;
                if (d == "0") {
                    zeroCount++;
                }
                else {
                    if (zeroCount > 0) {
                        outputCharacters += digits[0];
                    }
                    zeroCount = 0;
                    outputCharacters += digits[Number(d)] + radices[modulus];
                }
                if (modulus == 0 && zeroCount < 4) {
                    outputCharacters += bigRadices[quotient];
                }
            }
            outputCharacters += CN_DOLLAR;
        }
        
        // 包含小数部分处理逻辑
        if (decimal != "") {
            for (i = 0; i < decimal.length; i++) {
                d = decimal.substr(i, 1);
                if (d != "0") {
                    outputCharacters += digits[Number(d)] + decimals[i];
                }
            }
        }
        //确认并返回最终的输出字符串
        if (outputCharacters == "") {
            outputCharacters = CN_ZERO + CN_DOLLAR;
        }
        if (decimal == "") {
            outputCharacters += CN_INTEGER;
        }
        
        //获取人民币大写
        tipWarp.html(outputCharacters);
    }
});