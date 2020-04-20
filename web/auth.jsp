<%--
  Created by IntelliJ IDEA.
  User: sb
  Date: 03/12/2019
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<form action="/auth" method="post">
    Login:
    <input type="text" name="login">
    Password:
    <input type="password" name="pass">
    <input type="submit" value="Sign in">
</form>
</body>
</html>
