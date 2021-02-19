<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>


<%-- Component to wrap around a form button group for wider layout. 

Example usage:

<b:form-buttons-container-wide>
	<b:form-submit-button />
</b:form-buttons-container-wide>

--%>

<div class="form-group">
	<div class="text-left col-lg-7 col-lg-offset-4 col-xs-9 col-sm-7">
		<jsp:doBody />	
	</div>
</div>