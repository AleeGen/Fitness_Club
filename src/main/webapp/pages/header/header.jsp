<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/style3.css" type="text/css">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
</head>
<body>
<header>
    <nav class="container">
        <a class="logo" href="">
            <span><fmt:message key="fitness_club"/></span>
        </a>
        <div id="locale">
            <jsp:include page="part/locale.jsp"/>
        </div>
        <c:if test="${sessionScope.get('role')=='ADMIN'}">
            <div id="admin">
                <jsp:include page="../person/admin/part/adminSwitch.jsp"/>
            </div>
        </c:if>
        <ul id="menu">
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="view_index">
                    <a><input type="submit" name="submit" value="<fmt:message key="head.main"/>"/></a>
                </form>
            </li>
            <li>
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="open_page">
                    <input type="hidden" name="page" value="pages/common/service.jsp">
                    <input type="submit" name="submit" value="<fmt:message key="head.services"/>"/>
                </form>
            </li>
            <c:choose>
                <c:when test="${sessionScope.get('login')==null}">
                    <li>
                        <jsp:include page="part/signInUp.jsp"/>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <jsp:include page="part/profileBlock.jsp"/>
                    </li>
                    <li>
                        <jsp:include page="part/signOut.jsp"/>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</header>
<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="open_page">
    <input type="hidden" name="page" value="pages/test.jsp">
    <input type="submit" name="submit" value="test"/>
</form>
</body>

</html>
