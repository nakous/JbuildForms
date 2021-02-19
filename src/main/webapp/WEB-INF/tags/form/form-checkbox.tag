<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="path" required="false"%>
<%@ attribute name="required" type="java.lang.Boolean" required="false"%>
<%@ attribute name="label" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>


<%-- Component to provide the same support for individual checkboxes as has the element container:
   1) error messages, highlighting,
   2) labels
   3) required marking. 

Example usage:

<b:form-element-container>
	<b:form-checkbox path="path" required="true">
		<label>
			<form:checkbox path="path"/>
			<spring:message code="label.code"/>
		</label>
	</b:form-checkbox>
	<b:form-checkbox path="path" required="true">
		<label>
			<form:checkbox path="path"/>
			<spring:message code="label.code"/>
		</label>
	</b:form-checkbox>
</b:form-element-container>

--%>

<b:form-element-container-support path="${path}" required="${required}" label="${label}">
	<div ${containerId} class="checkbox ${containerClasses}">
		<jsp:doBody />			
		${errorElement}
	</div>
</b:form-element-container-support>