package com.nisum.carpool.service.exception;

public class CategoryServiceException extends Exception{
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public CategoryServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public CategoryServiceException() {
		super();
	}

}
