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
    <title>JavaScript Accordion - Example 1</title>
    <link rel="stylesheet" href="css/accordion.css" type="text/css"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <script src="script/ItcAccordion.js">
    </script>
</head>

<body>
<div class="decor">
    <c:import url="/pages/support/header.jsp"/>
</div>
<div class="accordion" id="accordion-1" style="max-width: 30rem; margin: 1rem auto 2rem;">
    <c:forEach var="appointment" items="${temp_attribute.get('appointments')}">
        <div class="accordion__item">
            <div class="accordion__header">
                <c:out value="${appointment.getDate()}"/>: <c:out value="${appointment.getType()}"/>
            </div>
            <div class="accordion__body">
                <table class="table">
                    <tr>
                        <td><c:out value="${appointment.getExerciseName()}"/></td>
                        <td><c:out value="${appointment.getNumberSets()}"/></td>
                        <td><c:out value="${appointment.getNumberRepetitions()}"/></td>
                        <td><c:out value="${appointment.getEquipment()}"/></td>
                        <td><c:out value="${appointment.getRunTime()}"/></td>
                    </tr>
                    <tr>
                        <td>6<c:out value="${appointment.getNutrition()}"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </c:forEach>
    <div class="accordion__item">
        <div class="accordion__header">
            1
        </div>
        <div class="accordion__body">
            2
        </div>
    </div>
    <div class="accordion__item">
        <div class="accordion__header">
            1
        </div>
        <div class="accordion__body">
            2
        </div>
    </div>
    <div class="accordion__item">
        <div class="accordion__header">
            1
        </div>
        <div class="accordion__body">
            2
        </div>
    </div>
</div>
<script>
    new ItcAccordion('#accordion-1');
</script>
</body>

</html>

