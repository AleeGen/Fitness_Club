<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<c:set var="sex">${temp_attribute.get('user').getSex().toString()}</c:set>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.profile_edit"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <div class="posts-list">
        <div class="decor">
            <output style="color: red">${message}</output>
            <div class="form-row">
                <img src="${queryImage}=${temp_attribute.get("user").getPathAvatar()}" alt="">
                <form action="${pageContext.request.contextPath}/controller" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="command" value="edit_avatar"/>
                    <input style="color: white;" type="file" name="avatar"/>
                    <input type="submit" value="<fmt:message key="submit.load"/>"/>
                    <output style="color: red">${param_user.get("message")}</output>
                </form>

                <form action="${pageContext.request.contextPath}/controller" method="post" novalidate>

                    <input type="password" name="password" placeholder=" "
                           style="background: ${param_user.get("password_color")}"/>
                    <label><fmt:message key="field.user.password"/></label>
                    <div style="color: red">${param_user.get("password")}</div>
                    <input type="password" name="replace_password" placeholder=" "
                           style="background: ${param_user.get("replace_password_color")}"
                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,25}$"/>
                    <label><fmt:message key="field.user.replace_password"/></label>
                    <div style="color: red">${param_user.get("replace_password")}</div>
                    <input type="password" name="repeat_password" placeholder=" "
                           style="background: ${param_user.get("repeat_password_color")}"
                           pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,25}$"/>
                    <label><fmt:message key="field.user.repeat_password"/></label>
                    <div style="color: red">${param_user.get("repeat_password")}</div>
                    <input type="hidden" name="command" value="edit_password">
                    <input type="submit" value="<fmt:message key="submit.edit_password"/>"/>
                </form>
                <form action="${pageContext.request.contextPath}/controller" method="post"
                      novalidate>
                    <div>
                        <input type="text" name="lastname" style="background: ${param_user.get("lastname_color")}"
                               value="${temp_attribute.get("user").getLastname()}"
                               pattern="^\p{L}{2,}$"/>
                        <label> <fmt:message key="field.user.lastname"/></label>
                        <input type="text" name="name" style="background: ${param_user.get("name_color")}"
                               value="${temp_attribute.get("user").getName()}"
                               pattern="^\p{L}{2,}$"/>
                        <label><fmt:message key="field.user.name"/></label>
                        <input type="email" name="mail" style="background: ${param_user.get("mail_color")}"
                               value="${temp_attribute.get("user").getMail()}"/>
                        <label> <fmt:message key="field.user.email"/></label>
                        <input type="text" name="phone" style="background: ${param_user.get("phone_color")}"
                               value="${temp_attribute.get("user").getPhone()}"
                               pattern="^(\+?[0-9]{5,15})+$"/>
                        <label> <fmt:message key="field.user.phone"/></label>
                        <input type="text" name="number_card" style="background: ${param_user.get("number_card_color")}"
                               value="${temp_attribute.get("user").getNumberCard()}"
                               pattern="^[0-9]{13,20}$"/>
                        <label><fmt:message key="field.user.number_cart"/></label>
                        <input type="date" name="date_birth" style="background: ${param_user.get("date_birth_color")}"
                               value="${temp_attribute.get("user").getDateBirth()}"/>
                        <label><fmt:message key="field.user.date.birth"/></label>

                        <input style="transform: translateY(-200%); margin-top: 5%;" id="male" type="radio" name="sex"
                               value="male" <c:if test="${sex.equals('male')}"> checked</c:if>>
                        <label for="male"><fmt:message key="field.user.sex.male"/></label>
                        <input style="transform: translateY(-200%);margin-top: 3%;" id="female" type="radio" name="sex"
                               value="female" <c:if test="${sex.equals('female')}">
                               checked</c:if>>
                        <label for="female"><fmt:message key="field.user.sex.female"/></label>
                        <input type="text" name="about_me" value="${temp_attribute.get("user").getAboutMe()}"/>
                        <label> <fmt:message key="field.user.about_me"/></label>
                    </div>
                    <div>
                        <input type="hidden" name="command" value="edit_user">
                        <input type="submit" value="<fmt:message key="submit.save_changes"/>"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>
