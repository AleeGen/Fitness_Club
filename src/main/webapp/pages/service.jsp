<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Service</title>
    <link rel="stylesheet" href="css/style3.css" type="text/css">
</head>

<body>
<form class="decor" style="max-width: 900px">

    <div class="form-row">
        Сообщение: ${message}
        <table class="table">
            <tr>
                <th>Название услуги
                </td>
                <th>Количество посещений
                </td>
                <th>Цена
                </td>
                <th>Описание
                </td>
            </tr>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td><c:out value="${service.getServiceName()}"/></td>
                    <td><c:out value="${service.getNumberVisit()}"/></td>
                    <td><c:out value="${service.getPrice()}"/></td>
                    <td><c:out value="${service.getDescription()}"/></td>
                    <td>
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="add_to_cart">
                            <input type="hidden" name="service_cart" value="${service.getId()}">
                            <input style="margin-block: 0px" type="submit" name="submit" value="В корзину"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>
</body>
</html>
