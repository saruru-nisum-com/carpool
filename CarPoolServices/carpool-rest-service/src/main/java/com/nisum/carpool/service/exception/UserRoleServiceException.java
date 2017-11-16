package com.nisum.carpool.service.exception;

public class UserRoleServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	

	public UserRoleServiceException(String errorMessage) {
		super();
		this.errorMessage=errorMessage;
		
		
	}

	
}
