package com.nisum.carpool.service.dto;

import java.sql.Timestamp;

/*
 * Author: @Rajesh Sekhamuri
 * ClassType: Pojo
 * ClassNamne: RiderStatusDTO
 */

public class RiderStatusDTO {

	private Integer id;
	private Integer cpid;
	private String riderName;  
	//private String riderMobileNo;
	private String riderEmailId;
	private Timestamp currentDate;
	private Integer riderStatus;
	private Integer reasonCode;
//	private double rewards;
//	private  boolean notifyme;
//	private Timestamp  createddate;
//	//private Timestamp modifieddate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCpid() {
		return cpid;
	}
	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}
	
	
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
//	public String getRiderMobileNo() {
//		return riderMobileNo;
//	}
//	public void setRiderMobileNo(String riderMobileNo) {
//		this.riderMobileNo = riderMobileNo;
//	}
	public String getRiderEmailId() {
		return riderEmailId;
	}
	public void setRiderEmailId(String riderEmailId) {
		this.riderEmailId = riderEmailId;
	}
	public Timestamp getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Timestamp currentDate) {
		this.currentDate = currentDate;
	}
	public Integer getRiderStatus() {
		return riderStatus;
	}
	public void setRiderStatus(Integer riderStatus) {
		this.riderStatus = riderStatus;
	}
	public Integer getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}
	 
	public String toString() {
		return "riderName "+riderName+" riderEmailId "+riderEmailId+" currentDate "+currentDate+" riderStatus "+riderStatus+" reasonCode "+reasonCode ;
	}
	
	
}
