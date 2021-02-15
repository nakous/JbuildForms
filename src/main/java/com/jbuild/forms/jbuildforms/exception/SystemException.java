package com.jbuild.forms.jbuildforms.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * System unchecked exception.
 * <p>
 * Wrap unpredicted exception with {@link SystemException} when the user can't do anything to recover. In case the user can recover throw a {@link BusinessException}
 * <ul>
 * <li>extends {@link RuntimeException})</li>
 * <li>rollbacks transactions</li>
 * <li>logs to stderr a unique error code for each exception instance</li>
 * </ul>
 * 
 * @author Argyriou
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -45404058069216493L;

	protected List<String> messages = new ArrayList<String>();

	protected String id;

	/**
	 * 
	 */
	public SystemException() {
		super();
		id = UUID.randomUUID().toString();
		setMessage("Unexpected system problem occurred");
		logMe();
	}

	/**
	 * @param message
	 */
	public SystemException(String message) {
		super();
		this.id = UUID.randomUUID().toString();
		setMessage(message);
		logMe();
	}

	/**
	 * @param messageKey
	 * @param t
	 */
	public SystemException(String message, Throwable t) {
		super(message, t);
		setMessage(message);
		if (t instanceof SystemException) {
			id = ((SystemException) t).id;
		} else {
			id = UUID.randomUUID().toString();
		}
		logMe();
	}

	public void setMessage(String message) {
		messages.clear();
		messages.add(message);
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public SystemException getInitSystemException() {
		Throwable inner1 = this.getCause();
		Throwable inner2 = this;
		while (inner1 instanceof SystemException) {
			inner2 = inner1;
			inner1 = inner1.getCause();
		}
		return (SystemException) inner2;
	}

	/**
	 * @return
	 */
	public Throwable getInitCheckedException() {
		return getInitSystemException().getCause();
	}

	/**
	 * @return
	 */
	public String getInitRepExceptionMessage() {
		return getInitSystemException().getLocalizedMessage();
	}

	/**
	 * @return
	 */
	public String getInitCheckedExceptionMessage() {
		return getInitCheckedException().getLocalizedMessage();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return !messages.isEmpty() ? messages.get(0) : "";
	}

	public List<String> getMessages() {
		return messages;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return getMessage();
	}

	/**
	 * log at ERROR level all Business Exceptions based on the first level of the stacktrace
	 */
	private final void logMe() {
		final Logger log = LoggerFactory.getLogger(SystemException.class);
		String msg = "id " + getId() + ", " + super.getMessage();
		log.error(msg, getCause());
	}
}