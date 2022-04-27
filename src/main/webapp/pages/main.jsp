<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.04.2022
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h1 align="center">Main to welcome!!!</h1>
<hr/>
Hello (forward) ${user}!
<hr/>
Hi (redirect/forward) ${user_name}!
<hr/>
${filter_attr}!
<hr/>
<form action="controller" method="get">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logOut"/>
</form>
</body>
</html>
