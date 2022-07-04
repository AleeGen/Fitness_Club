<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
    <script src="script/ItcAccordion.js"></script>
</head>
<body>
<div id="accordion-profile">
    <div class="accordion__item">
        <div class="accordion__header">
            <a><fmt:message key="head.profile"/></a>
        </div>
        <div class="accordion__body">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="view_profile"/>
                <input type="hidden" name="login" value="${sessionScope.get('login')}"/>
                <input type="submit" name="input" value="<fmt:message key="submit.personal_page"/>"/>
            </form>
        </div>
        <div class="accordion__body">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="view_workout">
                <input type="hidden" name="login" value="${sessionScope.login}">
                <input type="submit" value="<fmt:message key="head.appointments"/>"/>
            </form>
        </div>
    </div>
    <div class="accordion__item">
        <div class="accordion__header">
            <a style="white-space: nowrap"><fmt:message key="head.my_service"/></a>
        </div>
        <div class="accordion__body">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="view_payment">
                <input type="hidden" name="payment_status" value="true">
                <input type="submit" name="submit" value="<fmt:message key="submit.current"/>"/>
            </form>
        </div>
        <div class="accordion__body">
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="view_payment">
                <input type="hidden" name="payment_status" value="false">
                <input type="submit" name="submit" value="<fmt:message key="submit.cart"/>"/>
            </form>
        </div>
        <c:choose>
            <c:when test="${sessionScope.get('role')=='TRAINER'}">
                <div class="accordion__body">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="view_clients">
                        <input type="submit" name="submit" value="<fmt:message key="submit.clients"/>"/>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="accordion__body">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="view_personal_trainer">
                        <input type="submit" name="submit" value="<fmt:message key="submit.personal_trainer"/>"/>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script>
    new ItcAccordion('#accordion-profile');
</script>
</body>
</html>
