package com.jbuild.forms.jbuildforms.enumeration;

import java.util.HashMap;
import java.util.Map;


public enum DossierQuestionStatusType {
	DRAFT("dossier.question.status.type.draft"),
	FINAL("dossier.question.status.type.final");
	
	private final String label;
	
	private static final Map<String, DossierQuestionStatusType> ALL_TYPES = new HashMap<String, DossierQuestionStatusType>();

    static {
        for (DossierQuestionStatusType type : DossierQuestionStatusType.values()) {
        	ALL_TYPES.put(type.getLabel(), type);
        }
    }
	
	/**
	 * Private default constructor; required when used reflection, e.g. when
	 * used as argument on Web Services.
	 */
    private DossierQuestionStatusType() {
    	this.label = "UNINITIALIZED";
    }
    
	private DossierQuestionStatusType(String label) {
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
     * @return the <UserType> corresponds to the given value
     */
    public static DossierQuestionStatusType fromLabel(String label) {
        if (label == null) {
            return null;
        }
        if (ALL_TYPES.containsKey(label)) {
            return ALL_TYPES.get(label);
        }
        throw new IllegalArgumentException("The label is incorrect: " + label);
    }
	
}
