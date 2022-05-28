<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.04.2022
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ExceptionPage_500</title>
</head>
<body>
RequestURI: ${pageContext.errorData.requestURI}
<hr/>
ServletName: ${pageContext.errorData.servletName}
<hr/>
StatusCode: ${pageContext.errorData.statusCode}
<hr/>
Exception: ${pageContext.exception}
</body>
</html>
