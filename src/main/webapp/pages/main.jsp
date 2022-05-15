<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<div class="decor" style="max-width: 900px">
    <div class="form-row">
        <h1 align="center">Приветствуем, ${all_info_user.getLastname()} ${all_info_user.getName()}!</h1>
        <div>
            <form style="margin-right: 600px" action="controller" method="get">
                <div>
                    <input type="hidden" name="command" value="check_service">
                    <input type="submit" name="submit" value="Услуги"/>
                </div>
            </form>
            <h5>Роль:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getRole()}</h4>
            <h5>Email:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getMail()}</h4>
            <h5>Дата рождения:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getDateBirth()}</h4>
            <h5>Пол:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getSex()}</h4>
            <h5>Телефон:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getPhone()}</h4>
            <h5>Корпоративный работник:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.isCorporate()}</h4>
            <h5>visitPeriodMonths:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getVisitPeriodMonths()}</h4>
            <h5>discountCode:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getDiscountCode()}</h4>
            <h5>numberCard:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${all_info_user.getNumberCard()}</h4>

            <form style="margin-right: 700px" action="controller" method="get">
                <div>
                    <input type="hidden" name="command" value="open_page">
                    <input type="submit" value="Изменить"/>
                </div>
            </form>
        </div>
        <form action="controller" method="get">
            <div>
                <input type="hidden" name="command" value="logout">
                <input type="submit" value="Выйти"/>
            </div>
        </form>
    </div>
</div>
<hr/>
<hr/>
<hr/>
param.get("login"): ${param.get("login")}
<hr/>
initParam.get("passAdmin"): ${initParam.get("passAdmin")}
<hr/>
requestScope: ${requestScope}
<hr/>
response: ${response}
<hr/>
pageContext.session.getAttribute(current_page): ${pageContext.session.getAttribute("current_page")}
<hr/>
pageContext.request.getAttribute(current_page): ${pageContext.request.getAttribute("current_page")}
<hr/>
</body>
</html>
