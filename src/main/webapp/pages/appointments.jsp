<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.appointments"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<div class="decor">
    <c:import url="/pages/support/header.jsp"/>
</div>
<div class="decor" style="max-width: 900px">
    <div class="form-row">
        <table class="table">
            <tr>
                <th><fmt:message key="field.appointment.date"/>
                </td>
                <th><fmt:message key="field.appointment.type"/>
                </td>
                <th><fmt:message key="field.appointment.description"/>
                </td>
            </tr>
            <c:forEach var="appointment" items="${temp_attribute.get('appointments')}">
                <tr>
                    <td><c:out value="${appointment.getDate()}"/></td>
                    <td><c:out value="${appointment.getType()}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="get">
                            <input type="hidden" name="command" value="view_appointment">
                            <input type="hidden" name="appointment_id" value="${appointment.getId()}">
                            <input style="margin-block: 0px" type="submit" name="submit"
                                   value="<fmt:message key="submit.more_details"/>"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
