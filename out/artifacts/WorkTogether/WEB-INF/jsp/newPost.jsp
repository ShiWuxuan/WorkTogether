<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/7
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<html>
<head>
    <title>发帖</title>

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
                    发帖
                </h1>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/post/AllPost/1" class="btn btn-default active" role="button">返回论坛</a>
                <p> </p>
            </div>

            <form id="newPost" action="${pageContext.request.contextPath}/post/makePost">
                <fieldset>

                    <div class="form-group">
                        <label for="inputId"></label>
                        <input type="text" class="form-control hide" id="inputId" name="userId" value="${userId}">
                    </div>
                    <div class="form-group">
                        <label for="inputTitle">标题</label>
                        <input type="text" class="form-control" id="inputTitle" name="title" placeholder="请输入标题" required>
                    </div>
                    <div class="form-group">
                        <label for="inputDetail">详细描述</label>
                        <textarea class="form-control" id="inputDetail" name="detail" rows="10" required></textarea>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-info btn-lg" onclick="submitForm()">发表</button>
                    </div>

                </fieldset>
            </form>
        </div>
    </div>
</div>

<script>
    function submitForm(){
        var title = $("#inputTitle").val();
        var detail = $("#inputDetail").val();

        if(title === "" || title == null){
            alert("请填写标题");
            return false;
        }else if(detail === "" || detail == null) {
            alert("请填写详细描述");
            return false;
        }
        <%--else {--%>
        <%--    $.post({--%>
        <%--        url:"${pageContext.request.contextPath}/post/makePost",--%>
        <%--        data: {--%>
        <%--            "title":title,--%>
        <%--            "detail":detail,--%>
        <%--            "userId":${userID}--%>
        <%--        },--%>
        <%--        success:function (data) {--%>
        <%--            console.log(data);--%>
        <%--        }--%>
        <%--    })--%>
        <%--}--%>
    }
</script>

</body>
</html>
