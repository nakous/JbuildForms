package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Represents an access right of a question.
 *
 */
@XStreamAlias("access-right")
public class AccessRight implements Serializable {
	private static final long serialVersionUID = -684689224921497409L;

	/**
	 * The type of the access right (view/modify)
	 */
	@XStreamAsAttribute
	@XStreamAlias("type")
	private String type;

	/**
	 * The user role (linked to the user account) this access right applies to. This attribute can be empty and will then apply to everyone.
	 */
	@XStreamAsAttribute
	@XStreamAlias("role")
	private String role;

	/**
	 * The status of the questionnaire this access right applies to.
	 */
	@XStreamAsAttribute
	@XStreamAlias("status")
	private String status;

	/**
	 * The access level of the access right (self-only,all).
	 */
	@XStreamAsAttribute
	@XStreamAlias("access-level")
	private String accessLevel;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
}