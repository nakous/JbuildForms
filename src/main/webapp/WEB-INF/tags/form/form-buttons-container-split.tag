<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="links" fragment="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>


<%-- Component to wrap around a form button group. 

Example usage:

<b:form-buttons-container includeResetButton="true">
	<input type="submit"/>
</b:form-buttons-container>

--%>

<div class="form-group">
	<div class="text-left col-lg-4 col-lg-offset-4 col-xs-6 col-sm-3">
		<jsp:doBody />			
	</div>
	<div class="text-right col-lg-3 col-xs-6 col-sm-9">
		<b:need-help />
		<jsp:invoke fragment="links" />
	</div>
</div>
