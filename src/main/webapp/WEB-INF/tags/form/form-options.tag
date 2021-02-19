<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="items" type="java.lang.Iterable" required="true"%>
<%@ attribute name="itemMap" type="java.util.Map" required="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<%-- Component to show list options. 

Example usage:

<b:form-options items="${items}"/>

--%>

<c:set var="isMap" value="${(empty itemMap) ? false : true}"/>
<c:set var="isIterable" value="${false}"/>
<c:if test="${!isMap}">
	<c:set var="isIterable" value="${(empty items) ? false : true}"/>
</c:if>
<c:if test="${isMap}">
	<c:forEach items="${itemMap}" var="item">
			<form:option value="${item.key}">${item.value}</form:option>
	</c:forEach>
</c:if>
<c:if test="${isIterable}">
	<c:forEach items="${items}" var="item">
			<form:option value="${item}">${item}</form:option>
	</c:forEach>
</c:if>