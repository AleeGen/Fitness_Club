<%@ page language="java" %>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8"/>
</head>
<body>
temp: ${temp_attribute.size()} <br/>
<div>
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
</div>
</body>
</html>
