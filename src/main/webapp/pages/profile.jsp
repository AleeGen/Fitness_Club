<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.profile"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<div class="decor">
    <c:import url="/pages/support/header.jsp"/>
</div>
<div>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="view_appointments">
        <input type="submit" value="<fmt:message key="head.appointments"/>"/>
    </form>
</div>
<div class="decor" style="max-width: 900px">
    <div class="form-row">
        <h1 align="center"><fmt:message key="message.hello"/>, ${temp_attribute.get("user").getLastname()} ${temp_attribute.get("user").getName()}!</h1>
        <img src="${queryImage}=${temp_attribute.get("user").getPathAvatar()}" alt="">
        <div>
            <h5><fmt:message key="field.user.role"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getRole()}</h4>
            <h5><fmt:message key="field.user.email"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getMail()}</h4>
            <h5><fmt:message key="field.user.date.birth"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getDateBirth()}</h4>
            <h5><fmt:message key="field.user.sex"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getSex()}</h4>
            <h5><fmt:message key="field.user.phone"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getPhone()}</h4>
            <h5><fmt:message key="field.user.corporate"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").isCorporate()}</h4>
            <h5><fmt:message key="field.user.visit_period_months"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getVisitPeriodMonths()}</h4>
            <h5><fmt:message key="field.user.discount_code"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getDiscountCode()}</h4>
            <h5><fmt:message key="field.user.number_cart"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp_attribute.get("user").getNumberCard()}</h4>
            <div>
            <form style="margin-right: 700px" action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="open_page">
                    <input type="hidden" name="page" value="pages/profileEdit.jsp">
                    <input type="submit" value="<fmt:message key="submit.edit"/>"/>
            </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
