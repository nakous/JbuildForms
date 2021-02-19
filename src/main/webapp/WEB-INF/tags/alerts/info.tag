<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ attribute name="messageCode" required="true"%>
<%@ attribute name="alertId" required="false"%>
<%@ attribute name="visible" type="java.lang.Boolean" required="false"%>

<%-- Component to show a single info message according to messageCode parameter. 
It uses success alert with icon from
http://www.hrmepdv.ep.parl.union.eu/hr-apps-layout/latest/styleguide/section-4.html#kssref-4-6

Example usage:
<b:info messageCode="message.code" />
--%>

<c:set var="hidden" value="${(empty visible) ? '' : (visible ? '' : 'hidden')}" />

<div id="${alertId}" class="alert alert-info alert-dismissible fade in ${hidden}" role="alert">
	<div class="alert-icon">
		<span class="hr-icon__info-circle hr-icon-38 text-info"></span>
	</div>
	<div class="alert-body">
		<button class="close" aria-label="<spring:message code="system.messages.close" />" data-dismiss="alert" type="button">
			<span class="hr-icon__close" aria-hidden="true"></span>
		</button>
		<strong><spring:message code="system.messages.information" /></strong><br />
		<spring:message code="${messageCode}" />
	</div>
</div>