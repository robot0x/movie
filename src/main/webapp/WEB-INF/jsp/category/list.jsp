<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类-${appName}</title>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="span9">
    <label>部门列表</label>
    <hr/>
    <div id="list"></div>

    <div id="example"></div>
</div>

<%@include file="../common/footer.jspf" %>
<script type="text/javascript" src="/assets/js/bootstrap-paginator.js"></script>

</body>

<script>
    $(function () {
        $.ajax({
            url: "/category/list",
            datatype: 'json',
            data: {pageNo: 1},
            success: function (data) {
                if (data != null) {
                    $.each(eval("(" + data + ")").resultList, function (index, item) { //遍历返回的json
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
                    var pageCount = eval("(" + data + ")").totalPages; //取到pageCount的值(把返回数据转成object类型)
                    var currentPage = eval("(" + data + ")").pageNo; //得到urrentPage
                    var options = {
                        bootstrapMajorVersion: 2, //版本
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
                            $.ajax({
                                url: "/category/list",
                                data: {pageNo: page},
                                success: function (data) {
                                    if (data != null) {
                                        $.each(eval("(" + data + ")").resultList, function (index, item) { //遍历返回的json
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
                                    }
                                }
                            });
                        }
                    };
                    $('#example').bootstrapPaginator(options);
                }
            }
        });
    })
</script>
</html>
