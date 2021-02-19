<%@ tag language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ attribute name="optionListName" required="true"%>
<%@ attribute name="var" required="true" type="java.lang.String" rtexprvalue="false"%>
<%@ variable alias="optionList" name-from-attribute="var" scope="AT_END" variable-class="java.util.Map"%>

<%@ tag import="java.util.Map"%>

<%
	String optionListName = (String) jspContext.getAttribute("optionListName");

	@SuppressWarnings("unchecked")
	Map<String, Map<Integer, String>> optionLists = (Map<String, Map<Integer, String>>) jspContext.findAttribute("optionLists");
	Map<Integer, String> optionList = optionLists.get(optionListName);
	//for (Map.Entry<Integer, String> option : optionList.entrySet()) {
	//	option.setValue(option.getValue().replaceAll(" ", "&nbsp;"));
	//}

	jspContext.setAttribute("optionList", optionList);
%>