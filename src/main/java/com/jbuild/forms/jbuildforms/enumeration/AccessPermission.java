package com.jbuild.forms.jbuildforms.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum AccessPermission {

	READ("access.read"),
	WRITE("access.write"),
	ACCESS_DENIED("access.denied"),
	ERROR("access.error");

	private final String label;

	private static final Map<String, AccessPermission> ALL_TYPES = new HashMap<String, AccessPermission>();

	static {
		for (AccessPermission type : AccessPermission.values()) {
			ALL_TYPES.put(type.getLabel(), type);
		}
	}

	/**
	 * Private default constructor; required when used reflection, e.g. when
	 * used as argument on Web Services.
	 */
	private AccessPermission() {
		this.label = "UNINITIALIZED";
	}

	private AccessPermission(String label) {
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
	 * @return the {@link AccessPermission} corresponds to the given value
	 */
	public static AccessPermission fromLabel(String label) {
		if (label == null) {
			return null;
		}
		if (ALL_TYPES.containsKey(label)) {
			return ALL_TYPES.get(label);
		}
		throw new IllegalArgumentException("The label is incorrect: " + label);
	}

}
