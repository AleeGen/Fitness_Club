<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.list.users"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <script src="${pageContext.servletContext.contextPath}/script/sort.js"></script>
</head>
<body>
<c:import url="../../header/header.jsp"/>
<div class="container">
<table class="table">
    <thead>
    <tr>
        <th><fmt:message key="field.user.role"/></th>
        <th><fmt:message key="field.user.login"/></th>
        <th><fmt:message key="field.user.lastname"/></th>
        <th><fmt:message key="field.user.name"/></th>
        <th><fmt:message key="field.user.email"/></th>
        <th><fmt:message key="field.user.phone"/></th>
        <th><fmt:message key="field.user.date.birth"/></th>
        <th><fmt:message key="field.user.sex"/></th>
        <th><fmt:message key="field.user.corporate"/></th>
        <th><fmt:message key="field.user.visit_period_months"/></th>
        <th><fmt:message key="field.user.is_blocked"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${temp_attribute.get('users')}">
        <tr>
            <td>${user.getRole()}</td>
            <td>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="view_profile"/>
                    <input class="glow-button" type="submit" name="login" value="${user.getLogin()}"/>
                </form>
            </td>
            <td>${user.getLastname()}</td>
            <td>${user.getName()}</td>
            <td>${user.getMail()}</td>
            <td>${user.getPhone()}</td>
            <td>${user.getDateBirth()}</td>
            <td>${user.getSex()}</td>
            <td>${user.isCorporate()}</td>
            <td>${user.getVisitPeriodMonths()}</td>
            <td>${user.isBlocked()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
