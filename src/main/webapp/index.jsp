<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>INDEX</title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>
<div class="decor">
    <div class="form-row">
        <h1 align="center">Фитнес-клуб</h1>
        <form action="controller" method="get">
            <div>
                <input type="hidden" name="command" value="login"/>
                <input type="text" name="login" placeholder=" " value=""/><label>Логин</label>
                <input type="password" name="password" placeholder=" " value=""/><label>Пароль</label>
                <output style="color: red">${message}</output>
                <input type="submit" name="input" value="Вход"/>
            </div>
        </form>
        <form action="controller" method="get">
            <div>
                <input type="hidden" name="command" value="open_page">
                <input type="hidden" name="page" value="pages/registrationStepOne.jsp">
                <input type="submit" name="submit" value="Регистрация"><br/>
            </div>
        </form>
    </div>
</div>
</body>
</html>