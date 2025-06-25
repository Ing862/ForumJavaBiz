<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Lista postów</title>
</head>
<body>
<h1>Lista postów</h1>

<a href="${pageContext.request.contextPath}/post/edit">Dodaj nowy post</a>

<ul>
    <c:forEach var="post" items="${postList}">
        <li>
            <strong>${post.title}</strong> - ${post.author.username}
            <br/>
            <a href="${pageContext.request.contextPath}/post/view/${post.id}">Zobacz</a> |
            <%--      Dostępne tylko dla autora postu --%>
            <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.id == post.author.id}">
                <a href="${pageContext.request.contextPath}/post/edit/${post.id}">Edytuj</a> |
                <a href="${pageContext.request.contextPath}/post/remove/${post.id}">Usuń</a>
            </c:if>
        </li>
    </c:forEach>
</ul>

</body>
</html>
