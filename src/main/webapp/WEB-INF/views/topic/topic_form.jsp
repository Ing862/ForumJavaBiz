<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
  <title>${title == null ? "Create Topic" : "Edit Topic"}</title>
</head>
<body>

<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
  <h1>${title == null ? "Create a new topic" : "Edit your topic"}</h1>
</div>

<form method="post" action="${pageContext.request.contextPath}/topic/edit" style="max-width: 600px; margin: 0 auto;">
  <c:if test="${id != null}">
    <input type="hidden" name="id" value="${id}"/>
  </c:if>

  <label for="title"><strong>Title:</strong></label><br/>
  <input type="text" id="title" name="title" style="width: 100%; padding: 5px;" value="${title != null ? title : ''}"/>
  <c:if test="${errors.title != null}">
    <div style="color:red; margin-top: 5px;">${errors.title}</div>
  </c:if>
  <br/><br/>

  <label for="description"><strong>Description:</strong></label><br/>
  <textarea id="description" name="description" rows="4" style="width: 100%; padding: 5px;">${description != null ? description : ''}</textarea>
  <c:if test="${errors.description != null}">
    <div style="color:red; margin-top: 5px;">${errors.description}</div>
  </c:if>
  <br/><br/>

  <input type="submit" value="${title == null ? "Create topic" : "Save changes"}" style="padding: 6px 12px; border-radius: 8px;" />
</form>

<div style="text-align: center; margin-top: 20px;">
  <a href="${pageContext.request.contextPath}/topic/list">Return to topic list</a>
</div>

</body>
</html>
