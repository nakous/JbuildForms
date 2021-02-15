package com.jbuild.forms.jbuildforms.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum TemplateName {
	DOCUMENT_CHECK_LIST("template.name.document.check.list"),
	ANY_LIST_TEMPLATE("template.name.any.list"),
	GENERAL_INFO("template.name.general.info"),
	GENERAL_EMAIL("template.name.general.email"),

	QUESTION_POC("template.name.poc"),
	CHILD_BIRTH("template.name.child.birth"),
	DOCUMENT_GUIDE_CHILD_BIRTH("template.name.document.guide.child.birth"),
	CHILD_BIRTH_DOC_VIEW("template.name.child.birth.doc.view"),
	DIVORCE("template.name.divorce"),
	DIVORCE_DOC_VIEW("template.name.divorce.doc.view"),
	ART60_DOC_VIEW("template.name.house.move.doc.view"),

	DOCUMENT_GUIDE_DIVORCE("template.name.document.guide.divorce"),

	ART60("template.name.house.move"),
	ATR60_CHECKLIST1("template.name.art.60.checklist.1"),
	ART60_QUESTIONS("template.name.art.60.question"),
	ART60_PERSONAL_DATA("template.type.art60.personal.data"),
	ART60_PERSONAL_DATA_APPROVER_VIEW("template.type.art60.personal.data"),
	ART60_CHECKLIST_DOCUMENTS("template.name.art.60.checklist.documents"),
	ART60_CHECKLIST_DOCUMENTS_MED("template.name.art.60.checklist.documents.med"),
	ART60_CHECKLIST_DOCUMENTS_AIPN("template.name.art.60.checklist.documents.aipn"),
	SPACTC_QUESTIONS("template.name.entitlements.spouse.activity.change.questions"),
	SPACTC_DOCUMENTS("template.name.entitlements.spouse.activity.change.documents.guide"),
	SPACTC_DOC_VIEW("template.name.entitlements.spouse.activity.change.documents.view"),
	SPACTC_PERSONAL_DATA("template.name.entitlements.spouse.activity.personal.data"),
	SPACTC_CHECKLIST("template.name.entitlements.spouse.activity.checklist"),
	SPACTC_ENTITLEMENTS("template.name.entitlements.spouse.activity.entitlements");
	private final String label;

	private static final Map<String, TemplateName> ALL_TYPES = new HashMap<String, TemplateName>();

	static {
		for (TemplateName type : TemplateName.values()) {
			ALL_TYPES.put(type.getLabel(), type);
		}
	}

	private TemplateName(String label) {
		this.label = label;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the enum constant of this type with the specified value.
	 * 
	 * @param value
	 * @return the {@link TemplateName} corresponds to the given value
	 */
	public static TemplateName fromLabel(String label) {
		if (label == null) {
			return null;
		}
		if (ALL_TYPES.containsKey(label)) {
			return ALL_TYPES.get(label);
		}
		throw new IllegalArgumentException("The label is incorrect: " + label);
	}

	/**
	 * @param inputString
	 * @return
	 */
	public static TemplateName fromToString(final String inputString) {
		TemplateName outTemplate = null;

		for (TemplateName currentTemplate : TemplateName.values()) {
			if (currentTemplate.toString().equals(inputString)) {
				outTemplate = currentTemplate;
				break;
			}
		}
		return outTemplate;
	}
}