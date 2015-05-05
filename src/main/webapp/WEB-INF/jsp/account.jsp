<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心-${appName}</title>
    <%@include file="common/head.jspf" %>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div class="container">
    <div class="margin-top-100"></div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>用户名</th>
            <th>密码</th>
            <th>注册时间</th>
            <th>是否为管理员</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${SessionUtils.user.userName}</td>
            <td><mv:stringMask beforeMask="${SessionUtils.user.password}"/></td>
            <td><fmt:formatDate value="${SessionUtils.user.createTime}" pattern="yyyy年MM月dd日"/></td>
            <td><c:choose>
                <c:when test="${SessionUtils.user.admin}">
                    是
                </c:when>
                <c:otherwise>
                    否
                </c:otherwise>
            </c:choose>
            </td>
        </tr>
        </tbody>
    </table>

    <div class="margin-bottom-100"></div>
</div>


<%@include file="common/footer.jspf" %>


</body>
</html>
