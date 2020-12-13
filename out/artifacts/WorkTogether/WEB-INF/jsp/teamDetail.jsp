<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/5
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
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
                        页面导航
                    </h4>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}">首页</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/team/myTeam/${userTel}"><span class="glyphicon glyphicon-grain" aria-hidden="true"></span> 我的团队</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/task/myTask/${userId}"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 我的任务</a>
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> 日历</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/log/mylog/${userId}"><span class="glyphicon glyphicon-file" aria-hidden="true"></span> 日志</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/post/AllPost/1">
                        <span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
                        论坛
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/user/userDetail/${userId}"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> 个人中心</a>
                </li>
                <li class="divider">
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span> 帮助</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
                <div class="page-header">
                <c:set var="team" value="${team}"/>
                    <h1>${team.teamName}团队的详情</h1>
                </div>

                <div class="col-md-4 column">
                    <a href="${pageContext.request.contextPath}/team/backToMyTeam" class="btn btn-default active" role="button">返回我的团队</a>
                </div>

                <div class="col-md-8 column">
                    <h3>团队介绍：${team.teamIntroduction}</h3>
                </div>
                <table class="table table-hover table-striped">
                    <thead>

                    <tr>
                        <th>成员类型</th>
                        <th style="vertical-align: middle !important;text-align: center;">成员姓名</th>
                        <th style="vertical-align: middle !important;text-align: center;">成员手机</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td style="vertical-align: middle">${user.userType}</td>
                            <td style="vertical-align: middle ;text-align: center;">${user.userName}</td>
                            <td style="vertical-align: middle ;text-align: center;">${user.userTel}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
