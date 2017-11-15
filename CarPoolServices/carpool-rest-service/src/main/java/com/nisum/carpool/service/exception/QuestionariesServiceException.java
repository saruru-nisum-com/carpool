package com.nisum.carpool.service.exception;

public class QuestionariesServiceException  extends Exception{
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public QuestionariesServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public QuestionariesServiceException() {
		super();
	}

}
