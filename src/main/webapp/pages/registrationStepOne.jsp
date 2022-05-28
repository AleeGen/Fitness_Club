<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <title><fmt:message key="head.registration.step_one"/></title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>
<form class="decor" action="${pageContext.request.contextPath}/controller" method="get" novalidate>
    <div class="form-row">
        <h1 align="center"><fmt:message key="head.registration"/></h1>
        <input type="hidden" name="command" value="registration_step_one">

        <input type="text" placeholder=" " name="login" value="${temp.get("user").get("login")}"
               style="background: ${temp.get("user").get("login_color")}"
               pattern="^[a-zA-Z0-9._-]{3,15}$" required>
        <label><fmt:message key="field.user.login"/></label>

        <input type="password" name="password" placeholder=" " value="${temp.get("user").get("password")}"
               style="background: ${temp.get("user").get("password_color")}"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,25}$" required>
        <label><fmt:message key="field.user.password"/></label>

        <input type="text" name="name" placeholder=" " value="${temp.get("user").get("name")}"
               style="background: ${temp.get("user").get("name_color")}" pattern="^\p{L}{2,}$" required>
        <label><fmt:message key="field.user.name"/></label>

        <input type="text" name="lastname" placeholder=" " value="${temp.get("user").get("lastname")}"
               style="background: ${temp.get("user").get("lastname_color")}" pattern="^\p{L}{2,}$" required>
        <label><fmt:message key="field.user.lastname"/></label>

        <input type="email" name="mail" placeholder=" " value="${temp.get("user").get("mail")}"
               style="background: ${temp.get("user").get("mail_color")}" required>
        <label><fmt:message key="field.user.email"/></label>

        <input type="reset" value="<fmt:message key="submit.reset"/>"/>
        <input type="submit" name="registration" value="<fmt:message key="submit.next"/>">
    </div>
</form>
<div class="table">
    <c:import url="insert/locale.jsp"/>
</div>
</body>
</html>