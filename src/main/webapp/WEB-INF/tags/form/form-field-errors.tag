<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="path" required="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- Component to show list of Spring field error messages. 

This component is designed to allow empty messages to be used to signal field error but skip them on displaying.

Example usage:
--%>

<spring:bind path="${path}">
	<c:forEach items="${status.errorMessages}" var="fieldError">
		<c:if test="${fn:length(fn:trim(fieldError)) > 0}"><c:out value="${fieldError}"/><br/></c:if>
	</c:forEach>
</spring:bind>