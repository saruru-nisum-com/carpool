package com.nisum.carpool.service.dto;

public class CPCancellRideReasons {

	private Integer reasonCode; //Rider Cancellation reason code
	private String reasonName;  //Rider Cancellation reason name
	private String rejectionType; //Rider Cancellation reason type
	
	
	public Integer getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getReasonName() {
		return reasonName;
	}
	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}
	public String getRejectionType() {
		return rejectionType;
	}
	public void setRejectionType(String rejectionType) {
		this.rejectionType = rejectionType;
	}
	
	
	
}