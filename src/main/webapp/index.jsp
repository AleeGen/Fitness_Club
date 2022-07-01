<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
${pageContext.request.session.setAttribute("current_page","index.jsp")}
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.main"/></title>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<a href="pages/common/exerciseRoom.jsp">
    <div class="triangle-caption">
        <img src="pictures/exercise_room.jpg" alt=""/>
        <div class="caption">
            <p><fmt:message key="exercise_room"/></p>
        </div>
    </div>
</a>
<a href="pages/common/fitnessRoom.jsp">
    <div class="triangle-caption">
        <img src="pictures/fitness_room.jpg" alt=""/>
        <div class="caption">
            <p><fmt:message key="fitness_room"/></p>
        </div>
    </div>
</a>
<a href="pages/common/yoga.jsp">
    <div class="triangle-caption">
        <img src="pictures/yoga.jpg" alt=""/>
        <div class="caption">
            <p><fmt:message key="yoga"/></p>
        </div>
    </div>
</a>
<a href="pages/common/swimming.jsp">
    <div class="triangle-caption">
        <img src="pictures/swimming.jpg" alt=""/>
        <div class="caption">
            <p><fmt:message key="swimming"/></p>
        </div>
    </div>
</a>
<a href="pages/common/trainer.jsp">
    <div class="triangle-caption">
        <img src="pictures/trainer.jpg" alt=""/>
        <div class="caption">
            <p><fmt:message key="trainer"/></p>
        </div>
    </div>
</a>
<a href="pages/common/martialArts.jsp">
    <div class="triangle-caption">
        <img src="pictures/martial_arts.jpg" alt=""/>
        <div class="caption">
            <p><fmt:message key="martial_arts"/></p>
        </div>
    </div>
</a>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
