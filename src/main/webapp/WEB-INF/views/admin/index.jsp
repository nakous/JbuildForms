<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<table>
        <c:forEach items="${process}" var="proces">
            <tr>
                <td>${proces.name}</td>
                 <td><a href="/admin/process?processid=${ proces.name}"><spring:message code="Details"/> !c</a></td>
                  <td><a href=""><spring:message code="Update"/> !u</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>