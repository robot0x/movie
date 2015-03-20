/**
 * Created by XiaoJunfeng on 14/11/7.
 *
 * This is a global initialization for rmbbox.com
 * It must be place after sea.js
 *
 */
if(typeof(window.RmbboxApp)!="object") window.RmbboxApp = {};
window.RmbboxApp['html5'] = document.createElement("canvas").getContext ? true : false;
window.RmbboxApp['dev'] = window.location.href.indexOf("?dev=")>0;
window.RmbboxApp['isMobile'] = /Android|webOS|iOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
window.RmbboxApp['isNarrow'] = window.innerWidth <= 780;
window.RmbboxApp['isDownLevel'] = /MSIE 6|MSIE 7/.test(navigator.userAgent);

if(!window.RmbboxApp['baseUrl']) {
    window.RmbboxApp['baseUrl'] = '/';
    //get site name (虚拟目录) ?这里js获取不正确，不使用了, 要使用的话，在header里将baseUrl输出
    /*
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    if (webName) window.RmbboxApp['baseUrl'] = '/' + webName + '/';
    */
}

//ADS:360推广变量
var _agt = [];

//fix console, ie8 and down level browsers
if(typeof(console) === "undefined") {
    console = {};
}
if(typeof(console.log) === "undefined") {
    console.log = function() {
    };
}

//fix JSON
//document.write('<script src="'+window.RmbboxApp['baseUrl']+'assets/js/json2.js" type="text/javascript"></script>');
document.write('<script src="//lib.sinaapp.com/js/json2/json2.js" type="text/javascript"></script>');

// Set configuration
if(window.RmbboxApp.html5){
    seajs.config({
        base: window.RmbboxApp['baseUrl'] + "assets/js/",
        alias: {
            "jquery" : "jquery-2.1.1.min.js",
            "utility" : "utility.js"
        },
        preload: ["jquery", "utility"]
    });

    // register jQuery to global
    //document.write('<script src="'+window.RmbboxApp['baseUrl']+'assets/js/jquery-2.1.1.min.js" type="text/javascript"></script>');
    document.write('<script src="//upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.3.min.js" type="text/javascript"></script>');
}
else{
    seajs.config({
        base: window.RmbboxApp['baseUrl'] + "assets/js/",
        alias: {
            "jquery" : "jquery-1.11.1.min.js",
            "utility" : "utility.js"
        },
        preload: ["jquery", "utility"]
    });

    // register jQuery to global
    //document.write('<script src="'+window.RmbboxApp['baseUrl']+'assets/js/jquery-1.11.1.min.js" type="text/javascript"></script>');
    document.write('<script src="//upcdn.b0.upaiyun.com/libs/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>');
}


/**
 * go to top behavior
 *
 * @method initToTop
 */
function initToTop(){
    if(typeof(jQuery)=="undefined"){
        setTimeout(initToTop, 10);
        return;
    }
    
    function changeStyleH(type){
    	if(type=='up')
    		for(var i=41;i>-1;i--){
    			$(".totop").css('height',i+'px');
    		}
    	else
    		for(var i=0;i<42;i++){
    			$(".totop").css('height',i+'px');
    		}
    }

    console.log("go to top behavior init.");

    var totopTimer = setInterval(function(){
        var st = $(document).scrollTop();
        if(st>90)
        	changeStyleH('down');
        else
        	changeStyleH('up');

        if(!window.RmbboxApp['isMobile'] && !window.RmbboxApp['isNarrow']) {
            if (st > 32)
                $(".header-2").css('position', 'fixed').css('top', '0px').css('z-index', '11').css('box-shadow', 'rgba(232, 232, 232, 0.7) 0px 3px 5px 0px');
            else
                $(".header-2").css('position', 'relative').css('box-shadow', '');
        }
    },100);

    //clear timer before unload
    $(window).on('beforeunload', function(){
        clearInterval(totopTimer);
    });

    $(".totop").click(function(){
        $("html,body").animate({scrollTop:"0px"}, 500);
    });

    delete initToTop;
}



/**
 *
 * @method initGlobalDataAction 初始化全局带有data-action的操作
 */
