<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <title><fmt:message key="head.profile"/></title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
</head>
<body>

<div class="decor" style="max-width: 900px">
    <div class="form-row">
        <h1 align="center"><fmt:message key="message.hello"/>, ${temp.get("user").getLastname()} ${temp.get("user").getName()}!</h1>
        <div>
            <form style="margin-right: 600px" action="${pageContext.request.contextPath}/controller" method="get">
                <div>
                    <input type="hidden" name="command" value="check_service">
                    <input type="submit" name="submit" value="<fmt:message key="head.services"/>"/>
                </div>
            </form>
            <h5><fmt:message key="field.user.role"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getRole()}</h4>
            <h5><fmt:message key="field.user.email"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getMail()}</h4>
            <h5><fmt:message key="field.user.date.birth"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getDateBirth()}</h4>
            <h5><fmt:message key="field.user.sex"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getSex()}</h4>
            <h5><fmt:message key="field.user.phone"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getPhone()}</h4>
            <h5><fmt:message key="field.user.corporate"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").isCorporate()}</h4>
            <h5><fmt:message key="field.user.visit_period_months"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getVisitPeriodMonths()}</h4>
            <h5><fmt:message key="field.user.discount_code"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getDiscountCode()}</h4>
            <h5><fmt:message key="field.user.number_cart"/>:</h5>
            <h4 style="margin-top: -20px;margin-left: 20px; color: #343636">${temp.get("user").getNumberCard()}</h4>
            <form style="margin-right: 700px" action="${pageContext.request.contextPath}/controller" method="get">
                <div>
                    <input type="hidden" name="command" value="open_page">
                    <input type="submit" value="<fmt:message key="submit.edit"/>"/>
                </div>
            </form>
        </div>
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <div>
                <input type="hidden" name="command" value="logout">
                <input type="submit" value="<fmt:message key="submit.sign_out"/>"/>
            </div>
        </form>
    </div>
</div>
<div class="table">
    <c:import url="insert/locale.jsp"/>
</div>
<hr/>
<hr/>
<hr/>
param.get("login"): ${param.get("login")}
<hr/>
initParam.get("passAdmin"): ${initParam.get("passAdmin")}
<hr/>
requestScope: ${requestScope}
<hr/>
response: ${response}
<hr/>
pageContext.session.getAttribute(current_page): ${pageContext.session.getAttribute("current_page")}
<hr/>
pageContext.request.getAttribute(current_page): ${pageContext.request.getAttribute("current_page")}
${pageContext.request}
<hr/>
</body>
</html>
