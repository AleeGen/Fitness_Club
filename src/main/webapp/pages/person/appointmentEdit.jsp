<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<fmt:setBundle basename="com.example.fitnessclub.model.entity.Workout" var="workout"/>
<div style="display: none">${workout=temp_attribute.get("workout")}</div>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="head.edit_appointments"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="posts-list">
        <output style="color: red">${temp_attribute.get("message")}</output>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <article class="post">
                <div class="accordion__content_body">
                    <input type="date" name="date" value="${workout.getAppointment().getDate()}"/>
                    <hr style="margin-bottom: 10px; margin-top: 5px">
                    <c:forEach var="service" items="${applicationScope.services}">
                        <input id="${service.getServiceName()}" type="radio" name="type_appointment"
                               value="${service.getServiceName()}" <c:if
                                test="${service.getServiceName()==workout.getAppointment().getType().name().toLowerCase()}">
                            checked</c:if> />
                        <label for="${service.getServiceName()}">${service.getServiceName()}</label>
                    </c:forEach>
                    <hr style="margin-bottom: 10px; margin-top: 5px">
                    <fmt:message key="field.appointment.nutrition"/>
                    <input type="text" name="nutrition"
                           value="${workout.getAppointment().getNutrition()}">
                    <div class="post-content">
                        <div class="decor">
                            <div class="form-row">
                                <a href="javascript://" onclick="addRow('table');"><fmt:message key="submit.add"/></a>
                            </div>
                        </div>
                    </div>
                </div>

                <table class="table" id="table">
                    <td><fmt:message key="field.exercise.name"/></td>
                    <td><fmt:message key="field.exercise.number_sets"/></td>
                    <td><fmt:message key="field.exercise.number_repetitions"/></td>
                    <td><fmt:message key="field.exercise.equipment"/></td>
                    <td><fmt:message key="field.exercise.description"/></td>
                    <c:forEach var="exercise" items="${workout.getExercises()}">
                        <tbody>
                        <tr style="height: 10px">
                            <input type="hidden" name="exercise_id" placeholder=" " value="${exercise.getId()}">
                            <td>
                                <input type="text" name="name" value="${exercise.getName()}">
                            </td>
                            <td><input type="text" name="number_sets"
                                       value="${exercise.getNumberSets()}">
                            </td>
                            <td><input type="text" name="number_repetitions"
                                       value="${exercise.getNumberRepetitions()}"></td>
                            <td><input type="text" name="equipment" value="${exercise.getEquipment()}">
                            </td>
                            <td><input type="text" name="description"
                                       value="${exercise.getDescription()}">
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </article>
            <div class="post-content">
                <div class="decor">
                    <div class="form-row">
                        <input type="hidden" name="command" value="edit_appointment">
                        <input type="hidden" name="appointment_id"
                               value="${workout.getAppointment().getId()}">
                        <input type="submit" value="<fmt:message key="submit.save_changes"/>">
                    </div>
                </div>
            </div>
        </form>
        <div class="post-content">
            <div class="decor">
                <div class="form-row">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="command" value="delete_appointment">
                        <input type="hidden" name="appointment_id"
                               value="${workout.getAppointment().getId()}">
                        <input type="submit" value="<fmt:message key="submit.delete"/>">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function addRow(id) {
        var tbody = document.getElementById(id).getElementsByTagName("tbody")[0];
        var row = document.createElement("tr")
        var hd = document.createElement("input")
        hd.setAttribute("type","hidden")
        hd.setAttribute("name","exercise_id")
        hd.setAttribute("value","")
        var td1 = document.createElement("td")
        var in1 = document.createElement("input")
        in1.setAttribute("name", "name")
        td1.appendChild(in1)
        var td2 = document.createElement("td")
        var in2 = document.createElement("input")
        in2.setAttribute("name", "number_sets")
        td2.appendChild(in2)
        var td3 = document.createElement("td")
        var in3 = document.createElement("input")
        in3.setAttribute("name", "number_repetitions")
        td3.appendChild(in3)
        var td4 = document.createElement("td")
        var in4 = document.createElement("input")
        in4.setAttribute("name", "equipment")
        td4.appendChild(in4)
        var td5 = document.createElement("td")
        var in5 = document.createElement("input")
        in5.setAttribute("name", "description")
        td5.appendChild(in5)
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(hd);
        tbody.appendChild(row);
    }
</script>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>