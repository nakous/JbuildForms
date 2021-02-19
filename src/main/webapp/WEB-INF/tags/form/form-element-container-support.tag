<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="path" required="false"%>
<%@ attribute name="required" type="java.lang.Boolean" required="false"%>
<%@ attribute name="label" required="false"%>

<%@ variable name-given="containerId" scope="NESTED"%>
<%@ variable name-given="containerClasses" scope="NESTED"%>
<%@ variable name-given="labelSet" scope="NESTED"%>
<%@ variable name-given="labelText" scope="NESTED"%>
<%@ variable name-given="errorElement" scope="NESTED"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>


<%-- Component to wrap around a form layout to provide its needed elements:
   1) error messages, highlighting,
   2) labels
   3) required marking. 

Example usage:

<b:form-element-container-support path="${path}" required="${required}" label="${label}">
	<div ${containerId} class="form-group ${containerClasses}">
		<c:choose>
			<c:when test="${labelSet}">
				<label for="${path}" class="col-lg-3 col-lg-offset-1 control-label">
					${labelText}
				</label>
			</c:when>
			<c:otherwise>
				<div class="col-lg-3 col-lg-offset-1"></div>
			</c:otherwise>
		</c:choose>
		<div class="col-lg-7">
			<jsp:doBody />			
			${errorElement}
		</div>
	</div>
</b:form-element-container-support>

--%>

<c:set var="required" value="${(empty required) ? false : required}" />
<c:set var="pathSet" value="${(empty path) ? false : true}" />

<c:if test="${pathSet}">
	<spring:bind path="${path}">
		<c:set var="hasError" value="${status.error ? true : false}" />
	</spring:bind>
</c:if>	

<c:set var="containerId"><c:if test="${pathSet}">id="${path}-container"</c:if></c:set>
<c:set var="containerClasses">${required ? 'is-mandatory ' : ''} ${hasError ? 'has-error' : ''}</c:set>

<c:set var="labelSet" value="${(empty label) ? false : true}" />
<c:set var="labelText">
	<spring:message code="${label}"/>
</c:set>
			
<c:set var="errorElement">
	<c:if test="${pathSet}">
		<span id="${path}-input-error-status" class="sr-only ${!hasError ? 'hidden' : ''}">(<spring:message code="system.messages.error"/>)</span>
		<div class="text-left text-danger">
			<small id="${path}-error-text">
				<%-- <form:errors path="${path}" />--%>
				<b:form-field-errors path="${path}" />	
			</small>
		</div>
	</c:if>
</c:set>

<jsp:doBody />