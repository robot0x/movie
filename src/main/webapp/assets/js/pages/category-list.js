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

        $.getJSON("/category/list", {pageNo: page}, function (data) {
            if (data != null) {
                $.each(data.resultList, function (index, item) {
                    $("#list").html("");
                    $("#list").append('<table id="data_table" class="table table-striped">');
                    $("#list").append('<thead>');
                    $("#list").append('<tr>');
                    $("#list").append('<th>id</th>');
                    $("#list").append('<th>名称</th>');
                    $("#list").append('<th>导演</th>');
                    $("#list").append('<th> </th>');
                    $("#list").append('</tr>');
                    $("#list").append('</thead>');
                    $("#list").append('<tbody>');
                    $("#list").append('<tr>');
                    $("#list").append('<td>' + item.id + '</td>');
                    $("#list").append('<td>' + item.title + '</td>');
                    $("#list").append('<td>备注</td>');
                    $("#list").append('<td>');
                    $("#list").append('<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">修改</button>');
                    $("#list").append('<button class="btn btn-warning" onclick="Edit(' + item.id + ' );">删除</button>');
                    $("#list").append('</td>');
                    $("#list").append('</tr>');
                    $("#list").append('</tbody>');

                    $("#list").append('<tr>');
                    $("#list").append('<td>内容</td>');
                    $("#list").append('<td>' + item.title + '</td>');
                    $("#list").append('</tr>');
                    $("#list").append('</table>');
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
                    $('#example').bootstrapPaginator(options);
                }
            }
        });
    };


    module.exports = new List();
})
;