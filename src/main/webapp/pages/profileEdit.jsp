<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.profile_edit"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<div class="decor">
    <c:import url="/pages/support/header.jsp"/>
</div>
<div class="decor" style="max-width: 900px">
    ${message}
    <div class="form-row">
        <img src="${queryImage}=${temp_attribute.get("user").getPathAvatar()}" alt="">
        <form action="${pageContext.request.contextPath}/controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="edit_avatar"/>
            <input type="file" name="avatar"/>
            <input type="submit" value="<fmt:message key="submit.load"/>"/>
            <output style="color: red">${param_user.get("message")}</output>
        </form>
        </br>
        <form action="${pageContext.request.contextPath}/controller" method="post" novalidate>
            <div>
                <fmt:message key="field.user.password"/><input type="password" name="password"
                                                               style="background: ${param_user.get("password_color")}"/>
                </br>${param_user.get("password")}
                <fmt:message key="field.user.replace_password"/><input type="password" name="replace_password"
                                                                       style="background: ${param_user.get("replace_password_color")}"
                                                                       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,25}$"/>
                </br>${param_user.get("replace_password")}
                <fmt:message key="field.user.repeat_password"/><input type="password" name="repeat_password"
                                                                      style="background: ${param_user.get("repeat_password_color")}"
                                                                      pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,25}$"/>
                </br>${param_user.get("repeat_password")}
            </div>
            <div>
                <input type="hidden" name="command" value="edit_password">
                <input type="submit" value="<fmt:message key="submit.edit_password"/>"/>
            </div>
        </form>
        <form style="margin-right: 600px" action="${pageContext.request.contextPath}/controller" method="post"
              novalidate>
            <div>
                <fmt:message key="field.user.lastname"/><input type="text" name="lastname"
                                                               style="background: ${param_user.get("lastname_color")}"
                                                               value="${temp_attribute.get("user").getLastname()}"
                                                               pattern="^\p{L}{2,}$"/>
                <fmt:message key="field.user.name"/><input type="text" name="name"
                                                           style="background: ${param_user.get("name_color")}"
                                                           value="${temp_attribute.get("user").getName()}"
                                                           pattern="^\p{L}{2,}$"/>
                <fmt:message key="field.user.email"/><input type="email" name="mail"
                                                            style="background: ${param_user.get("mail_color")}"
                                                            value="${temp_attribute.get("user").getMail()}"/>
                <fmt:message key="field.user.phone"/><input type="text" name="phone"
                                                            style="background: ${param_user.get("phone_color")}"
                                                            value="${temp_attribute.get("user").getPhone()}"
                                                            pattern="^(\+?[0-9]{5,15})+$"/>
                <fmt:message key="field.user.number_cart"/><input type="text" name="number_card"
                                                                  style="background: ${param_user.get("number_card_color")}"
                                                                  value="${temp_attribute.get("user").getNumberCard()}"
                                                                  pattern="^[0-9]{13,20}$"/>
                <fmt:message key="field.user.date.birth"/><input type="date" name="date_birth"
                                                                 style="background: ${param_user.get("date_birth_color")}"
                                                                 value="${temp_attribute.get("user").getDateBirth()}"/>
                <fmt:message key="field.user.sex"/>
                <c:choose>
                    <c:when test="${temp_attribute.get('user').getSex().toString().equals('male')}">
                        <input type="radio" name="sex" value="male" checked>
                        <input type="radio" name="sex" value="female">
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="sex" value="male">
                        <input type="radio" name="sex" value="female" checked>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <input type="hidden" name="command" value="edit_user">
                <input type="submit" value="<fmt:message key="submit.save_changes"/>"/>
            </div>
        </form>

    </div>
</div>
</body>
</html>
