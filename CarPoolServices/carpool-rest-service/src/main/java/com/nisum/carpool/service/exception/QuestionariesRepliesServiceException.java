package com.nisum.carpool.service.exception;

public class QuestionariesRepliesServiceException  extends Exception{
	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public QuestionariesRepliesServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public QuestionariesRepliesServiceException() {
		super();
	}

}
