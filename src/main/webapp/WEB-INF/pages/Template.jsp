<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="utf-8" language="java" %>
<jsp:useBean
        id="user"
        class="com.olukelom.gamestat.model.User"
        scope="session"/>
<!DOCTYPE HTML>
<html>
<head>
    <link
            rel="stylesheet"
            type="text/css"
            href="css/menu.css">
    <link
            rel="stylesheet"
            type="text/css"
            href="css/stylesheet.css">
    <link
            rel="stylesheet"
            type="text/css"
            href="css/server-list.css">
</head>
<body>
<c:choose>
    <c:when test="${user.notAnonymous}">
        <jsp:include page="/WEB-INF/pages/UserMenu.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/pages/GuestMenu.jsp"/>
    </c:otherwise>
</c:choose>
<jsp:include page="${requestScope.content}"/>
</body>
</html>


