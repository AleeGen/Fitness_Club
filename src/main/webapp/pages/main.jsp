<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h1 align="center">Main to welcome!</h1>
<hr/>
<h2 align="center">Hello, ${all_info_user.getLastname()} ${all_info_user.getName()} !</h2>
INFO ${all_info_user.getRole()}<br/>
mail: ${all_info_user.getMail()}<br/>
dateBirth: ${all_info_user.getDateBirth()}<br/>
sex: ${all_info_user.getSex()}<br/>
phone: ${all_info_user.getPhone()}<br/>
corporate: ${all_info_user.isCorporate()}<br/>
visitPeriodMonths: ${all_info_user.getVisitPeriodMonths()}<br/>
discountCode: ${all_info_user.getDiscountCode()}<br/>
numberCard: ${all_info_user.getNumberCard()}<br/>
<hr/>
${filter_attr}!
<hr/>

<form action="controller" method="get">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logOut"/>
</form>

<hr/>
<hr/>
<hr/>
param.get("login"): ${param.get("login")}
<hr/>
initParam.get("passAdmin"): ${initParam.get("passAdmin")}
requestScope: ${requestScope}
<hr/>
response: ${response}
<hr/>
pageContext.session.getAttribute(current_page): ${pageContext.session.getAttribute("current_page")}
<hr/>
</body>
</html>
