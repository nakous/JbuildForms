<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="containerId" required="true"%>

<%-- Component to show a collapsible line linked to a collapsible container. 

Example usage:

<b:collapsible-container containerId="optionalItems"/>
	
--%>
<div id="${containerId}" class="collapse" tabindex="-1">
	<jsp:doBody/>
</div>