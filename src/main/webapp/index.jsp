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
    Логин: <input type="text" name="login" value=""/><br/>
    Пароль: <input type="password" name="password" value=""/><br/>
    <input type="submit" name="input" value="Вход"/><br/>
</form>
${msg}
<form action="controller" method="get">
    <input type="hidden" name="command" value="open_page">
    <input type="hidden" name="page" value="pages/registrationStepOne.jsp">
    <input type="submit" name="submit" value="Регистрация"><br/>
</form>


<br/> <br/> <br/> <br/> <br/> <br/> <br/>
<form action="controller" method="get">
    <input type="hidden" name="command" value="open_page"/>
    <input type="week"/><br/>
    <input type="button"/><br/>
    <input type="checkbox"/><br/>
    <input type="color"/><br/>
    <input type="date"/><br/>
    <input type="datetime-local"/><br/>
    <input type="email"/><br/>
    <input type="image"/><br/>
    <input type="month"/><br/>
    <input type="number"/><br/>
    <input type="radio"/><br/>
    <input type="range"/><br/>
    <input type="reset"/><br/>
    <input type="search"/><br/>
    <input type="tel"/><br/>
    <input type="time"/><br/>
    <input type="url"/><br/>
</form>
</body>
</html>