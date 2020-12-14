<%--
  Created by IntelliJ IDEA.
  User: 发功阿你
  Date: 2020/12/2
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<html>
<head>
    <title>工作心得</title>
    <title>心得详情</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>

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
                    <a href="${pageContext.request.contextPath}/post/AllPost/1">
                        <span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
                        论坛
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/user/userDetail/${userId}"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> 个人中心</a>
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
                <h1>
                    写心得
                </h1>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/log/mylog/${userId}" class="btn btn-default active" role="button">返回我的工作心得</a>
                <p> </p>
            </div>

            <form action="${pageContext.request.contextPath}/log/publishLog/${userId}" method="post"
                  id="newLog" onsubmit="return submitForm();">
                <fieldset>

                    <div class="form-group">
                        <label for="inputLogTitle">标题</label>
                        <input type="text" class="form-control" id="inputLogTitle" name="inputLogTitle" placeholder="心得标题">
                    </div>

                    <div class="form-group">
                        <label for="inputTeam">选择项目团队</label>
                        <select class="form-control" id="inputTeam" name="inputTeam">
                            <option>#001</option>
                            <option>#002</option>
                            <option>#003</option>
                            <option>#004</option>
                            <option>#005</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="0"> 日志
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="1"> 周志
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="2"> 月志
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="inputLogContent">内容</label>
                        <textarea class="form-control" id="inputLogContent" name="inputLogContent" rows="15"></textarea>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg">发表</button>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
</div>

<script>
    function submitForm(){
        var logTitle = $("#inputLogTitle").val();
        var logContent = $("#inputLogContent").val();
        var logType0=$("#inlineRadio1").val();
        var logType1=$("#inlineRadio2").val();
        var logType2=$("#inlineRadio3").val();

        if(logTitle === "" || logTitle == null){
            alert("请填写心得标题");
            return false;
        }else if(logContent === "" || logContent == null){
            alert("心得内容为空");
            return false;
        // }else if(!(logType0 || logType1 || logType2)){
        //     alert("请选择心得类型");
        //     return false;
        }
        <%--else{--%>
        <%--    $("#newLog").ajaxSubmit(function (..) {--%>
        <%--        var mes = JSON.parse(date);--%>
        <%--        alert(mes.tip);--%>
        <%--        window.location.href="${pageContext.request.contextPath}/log/mylog/${userId}";--%>
        <%--    });--%>
        <%--    return false;--%>
        // }
    }
</script>

</body>
</html>
