<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.registration.step_two"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="decor" style="max-width: 40%">
    <div class="form-row">
        <form action="${pageContext.request.contextPath}/controller" method="post" novalidate>
            <h1 align="center"><fmt:message key="head.registration"/></h1>
            <input type="hidden" name="command" value="registration_step_two">

            <input type="text" name="phone" placeholder=" " value="${temp_attribute.get("user").get("phone")}"
                   style="background: ${temp_attribute.get("user").get("phone_color")}"
                   pattern="^(\+?[0-9]{5,15})+$">
            <label><fmt:message key="field.user.phone"/></label>

            <input type="text" name="number_card" placeholder=" "
                   value="${temp_attribute.get("user").get("number_card")}"
                   style="background: ${temp_attribute.get("user").get("number_card_color")}"
                   pattern="^[0-9]{13,20}$">
            <label><fmt:message key="field.user.number_cart"/></label>

            <input type="date" name="date_birth" placeholder=" " value="${temp_attribute.get("user").get("date_birth")}"
                   style="background: ${temp_attribute.get("user").get("date_birth_color")}" pattern="^[0-9]{13,20}$">
            <label><fmt:message key="field.user.date.birth"/></label>

            <input style="margin-top: 3%;" type="radio" name="sex" id="id_male" value="male">
            <label for="id_male" style="margin-top: 4%"><fmt:message key="field.user.sex.male"/></label>

            <input style="margin-top: 3%;" type="radio" name="sex" id="id_female" value="female">
            <label for="id_female" style="margin-top: 4%"><fmt:message key="field.user.sex.female"/></label>

            <input type="reset" value="<fmt:message key="submit.reset"/>"/>
            <input type="submit" name="registration" value="<fmt:message key="submit.sign_up"/>">
        </form>
    </div>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>