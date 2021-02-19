<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="messageCode" required="true"%>

<%-- Component to show simple usage hint on the top of the form. 

Example usage:

<b:hint messageCode="prompt.suggested.form.usage"/>
	
--%>
<div style="margin-left : 20px;">
	<span class="hr-icon__info-circle" style="margin-left : -20px"></span>
	<spring:message code="${messageCode}" />
</div>