<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <title>${subject.title}评论-${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/plugins/bootstrap-star-rating/css/star-rating.min.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/comments.css"/>
</head>
<body>
<%@include file="../common/topNav.jspf" %>

<%--<input class="rating" type="number" class="rating" min=1 max=10 step=2 data-size="sm" data-rtl="true"/>--%>
<%--<%@include file="../common/footer.jspf" %>--%>

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
                <p class="pl2">>&nbsp;<a href="" class="blue" name="">去&nbsp;${subject.title}&nbsp;的页面</a></p>
                <div class="decri">
                    <div class="pic">
                        <a href="">
                            <img alt=""
                                 title=""
                                 src="http://img3.douban.com/view/movie_poster_cover/spst/public/p480747492.jpg" rel="v:image">
                        </a>
                    </div>
                    <div class="details">
                        <span class="attrs">
                            <p>
                                <span class="pl">导演:</span><a href="http://movie.douban.com/celebrity/1047973/">弗兰克·德拉邦特</a>
                            </p>

                            <p>
                                <span class="pl">主演:</span><a href="http://movie.douban.com/celebrity/1054521/">蒂姆·罗宾斯</a> / <a href="http://movie.douban.com/celebrity/1054534/">摩根·弗里曼</a> / <a href="http://movie.douban.com/celebrity/1041179/">鲍勃·冈顿</a> / <a href="http://movie.douban.com/celebrity/1000095/">威廉姆·赛德勒</a> / <a href="http://movie.douban.com/celebrity/1013817/">克兰西·布朗</a> / <a href="http://movie.douban.com/celebrity/1010612/">吉尔·贝罗斯</a> / <a href="http://movie.douban.com/celebrity/1054892/">马克·罗斯顿</a> / <a href="http://movie.douban.com/celebrity/1027897/">詹姆斯·惠特摩</a> / <a href="http://movie.douban.com/celebrity/1087302/">杰弗里·德曼</a> / <a href="http://movie.douban.com/celebrity/1074035/">拉里·布兰登伯格</a> / <a href="http://movie.douban.com/celebrity/1099030/">尼尔·吉恩托利</a> / <a href="http://movie.douban.com/celebrity/1343305/">布赖恩·利比</a> / <a href="http://movie.douban.com/celebrity/1048222/">大卫·普罗瓦尔</a> / <a href="http://movie.douban.com/celebrity/1343306/">约瑟夫·劳格诺</a> / <a href="http://movie.douban.com/celebrity/1315528/">祖德·塞克利拉</a>
                            </p>

                            <p>
                                <span class="pl">类型:</span>犯罪, 剧情
                            </p>

                            <p>
                                <span class="pl">地区:</span>美国
                            </p>

                            <p>
                                <span class="pl">片长:</span>142 分钟
                            </p>

                            <p>
                                <span class="pl">上映:</span>1994-09-10(多伦多电影节), 1994-10-14(美国)
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
<script type="application/javascript" src="/assets/plugins/bootstrap-star-rating/js/star-rating.min.js"/>
<script type="application/javascript">
    $('input').rating();
</script>
</html>
