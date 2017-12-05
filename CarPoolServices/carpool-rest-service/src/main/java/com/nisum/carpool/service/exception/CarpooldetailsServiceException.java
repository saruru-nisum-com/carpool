package com.nisum.carpool.service.exception;

public class CarpooldetailsServiceException extends Exception {
	//the errorCode
	private String errorCode;
	//the errorMessage
	private String errorMessage;
	public CarpooldetailsServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarpooldetailsServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public CarpooldetailsServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CarpooldetailsServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CarpooldetailsServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CarpooldetailsServiceException(String message, String errorCode) {
		this.errorCode=errorCode;
		this.errorMessage=message;
	}
	


}
