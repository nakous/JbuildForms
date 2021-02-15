package com.jbuild.forms.jbuildforms.model.tools;

import java.io.Serializable;

public class TemplateVariable implements Serializable {
	private static final long serialVersionUID = -7884976088955869781L;

	private String name;

	private String velocityVariableName;

	private String velocitySkeletonCode;

	private Boolean visible;

	public TemplateVariable() {
		super();
	}

	/**
	 * Creates a TemplateVariable. Flag visible defaults to "true"
	 * 
	 * @param name
	 * @param velocityVariableName
	 */
	public TemplateVariable(String name, String velocityVariableName) {
		this.name = name;
		this.velocityVariableName = velocityVariableName;
		this.visible = true;
	}

	/**
	 * Creates a TemplateVariable. Flag visible defaults to "true"
	 * 
	 * @param name
	 * @param velocityVariableName
	 * @param velocitySkeletonCode
	 */
	public TemplateVariable(String name, String velocityVariableName, String velocitySkeletonCode) {
		this.name = name;
		this.velocityVariableName = velocityVariableName;
		this.velocitySkeletonCode = velocitySkeletonCode;
		this.visible = true;
	}

	/**
	 * Creates a TemplateVariable
	 * 
	 * @param name
	 * @param velocityVariableName
	 * @param velocitySkeletonCode
	 * @param visible
	 */
	public TemplateVariable(String name, String velocityVariableName, String velocitySkeletonCode, Boolean visible) {
		this.name = name;
		this.velocityVariableName = velocityVariableName;
		this.velocitySkeletonCode = velocitySkeletonCode;
		this.visible = visible;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVelocityVariableName() {
		return velocityVariableName;
	}

	public String getEnclosedVelocityVariableName() {
		StringBuilder outBuilder = new StringBuilder();
		if (velocityVariableName != null) {
			outBuilder = outBuilder.append("${").append(velocityVariableName).append("}");
		}
		return outBuilder.toString();
	}

	public void setVelocityVariableName(String velocityVariableName) {
		this.velocityVariableName = velocityVariableName;
	}

	public String getVelocitySkeletonCode() {
		return velocitySkeletonCode;
	}

	public void setVelocitySkeletonCode(String velocitySkeletonCode) {
		this.velocitySkeletonCode = velocitySkeletonCode;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}