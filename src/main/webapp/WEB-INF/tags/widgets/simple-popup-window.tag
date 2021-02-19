<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="popupId" required="true"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b"%>

<%-- Component to show a simple closeable popup window. 

Example usage:

<b:simple-popup-window popupId="popup1">
	Something to pop up.
</b:simple-popup-window>
	
--%>

<b:basic-popup-window popupId="${popupId}">
	<jsp:attribute name="header">
		<div class="modal-title" id="popupConfirmLabel">
			<spring:message code="system.messages.information" />
		</div>
	</jsp:attribute>				
	<jsp:attribute name="footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">
			<spring:message code="system.messages.close" />
		</button>
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />				
	</jsp:body>	
</b:basic-popup-window>