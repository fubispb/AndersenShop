<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container" style="width: 300px;">
    <c:url value="/login" var="loginUrl" />
    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" name="j_username" required autofocus value="user1">
        <input type="password" class="form-control" name="j_password" placeholder="Password" required value="pass1">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Enter</button>
    </form>
</div>
</body>
</html>
