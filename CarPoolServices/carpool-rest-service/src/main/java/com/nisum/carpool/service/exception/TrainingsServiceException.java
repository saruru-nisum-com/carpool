package com.nisum.carpool.service.exception;

public class TrainingsServiceException extends Exception{
	private static final long serialVersionUID = 1L;

	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public TrainingsServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public TrainingsServiceException() {
		super();
	}


}
