<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--header--%>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
  <title>Confirm Topic Deletion</title>
</head>
<body>
<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
  <div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
    <h1>Confirm Topic Deletion</h1>
  </div>


<p>Are you sure you want to delete this topic: <strong>${topic.title}</strong>?</p>
<p>This action cannot be undone.</p>
<br/>
  
<form method="post" action="${pageContext.request.contextPath}/topic/remove/${topic.id}">
  <input style="padding: 3px" type="submit" value="Yes, delete it" />
  <a style="margin-left: 3px" href="${pageContext.request.contextPath}/topic/list">Cancel</a>
</form>
</div>
</body>
</html>
