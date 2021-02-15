package com.jbuild.forms.jbuildforms.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum TemplateType {
	/** Email notifications */
	EMAIL_NOTIFICATION("template.type.email.notification"),
	/** Email notifications for Medical appointments */
	MEDICAL_EMAIL_NOTIFICATION("template.type.medical.email.notification"),
	/** Email notifications for Signature appointments */
	SIGNATURE_EMAIL_NOTIFICATION("template.type.signature.email.notification"),
	ENTITLEMENTS_EMAIL_NOTIFICATION("template.type.entitlements.email.notification"),
	RELATIONS_EMAIL_NOTIFICATION("template.type.relations.email.notification"),
	/** Email communications */
	EMAIL_LETTER("template.type.email.letter"),
	/** Automatic SMS automatically send from system */
	SMS_NOTIFICATION("template.type.sms.notification"),
	QUESTION("template.type.question"),
	DOCUMENT_GUIDE("template.type.answer"),
	TEXT("template.type.text"),
	HELP_TOPIC("template.type.help.topic"),
	OFFER("template.type.offer"),
	DECLARATION("template.type.declaration"),
	WEB_CONTENT("template.type.web.content"),
	MEDICAL_TEMPLATE("template.type.medical"),
	
	PERSONAL_DATA("template.type.personal.data");

	private final String label;

	private static final Map<String, TemplateType> ALL_TYPES = new HashMap<String, TemplateType>();

	static {
		for (TemplateType type : TemplateType.values()) {
			ALL_TYPES.put(type.getLabel(), type);
		}
	}

	/**
	 * Private default constructor; required when used reflection, e.g. when
	 * used as argument on Web Services.
	 */
	private TemplateType() {
		this.label = "UNINITIALIZED";
	}

	private TemplateType(String label) {
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
	 * @return the {@link TemplateType} corresponds to the given value
	 */
	public static TemplateType fromLabel(String label) {
		if (label == null) {
			return null;
		}
		if (ALL_TYPES.containsKey(label)) {
			return ALL_TYPES.get(label);
		}
		throw new IllegalArgumentException("The label is incorrect: " + label);
	}

}
