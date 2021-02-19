<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="popupId" required="true"%>
<%@ attribute name="disabled" type="java.lang.Boolean" required="false"%>
<%@ attribute name="hidden" type="java.lang.Boolean" required="true"%>
<%@ attribute name="cssClass" required="false"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%-- Component to show a simple link to open popup window. 

Example usage:

<b:popup-button popupId="popup1"> 
 	This is button 
</b:popup-button> 

<b:simple-popup-window popupId="popup1">
	Something to pop up.
</b:simple-popup-window>
	
--%>
<c:if test="${!hidden}">
<c:set var="disabled" value="${(empty disabled) ? false : disabled}" />
<c:set var="cssClass" value="${(empty cssClass) ? 'btn-primary' : cssClass} " />

<button type="button" 
	class="btn ${cssClass} ${disabled ? 'disabled' : ''}"
	data-toggle="modal" data-target="#${popupId}">
	<jsp:doBody />
</button>
</c:if>