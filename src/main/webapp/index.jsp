<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1 align="center"> "Hello Index!"
</h1>
<br/>
<form action="controller" method="get">
    <input type="hidden" name="command" value="login"/>
    Логин: <input type="text" name="login" value=""/>
    <br/>
    Пароль: <input type="password" name="pass" value=""/>
    <br/>
    <input type="submit" name="input" value="Вход"/>
    <br/>
    ${login_msg}
    <br/>
    ${pageContext.session.id}
    <br/>
    ${filter_attr}
</form>

<form action="controller" method="get">
    <input type="submit" name="registration" value="Регистрация">
</form>
</body>
</html>