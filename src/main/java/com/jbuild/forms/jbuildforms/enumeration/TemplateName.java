package com.jbuild.forms.jbuildforms.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum TemplateName {
	MODONE_FIRSTQ("modone.first.question");
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