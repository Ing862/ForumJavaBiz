<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
    <title>Topic details</title>
</head>
<body>

<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
    <h1>${topic.title}</h1>
</div>

<div style="margin-left: 40px; display: flex; gap: 20px; align-items: center;">
    <p><strong>Author:</strong> ${topic.author.username}</p>
    <p><strong>Date:</strong> ${topic.creationDate}
<%--        <fmt:formatDate value="${topic.creationDate}" pattern="dd.MM.yyyy HH:mm"/>--%>
    </p>
</div>

<div style="padding: 0 10px; margin-left: 40px; margin-top: 10px; margin-bottom: 20px;">
    <p>${topic.description}</p>
</div>

<div style="text-align: center; margin-top: 20px; margin-bottom: 40px;">
    <%--    <a href="${pageContext.request.contextPath}/post/edit?topicId=${topic.id}&redirect=${pageContext.request.requestURI}">--%>
    <%--        <button style="padding: 6px 12px; border-radius: 8px;">Add new post</button>--%>
    <%--    </a>--%>

    <c:choose>
        <c:when test="${not empty sessionScope.loggedUser}">
            <a href="${pageContext.request.contextPath}/post/edit?topicId=${topic.id}&redirect=${pageContext.request.requestURI}">
                <button style="padding: 6px 12px; border-radius: 8px;">Create new post</button>
            </a>
        </c:when>
        <c:otherwise>
            <p style="color: red;">You must be logged in to create a new post in this topic.</p>
        </c:otherwise>
    </c:choose>
</div>

<h2 style="margin-left: 40px; margin-bottom: 20px">Posts in this topic</h2>

<div style="padding: 0 10px; margin-left: 40px;">
    <c:choose>
        <c:when test="${empty postList}">
            <p>No posts in this topic yet.</p>
        </c:when>
        <c:otherwise>
            <ul style="padding: 0 10px;">
                <c:forEach var="post" items="${postList}">
                    <li style="margin-bottom: 15px;">
                        <a href="${pageContext.request.contextPath}/post/view/${post.id}">
                            <strong>${post.title}</strong>
                        </a>
                        by ${post.author.username}
                        <br/>
                        <c:if test="${not empty sessionScope.loggedUser && (sessionScope.loggedUser.id == post.author.id || sessionScope.loggedUser.role eq 'ADMIN')}">
                            | <a href="${pageContext.request.contextPath}/post/edit/${post.id}">Edit</a>
                            | <a href="${pageContext.request.contextPath}/post/remove/${post.id}">Delete</a>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>
