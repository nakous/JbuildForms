<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="questionnaire" type="com.jbuild.forms.jbuildforms.model.questionnaire.Questionnaire" required="true"%>

<%@ attribute name="var" required="true" type="java.lang.String" rtexprvalue="false"%>
<%@ variable alias="section" name-from-attribute="var" scope="NESTED" variable-class="com.jbuild.forms.jbuildforms.model.questionnaire.Section"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
	function setIsMandatory() {
		$(".custmandatory").each(function() {
			var x = $(this).attr('id');
			$("div[id='" + x + "']").addClass("is-mandatory");
		});
	};

	function clearformelement(qid , appearence) {
		var frm_elementsid = 'answerSheet.answers' + qid + '.values';
		var frm_elementsname = 'answerSheet.answers[' + qid + '].values';
		clearquestionfield(frm_elementsname);
	}

	function dependsOnEvent(field, depOnQuest) {
		field.attr("dependsonquestion", depOnQuest);
	};

	function getQuestionValue(qid) {
		var tagtype = $("[name^='answerSheet.answers[" + qid + "].values']").attr("type");
		var frm_elements = $("[name^='answerSheet.answers[" + qid + "].values']");
		if (tagtype == undefined) {
			tagtype = frm_elements.prop("tagName");
		}
		var qvalue = null;
		switch (tagtype) {
			case "text":
				qvalue = frm_elements.val();
				break;
			case "radio":  case "checkbox":
				qvalue = $("[name^='answerSheet.answers[" + qid + "].values']:checked").val();
				break;
			case 'SELECT':
				qvalue = frm_elements.val();
				break;
			default:
				break;
		}
		return qvalue;
	};

	function checkRenderedQuestion(question, qId, target, changeCaller) {
		var depquestions =[];
		var questionId = {};

		for (var i = 0 ; i < question.length ; i++) {
			var tmpdepq = getQuestionValue(question[i]);
			questionId[question[i]] = tmpdepq;
		}

		var regExp =/\[(.*?)\]/g;
		var matches = regExp.exec(target.attr("name"));
		var questiontoCheckId = matches[1];
		var data = {
			questionId: null,
			templateName: "${templateName}",
			questionCheckId: parseInt(questiontoCheckId),
			personId: "${personId}",
			dossierId: "${dossier.id}",
		};
		data.questionId = questionId;
		var myJSON = JSON.stringify(data);
		ApaPeople.Documents.showspiner();
		$.ajax({
			url:"${pageContext.request.contextPath}/checkDisabledEnabledQuestionAsync",
			type: "post",
			data: JSON.stringify(data),
			contentType: "application/json",
			dataType: "json",
			success: function(result) {
				if (result.result === "enable") {
					var frm_elementsname = target.attr("name");
					var qnum = frm_elementsname.substring(frm_elementsname.lastIndexOf("[") + 1, frm_elementsname.lastIndexOf("]."));
					setFieldMandatory(qnum);
					$("div[id^='" + frm_elementsname + "-container']").removeClass("hidden");
				} else {
					clearquestionfield(target.attr("name"));
				}
				$("input.datepicker").Zebra_DatePicker({format: "d/m/Y"});
				ApaPeople.Documents.hidespiner();
			}
		});
	};

	function clearquestionfield(frm_elementsname) {
		$("div[id^='" + frm_elementsname + "-container']").removeClass("is-mandatory");
		$("label[for^='" +frm_elementsname+"'] span:first").remove();
		$("div[id^='" + frm_elementsname + "-container']").addClass("hidden");

		var tagtype = $("[name^='" + frm_elementsname + "']").attr("type");
		var frm_elements = $("[name^='" + frm_elementsname + "']");
		if (tagtype == undefined) {
			tagtype = $("[name^='" + frm_elementsname + "']").prop("tagName");
		}
		switch (tagtype) {
			case "text":
				frm_elements.val("");
				break;
			case "password":
			case "textarea":
			case "radio":
				$("[name^='" + frm_elementsname + "']:checked").trigger("clearSelection");
				$("[name^='" + frm_elementsname + "']:checked").prop("checked", false);
				break;
			case "checkbox":
				if (frm_elements.checked) {
					frm_elements.checked = false;
				}
				break;
			case 'SELECT':
				frm_elements.selectedIndex = -1;
				break;
			case "select-multi":
				frm_elements.selectedIndex = -1;
				break;
			default:
				break;
		}
		frm_elements.change();
	};

	function fieldChangeEvent(field) {
		field.unbind("change");
		field.change(function(e) {
			var $changeCaller = $("[name^='" + e.target.getAttribute('name') + "']");

			$('[dependsonquestion]').each(function() {
				var depquestion = $(this).attr('dependsonquestion');
				if (depquestion != "") {
					var depquestionarr = depquestion.split(",");
					var namearr = [];
					var fLen = depquestionarr.length;
					for (var i = 0 ; i < fLen ; i++) {
						namearr[i] = "answerSheet.answers[" + depquestionarr[i] + "].values";
					}
					if (namearr.indexOf($changeCaller.attr('name')) > -1) {
						checkRenderedQuestion(depquestionarr, $(this).attr('dependsonquestion'), $(this), $changeCaller);
					}
				}
			});
			if (checkwarningsarr.indexOf($changeCaller.attr('name')) != -1) {
				checkFieldWrnings($changeCaller);
			}
		});
	};

	var checkwarningsarr = [];
 	var dependencies = {maparray: []};
	var dependmap = [];

	function getDependElements() {
		console.log(dependencies.maparray.length);
		for (var i = 0 ; i <= dependencies.maparray.length - 1 ; i++) {
			console.log(dependencies.maparray[i][1]);
			fieldChangeEvent($("[name^='answerSheet.answers[" + dependencies.maparray[i][0] + "].values']"));
		}
	};

	function setcutommandatory(labelid) {
		if ($("div[id^='answerSheet.answers[" + labelid + "].values-container']").hasClass("is-mandatory")){
			$("div[id^='answerSheet.answers[" + labelid + "].values-container']").addClass("custmandatory");
		}
	};

	function setFieldMandatory(labelid) {
		if ($("div[id^='answerSheet.answers[" + labelid + "].values-container']").hasClass("custmandatory")) {
			$("div[id^='answerSheet.answers[" + labelid + "].values-container']").addClass("is-mandatory");
			$("label[for^='answerSheet.answers[" + labelid + "].values']").append('<span class="mandatory-star">*</span>');
		}
	};

	function detectDependson() {
		$("[dependsonquestion]").each(function() {
			var dependmap = [];
			var depOnQuest = $(this).attr("dependsonquestion");
			if (depOnQuest != "") {
				var depquesidarr = depOnQuest.split(",");
				for (var j = 0 ; j <= depquesidarr.length-1 ; j++) {
					var regExp = /\[(.*?)\]/g;
					dependmap[0] = depquesidarr[j];
					var matches = regExp.exec($(this).attr("name"));
					dependmap[1] = matches[1];
					var pos = isItemInArray(dependencies.maparray, dependmap);
					if (pos == -1) {
						dependencies.maparray[dependencies.maparray.length] = dependmap;
					} else {
						var tmpdepmap = dependencies.maparray[pos];
						dependencies.maparray[pos][1] = tmpdepmap[1] + ',' + dependmap[1];
					}
				}
			}
		});
	};

	function isItemInArray(array, item) {
		if (array != undefined) {
			for (var i = 0 ; i < array.length ; i++) {
	 			// This if statement depends on the format of your array
	 			if (array[i][0] == item[0]) {
	 				return i; // Found it
	 			}
	 		}
		};
		return -1; // Not found
	};

	$(document).ready(function() {
		setIsMandatory();
		detectDependson();
		getDependElements();
	});
</script>
<c:forEach items="${questionnaire.sections}" var="section">
	<jsp:doBody/>
</c:forEach>