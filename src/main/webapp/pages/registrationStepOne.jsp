<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация_1</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>

<form class="decor" action="controller" method="get" novalidate>
    <div class="form-row">
        <h1 align="center">Регистрация</h1>
        <input type="hidden" name="command" value="registration_step_one">

        <input type="text" placeholder=" " name="login" value="${attr_user.get("login")}"
               style="background: ${attr_user.get("login_color")}"
               pattern="^[a-zA-Z0-9._-]{3,15}$" required>
        <label>Логин</label>

        <input type="password" name="password" placeholder=" " value="${attr_user.get("password")}"
               style="background: ${attr_user.get("password_color")}"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,25}$" required>
        <label>Пароль</label>

        <input type="text" name="name" placeholder=" " value="${attr_user.get("name")}"
               style="background: ${attr_user.get("name_color")}" pattern="^\p{L}{2,}$" required>
        <label>Имя</label>

        <input type="text" name="lastname" placeholder=" " value="${attr_user.get("lastname")}"
               style="background: ${attr_user.get("lastname_color")}" pattern="^\p{L}{2,}$" required>
        <label>Фамилия</label>

        <input type="email" name="mail" placeholder=" " value="${attr_user.get("mail")}"
               style="background: ${attr_user.get("mail_color")}" required>
        <label>Email</label>

        <input type="reset"/><input type="submit" name="registration" value="Далее">
    </div>
</form>

</body>
</html>