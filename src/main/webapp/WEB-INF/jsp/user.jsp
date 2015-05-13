<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心-${appName}</title>
    <%@include file="common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/user.css"/>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div id="wrapper">
    <div class="margin-top-40"></div>
    <div id="content">
        <h1>${SessionUtils.user.userName}的影评（66）</h1>

        <div class="margin-top-20"></div>

        <div class="all">
            <div class="col-md-9 article">
                <div class="mod-bd" id="coments">
                    <div class="col-sm-3 pic">
                        <img width="120px" height="170px" src="http://7xia3v.com1.z0.glb.clouddn.com/10741834">
                    </div>
                    <div class="col-sm-9 content">
                        <div class="comment-item">
                            <div class="comment">
                                <div class="list-title">
                                    <div class="pull-left blue">${SessionUtils.user.userName}&nbsp;&nbsp;评论:《太平轮上》</div>
                                    <div class="pull-left allstar10 rating"></div>
                                    <div class="pull-right">
                                        <div class="delete"><a href="#"><img src="../assets/images/display.png"></a>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="margin-top-10"></div>
                                <div class="list-content" id="wrap">
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
                <div class="margin-top-10"></div>
                <p class="pl">欢迎观看我的评论......</p>

                <div class="margin-top-30"></div>
                <p class="p2">>&nbsp;<a class="blue" name="">${SessionUtils.user.userName}的电影首页</a></p>

                <div class="margin-top-30"></div>
                <div class="decri">
                    <p>这是我对所有电影的评论，您也可以发表不同意见哦~~</p>

                    <div class="margin-top-10"></div>
                    <p>欢迎亲多来瞅瞅~~</p>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>


<%@include file="common/footer.jspf" %>


</body>
</html>
