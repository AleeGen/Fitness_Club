<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.services"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>

<body>
<div class="decor">
    <c:import url="/pages/support/header.jsp"/>
</div>
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
            <c:forEach var="service" items="${services}">
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
                    <c:if test="${sessionScope.get('login')!=null}">
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="add_to_cart">
                                <input type="hidden" name="service_id_cart" value="${service.getId()}">
                                <input style="margin-block: 0px" type="submit" name="submit"
                                       value="<fmt:message key="submit.add.cart"/>"/>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
