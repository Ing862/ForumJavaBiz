<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
    <title>Login form</title>
</head>
<body>
<div style="width: 300px; margin: 50px auto; text-align: left;">

    <div style="text-align: center; margin-top: 50px; margin-bottom: 50px;">
        <h1>Login</h1>
    </div>

    <c:if test="${not empty loginError}">
        <p style="color:red; text-align: center; margin-top: 5px; margin-bottom: 5px">${loginError} Please try again</p>
    </c:if>

    <form method="post" action="login" style="padding: 0 10px;">
        <div style="display: flex; flex-direction: column; gap: 10px;">
            <input name="username" placeholder="Username" required />
            <input name="password" type="password" placeholder="Password" required />
            <button type="submit">Login</button>
        </div>

    </form>

    <div style="text-align: center; margin-top: 50px; margin-bottom: 50px;">
        <h3>Don't have an account?</h3>
        <a href="${pageContext.request.contextPath}/user/register">→ Register ←</a>
    </div>
</div>
</body>
</html>
