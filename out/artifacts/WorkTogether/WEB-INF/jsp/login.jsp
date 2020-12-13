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
        var form = document.getElementById("loginForm");
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
    <title>WorkTogether</title>
</head>
<body>
<header align="center"><img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="加载失败" style="width: 300px;height: 300px"></header>
<form id="loginForm" action="${pageContext.request.contextPath}/user/checkLogin" method="post">
    <table  align="center">
        <tr>
            <td>手机号:</td>
            <td><input id="userTel" name="userTel" type="text"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td style="height: 50px"><input id="userPwd" name="userPwd" type="password"/></td>
        </tr>
        <tr>
            <td><button id="TencentCaptcha" style="width: 56px" data-appid="2076025590" data-cbfn="callback" type="button">登录</button></td>
            <td align="right"><input type="button" style="width: 56px;height: 24px" value="注册" onclick="window.location.href='${pageContext.request.contextPath}/user/toRegister'"/>
        </tr>
    </table>
</form>
</body>
</html>
