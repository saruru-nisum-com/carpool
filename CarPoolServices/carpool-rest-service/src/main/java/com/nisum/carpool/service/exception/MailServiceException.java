package com.nisum.carpool.service.exception;

/**
 * 
 * @author Rajendra Prasad Dava 
 * 		This class will handles the exceptions raised by SMTP mail
 *         
 * 
 */
public class MailServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	/**
	 * 
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/*
	 * @param errorMessage the errorMessage to set
	 */

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public MailServiceException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
}
