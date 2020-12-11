<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: swx
  Date: 2020/11/30
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<%@ page import="java.io.*,java.util.*" %>
<%
    String userIDKey = new String("userID");
    Integer userID = 1;
    if (session.isNew()){
        session.setAttribute(userIDKey, userID);
    }
%>
<html>
<head>
    <title>任务详情</title>
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
                <li class="divider">
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span> 帮助</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
            <div class="page-header">
                <h1>任务详情</h1>
            </div>

            <div class="col-md-2 column">
                <a href="${pageContext.request.contextPath}/task/myTask/${userID}" class="btn btn-default active" role="button">返回我的任务列表</a>
            </div>

            <div class="col-md-8 column">
                <h3>当前任务：${taskName}&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;任务进度：${progress}%</h3>
            </div>
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th style="vertical-align: middle !important;text-align: center;">任务内容</th>
                    <th style="vertical-align: middle !important;text-align: center;">占比</th>
                    <th style="vertical-align: middle !important;text-align: center;">当前状态</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="subtask" items="${subTasks}">
                    <tr>
                        <td style="vertical-align: middle;text-align: center">${subtask.content}</td>

                        <td style="vertical-align: middle ;text-align: center;">${subtask.weight}%</td>
                        <c:choose>
                        <c:when test="${subtask.isComplete==true}">
                            <td style="vertical-align: middle ;text-align: center;color: black; font-size: larger">已完成</td>
                        </c:when>
                        <c:when test="${subtask.isComplete==false}">
                        <td style="vertical-align: middle;text-align: center;">
                            <a href="${pageContext.request.contextPath}/task/completeSubTask/${taskId}/${subtask.subTaskId}" class="btn btn-info" role="button">完成</a>
                        </td>
                        </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
