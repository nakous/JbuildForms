<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>

<%-- Component to show "Need Help?" popup link. 

Example usage:

<b:need-help />
	
--%>

<b:popup-link popupId="need-help-popup">
   	<spring:message code="prompt.assistance" />
</b:popup-link>

<b:simple-popup-window popupId="need-help-popup">
	<p class="text-center">
		<spring:message code="contact.recruitment.and.transfer.unit.tooltip"/>
	</p>
</b:simple-popup-window>
	