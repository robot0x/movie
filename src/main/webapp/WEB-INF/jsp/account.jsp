<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心-${appName}</title>
    <%@include file="common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/account.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/comments.css"/>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div id="wrapper">
    <div class="margin-top-40"></div>
    <div id="content">
        <h1>${SessionUtils.user.userName}的影评（66）</h1>

        <div class="all">
            <div class="col-md-9 article">
                <div class="mod-bd" id="coments">
                    <div class="col-sm-2">
                        <img src="http://img3.douban.com/view/movie_poster_cover/spst/public/p511118051.jpg">
                    </div>
                    <div class="col-sm-10">
                        <div class="comment-item">
                            <div class="comment">
                                <div class="list-title">
                                    <div class="pull-left blue">${SessionUtils.user.userName}&nbsp;&nbsp;评论:《太平轮上》</div>
                                    <div class="pull-left allstar10 rating"></div>
                                    <div class="pull-right">
                                        <div id="tb-7222584" class="rr">
                                            <a id="af-7222584" rel="sw-False" href="http://movie.douban.com/review/7222584/" class="j a_unfolder" style="display: none; background: none;">
                                                <img onclick="moreurl(this, {from:'review-show-full',track:'',owner:'3078390',itself:7222584})" src="http://img3.douban.com/pics/blank.gif" alt=">" width="48" height="19" class="bn-arrow" title="展开全文">
                                            </a>
                                            <a id="au-7222584" href="javascript:void(0);" class="j a_folder" style="display: inline; background: none;">
                                                <img src="http://img3.douban.com/pics/blank.gif" alt="<" width="48" height="19" class="bn-arrow" title="缩进全文">
                                            </a>

                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="list-content">
                                    去年年初，因为工作原因，我看了《太平轮》的剧本，当时好像还是叫《生死恋》，记得是七八万字，远超出一般剧本的含量，
                                    看完想到的第一个问题是这怎么拍？因为有《赤壁》的前科，我想到吴宇森应该会拍上下集。但这个故事和《赤壁》不同，
                                    赤壁的故事中国人很熟悉，而且具有阶段性，即使分成上下集，并不影响故事的贯通。而《太平轮》采用的是经典的三段式戏剧结构，
                                    它的情节推进和情感机制是连贯的，太平轮海难是人物命运交汇和情感爆发的载体，占有很大的篇幅。在一次观影中看不到戏剧高潮，
                                    观众必定的不满足的，关于影片的一些恶评也来源于此。其实从剧本本身的完成度来看，如果《太平轮》能精炼到一部，
                                    无论是戏剧的感染力还是画面的冲击力都是可以期待的超值享受，毕竟王惠玲和苏照彬都是很成熟的编剧。
                                    你能想象《泰坦尼克号》被砍成上下集分别上映的后果吗？而更加灾难的是，观众现在看了一部名叫《太平轮》而故事基本与太平轮无关的电影，
                                    并且在这部电影里，章子怡、金城武两个主角被排除在了核心事件之外，而他们的命运是要在沉没的太平轮上完成的。
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 aside">
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


<%@include file="common/footer.jspf" %>


</body>
</html>
