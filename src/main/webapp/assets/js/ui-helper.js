;define(function(require, exports, module) {

    /**
     * UI helper library, based on jQuery
     *
     * @constructor
     */
    function UIHelper(){

    }

    /**
     * tooltip
     *
     * @method tooltip
     * @param {Element} ele target element
     * @param {String} content The content showing
     * @param {String} pos placement
     * @param {Int} autoHide (ms)
     */
    UIHelper.prototype.tooltip = function(ele, content, pos, autoHide){    	

        if(ele._popovering){
            clearTimeout(ele._popovering);
            delete ele._popovering;
        }

        var opt = {content: content, container:'body'};
        if(pos) opt.placement = pos;

        $(ele).popover(opt);
        $(ele).popover('show');

        if(autoHide){
            if(typeof(autoHide)!="number")
                autoHide = 2000; // default 2 seconds
            ele._popovering = setTimeout(function(){
                delete ele._popovering;
                $(ele).popover('destroy');
            }, autoHide);
        }
    }


    module.exports = new UIHelper();
});