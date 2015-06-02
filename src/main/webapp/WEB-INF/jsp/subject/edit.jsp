<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑 ${subject.title}-${appName}</title>
    <%@include file="../common/head.jspf" %>
</head>
<body>
<%@include file="../common/topNav.jspf" %>
<div class="container">
    <div class="margin-top-50"></div>

    <form class="form-horizontal" id="form_register" role="form" action="/subject/${subject.id}/update" method="POST">
        <div class="form-group">
            <label class="col-sm-3 control-label">导演</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="directors"
                       placeholder="请输入导演" value="${subject.directors}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">主演</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="casts"
                       placeholder="请输入主演" value="${subject.casts}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">编剧</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="writers"
                       placeholder="请输入编剧" value="${subject.writers}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">类型</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="genres"
                       placeholder="请输入类型" value="${subject.genres}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">地区</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="countries"
                       placeholder="请输入地区" value="${subject.countries}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">语言</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="languages"
                       placeholder="请输入语言" value="${subject.languages}"/>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">片长</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="durations"
                       placeholder="请输入片长" value="${subject.durations}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">别名</label>

            <div class="col-sm-5">
                <input type="text" class="form-control" name="originalTitle"
                       placeholder="请输入别名" value="${subject.originalTitle}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">剧情介绍</label>

            <div class="col-sm-5">
                <textarea class="form-control" name="summary" rows="10">
                    ${subject.summary}
                </textarea>
            </div>
        </div>

        <div class="form-group margin-top-40">
            <div class="col-sm-offset-3 col-sm-10">
                <button type="submit" class="btn btn-info">提交
                </button>
            </div>
        </div>


    </form>
    <div class="margin-bottom-100"></div>
</div>


<%@include file="../common/footer.jspf" %>


</body>
</html>
