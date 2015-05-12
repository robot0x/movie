<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <title>${subject.title}评论-${appName}</title>
    <%@include file="../common/head.jspf" %>
    <link rel="stylesheet" type="text/css" href="/assets/plugins/bootstrap-star-rating/css/star-rating.min.css"/>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
${subject.title}的评论
评论 <textarea></textarea>
<input class="rating" type="number" class="rating" min=1 max=10 step=2 data-size="sm" data-rtl="true"/>
<%@include file="../common/footer.jspf" %>
</body>
<script type="application/javascript" src="/assets/plugins/bootstrap-star-rating/js/star-rating.min.js"/>
<script type="application/javascript">
    $('input').rating();
</script>
</html>
