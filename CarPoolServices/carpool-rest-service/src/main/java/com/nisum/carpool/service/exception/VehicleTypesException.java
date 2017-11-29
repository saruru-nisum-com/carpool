package com.nisum.carpool.service.exception;

public class VehicleTypesException extends Exception{

private static final long serialVersionUID = 1L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public VehicleTypesException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public VehicleTypesException() {
		super();
	}

}