function initGlobalDataAction(){
    if(typeof(jQuery)=="undefined"){
        setTimeout(initGlobalDataAction, 10);
        return;
    }

    var actionsWeixin = $("[data-action='show-weixin']");
    actionsWeixin.bind('mouseover', function(e){
        if(!$(this).popover) return;
        var opt = {html:true, content: '<div style="width:220px; height:110px; display: block; position:relative;">' +
        '<div style="width:110px; position:absolute; left:0px; top:0px;"><img src="/assets/images/wechat.jpg"/></div>' +
        '<div style="width:110px; position:absolute; left:110px; top:3px; color:#1c76b8; line-height:22px; font-size:14px;"><div>扫一扫关注</div><div>邦帮堂官方微信</div></div></div>', container:'body', placement:'top'};

        var st = $(document).scrollTop();
        var ot = $(this).offset().top;
        if(ot - st < 280)
            opt.placement = 'bottom';

        $(this).popover(opt);
        $(this).popover('show');
    });
    actionsWeixin.bind('mouseout', function(e){
        if(!$(this).popover) return;
        $(this).popover('destroy');
    });

    var actionsWeibo = $("[data-action='show-weibo']");
    actionsWeibo.bind('mouseover', function(e){
        if(!$(this).popover) return;
        var opt = {html:true, content: '<div style="width:220px; height:110px; display: block; position:relative;">' +
        '<div style="width:110px; position:absolute; left:0px; top:0px;"><img src="/assets/images/weibo.jpg"/></div>' +
        '<div style="width:110px; position:absolute; left:110px; top:3px; color:#1c76b8; line-height:22px; font-size:14px;"><div>扫一扫关注</div><div>邦帮堂官方微博</div>' +
        '</div></div>', container:'body', placement:'top'};

        var st = $(document).scrollTop();
        var ot = $(this).offset().top;
        if(ot - st < 280)
            opt.placement = 'bottom';

        $(this).popover(opt);
        $(this).popover('show');
    });
    actionsWeibo.bind('mouseout', function(e){
        if(!$(this).popover) return;
        $(this).popover('destroy');
    });

    delete initGlobalDataAction;
}

/**
 *
 * @method initGlobalLoaction 初始化全局的nav样式
 */
function initGlobalLoaction(){
    if(typeof(jQuery)=="undefined") {
        setTimeout(initGlobalLoaction, 10);
        return;
    }
    var path = document.location.pathname;
    var found = false;
    var navLink = $('.header-2 .nav a[href]');
    navLink.each(function(i,ele){
        if(i==0) return;
        var href = ele.getAttribute('href');
        if(path.indexOf(href)>=0){
            navLink.removeClass('curr');
            found = true;
            $(ele).addClass('curr');
        }
    });
    if(!found){
        if(path.indexOf('/loan/')>=0)
            $(navLink.get(1)).addClass('curr');
        else if(path.indexOf('/about')>=0)
            $(navLink.get(3)).addClass('curr');
        else
            $(navLink.get(0)).addClass('curr');
    }

    delete initGlobalLoaction;
}

/**
 *
 * @method initMobileAdapt 手机适配初始化
 */
function initMobileAdapt() {
    if (!window.RmbboxApp['isNarrow'] || !window.RmbboxApp['isMobile']) return;

    if (typeof(jQuery) == "undefined") {
        setTimeout(initMobileAdapt, 10);
        return;
    }
    $('.header-2 .col-sm-5').removeClass('col-sm-5').hide();
    $('.header-2 .col-sm-7').removeClass('col-sm-7');
    $('.header-2').height('auto');
    $('.header-2 .nav').removeClass('text-right');
}

/**
 * @method slogan slogan动画
 * 
 */
function initSlogan(){
	if(typeof(jQuery)=="undefined") {
        setTimeout(initSlogan, 10);
        return;
    }
	var mt=$('.slogan img');

    if(mt.length==0) return;
	
	function sishow(){	
		var mtposition=mt.position();		
		if(mtposition.top=='0'){
			mt.animate({
			    top: "-50px"
			  }, 'slow' );
		}else{
			mt.animate({
			    top: "0px"
			  }, 'slow' );
		}
	}	
	setInterval(sishow,3000);
	
}

initToTop();

initSlogan();
initGlobalDataAction();
initGlobalLoaction();
initMobileAdapt();