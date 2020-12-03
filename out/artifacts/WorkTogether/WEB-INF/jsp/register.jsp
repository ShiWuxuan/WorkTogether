<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/11/30
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>WorkTogether</title>
</head>
<body>
<header align="center"><img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="加载失败" style="width: 300px;height: 300px"></header>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <table align="center">
        <tr>
        <td>手机号:</td>
        <td><input id="userTel" name="userTel" type="text"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td style="height: 50px"><input id="userPwd" name="userPwd" placeholder="请输入至少8位的密码" type="password"/></td>
        </tr>

        <tr>
            <td><input style="width: 56px" type="submit" value="注册"/></td>
            <td align="right"><input type="button" style="width: 56px;height: 24px" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/user/toLogin'"/>
        </tr>
    </table>
</form>
</body>
</html>