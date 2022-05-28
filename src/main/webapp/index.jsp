<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
${pageContext.request.session.setAttribute("current_page","index.jsp")}
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="head.index"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>
<body>
<div class="decor">
    <div class="form-row">
        <h1 align="center"><fmt:message key="fitness_club"/></h1>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <div>
                <input type="hidden" name="command" value="login"/>
                <input type="text" name="login" placeholder=" " value=""/><label><fmt:message
                    key="field.user.login"/></label>
                <input type="password" name="password" placeholder=" " value=""/><label><fmt:message
                    key="field.user.password"/></label>
                <output style="color: red">${message}</output>
                <input type="submit" name="input" value="<fmt:message key="submit.sing_in"/>"/>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <div>
                <input type="hidden" name="command" value="open_page">
                <input type="hidden" name="page" value="pages/registrationStepOne.jsp">
                <input type="submit" name="submit" value="<fmt:message key="submit.registration"/>"><br/>
            </div>
        </form>
    </div>
</div>
<div class="table">
    <c:import url="pages/insert/locale.jsp"/>
</div>
</body>
</html>