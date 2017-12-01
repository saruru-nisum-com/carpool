package com.nisum.carpool.service.exception;

public class VehicleTypesException extends Exception{

private static final long serialVersionUID = 1L;
	
	private Exception errorMessage;
 
	public Exception getErrorMessage() {
		return errorMessage;
	}
	public VehicleTypesException(Exception errorMessage) {
		super(errorMessage);
		errorMessage.printStackTrace();
		this.errorMessage = errorMessage;
	}
	public VehicleTypesException() {
		super();
	}

}
