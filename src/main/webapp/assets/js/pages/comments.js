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
    }


    var paginator = require('bootstrap-paginator');

    /**
     * method to init page
     */
    Comments.prototype.init = function (pageNo, pageSize) {
        var self = this;
        self.paginator(pageNo, pageSize);
    }


    /**
     * method to init paginator
     */
    Comments.prototype.paginator = function (pageNo, pageSize) {
        var self = this;
        var options = {
            bootstrapMajorVersion: 3, //版本
            currentPage: pageNo, //当前页数
            totalPages: pageSize, //总页数
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
            },
            pageUrl: function (type, page, current) {
                return window.location.pathname + "?pageNo=" + page;

            }
        };
        $('#paginator').bootstrapPaginator(options);

    };


    module.exports = new Comments();
})
;