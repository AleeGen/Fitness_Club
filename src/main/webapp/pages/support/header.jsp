<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
${sessionScope.get("login")}

<div class="table">
    <c:choose>
        <c:when test="${sessionScope.get('login')==null}">
            <jsp:include page="signInUp.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="signOut.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
<div class="table">
    <jsp:include page="locale.jsp"/>
</div>
<div class="form-row">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="view_index">
        <input type="submit" name="submit" value="<fmt:message key="head.main"/>"/>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="open_page">
        <input type="hidden" name="page" value="pages/service.jsp">
        <input type="submit" name="submit" value="<fmt:message key="head.services"/>"/>
    </form>
    <c:if test="${sessionScope.get('login')!=null}">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <div>
                <input type="hidden" name="command" value="view_profile"/>
                <input type="submit" name="input" value="<fmt:message key="head.profile"/>"/>
            </div>
        </form>
    </c:if>
</div>

</body>
</html>
