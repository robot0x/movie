<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${subject.title}评论-${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/comments.css"/>
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
                    <div class="pull-left">全部共138784条</div>
                    <div class="clearfix"></div>
                </div>
                <div class="mod-bd" id="coments">
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar10 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar20 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar30 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar40 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar50 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar50 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar50 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar50 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                    <div class="comment-item">
                        <div class="comment">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left allstar50 rating"></div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                恐惧让你沦为囚犯，希望让你重获自由。——《肖申克的救赎》
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 aside">
                <p class="pl2">>&nbsp;<a href="" class="blue" name="">我来写短评</a></p>

                <p class="pl2">>&nbsp;<a href="/subject/${subject.id}" class="blue" name="">去&nbsp;${subject.title}&nbsp;的页面</a></p>

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
                                <span class="pl">上映:</span><fmt:formatDate value="${subject.pubDate}" pattern="yyyy年MM月dd日"/>
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
</body>

</html>
