<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {

        font-family: sans-serif;
        background-color: #f5f5f5;
    }
</style>
<header style="background-color:rgba(73,11,102,0.91); padding: 20px; color: #ffffff;">
    <div style="display: flex; justify-content: space-between; align-items: center;">

        <div>
            <h1 style="margin: 10px;">Forum</h1>
        </div>

        <div>
            <nav>
                <a href="${pageContext.request.contextPath}/index.jsp" style="color:white; margin-right:15px;">Home</a>
                <a href="${pageContext.request.contextPath}/topic/list" style="color:white; margin-right:15px;">Topics</a>
            </nav>
        </div>

        <div>
            <c:choose>
                <c:when test="${not empty sessionScope.loggedUser}">
                    <span style="margin-right: 15px;">Hello <strong>${sessionScope.loggedUser.username}!</strong></span>
                    <a href="${pageContext.request.contextPath}/user/logout" style="color:white;">Log out</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/user/login" style="color:white; margin-right:15px;">Login</a>
                    <a href="${pageContext.request.contextPath}/user/register" style="color:white;">Register</a>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</header>