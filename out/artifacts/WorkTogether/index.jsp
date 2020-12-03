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
  <!--2秒后跳转到登录页面-->
  <meta http-equiv="refresh" content="2; url=${pageContext.request.contextPath}/user/toLogin" />
  <title>WorkTogether</title>
</head>
<body>

  <table style="margin-top:15%"  align="center">
    <tr>
      <td><input style="width: 56px" type="button" value="去登录" onclick = "window.location.href='${pageContext.request.contextPath}/user/toLogin'"/></td>
    </tr>
  </table>
</body>
</html>
