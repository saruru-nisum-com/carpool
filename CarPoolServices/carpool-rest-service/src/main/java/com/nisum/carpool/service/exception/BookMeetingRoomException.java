package com.nisum.carpool.service.exception;

public class BookMeetingRoomException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public BookMeetingRoomException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public BookMeetingRoomException() {
		super();
	}


}
