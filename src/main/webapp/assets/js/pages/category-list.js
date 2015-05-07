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

        var year = $('#year  option:selected').text();

        var place = $('#place  option:selected').text();


        var filmList = $('#film-list');

        $.getJSON("/category/list", {pageNo: page, year: year, place: place}, function (data) {
            if (data != null) {
                filmList.html("");
                $.each(data.resultList, function (index, item) {
                    filmList.append('<li><div class="space"><div class="pull-left"><img src="' + item.image + '" width="100px" height="140px"></div>\
                    <div class="pull-left margin-left-10 film-desc"><div class ="margin-bottom-10"><a  style="color: #9B8282;" href="/subject/' + item.id + '">' + item.title + "(" + item.year + ")" + '</a></div>\
                    <table class="table" style="font-size:12px;"><tbody><tr>\
                    <td width="50px">评分</td><td><span class="badge" style="color: orange; font-weight: bold;">' + item.rating.toFixed(2) + '</span></td>\
                    </tr><tr><td>类型</td><td>' + item.genres + '</td></tr><tr><td>主演</td><td>' + item.casts + '</td></tr>\
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


        $('#year').change(function () {
            self.getData(1, true);
        });
        $('#place').change(function () {
            self.getData(1, true);
        });

    };


    module.exports = new List();
})
;