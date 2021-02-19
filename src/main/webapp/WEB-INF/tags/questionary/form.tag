<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>


<%@ attribute name="id" required="true"%>
<%@ attribute name="pageNumber" required="true"%>
<%@ attribute name="cssClass" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<form:form modelAttribute="form" class="${cssClass}">
	<form:input type="hidden" path="answerSheet.questionnaireId" value="${id}"/>
	<form:input type="hidden" path="pageNumber" value="${pageNumber}"/>
				
	<jsp:doBody/>
				
</form:form>

<script type="text/javascript">

	$(document).ready(function() {
		
		$( "#form" ).submit(function( event ) {
			// disable buttons accept/reject
			$(":button").attr("disabled","disabled");
			$(":button").addClass("disabled");
		});
	});

</script> 				