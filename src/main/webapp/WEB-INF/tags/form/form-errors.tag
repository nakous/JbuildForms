<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="form" required="true"%>
<%@ attribute name="captionMessageCode" required="false"%>
<%@ attribute name="onlyGlobalErrors" type="java.lang.Boolean" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%-- Component to show list of Spring error messages. 

Example usage (along with alerts component):

<spring:hasBindErrors name="form">
	<b:warnings>
		<b:form-errors form="form"
			captionMessageCode="system.messages.error.fields.validation" />
	</b:warnings>
</spring:hasBindErrors>

--%>

<c:set var="onlyGlobalErrors" value="${(empty onlyGlobalErrors) ? false : onlyGlobalErrors}" />


<c:if test="${!(empty captionMessageCode)}">
	<p>
		<spring:message code="${captionMessageCode}"/>
	</p>
</c:if>
<ul>
	<spring:bind path="${form}.*">
		<c:forEach items="${onlyGlobalErrors ? status.errors.globalErrors : status.errors.allErrors}" var="formError">
			<li><spring:message message="${formError}"/></li>
		</c:forEach>
	</spring:bind>
</ul>	