<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<!DOCTYPE>
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
    <c:forEach var="trainer" items="${trainers}">
    <div class="posts-list" style="padding-left: 20%">
        <article class="post">
            <div class="post-content">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="view_profile"/>
                    <input type="hidden" name="login" value="${trainer.getLogin()}"/>
                    <div class="triangle-caption_t">
                        <img src="${queryImage}=${trainer.getPathAvatar()}" alt="">
                        <div class="caption" style="padding: 0 10px 0 0; text-align: center;">
                            <p>
                                <input style="border: none; cursor: pointer; background: none; color: white"
                                       type="submit" name="login"
                                       value="${trainer.getLastname()} ${trainer.getName()}"/>
                            </p>
                        </div>
                    </div>

                </form>
            </div>
        </article>
    </div>
    </c:forEach>
    <jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
