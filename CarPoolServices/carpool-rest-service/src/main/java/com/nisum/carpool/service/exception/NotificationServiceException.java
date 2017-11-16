package com.nisum.carpool.service.exception;

public class NotificationServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public NotificationServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public NotificationServiceException() {
		super();
	}

}
