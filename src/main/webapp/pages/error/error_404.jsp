<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17.04.2022
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
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
</html>
