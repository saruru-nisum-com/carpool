package com.nisum.carpool.service.exception;

public class MeetingRoomException  extends Exception{
private static final long serialVersionUID = 1L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public MeetingRoomException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public MeetingRoomException() {
		super();
	}

}
