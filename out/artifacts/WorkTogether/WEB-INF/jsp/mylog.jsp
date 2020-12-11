<%--
  Created by IntelliJ IDEA.
  User: 王子皓
  Date: 2020/11/24
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<html>
<head>
    <title>WorkTogether</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>

    <script>
        $("#dropdownMenu1").on('click',function(){
            $('.dropdown-toggle').dropdown();
        });
    </script>

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
                <h1>
                    我的工作心得
                </h1>
            </div>
            <div class="col-md-4 column">
                <!-- Single button -->
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        显示内容 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li>
                            <a href="${pageContext.request.contextPath}/log/mylog/${userId}">所有记录</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/log/mylog/${userId}/${0}">日志</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/log/mylog/${userId}/${1}">周志</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/log/mylog/${userId}/${2}">月志</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/log/keyLogs/${userId}" method="post" align="center">
                    <input type="text" name="keyword" class="form-control" placeholder="输入标题进行查询"/>
                    <button type="submit" class="btn btn-default active">查找</button>
                </form>
            </div>
            <div class="col-md-2 column">
                <a href="${pageContext.request.contextPath}/log/addLog/${userId}" class="btn btn-default active" role="button">写心得</a>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>
                        团队编号
                    </th>
                    <th>
                        提交时间
                    </th>
                    <th>
                        标题
                    </th>
                    <th>
                        类型
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="log" items="${logs}">
                    <tr>
                        <td>
                                ${log.teamId}
                        </td>
                        <td>
                                ${log.submitTime}
                        </td>
                        <td>
                                ${log.logTitle}
                        </td>
                        <c:choose>
                            <c:when test="${log.logType==0}">
                                <td>
                                    日志
                                </td>
                            </c:when>
                            <c:when test="${log.logType==1}">
                                <td>
                                    周志
                                </td>
                            </c:when>
                            <c:when test="${log.logType==2}">
                                <td>
                                    月志
                                </td>
                            </c:when>
                        </c:choose>
                        <td>
                            <a href="${pageContext.request.contextPath}/log/logDetail/${userId}/${log.logId}">查看</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
<%--            <div class="pagination pagination-centered">--%>
<%--                <ul>--%>
<%--                    <li>--%>
<%--                        <a href="#">上一页</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">1</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">2</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">3</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">4</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">5</a>--%>
<%--                    </li>--%>
<%--                    <li>--%>
<%--                        <a href="#">下一页</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
        </div>
    </div>
</div>
</body>
</html>
