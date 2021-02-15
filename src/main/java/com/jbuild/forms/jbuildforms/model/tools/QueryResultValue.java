package com.jbuild.forms.jbuildforms.model.tools;

import java.math.BigDecimal;
import java.util.Date;

 

public class QueryResultValue {
	private String keyString;

	private BigDecimal keyInteger;

	private Date keyDate;

	private Pair<?, ?> value;

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public BigDecimal getKeyInteger() {
		return keyInteger;
	}

	public void setKeyInteger(BigDecimal keyInteger) {
		this.keyInteger = keyInteger;
	}

	public Date getKeyDate() {
		return keyDate;
	}

	public void setKeyDate(Date keyDate) {
		this.keyDate = keyDate;
	}

	public Pair<?, ?> getValue() {
		return value;
	}

	public void setValue(Pair<?, ?> value) {
		this.value = value;
	}

	public QueryResultValue(String keyString, Pair<?, ?> value) {
		this.keyString = keyString;
		this.value = value;
	}

	public QueryResultValue(BigDecimal keyInteger, Pair<?, ?> value) {
		this.keyInteger = keyInteger;
		this.value = value;
	}

	public QueryResultValue(Date keyDate, Pair<?, ?> value) {
		this.keyDate = keyDate;
		this.value = value;
	}

	public QueryResultValue(String keyType, Object keyValue, Pair<?, ?> value) {
		/*if (keyType.equals(GenericDAOImpl.NUMBER_TYPE)) {
			this.keyInteger = (BigDecimal) keyValue;
		} else if (keyType.equals(GenericDAOImpl.DATE_TYPE)) {
			this.keyDate = (Date) keyValue;
		} else {
			this.keyString = keyValue.toString();
		}*/
		this.value = value;
	}
}