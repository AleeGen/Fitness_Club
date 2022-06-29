<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <title><fmt:message key="head.error.404"/></title>
</head>
<body>
Exception: ${pageContext.exception}
<hr/>
errorData: ${pageContext.errorData}
<hr/>
errorData.statusCode: ${pageContext.errorData.statusCode}
<hr/>
errorData.servletName: ${pageContext.errorData.servletName}
<hr/>
errorData.requestURI: ${pageContext.errorData.requestURI}
<hr/>
request: ${pageContext.request}
<hr/>
response: ${pageContext.response}
<hr/>
page: ${pageContext.page}
<hr/>
servletContext: ${pageContext.servletContext}
</body>
->${message}<-
</html>
