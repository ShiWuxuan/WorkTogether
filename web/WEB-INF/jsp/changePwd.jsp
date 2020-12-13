<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/13
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<script type="text/javascript">
    window.callback = function(res){
        console.log(res);
        var form = document.getElementById("changePwdForm");
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
    <title>Work Together</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ssl.captcha.qq.com/TCaptcha.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2 column">
            <div class="page-header">
                <img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="加载失败">
            </div>
            <ul class="nav nav-list">
                <li class="nav-header">
                    <h4>
                        用户：${user.userName}
                    </h4>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}/user/userDetail/${userId}">
                        <span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
                        个人资料</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/user/userPwd/${userId}">
                        <span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
                        修改密码</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
            <div class="auth_toolbar">
                <a style="font-size:14px" href="${pageContext.request.contextPath}/task/myTask/${userId}">
                    <span style="font-weight:bold;font-family: fantasy;">&lt;&lt;&nbsp;</span>返回
                </a>
                <a id="logout" href="${pageContext.request.contextPath}/user/toLogin" title="安全退出">
                    <i class="nav_icon nav_icon_logout"></i><span>安全退出</span>
                </a>
            </div>
            <div class="page-header">
                <c:set var="user" value="${user}"/>
                <h2>修改密码</h2>
            </div>
            <form id="changePwdForm" action="${pageContext.request.contextPath}/user/changePwd/${userId}" method="post">
                <table>
                    <tr>
                        <td>原密码:</td>
                        <td style="height: 50px"><input id="userPwd" name="userPwd" placeholder="请输入原密码" type="password"/></td>
                    </tr>
                    <tr>
                        <td>新密码:</td>
                        <td style="height: 50px"><input id="newUserPwd" name="newUserPwd" placeholder="请输入至少8位的密码"  type="password"/></td>
                    </tr>
                    <tr>
                        <td>新密码确认:</td>
                        <td style="height: 50px"><input id="confirmUserPwd" name="confirmUserPwd" placeholder="请重复输入您的新密码" type="password"/></td>
                    </tr>
                    <tr>
                        <td><button id="TencentCaptcha" style="width: 56px" data-appid="2076025590" data-cbfn="callback" type="button">保存</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
