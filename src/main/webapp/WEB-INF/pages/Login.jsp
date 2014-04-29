<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../../css/stylesheet.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post" class="login-form">
    <h1>${requestScope.message}</h1>
    <input class="login-input" type="text" name="login" placeholder="Login">
    <input class="login-input" type="password" name="password" placeholder="Password">
    <button class="login-submit" type="submit">Sign in</button>
</form>
</body>
</html>
