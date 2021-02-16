package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("validation")
public class Validation implements Serializable {
	private static final long serialVersionUID = -684689284900297409L;

	/**
	 * unique id
	 */
	@XStreamAsAttribute
	@XStreamAlias("type")
	private String type;

	@XStreamAlias("rule")
	private String rule;

	@XStreamAlias("label")
	private Label label;
	
	@XStreamAsAttribute
	@XStreamAlias("message-type")
	private String messageType;

	@XStreamAsAttribute
	@XStreamAlias("forward-as-message")
	private Boolean ForwardAsMessage;
	
	/**
	 * default constructor
	 */
	public Validation() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Boolean getForwardAsMessage() {
		return ForwardAsMessage;
	}

	public void setForwardAsMessage(Boolean forwardAsMessage) {
		ForwardAsMessage = forwardAsMessage;
	}

	
}