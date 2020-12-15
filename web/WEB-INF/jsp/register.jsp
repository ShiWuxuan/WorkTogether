<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/30
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    window.callback = function(res){
        console.log(res);
        var form = document.getElementById("registerForm");
        if(res.ret === 0) {
            form.submit();
        }
        else {
            alert("验证失败")
        }
    };
</script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://ssl.captcha.qq.com/TCaptcha.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-md-offset-4">
<img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="加载失败" style="width: 300px;height: 300px">
</div>
<div class="col-md-offset-4">
    <form style="width: 330px" id="registerForm" action="${pageContext.request.contextPath}/user/register" method="post" class="bs-example bs-example-form" role="form">
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">手机号  ：</span>
                <input id="userTel" name="userTel" type="text" class="form-control" placeholder="请输入您希望注册的手机号"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">密码    ：</span>
                <input id="userPwd" name="userPwd" type="password" class="form-control" placeholder="请输入至少8位的密码"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">确认密码：</span>
                <input id="confirmUserPwd" name="confirmUserPwd" type="password" class="form-control" placeholder="请重复输入您的密码"/>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <div class="col-md-9"><button id="TencentCaptcha" style="width: 56px" data-appid="2076025590" data-cbfn="callback" type="button">注册</button></div>
                <div class="col-md-3"><input type="button" style="width: 56px;height: 24px" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/user/toLogin'"/></div>
            </div>
        </div>
    </form>
</div>
</body>
</html>