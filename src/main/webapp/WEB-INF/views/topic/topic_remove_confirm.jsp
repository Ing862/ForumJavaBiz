<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Potwierdzenie usunięcia tematu</title>
</head>
<body>
<h1>Potwierdzenie usunięcia tematu</h1>

<p>Czy na pewno chcesz usunąć temat: <strong>${topic.title}</strong>?</p>

<form method="post" action="${pageContext.request.contextPath}/topic/remove/${topic.id}">
  <input type="submit" value="Tak, usuń temat" />
  <a href="${pageContext.request.contextPath}/topic/list">Anuluj</a>
</form>
</body>
</html>
