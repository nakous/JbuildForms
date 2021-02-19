<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%-- Button to reveal typed password. 

Example usage (along with password field):

<input type="password" class="form-control" placeholder="********"><b:password-button/>

--%>

<span class="input-group-btn" aria-hidden="true">
  <button class="btn btn-default btn-password" 
  			type="button" data-toggle="tooltip" 
  			data-container='body' data-placement="top" title="<spring:message code="placeholder.show.password"/>">
    <span class="hr-icon__eye"></span>
  </button>
</span>