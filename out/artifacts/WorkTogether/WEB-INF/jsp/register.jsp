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

<form action="${pageContext.request.contextPath}/user/register" method="post">
    <table style="margin-top:15%"  align="center">
        <tr>
            <td>用户名:</td>
            <td><input id="userName" name="userName" type="text"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input id="userPwd" name="userPwd" type="password"/></td>
        </tr>
        <tr>
            <td>手机号:</td>
            <td><input id="userTel" name="userTel" type="text"/></td>
        </tr>
        <tr>
            <td><input style="width: 56px" type="submit" value="注册"/></td>
            <td align="right"><input type="button" style="width: 56px;height: 24px" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/user/toLogin'"/>
        </tr>
    </table>
</form>
</body>
</html>