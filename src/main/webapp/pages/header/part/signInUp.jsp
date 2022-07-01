<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="prop.text"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="open_page">
        <input type="hidden" name="page" value="pages/common/logIn.jsp">
        <input type="submit" name="submit" value="<fmt:message key="submit.sign_in_up"/>">
</form>
</body>
</html>
