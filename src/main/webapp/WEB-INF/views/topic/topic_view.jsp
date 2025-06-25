<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
    <title>Podgląd tematu</title>
</head>
<body>
<h1>${topic.title}</h1>
<p><strong>Autor:</strong> ${topic.author.username}</p>
<p>${topic.description}</p>

<a href="${pageContext.request.contextPath}/topic/list">Powrót do listy</a>

<h2>Posts in this topic</h2>

<c:choose>
    <c:when test="${empty postList}">
        <p>No posts in this topic yet.</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="post" items="${postList}">
                <li>
                    <strong>${post.title}</strong> – ${post.author.username}<br/>
                    <a href="${pageContext.request.contextPath}/post/view/${post.id}">View</a>
                    <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.id == post.author.id}">
                        | <a href="${pageContext.request.contextPath}/post/edit/${post.id}">Edit</a>
                        | <a href="${pageContext.request.contextPath}/post/remove/${post.id}">Delete</a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/post/edit?topicId=${topic.id}&redirect=${pageContext.request.requestURI}">
    <button>Add new post</button>
</a>

</body>
</html>
