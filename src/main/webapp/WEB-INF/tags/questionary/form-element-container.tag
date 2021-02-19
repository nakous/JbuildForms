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

<q:form-element-container path="username" required="true" label="username">
	....
</q:form-element-container>

--%>

<%-- <c:set var="narrowLayout" value="${(empty narrowLayout) ? false : narrowLayout}"/> --%>
<%-- <c:set var="elementCssClass" value="${(narrowLayout) ? 'col-sm-6 col-md-5' : 'col-sm-6 col-md-5'}"/> --%>
<c:set var="elementCssClass" value="col-sm-6"/>
<c:set var="colSpanLabel" value="6"/>
<c:if test="${narrowLayout}">
	<c:set var="colSpanLabel" value="3"/>
</c:if>


<b:form-element-container-support path="${path}" required="${required}" label="${label}">
	<div ${containerId} class="form-group ${containerClasses}" style="margin-top:0px; margin-bottom : 15px">
		<c:choose>
			<c:when test="${labelSet}">
				<label for="${path}" class="col-sm-${colSpanLabel} control-label" id="${path}-label">
					${labelText}
				</label>
			</c:when>
			<c:otherwise>
				<div class="col-sm-${colSpanLabel}"></div>
			</c:otherwise>
		</c:choose>
		<div class="${elementCssClass}">
			<jsp:doBody />			
			${errorElement}
		</div>
	</div>
</b:form-element-container-support>