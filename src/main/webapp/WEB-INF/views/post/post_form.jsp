<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz posta</title>
</head>
<body>
<h1>${title == null ? "Dodaj nowy post" : "Edytuj post"}</h1>

<form method="post" action="${pageContext.request.contextPath}/post/edit">
    <label for="title">Tytuł:</label><br/>
    <input type="text" id="title" name="title" value="${title != null ? title : ''}"/>
    <c:if test="${errors.title != null}">
        <span style="color:red">${errors.title}</span>
    </c:if>
    <br/><br/>

    <label for="content">Treść:</label><br/>
    <textarea id="content" name="content" rows="10" cols="50">${content != null ? content : ''}</textarea>
    <c:if test="${errors.content != null}">
        <span style="color:red">${errors.content}</span>
    </c:if>
    <br/><br/>

    <input type="submit" value="Zapisz"/>
</form>

<a href="${pageContext.request.contextPath}/post/list">Powrót do listy</a>

</body>
</html>
