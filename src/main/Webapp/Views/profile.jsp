<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile - Restaurant Management System</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1>User Profile</h1>
<form method="post" action="/profile">
    <label>Username: <input type="text" name="username" value="${appUser.username}" readonly></label><br>
    <label>Email: <input type="email" name="email" value="${appUser.email}"></label><br>
    <button type="submit">Update Profile</button>
</form>
<p><a href="/dashboard">Back to Dashboard</a></p>
</body>
</html>