<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="path" required="false"%>
<%@ attribute name="required" type="java.lang.Boolean" required="false"%>
<%@ attribute name="label" required="false"%>
<%@ attribute name="narrowLayout" type="java.lang.Boolean" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>

<%-- Component to wrap around a form input element to provide standard support for:
   1) error messages, highlighting,
   2) labels
   3) required marking. 

Example usage:

<b:form-element-container path="username" required="true" label="username">
	<form:input type="text" cssClass="form-control" path="username"/>
</b:form-element-container>

--%>

<c:set var="narrowLayout" value="${(empty narrowLayout) ? false : narrowLayout}"/>
<c:set var="elementCssClass" value="${(narrowLayout) ? 'col-sm-4 col-md-4' : 'col-sm-8 col-md-9'}"/>

<b:form-element-container-support path="${path}" required="${required}" label="${label}">
	<div ${containerId} class="form-group ${containerClasses}">
		<c:choose>
			<c:when test="${labelSet}">
				<label for="${path}" class="col-sm-4 col-md-3 control-label">
					${labelText}
				</label>
			</c:when>
			<c:otherwise>
				<div class="col-sm-4 col-md-3"></div>
			</c:otherwise>
		</c:choose>
		<div class="${elementCssClass}">
			<jsp:doBody />			
			${errorElement}
		</div>
	</div>
</b:form-element-container-support>