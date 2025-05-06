<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Restaurant Management System</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1>Login</h1>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<form method="post" action="/login">
    <label>Username: <input type="text" name="username" required></label><br>
    <label>Password: <input type="password" name="password" required></label><br>
    <button type="submit">Login</button>
</form>
<p><a href="/password-recovery">Forgot Password?</a></p>
<script src="/js/login.js"></script>
</body>
</html>