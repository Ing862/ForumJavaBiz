<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Podgląd tematu</title>
</head>
<body>
<h1>${topic.title}</h1>
<p><strong>Autor:</strong> ${topic.author.username}</p>
<p>${topic.description}</p>

<a href="${pageContext.request.contextPath}/topic/list">Powrót do listy</a>

</body>
</html>
