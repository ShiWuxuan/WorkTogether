<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/7
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Work Together</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script>
    function changeTeamNumberLimit(teamId)
    {
        var teamNumberLimit = prompt("请输入新的团队成员上限(最多50人)：");
        if(teamNumberLimit != "" && teamNumberLimit != null)
            window.location.href = "${pageContext.request.contextPath}/team/updateMyLeadingTeam/1/"+teamId+"/"+teamNumberLimit+"/1";
        else
            window.location.href = "${pageContext.request.contextPath}/team/showLeadingTeam";
    }
    function changeTeamIntroduction(teamId,introduction)
    {
        var newTeamIntroduction = prompt("请输入团队介绍：",introduction);
        if(newTeamIntroduction != null)
            window.location.href = "${pageContext.request.contextPath}/team/updateMyLeadingTeam/2/"+teamId+"/1/"+newTeamIntroduction;
        else
            window.location.href = "${pageContext.request.contextPath}/team/showLeadingTeam";
    }
</script>
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
                <li class="divider">
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span> 帮助</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
            <div class="page-header">
                <h1>我担任队长的团队</h1>
            </div>

            <div class="col-md-4 column">
                <a href="${pageContext.request.contextPath}/team/backToMyTeam" class="btn btn-default active" role="button">返回我的团队</a>
            </div>
            <div class="col-md-8 column">
                <form class="form-inline" action="${pageContext.request.contextPath}/team/findMyTeamByKeyword" method="post" align="left">
                    <input type="text" name="keyword" class="form-control" placeholder="请输入团队名称"/>
                    <button type="submit" class="btn btn-default active">查找</button>
                </form>
            </div>

            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>团队名称</th>
                    <th style="vertical-align: middle !important;text-align: center;">成员数量/成员上限</th>

                </tr>
                </thead>

                <tbody>
                <c:forEach var="team" items="${teams}">
                    <tr>
                        <td style="vertical-align: middle">${team.teamName}</td>
                        <td style="vertical-align: middle ;text-align: center;">${team.memberNum}/${team.memberNumLimit}</td>
                        <td style="vertical-align: middle;text-align: center;">
                            <a onclick="changeTeamNumberLimit(${team.teamId})" class="btn btn-default active" role="button">修改成员上限</a>
                        </td>
                        <td style="vertical-align: middle;text-align: center;">
                            <a onclick= "changeTeamIntroduction(${team.teamId},'${team.teamIntroduction}')" class="btn btn-default active" role="button">修改团队介绍</a>
                        </td>
                        <td style="vertical-align: middle;text-align: center;">
                            <a href="${pageContext.request.contextPath}/team/showLeadingMember/${team.teamId}" class="btn btn-default active" role="button">管理成员</a>
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
