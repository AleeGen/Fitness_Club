<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.services"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
    <script src="${pageContext.servletContext.contextPath}/script/sort.js"></script>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="posts-list">
        <output style="color: red">${temp_attribute.get("message")}</output>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="field.service.name"/>
                <th><fmt:message key="field.service.number_visit"/>
                <th><fmt:message key="field.service.validity_period"/>
                <th><fmt:message key="field.service.price"/>
                <th>
                    <fmt:message key="field.service.description"/>
                    <c:if test="${sessionScope.get('login')!=null}">
                <th>
                        <fmt:message key="submit.cart"/>
                    </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td><c:out value="${service.getServiceName()}"/></td>
                    <c:if test="${service.getNumberVisit()==0}">
                        <td><fmt:message key="month"/></td>
                    </c:if>
                    <c:if test="${service.getNumberVisit()!=0}">
                        <td><c:out value="${service.getNumberVisit()}"/></td>
                    </c:if>
                    <td><c:out value="${service.getValidityPeriod()}"/></td>
                    <td><c:out value="${service.getPrice()}"/></td>
                    <td><c:out value="${service.getDescription()}"/></td>
                    <c:if test="${sessionScope.get('login')!=null}">
                        <c:choose>
                            <c:when test="${service.getServiceName().equals('trainer')}">
                                <td>
                                    <form action="${pageContext.request.contextPath}/controller" method="get">
                                        <input type="hidden" name="command" value="view_trainers">
                                        <input style="margin-block: 0px" type="submit" name="submit"
                                               value="<fmt:message key="submit.choose"/>"/>
                                    </form>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form action="${pageContext.request.contextPath}/controller" method="post">
                                        <input type="hidden" name="command" value="add_to_cart">
                                        <input type="hidden" name="service_id_cart" value="${service.getId()}">
                                        <input style="margin-block: 0px" type="submit" name="submit"
                                               value="<fmt:message key="submit.add"/>"/>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<jsp:include page="/pages/footer/footer.jsp"/>
</html>
