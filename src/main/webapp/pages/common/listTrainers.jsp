<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<c:set var="queryImage">${pageContext.request.contextPath}/uploadImage?imagePath</c:set>
<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
<!DOCTYPE>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <title><fmt:message key="list.trainer"/></title>
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
    <link rel="stylesheet" href="css/style3.css" type="text/css"/>
    <link rel="stylesheet" href="css/slideShow.css" type="text/css"/>
    <script src="${pageContext.servletContext.contextPath}/script/openCloseForm.js"></script>
</head>
<body>
<c:import url="/pages/header/header.jsp"/>
<div class="container">
    <jsp:include page="/pages/aside/aside.jsp"/>
    <c:forEach var="trainer" items="${trainers}">
        <div class="posts-list">
            <article class="post" style="background-color: rgba(255,255,255,0.1); display: inline-block">
                <div class="post-content" style="display: inline-flex;">
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="view_profile"/>
                        <input type="hidden" name="login" value="${trainer.getLogin()}"/>
                        <div class="triangle-caption_t">
                            <img src="${queryImage}=${trainer.getPathAvatar()}" alt="">
                            <div class="caption" style="padding: 0 10px 0 0; text-align: center;">
                                <p>
                                    <input style="border: none; cursor: pointer; background: none; color: white"
                                           type="submit" name="login"
                                           value="${trainer.getLastname()} ${trainer.getName()}"/>
                                </p>
                            </div>
                        </div>
                    </form>
                    <div style="display: inline-flex">
                        <div style="display: inline-block; position: relative; overflow: hidden;width: 600px; margin: 5px 0 0 10px;">
                            <h4>
                                    ${trainer.getAboutMe()}
                            </h4>
                            <hr style="margin-top: 5px; margin-bottom: 10px">
                            <c:if test="${sessionScope.get('login')!=null}">
                                <div class="post-footer">
                                    <a class="more-link_a" onclick="openForm(${trainer.getId()})"><fmt:message
                                            key="submit.arrange_contract"/></a>
                                </div>
                                <div class="form-popup" id="${trainer.getId()}">
                                    <div class="form-container">
                                        <form>
                                            <input type="hidden" name="command" value="arrange_contract">
                                            <input type="hidden" name="trainer_id" value="${trainer.getId()}"/>
                                            <div class="form-row">
                                                <input id="start_date" type="date" name="start_date"
                                                       min="${calendar.toZonedDateTime().toLocalDate()}" value="">
                                                <label for="start_date" style="color:white"><fmt:message
                                                        key="field.contract.start_date"/></label>
                                                <input id="end_date" type="date" name="end_date"
                                                       min="${calendar.toZonedDateTime().toLocalDate().plusDays(1)}"
                                                       value="">
                                                <label for="end_date" style="color:white"><fmt:message
                                                        key="field.contract.end_date"/></label>
                                            </div>
                                            <div class="more-link" style="margin-top: 10px">
                                                <input style="all: unset" type="submit"
                                                       value="<fmt:message key="submit.buy"/>">
                                            </div>
                                            <div class="more-link_c" style="margin-top: 10px">
                                                <button type="button" style="all: unset;"
                                                        onclick="closeForm(${trainer.getId()})">
                                                    <fmt:message key="submit.cancel"/>
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </article>
        </div>
    </c:forEach>
</div>
<jsp:include page="/pages/footer/footer.jsp"/>
<script>

</script>
</body>
</html>
