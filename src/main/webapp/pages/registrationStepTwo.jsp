<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <title><fmt:message key="head.registration.step_two"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>
<form class="decor" action="${pageContext.request.contextPath}/controller" method="get" novalidate>
    <div class="form-row">
        <h1 align="center"><fmt:message key="head.registration"/></h1>
        <input type="hidden" name="command" value="registration_step_two">

        <input type="text" name="phone" placeholder=" " value="${temp.get("user").get("phone")}"
               style="background: ${temp.get("user").get("phone_color")}"
               pattern="^(\+?[0-9]{5,15})+$">
        <label><fmt:message key="field.user.phone"/></label>

        <input type="text" name="number_card" placeholder=" " value="${temp.get("user").get("number_card")}"
               style="background: ${temp.get("user").get("number_card_color")}"
               pattern="^[0-9]{13,20}$">
        <label><fmt:message key="field.user.number_cart"/></label>

        <input type="date" style="margin-left: 156px; width: 48%;
        line-height: 40px;" name="date_birth" placeholder=" " value="${temp.get("user").get("date_birth")}"/>
        <h4 style="margin-left: 10px; margin-top: -40px;"><fmt:message key="field.user.date.birth"/></h4>
        </br>
        </br>
        <h4 style="margin-left: 10px;"><fmt:message key="field.user.sex"/></h4>
        <input class="custom-radio" type="radio" name="sex" id="id_male" value="male">
        <label for="id_male"
               style="margin-left: 60px; color: #343636; font-family: 'Roboto', sans-serif;"><fmt:message key="field.user.sex.male"/></label>
        </br>
        </br>
        <input class="custom-radio" type="radio" name="sex" id="id_female" value="female">
        <label for="id_female"
               style="margin-left: 60px; color: #343636; font-family: 'Roboto', sans-serif;"><fmt:message key="field.user.sex.female"/></label>

        <input type="reset" value="<fmt:message key="submit.reset"/>"/>
        <input type="submit" name="registration" value="<fmt:message key="submit.sign_up"/>">
    </div>
</form>
<div class="table">
    <c:import url="insert/locale.jsp"/>
</div>
</body>
</html>