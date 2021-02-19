<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="disabled" type="java.lang.Boolean" required="false"%>
<%@ attribute name="resetLink" required="false"%>
<%@ attribute name="discardQueryString" type="java.lang.Boolean" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>

<c:set var="disabled" value="${(empty disabled) ? false : disabled}"/>
<c:set var="discardQueryString" value="${(empty discardQueryString) ? false : discardQueryString}"/>

<c:choose>
	<c:when test="${(empty resetLink)}">
		<c:choose>
			<c:when test="${discardQueryString}">
				<c:set var="reset" value="window.location = window.location.href.split('\\?')[0];"/>
			</c:when>
			<c:otherwise>
				<c:set var="reset" value="window.location = window.location.href;"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<c:set var="reset" value="window.location = '${resetLink}';"/>
	</c:otherwise>
</c:choose>

<%-- Reset button component. 

Example usage:

<b:form-reset-button/>

--%>

<input
	type="reset" 
	value="<spring:message code="prompt.reset"/>" 
	title="<spring:message code="prompt.reset"/>" 
	class="btn btn-default ${disabled ? 'disabled' : ''}"
	${disabled ? 'disabled' : ''}
	<c:if test="${!disabled}">onClick="${reset}"</c:if>
/>