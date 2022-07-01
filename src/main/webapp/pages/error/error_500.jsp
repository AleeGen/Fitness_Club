<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="prop.text"/>
<!DOCTYPE>
<html>
<head>
<title><fmt:message key="head.error.500"/></title>
</head>
<body>
RequestURI: ${pageContext.errorData.requestURI}
<hr/>
ServletName: ${pageContext.errorData.servletName}
<hr/>
StatusCode: ${pageContext.errorData.statusCode}
<hr/>
Exception: ${pageContext.exception}
<hr/>
Exception.stackTrace: ${pageContext.exception.stackTrace}
<hr/>
Message: ${message}
<hr/>
<%
    response.getWriter().println("StackTrace:");
    for (int i = 0; i < 3; i++) {
        response.getWriter().print("</br>"+pageContext.getException().getStackTrace()[i]);
    }
    response.getWriter().print("</hr>");
%>
</body>
</html>
