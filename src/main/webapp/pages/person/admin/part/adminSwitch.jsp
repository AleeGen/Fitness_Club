<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="admin_switch">
        <input type="submit" name="submit" value="<fmt:message key="submit.admin_switch"/>">
    </form>

<c:if test="${sessionScope.get('admin_switch')==true}">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="view_users">
        <input type="submit" name="submit" value="<fmt:message key="submit.list.users"/>">
    </form>
</c:if>
</body>
</html>
