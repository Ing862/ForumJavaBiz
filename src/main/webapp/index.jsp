<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World! - wszystkie linki do wszystkiego póki się nie ustali wyglądu :)" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="login">Login</a>
<a href="register">Register</a>
<br/>
<h2>Posty</h2>
<a href="${pageContext.request.contextPath}/post/list">All Posts</a>
</body>
</html>