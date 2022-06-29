<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<aside style="width: 20%">
    <div class="widget">
        <h3 class="widget-title"><fmt:message key="head.services"/></h3>
        <ul class="widget-posts-list">
            <li>
                <div class="post-image-small">
                    <a href="${pageContext.servletContext.contextPath}/pages/common/exerciseRoom.jsp">
                        <img src="pictures/exercise_room.jpg" alt=""/></a>
                </div>
                <h4 class="widget-post-title"><fmt:message key="exercise_room"/></h4>
            </li>
            <li>
                <div class="post-image-small">
                    <a href="${pageContext.servletContext.contextPath}/pages/common/fitnessRoom.jsp"><img
                            src="pictures/fitness_room.jpg" alt=""/></a>
                </div>
                <h4 class="widget-post-title"><fmt:message key="fitness_room"/></h4>
            </li>
            <li>
                <div class="post-image-small">
                    <a href="${pageContext.servletContext.contextPath}/pages/common/yoga.jsp"><img
                            src="pictures/yoga.jpg" alt=""/></a>
                </div>
                <h4 class="widget-post-title"><fmt:message key="yoga"/></h4>
            </li>
            <li>
                <div class="post-image-small">
                    <a href="${pageContext.servletContext.contextPath}/pages/common/swimming.jsp"><img
                            src="pictures/swimming.jpg" alt=""/></a>
                </div>
                <h4 class="widget-post-title"><fmt:message key="swimming"/></h4>
            </li>
            <li>
                <div class="post-image-small">
                    <a href="${pageContext.servletContext.contextPath}/pages/common/trainer.jsp"><img
                            src="pictures/trainer.jpg" alt=""/></a>
                </div>
                <h4 class="widget-post-title"><fmt:message key="trainer"/></h4>
            </li>
            <li>
                <div class="post-image-small">
                    <a href="${pageContext.servletContext.contextPath}/pages/common/martialArts.jsp"><img
                            src="pictures/martial_arts.jpg" alt=""/></a>
                </div>
                <h4 class="widget-post-title"><fmt:message key="martial_arts"/></h4>
            </li>
        </ul>
    </div>
</aside>
</body>
</html>
