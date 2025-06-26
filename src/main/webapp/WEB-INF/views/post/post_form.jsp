<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/shared/header.jsp" %>
<html>
<head>
    <title>${title == null ? "Add Post" : "Edit Post"}</title>
</head>
<body>

<div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
    <h1>${title == null ? "Add a new post" : "Edit your post"}</h1>
</div>

<form method="post" action="${pageContext.request.contextPath}/post/edit" style="max-width: 600px; margin: 0 auto;">
    <label for="title"><strong>Title:</strong></label><br/>
    <input type="text" id="title" name="title" style="width: 100%; padding: 5px;" value="${title != null ? title : ''}"/>
    <c:if test="${errors.title != null}">
        <div style="color:red; margin-top: 5px;">${errors.title}</div>
    </c:if>
    <br/><br/>

    <label for="content"><strong>Content:</strong></label><br/>
    <textarea id="content" name="content" rows="10" style="width: 100%; padding: 5px;">${content != null ? content : ''}</textarea>
    <c:if test="${errors.content != null}">
        <div style="color:red; margin-top: 5px;">${errors.content}</div>
    </c:if>
    <br/><br/>

    <c:if test="${id != null}">
        <input type="hidden" name="id" value="${id}"/>
    </c:if>

    <c:if test="${topicId != null}">
        <input type="hidden" name="topicId" value="${topicId}"/>
    </c:if>

    <input type="submit" value="Save" style="padding: 6px 12px; border-radius: 8px;" />
</form>

<div style="text-align: center; margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/topic/list">Return to list</a>
</div>

</body>
</html>
