<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация_2</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>


<form class="decor" action="controller" method="get" novalidate>
    <div class="form-row">
        <h1 align="center">Регистрация</h1>
        <input type="hidden" name="command" value="registration_step_two">

        <input type="text" name="phone" placeholder=" " value="${attr_user.get("phone")}"
               style="background: ${attr_user.get("phone_color")}"
               pattern="^(\+?[0-9]{5,15})+$">
        <label>Телефон</label>


        <input type="text" name="number_card" placeholder=" " value="${attr_user.get("number_card")}"
               style="background: ${attr_user.get("number_card_color")}"
               pattern="^[0-9]{13,20}$">
        <label>Номер карточки</label>

        <input type="date" style="margin-left: 156px; width: 48%;
        line-height: 40px;" name="date_birth" placeholder=" " value="${attr_user.get("date_birth")}"/>
        <h4 style="margin-left: 10px; margin-top: -40px;">Дата рождения</h4>
        </br>
        </br>
        <h4 style="margin-left: 10px;">Пол</h4>
        <input class="custom-radio" type="radio" name="sex" id="id_male" value="male">
        <label for="id_male"
               style="margin-left: 60px; color: #343636; font-family: 'Roboto', sans-serif;">Мужской</label>
        </br>
        </br>
        <input class="custom-radio" type="radio" name="sex" id="id_female" value="female">
        <label for="id_female"
               style="margin-left: 60px; color: #343636; font-family: 'Roboto', sans-serif;">Женский</label>


        <input type="reset"/><input type="submit" name="registration" value="Зарегестрироваться">
    </div>
</form>

</body>
</html>