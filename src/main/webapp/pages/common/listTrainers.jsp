<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="list.trainer"/></title>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <link rel="stylesheet" href="css/slideShow.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <c:forEach var="trainer" items="trainers">
        <div class="posts-list">
            <article class="post">
                <div class="post-content">
                    <h2 class="post-title"><fmt:message key="trainer"/></h2>
                    <hr>
                    <p>
                        <jsp:include page="/text/trainer.htm"/>
                    </p>
                    <div class="decor">
                        <div class="form-row">
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="view_trainers">
                                <input type="submit" value="<fmt:message key="list.trainer"/>"/>
                            </form>
                        </div>
                    </div>
                    <div class="post-footer">
                        <c:choose>
                            <c:when test="${sessionScope.get('login')!=null}">
                                <a class="more-link"
                                   href="${pageContext.servletContext.contextPath}/pages/common/service.jsp"><fmt:message
                                        key="submit.buy"/></a>
                            </c:when>
                            <c:otherwise>
                                <a class="more-link"
                                   href="${pageContext.servletContext.contextPath}/pages/common/logIn.jsp"><fmt:message
                                        key="submit.buy"/></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </article>
        </div>
    </c:forEach>

</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
