<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
    <title>Topic list</title>
</head>
<body>
<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
  <h1>Topics</h1>
  <br/>
  <c:choose>
    <c:when test="${not empty sessionScope.loggedUser}">
      <a href="${pageContext.request.contextPath}/topic/edit">
        <button style="padding: 6px 12px; border-radius: 8px;">Create new topic</button>
      </a>
    </c:when>
    <c:otherwise>
      <p style="color: red;">You must be logged in to create a new topic.</p>
    </c:otherwise>
  </c:choose>
</div>

<ul style="padding: 0 10px; margin-left: 40px;">
  <c:forEach var="topic" items="${topicList}">
    <li style="margin-bottom: 15px;">
      <a href="${pageContext.request.contextPath}/topic/view/${topic.id}">
        <strong>${topic.title}</strong>
      </a>
      by ${topic.author.username}
      <br/>
      <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.id == topic.author.id}">
        | <a href="${pageContext.request.contextPath}/topic/edit/${topic.id}">Edit</a> |
        <a href="${pageContext.request.contextPath}/topic/remove/${topic.id}">Delete</a> |
      </c:if>
    </li>
  </c:forEach>
</ul>
</body>
</html>
