<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${subject.title}-${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/details.css"/>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="main-part">
    <div class="container">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="part1">
                <div class="margin-top-10"></div>
                <div class="space">
                    <div class="pull-left">
                        <img src="${subject.image}">

                        <p>相关链接：<a href="${douban}${subject.id}" target="_blank"><span
                                style="color: #66afe9;">豆瓣链接</span></a></p>
                    </div>
                    <div class="pull-left margin-left-30">
                        <div class="film-name">${subject.title}(${subject.year})</div>
                        <div class="margin-top-30"></div>
                        <table class="table table-condensed table-striped table-bordered" style="font-size:12px;">
                            <tbody>
                            <tr>
                                <td class="span2"><span class="x-m-label">导演</span></td>
                                <td>${subject.directors}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">主演</span></td>
                                <td>${subject.casts}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">编剧</span></td>
                                <td>${subject.writers}</td>
                            </tr>
                            <tr>
                                <td class="span2">
                                    <span class="x-m-label">类型</span>
                                </td>
                                <td>${subject.genres}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">地区</span></td>
                                <td>${subject.countries}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">语言</span></td>
                                <td>${subject.languages}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">上映时间</span></td>
                                <td><fmt:formatDate value="${subject.pubDate}" pattern="yyyy年MM月dd日"/></td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">片长</span></td>
                                <td>${subject.durations}</td>
                            </tr>
                            <tr>
                                <td class="span2"><span class="x-m-label">别名</span></td>
                                <td>${subject.originalTitle}</td>
                            </tr>
                            <tr class="x-m-rating">
                                <td class="span2">
                                    <span class="x-m-label">评分</span></td>
                                <td><span class="badge" style="color: orange; font-weight: bold;"><fmt:formatNumber
                                        value="${subject.rating}" pattern="#.##" minFractionDigits="2"/></span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="margin-top-10"></div>
            <div class="part2">
                <div class="descri">
                    <div class="descri-title">剧情介绍</div>
                    <div class="margin-top-20"></div>
                    <div class="descri-content">
                        ${subject.summary}
                    </div>
                </div>
            </div>
            <div class="margin-top-10"></div>
            <div class="part3">
                <div class="comments">
                    <div class="comment-title">评论</div>
                    <div class="margin-top-20"></div>
                    <div class="comment-content">
                        <div class="short-comment">
                            <div class="pull-left"><span class="green">国际市场的短评......</span><span class="blue">（全部845条）</span></div>
                            <div class="pull-right red">我来说两句</div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="margin-top-10"></div>
                        <div class="cator-comment">
                            热门/<span style="color: #009bff;">最新</span>/<span style="color: #009bff;">我的关注</span>
                        </div>
                        <div class="comment-list">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left"><img src="../assets/images/pic-1.png"> </div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                “还惦记出生入死吗？” 最好的一部，阿塞拜疆山林追车戏，阿布扎比高楼穿越戏，前后紧凑一气呵成，这才是我想要的《速度与激情》。最后的告别，
                                最后的STOP，最后的保罗·沃克。“怎么说的出口再见？”
                            </div>
                        </div>
                        <div class="comment-list">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left"><img src="../assets/images/pic-1.png"> </div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                “还惦记出生入死吗？” 最好的一部，阿塞拜疆山林追车戏，阿布扎比高楼穿越戏，前后紧凑一气呵成，这才是我想要的《速度与激情》。最后的告别，
                                最后的STOP，最后的保罗·沃克。“怎么说的出口再见？”
                            </div>
                        </div>
                        <div class="comment-list">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left"><img src="../assets/images/pic-1.png"> </div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                “还惦记出生入死吗？” 最好的一部，阿塞拜疆山林追车戏，阿布扎比高楼穿越戏，前后紧凑一气呵成，这才是我想要的《速度与激情》。最后的告别，
                                最后的STOP，最后的保罗·沃克。“怎么说的出口再见？”
                            </div>
                        </div>
                        <div class="comment-list">
                            <div class="list-title">
                                <div class="pull-left blue">杨雄伟</div>
                                <div class="pull-left"><img src="../assets/images/pic-1.png"> </div>
                                <div class="pull-left gray">2015-5-12</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="list-content">
                                “还惦记出生入死吗？” 最好的一部，阿塞拜疆山林追车戏，阿布扎比高楼穿越戏，前后紧凑一气呵成，这才是我想要的《速度与激情》。最后的告别，
                                最后的STOP，最后的保罗·沃克。“怎么说的出口再见？”
                            </div>
                        </div>
                </div>
            </div>
            <div class="margin-top-20"></div>
        </div>
        <div class="col-md-1"></div>
        <div class="clearfix"></div>
    </div>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>
