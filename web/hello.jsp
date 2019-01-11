<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello, ${fn:escapeXml(requestScope.username)}</title>
</head>
<body>
    Hello, ${fn:escapeXml(requestScope.username)}! | <a href="${requestScope.logoutUrl}">Log out</a>
</body>
</html>
