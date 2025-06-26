<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
  <title>Confirm Topic Deletion</title>
</head>
<body>

<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
  <h1>Confirm Topic Deletion</h1>
</div>

<div style="margin-left: 40px; margin-right: 40px; text-align: center;">
  <p>Are you sure you want to delete this topic: <strong>${topic.title}</strong>?</p>
  <p style="color: red;">This action cannot be undone.</p>

  <form method="post" action="${pageContext.request.contextPath}/topic/remove/${topic.id}" style="margin-top: 20px;">
    <input type="submit" value="Yes, delete it"
           style="padding: 6px 12px; border-radius: 8px; background-color: red; color: white; border: none;" />
    <a href="${pageContext.request.contextPath}/topic/list"
       style="margin-left: 10px; padding: 6px 12px; border-radius: 8px; text-decoration: none; border: 1px solid #ccc;">
      Cancel
    </a>
  </form>
</div>

</body>
</html>
