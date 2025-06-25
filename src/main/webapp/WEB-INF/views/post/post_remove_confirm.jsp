<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Confrim Post deletion</title>
</head>
<body>
<h1>Confirm Post Deletion</h1>

<p>Are you sure you want to delete this post: <strong>${post.title}</strong>?</p>
<p>This action cannot be undone.</p>

<form method="post" action="${pageContext.request.contextPath}/post/remove/${post.id}">
  <input type="submit" value="Yes, delete it" />
  <a href="${pageContext.request.contextPath}/post/list">Cancel</a>
</form>
</body>
</html>
