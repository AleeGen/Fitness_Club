<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
${pageContext.request.session.setAttribute("current_page","index.jsp")}
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.main"/></title>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
${services}
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
