<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<%--TODO: Ujednolicić język w aplikacji - polski lub angielski --%>
<html>
<head>
  <title>Welcome page</title>
</head>
<body>
  <div style="text-align: center; margin-top:50px;">
    <h1>Welcome to the Forum!</h1>
  </div>
<%--<h1><%= "Hello World! - wszystkie linki do wszystkiego póki się nie ustali wyglądu :)" %></h1>--%>
<%--<h1><c:if test="${not empty sessionScope.loggedUser}">--%>
<%--    <p>Zalogowany jako: <strong>${sessionScope.loggedUser.username}</strong></p>--%>
<%--  </c:if>--%>
<%--  <c:if test="${empty sessionScope.loggedUser}">--%>
<%--    <p>Nie jesteś zalogowany.</p>--%>
<%--  </c:if></h1>--%>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--<h2>Użytkownik</h2>--%>
<%--<a href="${pageContext.request.contextPath}/user/login">Login</a>--%>
<%--<a href="${pageContext.request.contextPath}/user/register">Register</a>--%>
<%--<br/>--%>
<%--<h2>Posty</h2>--%>
<%--<a href="${pageContext.request.contextPath}/post/list">All Posts</a>--%>
<%--<br/>--%>
<%--<h2>Tematy</h2>--%>
<%--<a href="${pageContext.request.contextPath}/topic/list">All Topics</a>--%>
</body>
</html>