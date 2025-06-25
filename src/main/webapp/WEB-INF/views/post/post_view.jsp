<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
  <title>Podgląd posta</title>
</head>
<body>
<h1>${post.title}</h1>
<p><strong>Autor:</strong> ${post.author.username}</p>
<p>${post.content}</p>

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

<a href="${pageContext.request.contextPath}/topic/view/${post.topic.id}">Powrót do listy postów w temacie</a>


</body>
</html>