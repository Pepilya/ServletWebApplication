<%--
  Created by IntelliJ IDEA.
  User: sb
  Date: 05/12/2019
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProfileEdit</title>
</head>
<body>
<h2>${user.login} fill fields which you want to edit</h2>
<form action="/editing" method="post">
    Fill password: <br>
    <input type="password" name="pass1"> <br>
    Fill passwoed again: <br>
    <input type="password" name="pass2"> <br>
    Fill email: <br>
    <input type="email" name="email"> <br>
    Fill field "About yourself": <br>
    <input type="text" name="info">
    <input type="submit" value="Submit">
</form>
</body>
</html>
