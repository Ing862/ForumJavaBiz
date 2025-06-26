<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
  <title>Post details</title>
</head>
<body>

<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
  <h1>${post.title}</h1>
</div>

<div style="margin-left: 40px; display: flex; gap: 20px; align-items: center;">
  <p><strong>Author:</strong> ${post.author.username}</p>
  <p><strong>Date:</strong> ${post.creationDate}</p>
</div>

<div style="padding: 0 10px; margin-left: 40px; margin-top: 10px; margin-bottom: 20px;">
  <p>${post.content}</p>
</div>

<div style="text-align: center; margin-top: 20px; margin-bottom: 40px;">
  <a href="${pageContext.request.contextPath}/topic/view/${post.topic.id}">
    <button style="padding: 6px 12px; border-radius: 8px;">Back to topic</button>
  </a>
</div>



</body>
</html>
