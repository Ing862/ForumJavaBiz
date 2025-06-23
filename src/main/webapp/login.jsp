<%--
  Created by IntelliJ IDEA.
  User: emili
  Date: 22.06.2025
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
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
