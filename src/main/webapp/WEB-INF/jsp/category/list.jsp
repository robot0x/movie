<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分类-${appName}</title>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="span9">
    <label>部门列表</label>
    <hr/>
    <div id="list"></div>

    <div>
        <ul id="example"></ul>
    </div>
</div>

<%@include file="../common/footer.jspf" %>
<script type="text/javascript" src="/assets/js/bootstrap-paginator.js"></script>

</body>

<script type="text/javascript">
    seajs.use("pages/category-list");
</script>
</html>
