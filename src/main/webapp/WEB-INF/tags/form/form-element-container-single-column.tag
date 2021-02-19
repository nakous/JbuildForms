<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="path" required="false"%>
<%@ attribute name="required" type="java.lang.Boolean" required="false"%>
<%@ attribute name="label" required="false"%>
<%@ attribute name="strong" required="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="b" uri="/WEB-INF/taglib/basetags.tld"%>


<%-- Component to wrap around a form input element (such a date picker) to provide standard support for:
   1) error messages, highlighting,
   2) labels
   3) required marking.
   
   Single column layout: produces 2 div containers when label is provided or only 1 without label.

Example usage:

<b:form-element-container-single-column path="birthDate" required="true" label="birthDate">
	<form:input type="text" cssClass="datepicker form-control" path="birthDate"/>
</b:form-element-container-single-column>

--%>

<b:form-element-container-support path="${path}" required="${required}" label="${label}">
	<div ${containerId} class="form-group ${containerClasses}">
		<c:choose>
			<c:when test="${labelSet}">
				<c:choose>
				<c:when test="${strong}">
				<div class="col-sm-12">
	            	<h4>
						${labelText}
					</h4>
	            </div>
	            </c:when>
	            <c:otherwise>
	            <div class="col-sm-12">
	            	<label for="${path}" class="control-label">
						${labelText}
					</label>
	            </div>
	            </c:otherwise>	
	            </c:choose>			
			</c:when>
		</c:choose>
		<div class="col-sm-12">
			<jsp:doBody />			
			${errorElement}
		</div>
	</div>
</b:form-element-container-support>


