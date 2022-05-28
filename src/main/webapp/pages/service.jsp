<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <title><fmt:message key="head.services"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>

<body>
<div class="decor" style="max-width: 900px">
    <div class="form-row">
        <fmt:message key="message"/>: ${message}
        <table class="table">
            <tr>
                <th><fmt:message key="field.service.name"/>
                </td>
                <th><fmt:message key="field.service.number_visit"/>
                </td>
                <th><fmt:message key="field.service.validity_period"/>
                </td>
                <th><fmt:message key="field.service.price"/>
                </td>
                <th><fmt:message key="field.service.description"/>
                </td>
            </tr>
            <c:forEach var="service" items="${temp.get('services')}">
                <tr>
                    <td><c:out value="${service.getServiceName()}"/></td>
                    <c:if test="${service.getNumberVisit()==0}">
                        <td><c:out value=""/></td>
                    </c:if>
                    <c:if test="${service.getNumberVisit()!=0}">
                        <td><c:out value="${service.getNumberVisit()}"/></td>
                    </c:if>
                    <td><c:out value="${service.getValidityPeriod()}"/></td>
                    <td><c:out value="${service.getPrice()}"/></td>
                    <td><c:out value="${service.getDescription()}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="add_to_cart">
                            <input type="hidden" name="service_cart" value="${service.getId()}">
                            <input style="margin-block: 0px" type="submit" name="submit"
                                   value="<fmt:message key="submit.add.cart"/>"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="table">
    <c:import url="insert/locale.jsp"/>
</div>
</body>
</html>
