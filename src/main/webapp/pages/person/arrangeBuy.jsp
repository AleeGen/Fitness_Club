<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<fmt:setBundle basename="com.example.fitnessclub.model.entity.Payment" var="payment"/>
<fmt:setBundle basename="com.example.fitnessclub.model.entity.User" var="client"/>
<div style="display: none">${client=temp_attribute.get("client")}${payment=temp_attribute.get("payment")}</div>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.arrange_buy"/></title>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div style="margin:auto">
    <div style="margin:auto; width: 600px;">
        <div class="post-footer">
            <div class="form-container">
                <output style="color: red">${message}</output>
                <table class="table">
                    <tr>
                        <td><fmt:message key="field.user.role.client"/></td>
                        <td>${client.getLastname()} ${client.getName()}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="field.service.name"/></td>
                        <td>${services.get(payment.getServiceId()-1).getServiceName()}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="field.payment.expiry"/></td>
                        <td>${temp_attribute.get("expiry")}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="account_balance"/></td>
                        <td>${client.getCash()}</td>
                    </tr>
                    <tr>
                        <td><fmt:message key="cost_service"/></td>
                        <td>${temp_attribute.get("cost")}</td>
                    </tr>
                </table>
                <c:choose>
                    <c:when test="${client.getCash()>=temp_attribute.get('cost')}">
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="buy">
                            <div class="more-link" style="margin-top: 10px">
                                <input style="all: unset" type="submit"
                                       value="<fmt:message key="submit.buy"/>">
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="view_profile">
                            <input type="hidden" name="login" value="${temp_attribute.get('client').getLogin()}">
                            <div class="more-link_a" style="margin-top: 10px">
                                <input style="all: unset" type="submit"
                                       value="<fmt:message key="submit.up_account_balance"/>">
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="view_payment">
                    <div class="more-link_c" style="margin-top: 10px">
                        <input style="all: unset" type="submit"
                               value="<fmt:message key="submit.cancel"/>">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
