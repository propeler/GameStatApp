<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean
        id="server"
        class="com.olukelom.gamestat.model.GameServer"
        scope="request"
        />
<div class="content">
    You are playing at ${server.name} now.<br>
    The level is ${server.level.name} and game mode is ${server.mode.name}.
    <a href="${pageContext.request.contextPath}/servers">Leave</a>
</div>
