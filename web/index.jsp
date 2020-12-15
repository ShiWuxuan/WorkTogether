<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/11/8
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/shift.css" rel="stylesheet">

  <link rel="stylesheet" href="http://s3.amazonaws.com/codecademy-content/courses/ltp/css/bootstrap.css">

  <!--2秒后跳转到登录页面-->
  <%--  <meta http-equiv="refresh" content="2; url=${pageContext.request.contextPath}/user/toLogin" />--%>

  <title>WorkTogether</title>
</head>
<body>
<div class="nav">
  <div class="container">
    <ul class="pull-left">
      <li style="display: inline"><a href="index.jsp" style="font-size: 30px">Work Together</a></li>
    </ul>
    <ul class="pull-right">
      <li style="display: inline">
        <a href="${pageContext.request.contextPath}/user/toLogin" style="font-size: 20px">
          登录
        </a>
      </li>
      <li style="display: inline"><a href="${pageContext.request.contextPath}/user/toRegister" style="font-size: 20px">注册</a></li>
    </ul>
  </div>
</div>


<div class="jumbotron" style="min-height: 640px;background-repeat: no-repeat; position: relative;
        background-size: cover; background-image: url(${pageContext.request.contextPath}/img/indexbg.png)">
  <div class="container">
    <h1>WorkTogether</h1>
    <p>让你的团队更加高效</p>
    <a href="${pageContext.request.contextPath}/user/toLogin">加入我们</a>
  </div>
</div>
<%--<table style="margin-top:15%"  align="center">--%>
<%--  <tr>--%>
<%--    <td><input style="width: 56px" type="button" value="去登录" onclick = "window.location.href='${pageContext.request.contextPath}/user/toLogin'"/></td>--%>
<%--  </tr>--%>
<%--</table>--%>
</body>
</html>