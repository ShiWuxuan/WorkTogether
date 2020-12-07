<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/11/30
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userID" value="1"></c:set>
<c:set var="teamID" value="2"></c:set>
<html>
<head>
    <title>Work Together</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

</head>
<body>

<!-- bootstrap的核心js文件 -->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

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
                    <a href="${pageContext.request.contextPath}">首页</a>
                </li>
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-grain" aria-hidden="true"></span>
                        我的团队
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/task/myTask/${userID}">
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
                    <a href="${pageContext.request.contextPath}/log/mylog/${userID}">
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
            <div class="page-header">
                <h1>添加新任务</h1>
                <div style="float: left;margin-right: 80%;">
                    <h3>团队：</h3>
                </div>
                <div>
                <button id="submitTask" type="submit" class="btn btn-warning btn-lg" onclick="addTask()">提交任务</button>
                </div>
            </div>
            <form>
                <div class="form-group">
                    <label for="contentInput">任务名称</label>
                    <input type="text" class="form-control" id="taskNameInput" style="width: 40%">
                </div>
                <div class="form-group" style="float: left">
                    <label for="prioritySelect">优先级</label>
                    <select class="form-control" id="prioritySelect" style="width: 100px;margin-right: 100px">
                        <option value="0">一般</option>
                        <option value="1">重要</option>
                        <option value="2">紧急</option>
                    </select>
                </div>
                    <div class="form-group" >

                        <label>选择任务截止时间：</label>

                        <!--指定 date标记-->

                        <div class='input-group date' id="datetimepicker">

                            <input id="endTimeInput" type='text' class="form-control" style="width: 200px" />

                            <span class="input-group-addon" style="float: left;width: 60px;height: 34px">

                    <span class="glyphicon glyphicon-calendar"></span>

                </span>

                        </div>

                    </div>
                        <script type="text/javascript">
                            $(function () {
                                $('#datetimepicker').datetimepicker({
                                    format: 'YYYY-MM-DD hh:mm',
                                locale: moment.locale('zh-cn')
                            });
                            });
                        </script>
            </form>
            <script>
                function addTask() {
                    $.post({
                        url:"${pageContext.request.contextPath}/task/addTask",
                        data: {
                            "taskName":$("#taskNameInput").val(),
                            "priority":$("#prioritySelect").val(),
                            "endTime":$("#endTimeInput").val(),
                            "teamId":${teamID},
                            "memberId":2
                        },
                        success:function (data) {
                            if (data){
                                $('#myModal').modal('hide');
                                window.parent.location.reload(true);
                            }
                        }
                    })
                }
            </script>


            <div style="margin-bottom: 40px">
            <button class="btn btn-info" data-toggle="modal" data-target="#myModal" >添加子任务</button>
            </div>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">添加子任务</h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="contentInput">任务内容</label>
                                    <input type="text" class="form-control" id="contentInput" required>
                                </div>
                                <div class="form-group">
                                    <label for="weightInput">任务占比</label>
                                    <input type="text" class="form-control" id="weightInput" placeholder="请输入1-100间的整数" required>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-info" onclick="addSubTask()">提交</button>
                        </div>

                        <script>
                            function addSubTask() {
                                $.post({
                                    url:"${pageContext.request.contextPath}/task/addSubTask",
                                    data: {
                                        "content":$("#contentInput").val(),
                                        "weight":$("#weightInput").val()
                                    },
                                    success:function (data) {
                                        if (data){
                                            $('#myModal').modal('hide');
                                            window.parent.location.reload(true);
                                        }
                                    }
                                })
                            }
                        </script>
                    </div>
                </div>
            </div>

            <div class="col-md-8 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th style="vertical-align: middle !important;text-align: center;">序号</th>
                    <th style="vertical-align: middle !important;text-align: center;">任务内容</th>
                    <th style="vertical-align: middle !important;text-align: center;">占比</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="temp" items="${tempList}">
                    <tr>
                        <td style="vertical-align: middle;text-align: center">${temp.subTaskId}</td>

                        <td style="vertical-align: middle ;text-align: center;">${temp.content}</td>

                        <td style="vertical-align: middle ;text-align: center;">${temp.weight}%</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
</div>


</body>
</html>