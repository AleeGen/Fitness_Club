<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration step one</title>
</head>
<body>
<form action="controller" method="get">
    <input type="hidden" name="command" value="registration">
    Обязательные поля *
    Логин пользователя: <input type="text" name="login" value="">* <hr/>
    Пароль: <input type="text" name="password" value="">* <hr/>
    Имя: <input type="text" name="name" value="">* <hr/>
    Фамилия: <input type="text" name="lastname" value="">* <hr/>
    Почта: <input type="text" name="mail" value="">* <hr/>
    Телефон: <input type="text" name="phone" value=""> <hr/>
    Дата рождения: <input type="text" name="date_birth" value=""> <hr/>
    Пол: <input type="text" name="sex" value=""> <hr/>
    Номер карточки: <input type="text" name="number_card" value=""> <hr/>
    <input type="submit" name="registration" value="Зарегестрировать">
</form>
${msg_failed}
</body>
</html>
