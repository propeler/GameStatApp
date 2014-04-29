<jsp:useBean
        id="user"
        type="com.olukelom.gamestat.model.User"
        scope="session"/>
<div class="menu">
    <ul>
        <li class="left"><a href="${pageContext.request.contextPath}/home"> The name of the game </a></li>
        <li class="right"><a href="${pageContext.request.contextPath}/logout"> Logout </a></li>
        <li class="right"><a>${user.login}</a>
            <ul>
                <li><a href=""> Overview </a></li>
                <li><a href=""> Equipment </a></li>
                <li><a href=""> Statistics </a></li>
            </ul>
        </li>
    </ul>
</div>
