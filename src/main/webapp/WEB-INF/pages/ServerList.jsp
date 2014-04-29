<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean
        id="user"
        class="com.olukelom.gamestat.model.User"
        scope="session"/>
<c:choose>
    <c:when test="${user.notAnonymous}">
        <c:choose>
            <c:when test="${user.admin}">
                <c:set var="colspan" value="4"/>
            </c:when>
            <c:otherwise>
                <c:set var="colspan" value="3"/>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <c:set var="colspan" value="2"/>
    </c:otherwise>
</c:choose>

<table class="server-list">
    <tr>
        <td class="server-list-header" colspan="${colspan}">
            Servers
        </td>
    </tr>
    <c:forEach var="server" items="${requestScope.servers}">
        <tr>
            <c:if test="${server.online or (!server.online and user.admin)}">
                <td class="info">
                    <div class="info-name"> ${server.name} </div>
                    <div class="info-description">
                            ${server.mode.name} * ${server.level.name}
                        <c:if test="${server.description ne null}">
                            * ${server.description}
                        </c:if>
                    </div>
                </td>
            </c:if>

            <c:choose>
                <c:when test="${server.online}">
                    <td class="status-online">Online</td>
                </c:when>
                <c:otherwise>
                    <c:if test="${user.admin}">
                        <td class="status-offline">Offline</td>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <c:if test="${user.notAnonymous and server.online}">
                <td class="buttons">
                    <a href="${pageContext.request.contextPath}/servers?id=${server.id}">
                        <button class="join-button">Join</button>
                    </a>
                </td>
            </c:if>
            <c:if test="${!server.online and user.admin}">
                <td class="buttons">
                    <button class="join-button-inactive">Join</button>
                </td>
            </c:if>

            <c:if test="${user.admin}">
                <td class="buttons">
                    <a href="${pageContext.request.contextPath}/servers?id=${server.id}&mode=edit">
                        <button class="edit-button">Edit</button>
                    </a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

