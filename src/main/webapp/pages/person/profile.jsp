<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<fmt:setBundle basename="com.example.fitnessclub.model.entity.User" var="user"/>
<div style="display: none">${user=temp_attribute.get("user")}</div>
<c:set var="relevantUser">${user.getLogin()==sessionScope.get('login')}</c:set>
<c:set var="adminSwitch">${sessionScope.get('admin_switch')==true}</c:set>
<!DOCTYPE>
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
            <div class="form-row" style="padding: 1%;">
                <h1 style="text-align: center; padding-top: 1%">
                    <c:if test="${relevantUser}">
                        <fmt:message key="message.hello"/>,
                    </c:if>
                    ${user.getLastname()}
                    ${user.getName()}</h1>
                <c:choose>
                    <c:when test="${user.getRole()=='TRAINER'}">
                        <img style="border: 2px solid #F3D201FF;padding: 1px;"
                             src="${queryImage}=${user.getPathAvatar()}"
                             alt="">
                    </c:when>
                    <c:otherwise>
                        <img src="${queryImage}=${user.getPathAvatar()}" alt="pictures/not_avatar.png">
                    </c:otherwise>
                </c:choose>
                    <table style="text-align: left; margin-top:3%; margin:auto; padding-left: 3%">
                        <tr>
                            <th><h4><fmt:message key="field.user.role"/>:</h4></th>
                            <th><h5>${user.getRole()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.email"/>:</h4></th>
                            <th><h5>${user.getMail()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.date.birth"/>:</h4></th>
                            <th><h5>${user.getDateBirth()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.sex"/>:</h4></th>
                            <th><h5>${user.getSex()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.phone"/>:</h4></th>
                            <th><h5>${user.getPhone()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.corporate"/>:</h4></th>
                            <th><h5>${user.isCorporate()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.visit_period_months"/>:</h4></th>
                            <th><h5>${user.getVisitPeriodMonths()}</h5></th>
                        </tr>
                        <c:if test="${relevantUser}">
                            <tr>
                                <th><h4><fmt:message key="field.user.discount_code"/>:</h4></th>
                                <th><h5>${user.getDiscountCode()}</h5></th>
                            </tr>
                            <tr>
                                <th><h4><fmt:message key="field.user.number_cart"/>:</h4></th>
                                <th><h5>${user.getNumberCard()}</h5></th>
                            </tr>
                        </c:if>
                        <tr>
                            <th><h4><fmt:message key="field.user.about_me"/>:</h4></th>
                            <th><h5>${user.getAboutMe()}</h5></th>
                        </tr>
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
    <c:if test="${adminSwitch}">
        <c:import url="/pages/person/admin/part/userCharacteristics.jsp"/>
    </c:if>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
