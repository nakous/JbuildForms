package com.jbuild.forms.jbuildforms.model.questionnaire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Represents an answer to a question
 * 
 */
@XStreamAlias("answer")
public class Answer implements Serializable {
	private static final long serialVersionUID = -684689284900297409L;

	@XStreamAsAttribute
	@XStreamAlias("question-id")
	private Integer questionId;

	@XStreamImplicit(itemFieldName = "value")
	private List<String> values;

	public Answer() {
		super();
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		// TODO Add a field to know if it is a multiple choice answer, merge values only if it is not a multiple choice
		if (values != null && values.size() > 1) {
			boolean first = true;
			StringBuffer sb = new StringBuffer();
			for (String value : values) {
				if (first) {
					first = false;
				} else {
					sb.append(',');
				}
				sb.append(value);
			}
			values = new ArrayList<String>();
			values.add(sb.toString());
		}

		this.values = values;
	}
}