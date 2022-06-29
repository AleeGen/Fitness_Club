<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="trainer"/></title>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <link rel="stylesheet" href="css/slideShow.css" type="text/css"/>
    <script src="${pageContext.servletContext.contextPath}/script/slideShow.js"></script>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="posts-list">
        <article id="post-1" class="post">
            <div class="slideshow-container">
                <div class="mySlides fade">
                    <img src="pictures/detail/trainer_1.jpg" style="width:100%">
                </div>
                <div class="mySlides fade">
                    <img src="pictures/detail/trainer_2.jpg" style="width:100%">
                </div>
                <div class="mySlides fade">
                    <img src="pictures/detail/trainer_3.jpg" style="width:100%">
                </div>
                <div class="mySlides fade">
                    <img src="pictures/detail/trainer_4.jpg" style="width:100%">
                </div>
                <a class="prev" onclick="plusSlides(-1)">❮</a>
                <a class="next" onclick="plusSlides(1)">❯</a>
            </div>
            <div style="text-align:center">
                <span class="dot" onclick="currentSlide(1)"></span>
                <span class="dot" onclick="currentSlide(2)"></span>
                <span class="dot" onclick="currentSlide(3)"></span>
                <span class="dot" onclick="currentSlide(4)"></span>
            </div>

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
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
<script>
    var slideIndex = 1;
    showSlides(slideIndex);
</script>
</body>
</html>
