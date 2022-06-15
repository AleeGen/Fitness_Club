<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
  <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
  <meta charset="utf-8"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
  <div>
    <input type="hidden" name="command" value="open_page">
    <input type="hidden" name="page" value="pages/logIn.jsp">
    <input type="submit" name="submit" value="<fmt:message key="submit.sign_in_up"/>"><br/>
  </div>
</form>
</body>
</html>
