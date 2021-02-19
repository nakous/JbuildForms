<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="containerId" required="true"%>

<%-- Component to show a collapsible line linked to a collapsible container. 

Example usage:

<b:collapsible-line containerId="optionalItems"/>
	
--%>

<div class="collapsible-line">
	<a href="#" class="btn btn-link btn-xs collapsible-line__btn" 
		data-toggle="collapse" data-target="#${containerId}" 
		aria-expanded="false" aria-controls="collapsible-line">
		<hr class="hr-sm" />
		<span class="hr-icon__chevron-circle-up hr-icon-28 collapsible-line__icon box"></span>
	</a>
</div>