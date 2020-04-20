<%--
  Created by IntelliJ IDEA.
  User: sb
  Date: 05/12/2019
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<form action="/profile" method="post">

<h1>${user.login} profile</h1>

<p>Login: ${user.login}</p>

<p>Email: ${user.email}</p>

<p>Info: ${user.info}</p>
</form>
To exit
<form action="/profile" method="post">
    <input type="hidden" name="action" value="delete">
        <input type="submit" value="exit">
</form>
<p></p>
Edit profile <a href="/editing">Edit</a>
</body>
</html>
