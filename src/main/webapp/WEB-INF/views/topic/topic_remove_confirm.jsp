<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Confirm Topic Deletion</title>
</head>
<body>
<h1>Confirm Topic Deletion</h1>

<p>Are you sure you want to delete this post: <strong>${topic.title}</strong>?</p>
<p>This action cannot be undone.</p>

<form method="post" action="${pageContext.request.contextPath}/topic/remove/${topic.id}">
  <input type="submit" value="Yes, delete it" />
  <a href="${pageContext.request.contextPath}/topic/list">Cancel</a>
</form>
</body>
</html>
