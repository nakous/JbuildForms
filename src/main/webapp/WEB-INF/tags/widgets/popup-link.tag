<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="popupId" required="true"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- Component to show a simple link to open popup window. 

Example usage:

<b:popup-link popupId="popup1">
	This is link
</b:popup-link>

<b:simple-popup-window popupId="popup1">
	Something to pop up.
</b:simple-popup-window>
	
--%>

<a href="#" data-toggle="modal" data-target="#${popupId}"><jsp:doBody /></a>