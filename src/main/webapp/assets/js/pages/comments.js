/**
 * Created by veryyoung on 2015/5/13.
 */
;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class List
     * @constructor
     */
    function Comments() {
        this.init();
        console.log('Comments init calling');
    }


    /**
     * method to init page
     */
    Comments.prototype.init = function () {
        var self = this;
        self.paginator(1);
    }


    /**
     * method to init paginator
     */
    Comments.prototype.paginator = function (page) {

        var self = this;

        var pageCount = 2;
        var currentPage = page;
        var options = {
            bootstrapMajorVersion: 3, //版本
            currentPage: currentPage, //当前页数
            totalPages: pageCount, //总页数
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            }
            ,//点击事件，
            onPageClicked: function (event, originalEvent, type, page) {
                self.paginator(page);
            }
        };
        $('#paginator').bootstrapPaginator(options);

    };


    module.exports = new Comments();
})
;