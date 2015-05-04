<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页 - ${appName}</title>
    <%@include file="common/head.jspf" %>
    <%@include file="common/topNav.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/css/pages/index.css"/>

</head>
<body id="page-account-index">
<h1>共${subjects.size()}部热映影片</h1>

<div class="timeLayout" data-cfg-load="">

    <ul class="time-list">
        <c:forEach items="${subjects}" var="subject" varStatus="status">
            <li class="time-item <c:choose><c:when test="${status.count%2==0}">right</c:when><c:otherwise>left</c:otherwise></c:choose>"
                style="margin-top: 0px;"><span class="timedot">● <em
                    class="time-arr"></em></span>

                <div class="item-inner">
                    <div class="picArea"><a href="${qiniu}${subject.id}" class="fl" target="_blank">
                        <div class="loadmask"
                             style="position: absolute; width: 154px; height: 217px; display: none;"></div>
                        <img width="154" height="217"
                             src="${qiniu}${subject.id}"
                             style="visibility: visible; display: inline;"></a></div>
                    <div class="fl pl15 filmInfo">
                        <h3><a class="mdbColor" href="/subject/${subject.id}"
                               target="_blank"><c:if test="${not empty  subject.pubDate}">
                            ${subject.pubDate}上映</br>
                        </c:if>
                            《${subject.title}》</a></h3>

                        <p class="score"><label>评分:</label><span><b
                                class="mdbColor">${subject.totalRating/subject.ratingCount}</b></span></p>

                        <p class="direct">
                            <label>导演:</label><span>${subject.directors}</span>
                        </p>

                        <p class="rowlmt-1">
                            <label>主演:</label><span>${subject.casts}</span>
                        </p>

                        <p>
                            <label>类型:</label><span>${subject.genres}</span>
                        </p>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
    <div class="midLine"></div>
</div>

<%@include file="common/footer.jspf" %>
</body>
</html>