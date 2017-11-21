package com.nisum.carpool.service.exception;

public class RegisterServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}

	public RegisterServiceException(String errorMessage, Exception e) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		e.printStackTrace();
	}
	public RegisterServiceException() {
		super();
	}
	public RegisterServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
