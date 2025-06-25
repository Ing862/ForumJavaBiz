<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
  <title>Formularz tematu</title>
</head>
<body>
<h1>${title == null ? "Dodaj nowy temat" : "Edytuj temat"}</h1>

<form method="post" action="${pageContext.request.contextPath}/topic/edit">
  <c:if test="${id != null}">
    <input type="hidden" name="id" value="${id}"/>
  </c:if>

  <label for="title">Tytuł:</label><br/>
  <input type="text" id="title" name="title" value="${title != null ? title : ''}"/>
  <c:if test="${errors.title != null}">
    <span style="color:red">${errors.title}</span>
  </c:if>
  <br/><br/>

  <label for="description">Opis:</label><br/>
  <textarea id="description" name="description" rows="5" cols="10">${description != null ? description : ''}</textarea>
  <c:if test="${errors.description != null}">
    <span style="color:red">${errors.description}</span>
  </c:if>
  <br/><br/>

  <input type="submit" value="Zapisz"/>
</form>

<a href="${pageContext.request.contextPath}/topic/list">Powrót do listy</a>

</body>
</html>