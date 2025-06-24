<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form method="post" action="login">
        <input name="username" placeholder="Username" required />
        <input name="password" type="password" placeholder="Password" required />
        <button type="submit">Login</button>
        <c:if test="${param.error == 'true'}">
            <p style="color:red">Invalid login</p>
        </c:if>
    </form>
</head>
<body>

</body>
</html>
