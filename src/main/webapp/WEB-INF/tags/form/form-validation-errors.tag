<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="form" required="true"%>
<%@ attribute name="onlyGlobalErrors" type="java.lang.Boolean" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>

<%-- Component to show form validation error messages along with correct alert style and message. 

Example usages:

<b:form-validation-errors form="form" onlyGlobalErrors="true"/>

<b:form-validation-errors form="form"/>

--%>

<c:set var="onlyGlobalErrors" value="${(empty onlyGlobalErrors) ? false : onlyGlobalErrors}" />
<c:set var="captionMessageCode" value="${(onlyGlobalErrors) ? 'system.messages.error.fields.validation.message' : 'system.messages.error.fields.validation'}" />

<spring:hasBindErrors name="${form}">
	<b:errors>
		<b:form-errors form="${form}" onlyGlobalErrors="${onlyGlobalErrors}"
			captionMessageCode="${captionMessageCode}" />
	</b:errors>
</spring:hasBindErrors>