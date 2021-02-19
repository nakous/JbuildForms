<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="question" type="com.jbuild.forms.jbuildforms.model.questionnaire.Question" required="true"%>

<%@ taglib prefix="spring"	uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"		uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="q"		uri="/WEB-INF/taglib/questionary.tld"%>
<%@ taglib prefix="b"		uri="/WEB-INF/taglib/basetags.tld"%>

<c:choose>
	<c:when test="${question.type == 'list' && question.appearence == 'select' && !question.multiple}">
		<c:set var="narrowLayout" value="false"/>
		<c:if test="${question.appearence == 'select' && question.decaleInView}">
			<c:set var="narrowLayout" value="true"/>
		</c:if>
		
		<q:form-element-container
			path="answerSheet.answers[${question.id}].values"
			required="${question.mandatory}"
			label="${question.label.text}"
			narrowLayout="${narrowLayout}"
		>
			<form:select
				cssClass="form-control ${question.enabled == false || readOnly ? 'disabled' : ''}"
				path="answerSheet.answers[${question.id}].values"
				disabled="${question.enabled == false || readOnly ? 'true' : 'false'}"
				multiple="false"
			>
				<option value="">
					<spring:message code="prompt.please.select"/>
				</option>
				<c:if test="question.options.length> 0">
				
					<c:if test="${question.optionsBeanName == null}">
						<c:forEach items="${question.options}" var="option">
							<form:option value="${option.value}">
								<spring:message code="${option.label.text}"/>
							</form:option>
						</c:forEach>
					</c:if>
					<c:if test="${question.optionsBeanName != null}">
						<q:option-list-bean optionListName="${question.optionsBeanName}" var="optionList"/>
						<c:forEach items="${optionList}" var="option">
							<form:option value="${option.key}">
								<c:if test="${question.optionsBeanIsTranslated}">
									<spring:message code="${option.value}"/>
								</c:if>
								<c:if test="${!question.optionsBeanIsTranslated}">
									${option.value}
								</c:if>
							</form:option>
						</c:forEach>
					</c:if>
				</c:if>
			</form:select>
			<span id="inputError2Status" class="sr-only">(Error)</span>
			<div class="text-danger hidden" id="answerSheet.answers${question.id}.values.error.text">
				<small><spring:message code="choose.value"/></small>
			</div>
			<div class="text-warning hidden" id="answerSheet.answers${question.id}.values.warning.text">
				<small></small>
			</div>
			<c:set var="warningscheck" value="false"/>
			<c:forEach items="${question.validations}" var="validation">
				<c:if test="${validation.type == 'velocity'}">
					<c:set var="warningscheck" value="true" />
				</c:if>
			</c:forEach>
			<c:if test="${warningscheck}">
				<script type="text/javascript">
					checkWarnings($("[name^='answerSheet.answers[${question.id}].values']"));
				</script>
			</c:if>
			<c:if test="${question.dependsOnQuestion != '' }"> <%-- For this kind of hard coded solution we only want to include the script once --%>
				<script type="text/javascript">
					dependsOnEvent($("[name^='answerSheet.answers[${question.id}].values']"),"${question.dependsOnQuestion}");
				</script>
			</c:if>
		</q:form-element-container>
	</c:when>
</c:choose>