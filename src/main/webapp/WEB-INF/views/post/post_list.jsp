<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <c:forEach items='${postList}' var='post'>
        <tr>
            <td>${fn:escapeXml(post.title)}</td>
            <td>${fn:escapeXml(post.creator)}</td>
            <td>${fn:escapeXml(post.date)}</td>
            <td>
                <a href="<c:url value='/post/view/${post.id}'/>">View</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
