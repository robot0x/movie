<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${subject.title}评论-${appName}</title>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<%@include file="../common/topNav.jspf" %>


<div id="wrapper">
    <div class="margin-top-40"></div>
    <div id="content">
        <h1>${subject.title}&nbsp;&nbsp;&nbsp;短评</h1>

        <div class="all">
            <div class="col-md-8 article">
                <div class="title-line color-gray">
                    <div class="pull-left">全部共${subject.commentCount}条</div>
                    <div class="clearfix"></div>
                </div>
                <div class="mod-bd" id="coments">
                    <c:choose>
                        <c:when test="${subject.commentCount > 0}">
                            <c:forEach items="${pageInfo.resultList}" var="comment">
                                <div class="comment-list">
                                    <div class="list-title">
                                        <div class="pull-left"><a class="blue"
                                                                  href="/user/${comment.userId}">${appUtils.findUserNameById(comment.userId)}</a>
                                        </div>
                                        <div class="pull-left allstar${comment.rating}"></div>
                                        <div class="pull-left gray">${comment.submitDate}</div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="list-content">
                                            ${comment.content}
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            暂无评论
                        </c:otherwise>
                    </c:choose>
                    <div class="margin-left-30 margin-top-30">
                        <ul id="paginator"></ul>
                    </div>
                </div>
            </div>
            <div class="col-md-4 aside">
                <p class="pl2">>&nbsp;<a data-toggle="modal" data-target="#comment" class="blue">我来写短评</a></p>

                <p class="pl2">>&nbsp;<a href="/subject/${subject.id}" class="blue" name="">去&nbsp;${subject.title}&nbsp;的页面</a>
                </p>

                <div class="decri">
                    <div class="pic">
                        <img src="${subject.image}">
                    </div>
                    <div class="details">
                        <span class="attrs">
                            <p>
                                <span class="pl">导演:</span>${subject.directors}
                            </p>

                            <p>
                                <span class="pl">主演:</span>${subject.casts}
                            </p>

                            <p>
                                <span class="pl">类型:</span>${subject.genres}
                            </p>

                            <p>
                                <span class="pl">地区:</span>${subject.countries}
                            </p>

                            <p>
                                <span class="pl">片长:</span>${subject.durations}
                            </p>

                            <p>
                                <span class="pl">上映:</span><fmt:formatDate value="${subject.pubDate}"
                                                                           pattern="yyyy年MM月dd日"/>
                            </p>
                        </span>
                    </div>
                    <div class="movie"></div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<%@include file="../common/footer.jspf" %>
<%@include file="comment.jspf" %>
</body>
<script type="text/javascript">
    seajs.use("pages/comments", function (comments) {
        comments.init(${pageInfo.pageNo}, ${pageInfo.totalPages});
    });
</script>

</html>
