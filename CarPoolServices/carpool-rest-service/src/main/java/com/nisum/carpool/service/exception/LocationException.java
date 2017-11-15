package com.nisum.carpool.service.exception;

public class LocationException extends Exception {
private static final long serialVersionUID = 1L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public LocationException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public LocationException() {
		super();
	}


}
