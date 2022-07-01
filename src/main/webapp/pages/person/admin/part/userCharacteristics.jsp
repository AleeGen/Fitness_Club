<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="prop.text"/>
<c:set var="role" value="${temp_attribute.get('user').getRole()}"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<div class="posts-list">
    ${param_user.get("message")}
    <div class="decor">
        <div class="form-row">
            <form action="${pageContext.request.contextPath}/controller" method="post" novalidate>
                <input type="hidden" name="command" value="edit_user_features">
                <h2 style="margin: 2%; margin-bottom: 5%; margin-top:1%"><fmt:message key="field.user.role"/></h2>
                <input style="transform: translateY(-200%);" type="radio" name="role" id="id_admin" value="admin"
                       <c:if test="${role=='ADMIN'}">checked</c:if>>
                <label for="id_admin"><fmt:message key="field.user.role.admin"/></label><br>
                <input style="transform: translateY(-200%);" type="radio" name="role" id="id_trainer" value="trainer"
                       <c:if test="${role=='TRAINER'}">checked</c:if>>
                <label for="id_trainer"><fmt:message key="field.user.role.trainer"/></label><br>
                <input style="transform: translateY(-200%);" type="radio" name="role" id="id_client" value="client"
                       <c:if test="${role=='CLIENT'}">checked</c:if>>
                <label for="id_client"><fmt:message key="field.user.role.client"/></label><br>
                <h2 style="margin: 2%; margin-bottom: 5%"><fmt:message key="field.user.corporate"/></h2>
                <input style="transform: translateY(-200%);" type="radio" name="corporate" id="id_true" value="true"
                       <c:if test="${temp_attribute.get('user').isCorporate()}">checked</c:if>>
                <label for="id_true"><fmt:message key="yes"/></label><br>
                <input style="transform: translateY(-200%);" type="radio" name="corporate" id="id_false" value="false"
                       <c:if test="${!temp_attribute.get('user').isCorporate()}">checked</c:if>>
                <label for="id_false"><fmt:message key="no"/></label><br>
                <h2 style="margin: 2%;"><fmt:message key="field.user.discount_code"/>:</h2>
                ${param_user.get("discount_code")}
                <input style="margin-top: 1%" type="text" name="discount_code"
                       value="${temp_attribute.get("user").getDiscountCode()}"
                       style="margin-top:-10%; padding-left: 10%; background: ${param_user.get("discount_code_color")}"
                       pattern="^[a-zA-Z0-9._-]{3,15}$" required>
                <input type="submit" value="<fmt:message key="submit.save_changes"/>"/>
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="blocked_user">
                <input type="hidden" name="login" value="${temp_attribute.get('user').getLogin()}">
                <c:choose>
                    <c:when test="${temp_attribute.get('user').isBlocked()}">
                        <input type="hidden" name="is_blocked" value="false">
                        <input type="submit" value="<fmt:message key="submit.unlock"/>"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="is_blocked" value="true">
                        <input type="submit" value="<fmt:message key="submit.block"/>"/>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</div>
</body>
</html>
