<%@ page language="java" %>
<html>
<head>
    <title>Title_Locale</title>
</head>
<body>
current page: ${current_page}<br/>
temp: ${temp.size()} <br/>
temp user: ${temp.get("user")}<hr/>
temp services: ${temp.get("services")}<hr/>
requestAttribute User: ${user}<hr/>
requestAttribute services : ${services}<hr/>
<div>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="set_locale">
        <input type="hidden" name="locale" value="ru_RU">
        <input type="submit" name="submit" value="RU">
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="set_locale">
        <input type="hidden" name="locale" value="en_US">
        <input type="submit" name="submit" value="EN">
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="set_locale">
        <input type="hidden" name="locale" value="by_BY">
        <input type="submit" name="submit" value="BY">
    </form>
</div>
</body>
</html>
