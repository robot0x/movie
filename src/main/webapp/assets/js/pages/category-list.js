/**
 * Created by veryyoung on 2015/5/6.
 */
;
define(function (require, exports, module) {
    "require:nomunge,exports:nomunge,module:nomunge";

    /**
     * @class List
     * @constructor
     */
    function List() {
        this.init();
        console.log('List init calling');
    }


    /**
     * method to init page
     */
    List.prototype.init = function () {
        var self = this;
        self.getData(1, true);
    }


    /**
     * method to get data
     */
    List.prototype.getData = function (page, showPaginator) {

        var self = this;

        var filmList = $('#film-list');

        $.getJSON("/category/list", {pageNo: page}, function (data) {
            if (data != null) {
                filmList.html("");
                $.each(data.resultList, function (index, item) {
                    filmList.append('<li><div class="space"><div class="pull-left">\
                    <img src="'  +item.image + '" width="100px" height="140px"></div>\
                    <div class="pull-left margin-left-10">\
                    <div class="film-name">天台爱情 (2013)</div>\
                    <table class="table table-condensed table-striped table-bordered" style="font-size:12px;">\
                    <tbody>\
                    <tr class="x-m-rating">\
                    <td class="span2">\
                    <span class="x-m-label">评分</span></td>\
                    <td><span class="badge" style="color: orange; font-weight: bold;"><fmt:formatNumber value="${subject.rating}" pattern="#.##" minFractionDigits="2"/></span></td>\
                    </tr><tr><td class="span2"><span class="x-m-label">类型</span></td><td>喜剧 / 动作 / 爱情</td></tr>\
                    <tr><td class="span2"><span class="x-m-label">主演</span></td><td>周杰伦 / 王学圻 / 曾志伟</td></tr>\
                    </tbody></table></div><div class="clearfix"></div></div></li>');
                });
                if (showPaginator) {
                    var pageCount = data.totalPages;
                    var currentPage = data.pageNo;
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
                        },//点击事件，用于通过Ajax来刷新整个list列表
                        onPageClicked: function (event, originalEvent, type, page) {
                            self.getData(page, false);
                        }
                    };
                    $('#paginator').bootstrapPaginator(options);
                }
            }
        });
    };


    module.exports = new List();
})
;