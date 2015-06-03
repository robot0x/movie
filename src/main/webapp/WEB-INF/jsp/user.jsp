<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.userName}的影评-${appName}</title>
    <%@include file="common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/user.css"/>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div id="wrapper">
    <div class="margin-top-40"></div>
    <div id="content">
        <h1>${user.userName}的影评（${commentCount}）</h1>

        <div class="margin-top-20"></div>

        <div class="all">
            <div class="col-md-8">
                <c:choose>
                    <c:when test="${commentCount > 0}">
                        <c:forEach items="${pageInfo.resultList}" var="comment">
                            <c:set var="subject" scope="page" value="${appUtils.findSubjectById(comment.subjectId)}"/>
                            <div class="article">
                                <div class="mod-bd" id="coments">
                                    <div class="col-sm-3 pic">
                                        <img width="120px" height="170px"
                                             src="${subject.image}">
                                    </div>
                                    <div class="col-sm-9 content">
                                        <div class="comment-item">
                                            <div class="comment">
                                                <div class="list-title">
                                                    <div class="pull-left blue">${comment.submitDate}
                                                    </div>
                                                    <div class="pull-right">
                                                        <div class="delete"><mv:securityTag userId="${comment.userId}">
                                                            &nbsp;&nbsp;&nbsp;
                                                            <button type="button"
                                                                    class="close  btn-danger delete_comment"
                                                                    data-dismiss="alert" value="${comment.id}">×
                                                            </button>
                                                        </mv:securityTag>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="title2">
                                                    <div class="pull-left">${user.userName}&nbsp;&nbsp;评论:<a
                                                            href="/subject/${subject.id}" class="blue">《${subject.title}》</a>
                                                    </div>
                                                    <div class="pull-left allstar10 rating"></div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="list-content" id="wrap">
                                                        ${comment.content}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        暂无评论
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="col-md-4 aside">
                <div class="margin-top-10"></div>
                <p class="pl">欢迎观看我的评论......</p>

                <div class="margin-top-30"></div>
                <p class="p2">>&nbsp;<a class="blue" name="">${user.userName}的电影首页</a></p>

                <div class="margin-top-30"></div>
                <div class="decri">
                    <p>这是我对所有电影的评论，您也可以发表不同意见哦~~</p>

                    <div class="margin-top-10"></div>
                    <p>欢迎亲多来瞅瞅~~</p>
                </div>
                <div class="margin-top-30"></div>

                <c:if test="${SessionUtils.user.admin}">
                    <a href="/user/${user.id}/setAdmin">
                        <c:choose>
                            <c:when test="${user.admin}">
                                <button type="button" class="btn btn-danger">取消管理员</button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-success">设置为管理员</button>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </c:if>
                <c:if test="${SessionUtils.user.id eq user.id}">
                    <a href="/password">
                        <button type="button" class="btn btn-info">修改密码</button>
                    </a>
                </c:if>

            </div>
            <div class="clearfix"></div>
        </div>

        <div class="margin-left-30 margin-top-30">
            <ul id="paginator"></ul>
        </div>
    </div>
</div>


<%@include file="common/footer.jspf" %>


</body>
<script type="text/javascript">
    seajs.use("pages/comments", function (comments) {
        comments.init(${pageInfo.pageNo}, ${pageInfo.totalPages});
    });
</script>
</html>
