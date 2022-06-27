<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="prop.text"/>
<c:set var="role" value="${temp_attribute.get('user').getRole()}"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<div class="container" style="margin-top: -39%">
    ${param_user.get("message")}
    <aside>
        <div class="decor" style="max-width: 100%;">
            <div class="form-row">
                <form action="${pageContext.request.contextPath}/controller" method="post" novalidate>
                    <input type="hidden" name="command" value="edit_user_features">
                    <h2 style="margin: 20%"><fmt:message key="field.user.role"/></h2>
                    <input type="radio" name="role" id="id_admin" value="admin"
                           <c:if test="${role=='ADMIN'}">checked</c:if>>
                    <label for="id_admin"><fmt:message key="field.user.role.admin"/></label><br>
                    <input type="radio" name="role" id="id_trainer" value="trainer"
                           <c:if test="${role=='TRAINER'}">checked</c:if>>
                    <label for="id_trainer"><fmt:message key="field.user.role.trainer"/></label><br>
                    <input type="radio" name="role" id="id_client" value="client"
                           <c:if test="${role=='CLIENT'}">checked</c:if>>
                    <label for="id_client"><fmt:message key="field.user.role.client"/></label><br>
                    <h2 style="margin: 20%"><fmt:message key="field.user.corporate"/></h2>
                    <input type="radio" name="corporate" id="id_true" value="true"
                           <c:if test="${temp_attribute.get('user').isCorporate()}">checked</c:if>>
                    <label for="id_true"><fmt:message key="yes"/></label><br>
                    <input type="radio" name="corporate" id="id_false" value="false"
                           <c:if test="${!temp_attribute.get('user').isCorporate()}">checked</c:if>>
                    <label for="id_false"><fmt:message key="no"/></label><br>
                    <h2 style="margin: 20%"><fmt:message key="field.user.discount_code"/>:</h2>
                    ${param_user.get("discount_code")}
                    <input type="text" name="discount_code" value="${temp_attribute.get("user").getDiscountCode()}"
                           style="background: ${param_user.get("discount_code_color")}"
                           pattern="^[a-zA-Z0-9._-]{3,15}$" required>
                    <input type="submit" value="<fmt:message key="submit.save_changes"/>"/>
                </form>
            </div>
        </div>
    </aside>
</div>
</body>
</html>
