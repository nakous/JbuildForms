<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- Component to show application logo along with its name. 

Logo is linked to the application home page.

This component expects the existence of the application specific message property "app.name".

Example usage:

<b:logo-to-home />
	
--%>

<!-- Logo linked to the home page -->
<a href="${pageContext.servletContext.contextPath}"> <img
	src="${pageContext.servletContext.contextPath}/images/header_people.png"
	alt="<spring:message code="app.name" />" />
</a>
