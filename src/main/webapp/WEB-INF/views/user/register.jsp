<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
    <title>Registration form</title>
</head>
<body>
<div style="width: 300px; margin: 50px auto; text-align: left;">

    <div style="text-align: center; margin-top: 50px; margin-bottom: 50px;">
        <h1>Register</h1>
    </div>

    <form method="post" action="register" style="padding: 0 10px;">
        <div style="display: flex; flex-direction: column; gap: 10px;">
            <input name="username" placeholder="Username" required />
            <input name="password" type="password" placeholder="Password" required />
            <button type="submit">Register</button>
        </div>

        <c:if test="${param.error == 'true'}">
            <p style="color:red; margin-top: 10px;">Invalid login</p>
        </c:if>
    </form>

    <div style="text-align: center; margin-top: 50px; margin-bottom: 50px;">
        <h3>Already have an account?</h3>
        <a href="${pageContext.request.contextPath}/user/login">→ Log in ←</a>
    </div>
</div>
</body>
</html>
