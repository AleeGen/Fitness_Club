<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="<fmt:message key="submit.sign_out"/>"/>
</form>
</body>
</html>
