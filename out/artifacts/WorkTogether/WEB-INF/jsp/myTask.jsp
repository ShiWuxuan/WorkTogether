<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: swx
  Date: 2020/11/8
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*" %>
<%
    String userIDKey = new String("userID");
    Integer userID = 1;
    if (session.isNew()){
        session.setAttribute(userIDKey, userID);
    }
%>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<html>
<head>
    <title>我的任务列表</title>
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
                    <a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>首页</a>
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
                <h1>我的任务</h1>
            </div>
            <div class="col-md-2 column">
                <a href="${pageContext.request.contextPath}/task/myUrgentTask/${userId}" class="btn btn-default active" role="button">显示紧急任务</a>
            </div>
            <div class="col-md-2 column">
                <a href="${pageContext.request.contextPath}/task/myTask/${userId}" class="btn btn-default active" role="button">显示所有任务</a>
            </div>
            <div class="col-md-6 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/task/queryTask/${userId}" method="post" align="center">
                    <input type="text" name="keyword" class="form-control" placeholder="请输入任务内容"/>
                    <button type="submit" class="btn btn-default active">查找</button>
                </form>
            </div>

            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>任务名称</th>
                    <th width="300px" style="vertical-align: middle;text-align: center;">进度</th>
                    <th style="vertical-align: middle !important;text-align: center;">截止时间</th>
                    <th style="vertical-align: middle !important;text-align: center;">优先级</th>
                    <th style="vertical-align: middle !important;text-align: center;">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="task" items="${tasks}">
                    <tr>
                        <td style="vertical-align: middle">${task.taskName}</td>
                        <td style="vertical-align: middle;">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${task.taskProgress}" aria-valuemin="0" aria-valuemax="100" style="width: ${task.taskProgress}%">
                                    ${task.taskProgress}%
                                    </div>
                                </div>
                        </td>
                        <td style="vertical-align: middle ;text-align: center;">${task.endTime}</td>
                        <c:choose>
                            <c:when test="${task.priority==0}">
                                <td style="vertical-align: middle ;text-align: center; color: deepskyblue; font-size: larger">一般</td>

                            </c:when>
                            <c:when test="${task.priority==1}">
                                <td style="vertical-align: middle ;text-align: center;color: gold; font-size: larger">重要</td>
                            </c:when>
                            <c:when test="${task.priority==2}">
                                <td style="vertical-align: middle ;text-align: center; color: red; font-size: larger">紧急</td>
                            </c:when>
                        </c:choose>
                        <td style="vertical-align: middle;text-align: center;">
                            <a href="${pageContext.request.contextPath}/task/taskDetail/${task.taskId}" class="btn btn-info" role="button">查看详情</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
