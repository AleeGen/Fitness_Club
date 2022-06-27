<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="paid">${temp_attribute.get('paid')}</c:set>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.my_service"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <script src="${pageContext.servletContext.contextPath}/script/sort.js"></script>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    ${message}
    <table class="table">
        <thead>
        <tr>
            <th><fmt:message key="field.service.name"/></th>
            <th><fmt:message key="field.service.number_visit"/></th>
            <th><fmt:message key="field.service.price"/></th>
            <c:if test="${!paid}">
                <th><fmt:message key="purchase"/></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="payment" items="${temp_attribute.get('payments')}">
            <tr>
                <td>${services.get(payment.getServiceId()-1).getServiceName()}</td>
                <c:if test="${services.get(payment.getServiceId()-1).getNumberVisit()==0}">
                    <td><fmt:message key="month"/></td>
                </c:if>
                <c:if test="${services.get(payment.getServiceId()-1).getNumberVisit()!=0}">
                    <td>${services.get(payment.getServiceId()-1).getNumberVisit()}</td>
                </c:if>
                <td>${services.get(payment.getServiceId()-1).getPrice()}</td>
                <c:if test="${!paid}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="command" value="buy">
                            <input type="hidden" name="payment_id" value="${payment.getId()}">
                            <input type="submit" value="<fmt:message key="submit.buy"/>">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
