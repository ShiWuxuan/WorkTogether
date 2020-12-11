<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/7
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<html>
<head>
    <title>论坛社区</title>
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
                    <h4>页面导航</h4>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}">
                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                        首页
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/team/myTeam/${userTel}">
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
                <li>
                    <a href="${pageContext.request.contextPath}/post/AllPost/1">
                        <span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
                        论坛
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
            <div class="page-header">
                <h1>论坛</h1>
            </div>

            <div class="col-md-4 column" style="horiz-align: center">
                <a href="${pageContext.request.contextPath}/post/gotoMakePost" class="btn btn-default active" role="button" style="margin-right: 15%">
                    我要发帖
                </a>
                <a href="${pageContext.request.contextPath}/post/getHotPost" class="btn btn-default active" role="button">
                    热门帖子
                </a>
            </div>

            <div class="col-md-6 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/task/queryTask/${userId}" method="post" align="center">
                    <input type="text" name="keyword" class="form-control" placeholder="请输入标题关键字"/>
                    <button type="submit" class="btn btn-default active">查找</button>
                </form>
            </div>

            <table class="table table-hover table-striped">

                <tbody>
                <c:forEach var="posting" items="${pagination.postList}">
                    <tr>
                        <td style="vertical-align: middle; width: 130px">
                        <img alt="加载失败" src="${pageContext.request.contextPath}/img/logo.png" class="img-circle" style="width: 100px;height: 100px"/>
                        </td>
                        <td style="vertical-align: middle">
                            <div class="caption" >
                                <h3>${posting.title} <a class="btn btn-info" href="${pageContext.request.contextPath}/post/AllRecord/${posting.id}" style="float: right;margin-right: 5%">查看详情</a></h3>
                                <p>
                                    ${posting.detail} <a class="btn btn-default" href="#" style="float: right;margin-right: 5%">分享</a>
                                </p>
                                <p style="color: lightslategrey">
                                    发帖人：${posting.userName}&emsp;&emsp; 发帖时间：${posting.time}&emsp;&emsp;
                                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                                        ${posting.likeNumber}
                                </p>
                            </div>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <ul class="pager" style="align-items: center">
                <c:choose>
                    <c:when test="${!pagination.showPre}">
                        <li class="disabled" style="horiz-align: left">&larr; 上一页</li>
                    </c:when>
                    <c:when test="${pagination.showPre}">
                        <li><a href="${pageContext.request.contextPath}/post/AllPost/${pagination.prePage}">&larr; 上一页</a></li>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${!pagination.showNext}">
                        <li class="disabled">下一页 &rarr;</li>
                    </c:when>
                    <c:when test="${pagination.showNext}">
                        <li ><a href="${pageContext.request.contextPath}/post/AllPost/${pagination.nextPage}">下一页 &rarr;</a></li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
