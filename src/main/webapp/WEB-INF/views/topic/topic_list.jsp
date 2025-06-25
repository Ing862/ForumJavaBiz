<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Lista Tematów</h1>

<a href="${pageContext.request.contextPath}/topic/edit">Dodaj nowy Temat</a>

<ul>
  <c:forEach var="topic" items="${topicList}">
    <li>
      <strong>${topic.title}</strong> - ${topic.author.username}
      <br/>
      <a href="${pageContext.request.contextPath}/topic/view/${topic.id}">Zobacz</a> |
<%--      Dostępne tylko dla autora tematu --%>
      <c:if test="${not empty sessionScope.loggedUser && sessionScope.loggedUser.id == topic.author.id}">
        <a href="${pageContext.request.contextPath}/topic/edit/${topic.id}">Edytuj</a> |
        <a href="${pageContext.request.contextPath}/topic/remove/${topic.id}">Usuń</a>
      </c:if>
    </li>
  </c:forEach>
</ul>
</body>
</html>
