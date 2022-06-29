<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="head.appointments"/></title>
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <script src="script/ItcAccordion.js"></script>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="post-list">
    <div id="accordion-appointments">
        <c:forEach var="workout" items="${temp_attribute.get('workouts')}">
            <div class="accordion__item_body">
                <div class="accordion__header" style="font-size: 20px;">
                    <c:out value="${workout.getAppointment().getDate()}"/>
                    <c:out value="${workout.getAppointment().getType()}"/>
                </div>
                <div class="accordion__body_body">
                    <div class="accordion__content_body">
                        <c:out value="${workout.getAppointment().getNutrition()}"/>
                    </div>
                    <table class="table">
                        <td><fmt:message key="field.exercise.name"/></td>
                        <td><fmt:message key="field.exercise.number_sets"/></td>
                        <td><fmt:message key="field.exercise.number_repetitions"/></td>
                        <td><fmt:message key="field.exercise.equipment"/></td>
                        <td><fmt:message key="field.exercise.description"/></td>
                        <c:forEach var="exercise" items="${workout.getExercises()}">
                            <tr>
                                <td><c:out value="${exercise.getName()}"/></td>
                                <td><c:out value="${exercise.getNumberSets()}"/></td>
                                <td><c:out value="${exercise.getNumberRepetitions()}"/></td>
                                <td><c:out value="${exercise.getEquipment()}"/></td>
                                <td><c:out value="${exercise.getDescription()}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
    <script>
        new ItcAccordion('#accordion-appointments');
    </script>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>