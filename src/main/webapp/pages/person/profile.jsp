<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<fmt:setBundle basename="com.example.fitnessclub.model.entity.User" var="client"/>
<div style="display: none">${client=temp_attribute.get("user")}</div>
<c:set var="relevantUser">${client.getLogin()==sessionScope.get('login')}</c:set>
<c:set var="adminSwitch">${sessionScope.get('admin_switch')==true}</c:set>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.profile"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <script src="${pageContext.servletContext.contextPath}/script/openCloseForm.js"></script>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div style="color:red">${temp_attribute.get("message")}</div>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="posts-list">
        <div class="decor">
            <div class="form-row" style="padding: 1%;">
                <h1 style="text-align: center; padding-top: 1%">
                    <c:if test="${relevantUser}">
                        <fmt:message key="message.hello"/>,
                    </c:if>
                    ${client.getLastname()}
                    ${client.getName()}</h1>
                <c:choose>
                    <c:when test="${client.getRole()=='TRAINER'}">
                        <img style="border: 2px solid #F3D201FF;padding: 1px;"
                             src="${queryImage}=${client.getPathAvatar()}"
                             alt="">
                    </c:when>
                    <c:otherwise>
                        <img src="${queryImage}=${client.getPathAvatar()}" alt="pictures/not_avatar.png">
                    </c:otherwise>
                </c:choose>
                <div class="post-footer">
                    <table style="text-align: left; margin-top:3%; margin:auto; padding-left: 3%">
                        <tr>
                            <th><h4><fmt:message key="field.user.role"/>:</h4></th>
                            <th><h5>${client.getRole()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.email"/>:</h4></th>
                            <th><h5>${client.getMail()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.date.birth"/>:</h4></th>
                            <th><h5>${client.getDateBirth()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.sex"/>:</h4></th>
                            <th><h5>${client.getSex()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.phone"/>:</h4></th>
                            <th><h5>${client.getPhone()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.corporate"/>:</h4></th>
                            <th><h5>${client.isCorporate()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.visit_period_months"/>:</h4></th>
                            <th><h5>${client.getVisitPeriodMonths()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4><fmt:message key="field.user.about_me"/>:</h4></th>
                            <th><h5>${client.getAboutMe()}</h5></th>
                        </tr>
                    </table>
                </div>
                <c:if test="${relevantUser}">
                    <table style="text-align: left; margin-top:3%; margin:auto; padding-left: 3%">
                        <tr>
                            <th><h4><fmt:message key="field.user.number_cart"/>:</h4></th>
                            <th><h5>${client.getNumberCard()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4 style="color: #47caff;  font-weight: 1000;"><fmt:message
                                    key="field.user.discount"/>:</h4></th>
                            <th><h5 style="color: #47caff;  font-weight: 1000;">${client.getDiscount()}</h5></th>
                        </tr>
                        <tr>
                            <th><h4 style="color: #f3d201;  font-weight: 1000;"><fmt:message
                                    key="account_balance"/>:</h4>
                            </th>
                            <th><h5 style="color: #f3d201;  font-weight: 1000;">${client.getCash()}</h5></th>
                        </tr>
                    </table>
                    <hr>
                    <div class="more-link" style="margin-top: 10px">
                        <button type="button" style="all: unset;"
                                onclick="openForm('form')">
                            <fmt:message key="submit.up_account_balance"/>
                        </button>
                    </div>
                    <div class="form-popup" id="form">
                        <div class="form-container">
                            <form>
                                <input type="hidden" name="command" value="up_account_balance">
                                <div class="form-row">
                                    <input type="number" name="cash" min="0">
                                    <label style="color:white"><fmt:message
                                            key="on"/></label>
                                </div>
                                <div class="more-link_g" style="margin-top: 10px">
                                    <input style="all: unset" type="submit"
                                           value="<fmt:message key="submit.top_up"/>">
                                </div>
                                <div class="more-link_c" style="margin-top: 10px">
                                    <button type="button" style="all: unset;"
                                            onclick="closeForm('form')">
                                        <fmt:message key="submit.cancel"/>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
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
