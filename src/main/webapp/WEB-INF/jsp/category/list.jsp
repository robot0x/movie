<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类-${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/category-list.css"/>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="container">
    <div class="subnav subnav-fixed">
        <ul class="nav nav-pills margin-left-30 margin-top-30">
            <li>
                <div>
                    <select class="form-control" id="year">
                        <option>年份不限</option>
                        <option>2015</option>
                        <option>2014</option>
                        <option>2013</option>
                        <option>2012</option>
                        <option>2011</option>
                        <option>2010</option>
                        <option>2009</option>
                        <option>2008</option>
                        <option>2007</option>
                        <option>2006</option>
                        <option>2005</option>
                        <option>2004</option>
                        <option>2003</option>
                        <option>2002</option>
                        <option>2001</option>
                        <option>2000</option>
                        <option>1999</option>
                        <option>1998</option>
                        <option>1997</option>
                        <option>1996</option>
                        <option>1995</option>
                        <option>1994</option>
                        <option>1993</option>
                        <option>1992</option>
                        <option>1991</option>
                        <option>1990</option>
                    </select>
                </div>

            </li>
            <li>
                <div>
                    <select class="form-control" id="place">
                        <option>地区不限</option>
                        <option>大陆</option>
                        <option>香港</option>
                        <option>台湾</option>
                        <option>美国</option>
                        <option>日本</option>
                        <option>韩国</option>
                        <option>法国</option>
                        <option>英国</option>
                        <option>印度</option>
                        <option>德国</option>
                        <option>加拿大</option>
                        <option>伊朗</option>
                        <option>泰国</option>
                        <option>俄罗斯</option>
                        <option>意大利</option>
                        <option>瑞典</option>
                        <option>澳洲</option>
                        <option>丹麦</option>
                        <option>土耳其</option>
                        <option>荷兰</option>
                        <option>巴西</option>
                        <option>波兰</option>
                        <option>西班牙</option>
                        <option>阿根廷</option>
                        <option>比利时</option>
                        <option>芬兰</option>
                        <option>匈牙利</option>
                        <option>墨西哥</option>
                        <option>瑞士</option>
                        <option>奥地利</option>
                        <option>南斯拉夫</option>
                        <option>苏联</option>
                        <option>挪威</option>
                        <option>菲律宾</option>
                        <option>捷克斯洛伐克</option>
                        <option>葡萄牙</option>
                        <option>爱尔兰</option>
                        <option>以色列</option>
                        <option>希腊</option>
                        <option>捷克</option>
                        <option>罗马尼亚</option>
                        <option>智利</option>
                        <option>古巴</option>
                        <option>保加利亚</option>
                        <option>南非</option>
                        <option>冰岛</option>
                        <option>新加坡</option>
                        <option>埃及</option>
                        <option>新西兰</option>
                    </select>
                </div>
            </li>
            <li>
                <div>
                    <select class="form-control" id="type">
                        <option>类型不限</option>
                        <option>喜剧</option>
                        <option>爱情</option>
                        <option>动作</option>
                        <option>科幻</option>
                        <option>惊悚</option>
                        <option>纪录</option>
                        <option>恐怖</option>
                        <option>短片</option>
                        <option>剧情</option>
                        <option>动画</option>
                        <option>冒险</option>
                        <option>家庭</option>
                        <option>悬疑</option>
                        <option>犯罪</option>
                        <option>战争</option>
                        <option>音乐</option>
                        <option>西部</option>
                        <option>历史</option>
                        <option>传记</option>
                        <option>运动</option>
                        <option>儿童</option>
                        <option>同性</option>
                        <option>黑色</option>
                        <option>古装</option>
                        <option>惊栗</option>
                        <option>武侠</option>
                        <option>舞台</option>
                        <option>鬼怪</option>
                        <option>灾难</option>
                        <option>荒诞</option>
                    </select>
                </div>
            </li>
            <li>
                <div>
                    <select class="form-control" id="sort">
                        <option value="hot">按热门排序</option>
                        <option value="rating">按评分排序</option>
                        <option value="date">按上映日期排序</option>
                    </select>
                </div>
            </li>
            <c:if test="${not empty key}">
                <li>
                    <div>
                        <input class="form-control" id="key" value="${key}" READONLY>
                    </div>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="container">
        <ul id="film-list">
        </ul>
    </div>
    <div class="margin-top-50"></div>
    <div class="margin-left-30 margin-top-30">
        <ul id="paginator"></ul>
    </div>
</div>

<%@include file="../common/footer.jspf" %>


</body>

<script type="text/javascript">
    seajs.use("pages/category-list");
</script>
</html>
