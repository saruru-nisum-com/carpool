package com.nisum.carpool.service.exception;

public class UserServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}

	public UserServiceException(String errorMessage, Exception e) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		e.printStackTrace();
	}
	public UserServiceException() {
		super();
	}
	public UserServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
