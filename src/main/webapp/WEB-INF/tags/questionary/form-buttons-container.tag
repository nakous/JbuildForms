<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>


<%-- Component to wrap around a form button group. 

Example usage:

<q:form-buttons-container>
	...
</q:form-buttons-container>

--%>

<div class="form-group">
 	<div class="text-left col-sm-6 col-md-5 col-sm-offset-5 col-md-offset-5"> 
		<jsp:doBody />	
	</div>
</div>