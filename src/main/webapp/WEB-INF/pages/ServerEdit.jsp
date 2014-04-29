<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean
        id="server"
        class="com.olukelom.gamestat.model.GameServer"
        scope="request"
        />
<div class="content">
    <form method="post" action="${pageContext.request.contextPath}/servers">
        <input type="hidden" value="${server.id}">
        <table>
            <tr>
                <td>
                    <label> Server name:
                        <input type="text" value="${server.name}">
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Server level:
                        <select name="level">
                            <c:forEach var="level" items="${requestScope.levels}">
                                <option value="${level.id}">${level.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Server mode:
                        <select name="mode">
                            <c:forEach var="mode" items="${requestScope.modes}">
                                <option value="${mode.id}">${mode.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label> Server description:
                        <input type="text" value="${server.description}">
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Submit changes</button>
                </td>
            </tr>
        </table>
    </form>
</div>