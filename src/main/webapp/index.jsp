<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:forEach items="${requestScope.list}" var="list">
    ${list.id}=====${list.name}===${list.password}==<br>
</c:forEach>
</body>
</html>