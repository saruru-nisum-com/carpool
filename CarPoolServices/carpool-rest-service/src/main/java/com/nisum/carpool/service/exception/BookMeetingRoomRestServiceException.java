package com.nisum.carpool.service.exception;

public class BookMeetingRoomRestServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public BookMeetingRoomRestServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public BookMeetingRoomRestServiceException(String errorMessage, Exception e) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		e.printStackTrace();
	}
	
	
	public BookMeetingRoomRestServiceException() {
		super();
	}


}
