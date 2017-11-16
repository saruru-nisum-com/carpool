package com.nisum.carpool.service.exception;

public class BlogServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public BlogServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public BlogServiceException() {
		super();
	}


}
