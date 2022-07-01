<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="head.logIn"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <div class="decor" style="max-width: 40%">
    <div class="form-row">
        <h1 align="center"><fmt:message key="head.logIn"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="text" name="login" placeholder=" " value=""/><label><fmt:message
                key="field.user.login"/></label>
            <input type="password" name="password" placeholder=" " value=""/><label><fmt:message
                key="field.user.password"/></label>
            <output style="color: red">${message}</output>
            <input type="submit" name="input" value="<fmt:message key="submit.sign_in"/>"/>
        </form>
    </div>
    <div class="form-row">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="open_page">
            <input type="hidden" name="page" value="pages/common/registrationStepOne.jsp">
            <input type="submit" name="submit" value="<fmt:message key="submit.registration"/>"><br/>
        </form>
    </div>
</div>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
</body>
</html>