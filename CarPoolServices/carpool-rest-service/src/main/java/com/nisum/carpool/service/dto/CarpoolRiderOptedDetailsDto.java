/**
 * 
 */
package com.nisum.carpool.service.dto;

/**
 * @author Lakshmi Kiran
 *
 */
public class CarpoolRiderOptedDetailsDto {
	
	private int carpoolRiderDetailsId;
	private int carpoolId;
	private String name;
	private String emailId;
	private String mobile;
	private int status;
	private int reason;
	private String fromdate;
	
	
	public int getCarpoolId() {
		return carpoolId;
	}
	public void setCarpoolId(int carpoolId) {
		this.carpoolId = carpoolId;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public int getCarpoolRiderDetailsId() {
		return carpoolRiderDetailsId;
	}
	public void setCarpoolRiderDetailsId(int carpoolRiderDetailsId) {
		this.carpoolRiderDetailsId = carpoolRiderDetailsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "CarpoolRiderOptedDetailsDto [carpoolRiderDetailsId=" + carpoolRiderDetailsId + ", carpoolId="
				+ carpoolId + ", name=" + name + ", emailId=" + emailId + ", mobile=" + mobile + ", status=" + status
				+ ", reason=" + reason + ", fromdate=" + fromdate + "]";
	}
	
	
}
