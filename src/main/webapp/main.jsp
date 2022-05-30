<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
${pageContext.request.session.setAttribute("current_page","main.jsp")}
<html>
<head>
    <title><fmt:message key="head.main"/></title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>

<body>
<form action="${pageContext.request.contextPath}/controller">
    <div>
        <input type="hidden" name="command" value="open_page">
        <input type="hidden" name="page" value="pages/logIn.jsp">
        <input type="submit" name="submit" value="<fmt:message key="submit.sign_in_up"/>"><br/>
    </div>
</form>

<div class="table">
    <c:import url="/pages/insert/locale.jsp"/>
</div>
</form>
</body>
</html>
