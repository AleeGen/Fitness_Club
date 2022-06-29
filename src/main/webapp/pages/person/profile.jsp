<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<c:set var="relevantUser">${temp_attribute.get('user').getLogin()==sessionScope.get('login')}</c:set>
<c:set var="adminSwitch">${sessionScope.get('admin_switch')==true}</c:set>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.profile"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="posts-list">
        <div class="decor">
            <div class="form-row">
                <h1 align="center">
                    <c:if test="${relevantUser}">
                        <fmt:message key="message.hello"/>,
                    </c:if>
                    ${temp_attribute.get("user").getLastname()}
                    ${temp_attribute.get("user").getName()}</h1>
                <img src="${queryImage}=${temp_attribute.get("user").getPathAvatar()}" alt="">
                <div>
                    <table style="text-align: left; margin:auto; margin-top:3%">
                        <tr>
                            <th><h4><fmt:message key="field.user.role"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getRole()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.email"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getMail()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.date.birth"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getDateBirth()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.sex"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getSex()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.phone"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getPhone()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.corporate"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").isCorporate()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.visit_period_months"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getVisitPeriodMonths()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.about_me"/>:</h4></th>
                            <th><h5>${temp_attribute.get("user").getAboutMe()}</h5></th>
                        </tr>
                        <c:if test="${relevantUser}">
                            <tr>
                                <th><h4><fmt:message key="field.user.discount_code"/>:</h4></th>
                                <th><h5>${temp_attribute.get("user").getDiscountCode()}</h5></th>
                            </tr>
                            <tr>
                                <th><h4><fmt:message key="field.user.number_cart"/>:</h4></th>
                                <th><h5>${temp_attribute.get("user").getNumberCard()}</h5></th>
                            </tr>
                        </c:if>
                    </table>
                    <c:if test="${relevantUser}">
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="view_edit_profile">
                            <input type="submit" value="<fmt:message key="submit.edit"/>"/>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${adminSwitch}">
        <c:import url="/pages/person/admin/part/userCharacteristics.jsp"/>
    </c:if>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
