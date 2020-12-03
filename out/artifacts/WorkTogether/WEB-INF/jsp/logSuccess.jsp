<%--
  Created by IntelliJ IDEA.
  User: 发功阿你
  Date: 2020/12/2
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>提交成功</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2 column">
            <div class="page-header">
                <img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="加载失败" />
            </div>
            <ul class="nav nav-list">
                <li class="nav-header">
                    <h4>页面导航</h4>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}">首页</a>
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
                        我的团队
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/task/myTask/${userId}">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                        我的任务
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
                        日历
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/log/mylog/${userId}">
                        <span class="glyphicon glyphicon-file" aria-hidden="true"></span>
                        日志
                    </a>
                </li>
                <li class="divider">
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span>
                        帮助
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
            <div class="hero-unit">
                <h1>
                    发表成功
                </h1>

                <p>
                    <a class="btn btn-primary btn-large" href="${pageContext.request.contextPath}/log/mylog/${userId}">返回我的工作心得</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
