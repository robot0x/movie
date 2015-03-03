<%--
  Created by IntelliJ IDEA.
  User: veryyoung
  Date: 2015/3/3
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<form class="form-horizontal" role="form" action="/login" method="POST">

    <label for="txt_loginname" class="col-sm-3 control-label">注册名</label>

    <div class="col-sm-9">
      <input type="text" class="form-control" id="txt_loginname" name="userName"
             placeholder="用户名/手机号"/>
    </div>

  </div>

  <div class="form-group">
    <label for="txt_password"
           class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;码</label>

    <div class="col-sm-9">
      <input type="password" class="form-control" id="txt_password" name="password"
             placeholder="密码"/>
    </div>
  </div>


  <div class="form-group">

      <button type="submit" class="btn btn-info" style="background: #168CE8;" id="login-submit">登录
      </button>
      &nbsp;
      <button type="reset" class="btn btn-warning">重置</button>
    </div>
  </div>
</form>

</body>
</html>
