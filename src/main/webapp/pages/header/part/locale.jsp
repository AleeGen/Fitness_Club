<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="set_locale">
    <input type="hidden" name="locale" value="ru_RU">
    <input type="submit" name="submit" value="RU">
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="set_locale">
    <input type="hidden" name="locale" value="en_US">
    <input type="submit" name="submit" value="EN">
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="set_locale">
    <input type="hidden" name="locale" value="by_BY">
    <input type="submit" name="submit" value="BY">
</form>
</body>
</html>
