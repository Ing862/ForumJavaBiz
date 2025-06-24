<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Szczegóły posta</title>
</head>
<body>
<h1>${post.title}</h1>

<p>
  <strong>Autor:</strong> ${fn:escapeXml(post.author)}<br/>
  <strong>Data:</strong> <fmt:formatDate value="${fn:escapeXml(post.createdAt)}" pattern="yyyy-MM-dd HH:mm" />
  <strong>Topic:</strong> ${fn:escapeXml(post.topic)}<br/>
</p>

<hr/>

<div>
  ${fn:escapeXml(post.content)}
</div>

<hr/>

<p><a href="${pageContext.request.contextPath}/post/list">← Powrót do listy postów</a></p>
</body>
</html>
