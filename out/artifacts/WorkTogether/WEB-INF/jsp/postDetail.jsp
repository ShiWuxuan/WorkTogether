<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/12/7
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userId" value="${userId}" scope="session"></c:set>
<c:set var="userTel" value="${userTel}" scope="session"></c:set>
<html>
<head>
    <title>Work Together</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="http://malsup.github.io/jquery.form.js"></script>

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
        <div class="col-md-10 column" ondblclick="like()">
            <div class="page-header">
                <h1>${postInfo.title}</h1>
            <div>
                <p>
                    ${postInfo.detail}
                </p>
                <div>
                    <a href="${pageContext.request.contextPath}/post/gotoMakePost" class="btn btn-default active" role="button">
                        我要发帖
                    </a>
                    <button id="likeBtn" class="btn btn-info" onclick="like()" style="margin-left: 10%">
                        点赞&emsp;
                        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>

                        ${postInfo.likeNumber}
                    </button>
                    <p style="color: lightslategrey;float: right;margin-right: 5%">
                        评论数：${number}
                    </p>
                </div>
            </div>


            </div>
            <script type="text/javascript">
                function like() {
                    $("#likeBtn").text("已赞同");
                    $("#likeBtn").prop('disabled', true);
                    $.post({
                        url: "${pageContext.request.contextPath}/post/like",
                        data: {
                            "postId":${postId}
                        },
                    })
                }
            </script>

            <div style="height: 490px;overflow: auto;">
            <table class="table table-hover table-striped">

                <tbody>
                <c:forEach var="comment" items="${comments}">
                    <tr>
                        <td style="vertical-align: middle; width: 130px">
                            <img alt="加载失败" src="${pageContext.request.contextPath}/img/head.jpg" class="img-circle" style="width: 100px;height: 100px"/>
                        </td>
                        <td style="vertical-align: top">
                            <p style="vertical-align: top;border: 2px solid;width: fit-content;border-radius: 10px;background: lightskyblue ">
                                ${comment.userName}
                            </p>
                                <p style="vertical-align: center">
                                        ${comment.content}
                                </p>
                        </td>

                        <td style="vertical-align: top;float: right">
                            <p style="color: lightslategrey">
                                发帖时间：${comment.time} &emsp;&emsp;
                            </p>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>

            <form>
                <div class="form-group">
                    <label for="inputCommentContent">评论</label>
                    <textarea class="form-control" id="inputCommentContent" name="inputCommentContent" rows="4" placeholder="快加入大家的讨论吧"></textarea>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-info" onclick="comment()">发表评论</button>
                </div>
            </form>
            <div id="failAlert" class="alert alert-warning alert-dismissable hide">
                发表评论失败，请检查网路后重试！
            </div>
            <div id="successAlert" class="alert alert-success alert-dismissable hide">
                评论发表成功！
            </div>

            <script>
                function comment() {
                    $.post({
                        url:"${pageContext.request.contextPath}/post/submitComment",
                        data: {
                            "postId":${postInfo.id},
                            "content":$("#inputCommentContent").val(),
                            "userId":${userId}
                        },
                        success:function (data) {
                            if(data===false){
                                $("#failAlert").show();
                            }
                        }
                    })
                }
            </script>
        </div>
    </div>
</div>

</body>
</html>
