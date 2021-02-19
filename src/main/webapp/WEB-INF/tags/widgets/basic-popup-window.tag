<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="popupId" required="true"%>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%-- Component to create a basic popup window. 

Example usage:

<b:basic-popup-window popupId="popup1">
	<jsp:attribute name="header">
		This is header    	
	</jsp:attribute>				
	<jsp:attribute name="footer">
		This is footer    	
	</jsp:attribute>
	<jsp:body>
		This is body					
	</jsp:body>	
</b:basic-popup-window>

--%>

<c:set var="hasHeader" value="${(empty header) ? false : true}" />
<c:set var="hasFooter" value="${(empty footer) ? false : true}" />

<div class="modal fade" id="${popupId}" tabindex="-1" role="dialog"	aria-labelledby="popupConfirmLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-label="<spring:message code="system.messages.close" />">
					<span aria-hidden="true">&times;</span>
				</button>
				<c:if test="${hasHeader}">
					<jsp:invoke fragment="header" />
				</c:if>
			</div>

			<div class="modal-body">
				<jsp:doBody />
			</div>

			<c:if test="${hasFooter}">
				<div class="modal-footer">
					<jsp:invoke fragment="footer" />
				</div>
			</c:if>
		</div>
	</div>
</div>