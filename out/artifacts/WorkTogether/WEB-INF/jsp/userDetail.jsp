<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/13
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<script>
    function changeUserName(userId,userName)
    {
        var newName = prompt("请决定您的新用户名：",userName);
        if(newName != null)
            window.location.href = "${pageContext.request.contextPath}/user/changeName/"+userId+"/"+newName;
        else
            window.location.href = "${pageContext.request.contextPath}/user/userDetail/${userId}";
    }
</script>
<html>
<head>
    <title>Work Together</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
                <h2>编辑个人资料</h2>
            </div>

            <p>
            <h4>用户名：${user.userName}<a class="btn" onclick="changeUserName(${user.userId},'${user.userName}')">更改用户名 »</a></h4>
            </p>
            <p>
            <h4>用户手机号：${user.userTel}</h4>
            </p>
            <p>
            <h4>所在团队：${user.teamName}<a class="btn" href="${pageContext.request.contextPath}/team/myTeam/${userTel}">前往团队界面 »</a></h4>
            </p>
        </div>
    </div>
</div>
</body>
</html>