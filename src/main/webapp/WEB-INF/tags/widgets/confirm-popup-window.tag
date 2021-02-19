<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="popupId" required="false" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="text" required="true" %>
<%@ attribute name="buttonId" required="false" %>
<%@ attribute name="buttonName" required="false" %>
<%@ attribute name="buttonLabelCode" required="false" %>
<%@ attribute name="buttonForm" required="false" %>
<%@ attribute name="buttonDisabled" type="java.lang.Boolean" required="false"%>
<%@ attribute name="buttonSubmitParameter" type="java.lang.Boolean" required="false"%>


<%@ taglib uri="/WEB-INF/taglib/basetags.tld" prefix="b" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%-- Component to create a confirm popup window. 

Example usage:

<b:confirm-popup-window popupId="popup1" title="myTitle" text="myText" buttonId="btnOK"/>

--%>

<c:set var="popupId" value="${(empty popupId) ? 'confirmPopup' : popupId}"/>
<c:set var="title" value="${(empty title) ? '' : title}"/>
<c:set var="buttonId" value="${(empty buttonId) ? 'btnOK' : buttonId}"/>
<c:set var="buttonLabelCode" value="${(empty buttonLabelCode) ? 'prompt.ok' : buttonLabelCode}"/>
<c:set var ="buttonName" value="${(empty buttonName) ?  '': buttonName}"/>

<b:basic-popup-window popupId="${popupId}">
	<jsp:attribute name="header">
		${title}
	</jsp:attribute>
	<jsp:body>
		<b:form-element-container-single-column>
			${text}
		</b:form-element-container-single-column>
		<jsp:doBody/>
		<br/><br/>		
		<b:form-buttons-container-wide>
			<b:form-submit-button 
					label="${buttonLabelCode}" 
					id="${buttonId}" name="${buttonName}" 
					submitParameter="${buttonSubmitParameter}" 
					form="${buttonForm}"
					disabled="${buttonDisabled}"
					hidden="false"/>
					
			<c:if test="${(not empty buttonName)}">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message text="Abort"/></button>
			</c:if>
			<c:if test="${(empty buttonName)}">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="prompt.cancel"/></button>
			</c:if>
		</b:form-buttons-container-wide>
		
	</jsp:body>
</b:basic-popup-window>
